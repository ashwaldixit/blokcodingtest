package com.blocksports.assignment.service;


import com.blocksports.assignment.enums.RecordStatus;
import com.blocksports.assignment.enums.UserRole;
import com.blocksports.assignment.enums.ValidationMode;
import com.blocksports.assignment.exceptions.BlokBadRequestException;
import com.blocksports.assignment.exceptions.BlokServerException;
import com.blocksports.assignment.model.User;
import com.blocksports.assignment.model.UserRoles;
import com.blocksports.assignment.repository.UserRepository;
import com.blocksports.assignment.util.ObjectUtil;
import com.blocksports.assignment.util.PasswordMasker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordMasker passwordMasker;

    @Autowired
    private UserRolesService userRolesService;

    private String getHashedPassword(User user) throws NoSuchAlgorithmException {
        return passwordMasker.getHashedPassword(user.getPassword());
    }

    @Override
    public User getById(long id) {
        return userRepository.findByIdAndStatus(id, RecordStatus.ACTIVE).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmailAndStatus(email, RecordStatus.ACTIVE).orElse(null);
    }

    @Override
    public User validateAndGetById(long id) {
        return userRepository.findByIdAndStatus(id, RecordStatus.ACTIVE).orElseThrow(() -> new BlokBadRequestException("User not found"));
    }

    @Override
    public User validateAndGetByEmail(String email) {
        return userRepository.findByEmailAndStatus(email, RecordStatus.ACTIVE).orElseThrow(() -> new BlokBadRequestException("User not found"));
    }

    @Override
    public void preValidate(@NonNull final User obj, final ValidationMode mode) {
        if (ObjectUtil.isStringEmptyOrNull(obj.getName())) {
            throw new BlokBadRequestException("User Name cannot be empty");
        }

        if (ObjectUtil.isStringEmptyOrNull(obj.getEmail())) {
            throw new BlokBadRequestException("Email cannot be empty");
        }

        if (ObjectUtil.isStringEmptyOrNull(obj.getPassword())) {
            throw new BlokBadRequestException("Password cannot be empty");
        }

        if(mode==ValidationMode.CREATE){
            var user = getByEmail(obj.getEmail());
            if(ObjectUtil.isNotNull(user))
                throw new BlokBadRequestException("Email already exists");
        }
    }

    @Override
    public void postInsert(User obj) {
        var userRoles = new UserRoles();
        userRoles.setUser(obj);
        userRoles.setUserRole(UserRole.USER);
        userRolesService.create(userRoles);
    }

    @Override
    public User insertRecord(User obj) {
        return userRepository.save(obj);
    }

    @Override
    public void preInsert(@NotNull User user) {
        try {
            user.setName(user.getName().strip());
            user.setPassword(getHashedPassword(user));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
          throw new BlokServerException("Password hashing failed, please try again");
        }
    }

    @Override
    public void preUpdate(User obj) {
        User existingUser = getById(obj.getId());
        obj.setId(existingUser.getId());
        obj.setKey(existingUser.getKey());
        obj.setCreatedDate(existingUser.getCreatedDate());
        obj.setStatus(existingUser.getStatus());
        obj.setLastModifiedDate(existingUser.getLastModifiedDate());
    }
}