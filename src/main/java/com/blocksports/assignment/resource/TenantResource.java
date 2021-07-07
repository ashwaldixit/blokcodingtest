package com.blocksports.assignment.resource;

import com.blocksports.assignment.model.Tenant;
import com.blocksports.assignment.model.Tenant;
import com.blocksports.assignment.service.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "tenants", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class TenantResource extends AbstractResource{

    @Autowired
    private TenantService tenantService;

    @GetMapping(value = "{id}")
    @Operation(summary = "Gets the Tenant by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gets the Tenant by id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tenant.class))}),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied", content = @Content)})
    public ResponseEntity<Optional<Tenant>> getById(@PathVariable("id") long id) {
        Optional<Tenant> tenant = tenantService.getById(id);
        return ResponseEntity.ok(tenant);
    }


    @GetMapping(value = "/name/{name}")
    @Operation(summary = "Gets the Tenant by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gets the Tenant by email",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tenant.class))}),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied", content = @Content)})
    public ResponseEntity<Optional<Tenant>> getByName(@PathVariable("name") String name) {
        Optional<Tenant> tenant = tenantService.getByName(name);
        return ResponseEntity.ok(tenant);
    }

    @PostMapping
    @Operation(summary = "Registers the Tenant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registered the Tenant",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tenant.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Tenant details", content = @Content)})
    public ResponseEntity<Tenant> createTenant(@RequestBody Tenant tenant) {
        return ResponseEntity.ok(tenantService.create(tenant));
    }


    @PutMapping
    @Operation(summary = "Updates the Tenant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updates the Tenant",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tenant.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Tenant details", content = @Content)})
    public ResponseEntity<Tenant> updateTenant(@RequestBody Tenant tenant) {
        return ResponseEntity.ok(tenantService.modify(tenant));
    }

}
