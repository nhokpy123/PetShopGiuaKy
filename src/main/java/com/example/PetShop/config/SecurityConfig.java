package com.example.PetShop.config;

import com.example.PetShop.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    // Inject CustomUserDetailsService thông qua constructor
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Cấu hình các URL được phép truy cập
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/api/user/login",  // Trang đăng nhập
                                "/api/user/register", // Trang đăng ký
                                "/api/user/home", // Trang home
                                "/css/**", "/js/**", "/images/**" // Static resources
                        ).permitAll() // Cho phép không cần đăng nhập
                        .requestMatchers("/api/products").authenticated() // Yêu cầu đăng nhập để truy cập trang chính
                        .anyRequest().authenticated() // Các URL khác cũng yêu cầu đăng nhập
                )
                // Cấu hình form đăng nhập
                .formLogin((form) -> form
                        .loginPage("/api/user/login") // Trang đăng nhập
                        .defaultSuccessUrl("/api/user/home", true) // Chuyển đến /home sau khi login thành công
                        .permitAll()
                )
                // Cấu hình logout
                .logout((logout) -> logout
                        .logoutUrl("/logout") // URL để logout
                        .logoutSuccessUrl("/api/user/login?logout") // Chuyển hướng sau khi logout
                        .permitAll() // Cho phép tất cả truy cập URL logout
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, AuthenticationConfiguration authConfig) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService) // Sử dụng CustomUserDetailsService
                .passwordEncoder(passwordEncoder()) // Sử dụng PasswordEncoder để so sánh mật khẩu
                .and()
                .build();
    }

}
