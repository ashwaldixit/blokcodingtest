package com.blocksports.assignment;

import com.blocksports.assignment.service.TenantService;
import com.blocksports.assignment.service.UserPreferenceService;
import com.blocksports.assignment.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssignmentApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Test
    void contextLoads() {
    }

}
