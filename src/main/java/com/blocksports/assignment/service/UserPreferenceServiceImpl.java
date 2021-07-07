package com.blocksports.assignment.service;


import com.blocksports.assignment.enums.RecordStatus;
import com.blocksports.assignment.exceptions.BlokBadRequestException;
import com.blocksports.assignment.model.Tenant;
import com.blocksports.assignment.model.User;
import com.blocksports.assignment.model.UserPreferences;
import com.blocksports.assignment.repository.UserPreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class UserPreferenceServiceImpl implements UserPreferenceService {

    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    @Autowired
    private TenantService tenantService;

    @Override
    public UserPreferences insertRecord(UserPreferences obj) {
        return userPreferenceRepository.save(obj);
    }

    @Override
    public UserPreferences getById(long id) {
        return userPreferenceRepository.findByIdAndStatus(id, RecordStatus.ACTIVE);
    }

    @Override
    public UserPreferences getAllByUserAndTenant(User user, Tenant tenant) {
        return userPreferenceRepository.findByUserAndTenantAndStatus(user, tenant, RecordStatus.ACTIVE);
    }


    private UserPreferences validateAndGetById(long id) {
        UserPreferences preferences = getById(id);
        return Optional.of(preferences).orElseThrow(() -> new BlokBadRequestException("Invalid user preference provided"));
    }

    @Override
    public void enablePreference(long userPreferenceId) {

        UserPreferences preferences = validateAndGetById(userPreferenceId);
        preferences.setEnabled(true);
        modify(preferences);
    }


    @Override
    public void disablePreference(long userPreferenceId) {
        UserPreferences preferences = validateAndGetById(userPreferenceId);
        preferences.setEnabled(false);
        modify(preferences);
    }

    @Override
    public UserPreferences copyPreference(long userPreferenceId, long tenantId) {

        Optional<Tenant> tenant = tenantService.getById(tenantId);
        if(tenant.isEmpty())
            throw new BlokBadRequestException("Invalid tenant information");
        UserPreferences userPreferences = validateAndGetById(userPreferenceId);
        userPreferences.setId(0L);
        userPreferences.setTenant(tenant.get());
        return create(userPreferences);
    }

    @Override
    public Page<UserPreferences> getAllByUser(User user, Pageable pageable) {
        return userPreferenceRepository.findByUserAndStatus(user, RecordStatus.ACTIVE, pageable);
    }
}