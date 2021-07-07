package com.blocksports.assignment.resource;

import com.blocksports.assignment.exceptions.BlokBadRequestException;
import com.blocksports.assignment.model.Tenant;
import com.blocksports.assignment.model.User;
import com.blocksports.assignment.model.UserPreferences;
import com.blocksports.assignment.service.TenantService;
import com.blocksports.assignment.service.UserPreferenceService;
import com.blocksports.assignment.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "preferences", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserPreferenceResource extends AbstractResource{

    @Autowired
    private TenantService tenantService;

    @Autowired
    private UserService  userService;

    @Autowired
    private UserPreferenceService userPreferenceService;


    @GetMapping(value = "tenant/{tenantId}/user/{userId}")
    @Operation(summary = "Gets the UserPreferences by tenant and user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gets the UserPreferences by tenant and user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserPreferences.class))}),
            @ApiResponse(responseCode = "404", description = "Invalid input supplied", content = @Content)})
    public ResponseEntity<UserPreferences> getById(@PathVariable("tenantId") long tenantId, @PathVariable("userId") long userId) {
        Optional<Tenant> tenant = tenantService.getById(tenantId);
        User user = userService.getById(userId);
        if(Objects.isNull(user)||tenant.isEmpty())
            throw new BlokBadRequestException("Invalid input. Please provide valid tenant and user info");

        return ResponseEntity.ok( userPreferenceService.getAllByUserAndTenant(user, tenant.get()));
    }


    @PostMapping(value = "tenant/{tenantId}/preference/{preferenceId}")
    @Operation(summary = "Copy a preference to tenant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Copy a preference to tenant",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tenant.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Tenant details", content = @Content)})
    public ResponseEntity<UserPreferences> copyPreference(@PathVariable("tenantId") long tenantId, @PathVariable("preferenceId") long preferenceId){

        return ResponseEntity.ok(userPreferenceService.copyPreference(preferenceId, tenantId));
    }


    @PostMapping
    @Operation(summary = "Creates a preference to user for a tenant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creates a preference to user for a tenant",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tenant.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Tenant details", content = @Content)})
    public ResponseEntity<UserPreferences> createPreference(@RequestBody UserPreferences userPreferences){

        return ResponseEntity.ok(userPreferenceService.create(userPreferences));
    }

    @PutMapping(value = "preference/{preferenceId}/enable")
    @Operation(summary = "Enable the preference")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enable the preference",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tenant.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid preference details", content = @Content)})
    public ResponseEntity<Tenant> enablePreference(@PathVariable("preferenceId") long preferenceId) {
        userPreferenceService.enablePreference(preferenceId);
        return ResponseEntity.ok().build();
    }



    @PutMapping(value = "preference/{preferenceId}/disable")
    @Operation(summary = "Enable the preference")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enable the preference",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tenant.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid preference details", content = @Content)})
    public ResponseEntity<Tenant> disablePreference(@PathVariable("preferenceId") long preferenceId) {
        userPreferenceService.enablePreference(preferenceId);
        return ResponseEntity.ok().build();
    }
}
