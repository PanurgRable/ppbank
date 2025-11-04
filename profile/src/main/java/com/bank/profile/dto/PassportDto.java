package com.bank.profile.dto;

import java.time.LocalDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO describing passport data of a profile.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassportDto {

    private Long id;

    @NotNull
    private Integer series;

    @NotNull
    private Long number;

    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    private String middleName;

    @NotBlank
    private String gender;

    @NotNull
    private LocalDate birthDate;

    @NotBlank
    private String birthPlace;

    @NotBlank
    private String issuedBy;

    @NotNull
    private LocalDate dateOfIssue;

    @NotNull
    private Integer divisionCode;

    private LocalDate expirationDate;

    @NotNull
    @Valid
    private RegistrationDto registration;
}
