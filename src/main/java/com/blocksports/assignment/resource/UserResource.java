package com.blocksports.assignment.resource;

import com.blocksports.assignment.model.User;
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

@RestController
@RequestMapping(path = "users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserResource extends AbstractResource{

    @Autowired
    private UserService userService;

    @GetMapping(value = "{id}")
    @Operation(summary = "Gets the User by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gets the User by id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied", content = @Content)})
    public ResponseEntity getUserById(@PathVariable("id") long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }


    @GetMapping(value = "/email/{email}")
    @Operation(summary = "Gets the User by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gets the User by email",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied", content = @Content)})
    public ResponseEntity<User> getUserByName(@PathVariable("email") String email) {
        User user = userService.getByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(summary = "Registers the User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registered the User",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid User details", content = @Content)})
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.create(user);
        return ResponseEntity.ok(createdUser);
    }


    @PutMapping
    @Operation(summary = "Updates the User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updates the User",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid User details", content = @Content)})
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.modify(user);
        return ResponseEntity.ok(updatedUser);
    }

}
