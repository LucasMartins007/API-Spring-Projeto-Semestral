/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.config;

import com.daki.api.controller.PessoaController;
import com.daki.domain.config.security.AccessDeniedHandlerImpl;
import com.daki.domain.config.security.AuthenticationEntryPointImpl;
import com.daki.domain.config.security.provider.DomainAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 *
 * @author lucas
 */
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        proxyTargetClass = true,
        securedEnabled = true,
        mode = AdviceMode.PROXY,
        order = Ordered.HIGHEST_PRECEDENCE)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/v1/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/configuration/security",
                "/webjars/**",
                "/actuator/**",
                "/login",
                PessoaController.PATH + "salvar"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable();
        http.csrf().disable();
        http.httpBasic().disable();

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest()
                .denyAll().and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler()).authenticationEntryPoint(authenticationEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth,
//                          UserDetailsService userDetailsService) {
//        auth.authenticationProvider(new DomainAuthenticationProvider(userDetailsService).passwordEncoder(passwordEncoder));
//    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

}
