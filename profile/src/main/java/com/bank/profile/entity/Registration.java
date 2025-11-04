package com.bank.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity describing the permanent registration address stored in passports.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "registration", schema = "profile")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country", nullable = false, length = 166)
    private String country;

    @Column(name = "region", length = 160)
    private String region;

    @Column(name = "city", length = 160)
    private String city;

    @Column(name = "district", length = 160)
    private String district;

    @Column(name = "locality", length = 230)
    private String locality;

    @Column(name = "street", length = 230)
    private String street;

    @Column(name = "house_number", length = 20)
    private String houseNumber;

    @Column(name = "house_block", length = 20)
    private String houseBlock;

    @Column(name = "flat_number", length = 40)
    private String flatNumber;

    @Column(name = "index", nullable = false)
    private Long postalIndex;

    @Column(name = "Column")
    private Integer columnCode;
}
