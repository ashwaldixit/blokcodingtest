package com.blocksports.assignment.service;


import com.blocksports.assignment.enums.RecordStatus;
import com.blocksports.assignment.model.Tenant;
import com.blocksports.assignment.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantRepository repository;

    @Autowired
    private UserService userService;

    @Override
    public Tenant insertRecord(Tenant obj) {
        return repository.save(obj);
    }

    @Override
    public Optional<Tenant> getByName(String name) {
        return repository.findByNameAndStatus(name, RecordStatus.ACTIVE);
    }

    @Override
    public Optional<Tenant> getById(long id) {
        return repository.findByIdAndStatus(id, RecordStatus.ACTIVE);
    }

    public void preUpdate(Tenant obj) {
        Optional<Tenant> existingRecordOptional = getById(obj.getId());
        if (existingRecordOptional.isEmpty()) return;
        Tenant existingRecord = existingRecordOptional.get();
        obj.setId(existingRecord.getId());
        obj.setKey(existingRecord.getKey());
        obj.setCreatedDate(existingRecord.getCreatedDate());
        obj.setStatus(existingRecord.getStatus());
        obj.setLastModifiedDate(existingRecord.getLastModifiedDate());
    }
}
