package com.library.library;

import com.library.library.models.User;
import com.library.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationManagerBuilder auth;

    @Autowired
    private UserRepository repository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                    .antMatchers("/", "/login", "/registration", "/css/**", "/fonts/**", "/img/**", "/scripts/**")
                    .permitAll()
                .anyRequest().not().access("hasRole('BLOCKED')")
                    .antMatchers("/all/home").access( "hasRole('LIBRARIAN') or hasRole('ADMIN') or hasRole('READER') or hasrole('BLOCKED')")
                    .antMatchers("/readers").access( "hasRole('LIBRARIAN') or hasRole('ADMIN') or hasRole('READER')")
                    .antMatchers("/librarians").access( "hasRole('LIBRARIAN') or hasRole('ADMIN') or hasRole('READER')")
                    .antMatchers("/books").access( "hasRole('LIBRARIAN') or hasRole('ADMIN') or hasRole('READER')")
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username, password, 'true' from users where username=?")
                .authoritiesByUsernameQuery("select username, user_role from users where username=?");
       // User.currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}