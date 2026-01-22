package com.gastawny.shockwave.dto;

import com.gastawny.shockwave.models.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
public class TokenDTO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;

    private User user;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

    public TokenDTO(User user, Boolean authenticated, Date created, Date expiration, String accessToken, String refreshToken) {
        this.user = user;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public TokenDTO() { }
}
