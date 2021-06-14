package com.hesfintech.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @ToString.Exclude
    private long id;

    @NotNull
    @Size(min = 3, max = 16)
    private String userName;

    @NotNull
    @Size(min = 3, max = 16)
    private String password;

    @NotNull
    @Size(min = 1, max = 16)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 16)
    private String lastName;

    private Role role;
    private Status status;
    private LocalDate createdAt;
}