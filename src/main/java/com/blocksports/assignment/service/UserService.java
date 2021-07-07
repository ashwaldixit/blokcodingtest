package com.blocksports.assignment.service;

import com.blocksports.assignment.model.User;

import java.util.Collection;

public interface UserService extends Service<User> {



    User getById(long id);

    User getByEmail(String email);

    User validateAndGetById(long id);

    User validateAndGetByEmail(String email);

}
