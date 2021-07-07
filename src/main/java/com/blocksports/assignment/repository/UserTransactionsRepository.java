package com.blocksports.assignment.repository;

import com.blocksports.assignment.enums.RecordStatus;
import com.blocksports.assignment.model.User;
import com.blocksports.assignment.model.UserTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionsRepository extends JpaRepository<UserTransaction, Long> {

    Page<UserTransaction> findByReceiverAndStatus(User user,RecordStatus status, Pageable pageable);

    Page<UserTransaction> findBySenderAndStatus(User user, RecordStatus status,Pageable pageable);

    UserTransaction findByIdAndStatus(long id, RecordStatus recordStatus);

}
