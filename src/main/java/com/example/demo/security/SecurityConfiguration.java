package com.example.demo.security;

import com.example.demo.model.UserService;
import domain.user.DUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;
//import org.springframework.security.core.userdetails.User;


//@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    UserService us = new UserService();
    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/signup";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_SUCCESS_URL = "/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestCache().requestCache(new CustomRequestCache())
                .and().authorizeRequests()
                .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()

                .anyRequest().authenticated()

                .and().formLogin()
                .loginPage(LOGIN_URL).permitAll()
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .failureUrl(LOGIN_FAILURE_URL)
                .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);
    }
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/VAADIN/**",
                "/favicon.ico",
                "/robots.txt",
                "/manifest.webmanifest",
                "/sw.js",
                "/offline.html",
                "/icons/**",
                "/images/**",
                "/styles/**",
                "/h2-console/**");
    }
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        List<DUser> users = us.getUsers();
        List<UserDetails> userDetailsList = new ArrayList<>();
        for (DUser userr : users) {
            userDetailsList.add(User.withUsername(userr.getName()).password("{noop}"+userr.getPassword()).roles("USER").build());
            System.out.println(userr.getName() + " " + userr.getPassword());
        }
//        UserDetails user =
//                User.withUsername("user")
//                        .password("{noop}password")
//                        .roles("USER")
//                        .build();

        //return new InMemoryUserDetailsManager(user);

        return new InMemoryUserDetailsManager(userDetailsList);
    }

}