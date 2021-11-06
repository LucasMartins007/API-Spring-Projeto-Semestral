/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model.classes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author lucas
 */
@Data
public class UserTransfer {
    
    private String nome;
    
    private String email;
    
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("expires_in")
    private Date expiresIn;
    
    @JsonProperty("access_token")
    private String accessToken;
    
    @JsonProperty("refresh_token")
    private String refreshToken;
    
    public UserTransfer(String name, String username, TokenBean tokenBean) {
        this.nome = name;
        this.email = username;

        if (tokenBean != null) {
            this.expiresIn = tokenBean.getDateExpire();
            this.accessToken = tokenBean.getAccessToken();
            this.refreshToken = tokenBean.getRefreshToken();
        }
    }
    
}
