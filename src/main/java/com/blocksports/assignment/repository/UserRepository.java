package com.blocksports.assignment.repository;


import com.blocksports.assignment.enums.RecordStatus;
import com.blocksports.assignment.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {

    Optional<User> findByIdAndStatus(long Id, RecordStatus recordStatus);

    Optional<User> findByKeyAndStatus(String key, RecordStatus recordStatus);

    Optional<User> findByEmailAndStatus(String email, RecordStatus recordStatus);

    Page<User> findByCreatedDateBetweenAndStatus(Instant startDate, Instant endDate, RecordStatus recordStatus, Pageable pageable);

    Page<User> findByKeyInAndStatus(Collection<String> keys, RecordStatus status, Pageable pageable);


}
