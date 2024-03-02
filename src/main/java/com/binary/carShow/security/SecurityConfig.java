package com.binary.carShow.security;

import com.binary.carShow.entity.Car;
import com.binary.carShow.exception.AuthEntryPoint;
import com.binary.carShow.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // each method return different type of bean(object), thats why we use
public class SecurityConfig {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationFilter authenticationFilter;



    // Header -> Authentication : Basic username : password
    // Body{
    // id:1
    // make:"Toyota",
        //}
    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //Everyone should be able to Get
//        return http
//                .cors(Customizer.withDefaults()) // to block others, only allow particular client
//                .csrf(c -> c.disable())
//                .httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(
//
//                        auth -> {
//                            try {
//                                auth
//                                        .requestMatchers(HttpMethod.POST,"/login").permitAll()
//                                        .requestMatchers(HttpMethod.GET, "/api/v1/car/*").hasAnyRole("USER","ADMIN")
//                                        .requestMatchers(HttpMethod.POST, "/api/v1/car/*").hasRole("ADMIN")
//                                        .anyRequest()
//                                        .authenticated()
//                                        .and()
//                                        .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
//
//                                        .exceptionHandling((ex) -> ex.authenticationEntryPoint(authEntryPoint));
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//
//
//
//                        )
//                .build();

        http.csrf((csrf) -> csrf.disable()).cors(c -> c.disable())
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests.anyRequest().permitAll());
        return  http.build();

    }
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());


    }

    // csrf Cross site request forgery (CSRF) attack
    //cors
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(bCryptPasswordEncoder().encode("adminPass"))
//                .roles("ADMIN")
//                .build();
//
//        var user = User.builder()
//                .username("user")
//                .password(bCryptPasswordEncoder().encode("userPass"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user,admin);
//
//    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source = new
                UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(""));
        config.setAllowedMethods(Arrays.asList(""));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(false);
        config.applyPermitDefaultValues();
        source.registerCorsConfiguration("/**",config);
        return source;
    }

}
