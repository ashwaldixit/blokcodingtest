package com.blocksports.assignment.service;


import com.blocksports.assignment.model.Tenant;

import java.util.Optional;

public interface TenantService extends Service<Tenant> {

    Optional<Tenant> getByName(String name);

     Optional<Tenant> getById(long id);




}
