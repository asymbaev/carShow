package com.binary.carShow.security;

import com.binary.carShow.entity.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // each method return different type of bean(object), thats why we use
public class SecurityConfig {
    // Header -> Authentication : Basic username : password
    // Body{
    // id:1
    // make:"Toyota",
        //}
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //Everyone should be able to Get
        return http
                .cors(Customizer.withDefaults()) // to block others, only allow particular client
                .csrf(c -> c.disable())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(

                        auth -> auth.requestMatchers(HttpMethod.GET, "/api/v1/car/*").hasAnyRole("USER","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/car/*").hasRole("ADMIN")
                                .anyRequest()
                                .authenticated()

                        )
                .build();

    }

    // csrf Cross site request forgery (CSRF) attack
    //cors
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder().encode("adminPass"))
                .roles("ADMIN")
                .build();

        var user = User.builder()
                .username("user")
                .password(bCryptPasswordEncoder().encode("userPass"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user,admin);

    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
