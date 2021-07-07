package com.blocksports.assignment.resource;

import com.blocksports.assignment.model.UserTransaction;
import com.blocksports.assignment.service.UserService;
import com.blocksports.assignment.service.UserTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "transactions", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserTransactionResource extends AbstractResource {

    @Autowired
    private UserTransactionService userTransactionService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "{id}")
    @Operation(summary = "Gets the transaction by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gets the transaction by id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserTransaction.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content)})
    public ResponseEntity<UserTransaction> getUserById(@NonNull @PathVariable("id") long id) {
        return ResponseEntity.ok(userTransactionService.getById(id));
    }


    @GetMapping(value = "{id}/sender")
    @Operation(summary = "Gets the transaction of the user acting as sender")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gets the transaction of the user acting as sender",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserTransaction.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content)})
    public ResponseEntity<Page<UserTransaction>> getAllRecordsOfSender(@NonNull @PathVariable("id") long id) {
        var user = userService.getById(id);
        return ResponseEntity.ok(userTransactionService.getAllBySender(user, getPageable()));
    }


    @GetMapping(value = "{id}/receiver")
    @Operation(summary = "Gets the transaction of the user acting as Receiver(")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gets the transaction of the user acting as Receiver(",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserTransaction.class))}),
            @ApiResponse(responseCode = "400", description = "Gets the transaction of the user acting as Receiver(", content = @Content)})
    public ResponseEntity<Page<UserTransaction>> getAllRecordsOfReceiver(@NonNull @PathVariable("id") long id) {
        var user = userService.getById(id);
        return ResponseEntity.ok(userTransactionService.getAllByReceiver(user, getPageable()));
    }


    @PostMapping
    @Operation(summary = "Creates a transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creates a transaction",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserTransaction.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid transaction details", content = @Content)})
    public ResponseEntity<UserTransaction> createUser(@NonNull @RequestBody UserTransaction userTransaction) {
        userTransaction.setReceiver(userService.getById(userTransaction.getReceiver().getId()));
        userTransaction.setSender(userService.getById(userTransaction.getSender().getId()));
        return ResponseEntity.ok(userTransactionService.create(userTransaction));
    }


    @PutMapping
    @Operation(summary = "Updates the transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updates the transaction",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserTransaction.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid transaction details", content = @Content)})
    public ResponseEntity<UserTransaction> updateUser(@NonNull @RequestBody UserTransaction userTransaction) {
        userTransaction.setReceiver(userService.getById(userTransaction.getReceiver().getId()));
        userTransaction.setSender(userService.getById(userTransaction.getSender().getId()));
        UserTransaction createdUser = userTransactionService.update(userTransaction);
        return ResponseEntity.ok(createdUser);
    }

}
