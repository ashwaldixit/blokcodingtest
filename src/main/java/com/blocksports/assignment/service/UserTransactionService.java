package com.blocksports.assignment.service;

import com.blocksports.assignment.model.User;
import com.blocksports.assignment.model.UserTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserTransactionService extends Service<UserTransaction> {

    UserTransaction getById(long id);

    Page<UserTransaction> getAllBySender(User user, Pageable pageable);

    Page<UserTransaction> getAllByReceiver(User user, Pageable pageable);

}
