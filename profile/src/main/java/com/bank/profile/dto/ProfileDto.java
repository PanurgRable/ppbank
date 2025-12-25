package com.bank.profile.dto;

import java.util.Set;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.Setter;

/**
 * DTO representing profile data exposed via API.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    private Long id;

    @NotNull
    private Long phoneNumber;

    private String email;

    private String nameOnCard;

    private Long inn;

    private Long snils;

    @NotNull
    @Valid
    private PassportDto passport;

    @Valid
    private ActualRegistrationDto actualRegistration;

    @Singular("accountDetail")
    @Valid
    private Set<AccountDetailsDto> accountDetails;
}
