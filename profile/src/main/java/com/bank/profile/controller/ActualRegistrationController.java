package com.bank.profile.controller;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.service.ActualRegistrationService;
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
 * REST controller exposing CRUD endpoints for actual registrations.
 */
@RestController
@RequestMapping("/actual-registrations")
@Validated
@RequiredArgsConstructor
@Tag(name = "Actual Registrations", description = "Operations with actual residence addresses")
public class ActualRegistrationController {

    private final ActualRegistrationService actualRegistrationService;

    @PostMapping
    @Operation(summary = "Create actual registration")
    public ResponseEntity<ActualRegistrationDto> create(@Valid @RequestBody ActualRegistrationDto dto) {
        ActualRegistrationDto created = actualRegistrationService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update actual registration")
    public ResponseEntity<ActualRegistrationDto> update(@PathVariable Long id,
                                                        @Valid @RequestBody ActualRegistrationDto dto) {
        return ResponseEntity.ok(actualRegistrationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete actual registration")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        actualRegistrationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get actual registration by id")
    public ResponseEntity<ActualRegistrationDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(actualRegistrationService.getById(id));
    }

    @GetMapping
    @Operation(summary = "Get all actual registrations")
    public ResponseEntity<List<ActualRegistrationDto>> getAll() {
        return ResponseEntity.ok(actualRegistrationService.getAll());
    }
}
