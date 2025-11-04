package com.bank.profile.dto;

import java.util.Set;
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

    private Long phoneNumber;

    private String email;

    private String nameOnCard;

    private Long inn;

    private Long snils;

    private PassportDto passport;

    private ActualRegistrationDto actualRegistration;

    @Singular("accountDetail")
    private Set<AccountDetailsDto> accountDetails;
}
