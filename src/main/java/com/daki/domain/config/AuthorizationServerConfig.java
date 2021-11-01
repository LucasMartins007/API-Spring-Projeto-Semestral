///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.daki.domain.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//
///**
// *
// * @author lucas
// */
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    
//    @Autowired
//    private UserDetailsService detailsService;
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
////        clients
////                .inMemory()
////                .withClient("daki-web") // caso possuimos uma aplicação web ou mobile consumindo a API
////                .secret(passwordEncoder.encode("123"))
////                .authorizedGrantTypes("password", "refresh_token")
////                .scopes("write", "read")
////                .accessTokenValiditySeconds(60 * 60 * 7) // 7 horas
////                .refreshTokenValiditySeconds(60 * 60 * 14) // 14 horas
////                
////             .and()
////                .withClient("teste-faturamento") 
////                .secret(passwordEncoder.encode("123"))
////                .authorizedGrantTypes("client_credentials")
////                .scopes("write", "read" )                
////                
////             .and()
////                .withClient("checktoken")
////                    .secret(passwordEncoder.encode("check123"))
////                ;
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
////        security.checkTokenAccess("permitAll()"); //isAuthenticated()
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
////        endpoints
////                .authenticationManager(authenticationManager)
////                .userDetailsService(detailsService)
////                .reuseRefreshTokens(false)
////                ;
//    }
//}
