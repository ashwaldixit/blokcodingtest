package com.blocksports.assignment.service;


import com.blocksports.assignment.model.User;
import com.blocksports.assignment.model.UserRoles;

import java.util.List;

public interface UserRolesService extends Service<UserRoles> {

     List<UserRoles> getAllForUser(User user);


}
