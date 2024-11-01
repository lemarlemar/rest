package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.itmentor.spring.boot_security.demo.service.CustomUserService;
import ru.itmentor.spring.boot_security.demo.service.UsersServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    private final SuccessUserHandler successUserHandler;

//    private final CustomUserService customUserService;


//    public WebSecurityConfig(SuccessUserHandler successUserHandler, CustomUserService customUserService) {
//       this.successUserHandler = successUserHandler;
//        this.customUserService = customUserService;
//    }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserService).passwordEncoder(encoder());
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN") // Доступ к /admin/** только для роли ADMIN
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Доступ к /user/** для USER и ADMIN
                .antMatchers("/", "/index").permitAll() // Доступ к / и /index для всех
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(new SuccessUserHandler()) // Перенаправление после успешной авторизации
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}