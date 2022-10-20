package com.tes.Penjualan.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//public class MvcSecurityConfig extends WebSecurityConfigurerAdapter {
public class MvcSecurityConfig  {

//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/resources/**", "/account/**").permitAll()
//                .antMatchers("/home/**",
//                        "/customer/**",
//                        "/product/**",
//                        "/my-cart/**",
//                        "/report/**").permitAll()
////
////                .antMatchers("/customer/upsertForm",
////                        "/customer/update",
////                        "/room/vacant-room",
////                        "/room/vacant-room/reserve",
////                        "/room/insert-reserve",
////                        "/reservation/my-reservation",
////                        "/reservation/reserve",
////                        "/transaction/index",
////                        "/change-profile-customer/update-profile",
////                        "/change-profile-customer/update").hasAuthority("Customer")
//
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/account/loginPage")
//                .loginProcessingUrl("/authenticateTheUser").permitAll()
//                .and()
//                .logout().permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/account/accessDenied");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }

    @Order(1)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //hasRole diganti hasAuthority, hasAnyRole diganti hasAnyAuthority
        http.authorizeRequests()
                .antMatchers("/resources/**", "/account/**").permitAll()
                .antMatchers("/home/**",
                        "/customer/**",
                        "/product/**",
                        "/my-cart/**",
                        "/report/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/account/loginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .and().logout()
                .and().exceptionHandling().accessDeniedPage("/account/accessDenied");

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager (HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) throws  Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
}
