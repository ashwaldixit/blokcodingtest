package com.blocksports.assignment.repository;

import com.blocksports.assignment.model.User;
import com.blocksports.assignment.model.UserRoles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Serializable> {

    List<UserRoles> findByUser(User user);

    Page<UserRoles> findByUser(User user, Pageable pageable);

}
