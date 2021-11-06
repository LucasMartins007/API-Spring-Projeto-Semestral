/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model.classes;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author lucas
 */
@Data
public class TokenBean {
    
    private String uniqueId;

    private Date dateExpire;

    private String accessToken;

    private String refreshToken;
}
