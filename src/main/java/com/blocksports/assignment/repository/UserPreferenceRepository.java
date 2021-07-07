package com.blocksports.assignment.repository;

import com.blocksports.assignment.enums.RecordStatus;
import com.blocksports.assignment.model.Tenant;
import com.blocksports.assignment.model.User;
import com.blocksports.assignment.model.UserPreferences;
import com.blocksports.assignment.model.UserTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreferences, Long> {

    UserPreferences findByUserAndTenantAndStatus(User user, Tenant tenant,RecordStatus status);

    Page<UserPreferences> findByUserAndStatus(User user, RecordStatus status,Pageable pageable);

    UserPreferences findByIdAndStatus(long id, RecordStatus recordStatus);

}
