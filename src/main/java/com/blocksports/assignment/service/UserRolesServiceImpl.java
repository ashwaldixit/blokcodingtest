package com.blocksports.assignment.service;


import com.blocksports.assignment.model.User;
import com.blocksports.assignment.model.UserRoles;
import com.blocksports.assignment.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRolesServiceImpl implements UserRolesService {

    @Autowired
    private UserRolesRepository repository;


    @Autowired
    private UserService userService;

    @Override
    public void preInsert(UserRoles obj) {
        User user = userService.getById(obj.getUser().getId());
        obj.setUser(user);
    }

    @Override
    public UserRoles insertRecord(UserRoles obj) {
        return repository.save(obj);
    }

    @Override
    public List<UserRoles> getAllForUser(User user) {
        return repository.findByUser(user);
    }

}
