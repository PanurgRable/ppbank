package com.bank.profile.controller;

import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.service.RegistrationService;
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
 * REST controller exposing CRUD endpoints for registrations.
 */
@RestController
@RequestMapping("/registrations")
@Validated
@RequiredArgsConstructor
@Tag(name = "Registrations", description = "Operations with permanent registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    @Operation(summary = "Create registration")
    public ResponseEntity<RegistrationDto> create(@Valid @RequestBody RegistrationDto dto) {
        RegistrationDto created = registrationService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update registration")
    public ResponseEntity<RegistrationDto> update(@PathVariable Long id, @Valid @RequestBody RegistrationDto dto) {
        return ResponseEntity.ok(registrationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete registration")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        registrationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get registration by id")
    public ResponseEntity<RegistrationDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(registrationService.getById(id));
    }

    @GetMapping
    @Operation(summary = "Get all registrations")
    public ResponseEntity<List<RegistrationDto>> getAll() {
        return ResponseEntity.ok(registrationService.getAll());
    }
}
