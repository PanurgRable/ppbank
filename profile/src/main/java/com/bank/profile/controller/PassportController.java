package com.bank.profile.controller;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.service.PassportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * REST controller exposing CRUD endpoints for passports.
 */
@Slf4j
@RestController
@RequestMapping("/passports")
@Validated
@RequiredArgsConstructor
@Tag(name = "Passports", description = "Operations with passport data")
public class PassportController {

    private final PassportService passportService;

    @PostMapping
    @Operation(summary = "Create passport")
    public ResponseEntity<PassportDto> create(@Valid @RequestBody PassportDto dto) {
        log.info("REST request to create passport");
        PassportDto created = passportService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update passport")
    public ResponseEntity<PassportDto> update(@PathVariable Long id, @Valid @RequestBody PassportDto dto) {
        log.info("REST request to update passport {}", id);
        return ResponseEntity.ok(passportService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete passport")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("REST request to delete passport {}", id);
        passportService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get passport by id")
    public ResponseEntity<PassportDto> getById(@PathVariable Long id) {
        log.debug("REST request to get passport {}", id);
        return ResponseEntity.ok(passportService.getById(id));
    }

    @GetMapping
    @Operation(summary = "Get all passports")
    public ResponseEntity<List<PassportDto>> getAll() {
        log.debug("REST request to get all passports");
        return ResponseEntity.ok(passportService.getAll());
    }
}
