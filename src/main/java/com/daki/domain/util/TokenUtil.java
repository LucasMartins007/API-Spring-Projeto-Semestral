/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.daki.domain.model.classes.CustomUserDetails;
import com.daki.domain.model.classes.TokenBean;
import com.daki.domain.patterns.SecurityConstants;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

/**
 *
 * @author lucas
 */
public class TokenUtil {
    
     public static TokenBean createToken(CustomUserDetails userDetails) {
        return createToken(userDetails, null);
    }

    public static TokenBean createToken(CustomUserDetails userDetails, Integer tenantId) {
        final String uniqueId = UUID.randomUUID().toString();
        final Algorithm signing = createSign(userDetails.getSigningKey());
        final Date dateExpire = DateUtil.add(Calendar.HOUR_OF_DAY, 8);

        final JWTCreator.Builder builder = JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer(userDetails.getSigningKey())
                .withIssuedAt(DateUtil.getDate())
                .withExpiresAt(dateExpire);

        final String accessToken = builder.sign(signing);

        final String refreshToken = JWT.create()
                .withJWTId(uniqueId)
                .withSubject(userDetails.getUsername())
                .withExpiresAt(DateUtil.add(dateExpire, Calendar.MINUTE, 15))
                .sign(signing);

        final TokenBean tokenBean = new TokenBean();
        tokenBean.setUniqueId(uniqueId);
        tokenBean.setDateExpire(dateExpire);
        tokenBean.setAccessToken(accessToken);
        tokenBean.setRefreshToken(refreshToken);

        return tokenBean;
    }

    public static String createTokenComunicao(final String signingKey) {
        return JWT.create()
                .withAudience(signingKey)
                .withExpiresAt(DateUtil.add(Calendar.HOUR_OF_DAY, 3))
                .sign(createSign(signingKey));
    }

    public static boolean validateToken(final String token, final String signingKey) {
        if (StringUtil.isNullOrEmpty(token)) {
            return false;
        }

        try {
            JWT.require(createSign(signingKey))
                    .build()
                    .verify(token.replace(SecurityConstants.BEARER_SCHEME, ""))
                    .getIssuer();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean validateTokenFromRequest(HttpServletRequest request) {
        final String token = request.getHeader(SecurityConstants.HEADER_TOKEN);
        final String dominio = request.getHeader(HttpHeaders.ORIGIN);

        return validateToken(token, dominio);
    }

    public static String getUsernameFromToken(String token) {
        return getClaimFromToken(token).getSubject();
    }

    public static String getUniqueIdentifierFromToken(String token) {
        return getClaimFromToken(token).getId();
    }

    public static String getAudienceFromToken(String token) {
        final List<String> audience = getClaimFromToken(token).getAudience();
        return ListUtil.first(audience);
    }

    private static DecodedJWT getClaimFromToken(String token) {
        return JWT.decode(token.replace(SecurityConstants.BEARER_SCHEME, ""));
    }

    private static Algorithm createSign(String signingKey) {
        return Algorithm.HMAC256(SecurityConstants.SECRET_KEY + ":" + signingKey);
    }
}
