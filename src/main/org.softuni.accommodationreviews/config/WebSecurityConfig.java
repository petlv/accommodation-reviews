package org.softuni.accommodationreviews.config;

import org.softuni.accommodationreviews.areas.users.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PERMITTED_ROUTES = {
            "/", "/register", "/map", "/images", "/ajax/**", "/accommodation/**", "/comment/**"
    };

    private final UserServiceImpl userDetailsService;

    @Autowired
    public WebSecurityConfig(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/ammap/**", "/json/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(PERMITTED_ROUTES).permitAll()
                //.antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/home")
                .failureUrl("/error2")
        .and()
                .rememberMe()
                .rememberMeParameter("remember")
                .rememberMeCookieName("rememberMeCookie")
                .key("48433e39-e610-4a2c-926c-f86d46f5360a")
                .tokenValiditySeconds(100)
                .userDetailsService(this.userDetailsService)
        .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();
    }
}
