package com.innowise.secretSanta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @Column(name = "first_name")
    @NotNull(message = "First name is required")
    @NotBlank(message = "First name can't be empty")
    @Size(min = 2, max = 100, message = "First name string length limits exceeded")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name can't be empty")
    @Size(min = 2, max = 100, message = "Last name string length limits exceeded")
    private String lastName;

    @NotNull(message = "Phone number is required")
    @Size(min = 0, max = 50, message = "Phone number string length limits exceeded")
    private String phoneNumber;

    @NotNull(message = "Address is required")
    @Size(max = 200, message = "Restaurant address string length limits exceeded")
    private String address;

    @NotNull(message = "Postcode is required")
    @Size(max = 50, message = "Restaurant address string length limits exceeded")
    private String postcode;
}
