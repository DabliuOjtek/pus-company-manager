package com.pus.companymanager.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private Long id;
    private boolean active;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String street;
    private String zipCode;
}
