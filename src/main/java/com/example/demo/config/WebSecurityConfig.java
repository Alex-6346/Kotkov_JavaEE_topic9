package com.example.demo.config;

import com.example.demo.entity.Permission;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


import lombok.RequiredArgsConstructor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/save", "/save/*").hasAuthority(Permission.VIEW_ADMIN.name())
                .antMatchers("/favorite*", "/save/*").hasAuthority(Permission.VIEW_CATALOG.name())
                .antMatchers("/search").hasAnyAuthority(Permission.VIEW_CATALOG.name())
                .antMatchers("/book","/book/*").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll()
                .defaultSuccessUrl("/book", true)
                .and()
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/book")
                .invalidateHttpSession(true)        // set invalidation state when logout
                .deleteCookies("JSESSIONID");
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new MyUserDetailsService(userRepository);
    }
}