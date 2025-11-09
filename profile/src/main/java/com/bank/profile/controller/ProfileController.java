package com.bank.profile.controller;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.service.ProfileService;
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
 * REST controller exposing CRUD endpoints for profiles.
 */
@Slf4j
@RestController
@RequestMapping("/profiles")
@Validated
@RequiredArgsConstructor
@Tag(name = "Profiles", description = "Operations with customer profiles")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    @Operation(summary = "Create profile")
    public ResponseEntity<ProfileDto> create(@Valid @RequestBody ProfileDto dto) {
        log.info("REST request to create profile");
        ProfileDto created = profileService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update profile")
    public ResponseEntity<ProfileDto> update(@PathVariable Long id, @Valid @RequestBody ProfileDto dto) {
        log.info("REST request to update profile {}", id);
        return ResponseEntity.ok(profileService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete profile")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("REST request to delete profile {}", id);
        profileService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get profile by id")
    public ResponseEntity<ProfileDto> getById(@PathVariable Long id) {
        log.debug("REST request to get profile {}", id);
        return ResponseEntity.ok(profileService.getById(id));
    }

    @GetMapping
    @Operation(summary = "Get all profiles")
    public ResponseEntity<List<ProfileDto>> getAll() {
        log.debug("REST request to get all profiles");
        return ResponseEntity.ok(profileService.getAll());
    }
}
