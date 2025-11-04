package com.bank.profile.dto;

import java.time.LocalDate;
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

    private Integer series;

    private Long number;

    private String lastName;

    private String firstName;

    private String middleName;

    private String gender;

    private LocalDate birthDate;

    private String birthPlace;

    private String issuedBy;

    private LocalDate dateOfIssue;

    private Integer divisionCode;

    private LocalDate expirationDate;

    private RegistrationDto registration;
}
