package com.gastawny.shockwave.dto.user;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean isManager;
}
