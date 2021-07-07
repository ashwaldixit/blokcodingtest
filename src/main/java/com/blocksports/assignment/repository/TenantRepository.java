package com.blocksports.assignment.repository;

import com.blocksports.assignment.enums.RecordStatus;
import com.blocksports.assignment.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Serializable> {

    Optional<Tenant> findByNameAndStatus(String token, RecordStatus recordStatus);

    Optional<Tenant> findByIdAndStatus(long id, RecordStatus status);

}

