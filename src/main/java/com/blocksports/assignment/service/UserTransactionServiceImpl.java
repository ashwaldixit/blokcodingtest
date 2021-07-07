package com.blocksports.assignment.service;


import com.blocksports.assignment.enums.RecordStatus;
import com.blocksports.assignment.enums.ValidationMode;
import com.blocksports.assignment.exceptions.BlokBadRequestException;
import com.blocksports.assignment.model.User;
import com.blocksports.assignment.model.UserTransaction;
import com.blocksports.assignment.repository.UserTransactionsRepository;
import com.blocksports.assignment.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserTransactionServiceImpl implements UserTransactionService {

    @Autowired
    private UserTransactionsRepository userTransactionsRepository;

    @Override
    public UserTransaction insertRecord(UserTransaction obj) {
        return userTransactionsRepository.save(obj);
    }

    @Override
    public UserTransaction getById(long id) {
        return userTransactionsRepository.findByIdAndStatus(id, RecordStatus.ACTIVE);
    }

    @Override
    public Page<UserTransaction> getAllBySender(User user, Pageable pageable) {
        return userTransactionsRepository.findBySenderAndStatus(user, RecordStatus.ACTIVE, pageable);
    }

    @Override
    public Page<UserTransaction> getAllByReceiver(User user, Pageable pageable) {
        return userTransactionsRepository.findByReceiverAndStatus(user, RecordStatus.ACTIVE, pageable);
    }

    @Override
    public void preValidate(@NonNull UserTransaction obj, ValidationMode mode) {

        if (obj.getAmountSent() < 0) {
            throw new BlokBadRequestException("Amount should be greater than zero");
        }
        if (ObjectUtil.isNull(obj.getReceiver())) {
            throw new BlokBadRequestException("Receiver cannot be empty");
        }
        if (ObjectUtil.isNull(obj.getSender())) {
            throw new BlokBadRequestException("Sender cannot be empty");
        }
    }

    @Override
    public void preUpdate(UserTransaction obj) {
        UserTransaction existingRecord = getById(obj.getId());
        obj.setId(existingRecord.getId());
        obj.setKey(existingRecord.getKey());
        obj.setCreatedDate(existingRecord.getCreatedDate());
        obj.setStatus(existingRecord.getStatus());
        obj.setLastModifiedDate(existingRecord.getLastModifiedDate());
    }
}