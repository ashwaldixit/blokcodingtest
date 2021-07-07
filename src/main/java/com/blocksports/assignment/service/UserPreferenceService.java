package com.blocksports.assignment.service;

import com.blocksports.assignment.model.Tenant;
import com.blocksports.assignment.model.User;
import com.blocksports.assignment.model.UserPreferences;
import com.blocksports.assignment.model.UserTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserPreferenceService extends Service<UserPreferences> {

    UserPreferences getById(long id);
    UserPreferences getAllByUserAndTenant(User user, Tenant tenant);
    void enablePreference(long userPreferenceId);
    void disablePreference(long userPreferenceId);
    UserPreferences copyPreference(long userPreferenceId, long tenantId);

    Page<UserPreferences> getAllByUser(User user, Pageable pageable);

}
