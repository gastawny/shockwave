package com.gastawny.shockwave.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class AccountCredentialsDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;

    public AccountCredentialsDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
