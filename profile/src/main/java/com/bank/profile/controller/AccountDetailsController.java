package com.bank.profile.controller;

import com.bank.profile.dto.AccountDetailsDto;
import com.bank.profile.service.AccountDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing CRUD endpoints for account details.
 */
@RestController
@RequestMapping("/account-details")
@Validated
@RequiredArgsConstructor
@Tag(name = "Account Details", description = "Operations with profile account relations")
public class AccountDetailsController {

    private final AccountDetailsService accountDetailsService;

    @PostMapping
    @Operation(summary = "Create account details")
    public ResponseEntity<AccountDetailsDto> create(@Valid @RequestBody AccountDetailsDto dto) {
        AccountDetailsDto created = accountDetailsService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update account details")
    public ResponseEntity<AccountDetailsDto> update(@PathVariable Long id,
                                                    @Valid @RequestBody AccountDetailsDto dto) {
        return ResponseEntity.ok(accountDetailsService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete account details")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accountDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account details by id")
    public ResponseEntity<AccountDetailsDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(accountDetailsService.getById(id));
    }

    @GetMapping
    @Operation(summary = "Get all account details")
    public ResponseEntity<List<AccountDetailsDto>> getAll() {
        return ResponseEntity.ok(accountDetailsService.getAll());
    }

    @GetMapping("/profile/{profileId}")
    @Operation(summary = "Get account details by profile id")
    public ResponseEntity<List<AccountDetailsDto>> getByProfile(@PathVariable Long profileId) {
        return ResponseEntity.ok(accountDetailsService.getByProfileId(profileId));
    }
}
