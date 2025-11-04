package com.bank.profile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO representing an actual registration address for a profile.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActualRegistrationDto {

    private Long id;

    @NotBlank
    private String country;

    private String region;

    private String city;

    private String district;

    private String locality;

    private String street;

    private String houseNumber;

    private String houseBlock;

    private String flatNumber;

    @NotNull
    private Long postalIndex;
}
