package com.teksocial.application.config;

import com.teksocial.application.filter.JwtEntryRequest;
import com.teksocial.application.filter.JwtFilter;
import com.teksocial.application.services.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.teksocial.application.services.userService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class securityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtEntryRequest jwtEntryRequest;
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private userService user_service;

    @Autowired
    private Token token;




    @Override
    @Bean(name= BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }


    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(user_service).passwordEncoder(passwordEncoder());
    }

    @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

            httpSecurity
                    .csrf().disable()
                    .authorizeRequests().antMatchers("/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .logout()
                    .logoutUrl("/api/logout") // specify the logout URL
                    .logoutSuccessUrl("/") // specify the URL to redirect to after logout
                    .invalidateHttpSession(true) // invalidate the HTTP session
                    .deleteCookies("JSESSIONID") // delete the JSESSIONID cookie
                    .permitAll()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(jwtEntryRequest).and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        config.addAllowedOrigin("http://localhost:8080/");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
            httpSecurity.cors().configurationSource(request -> config);
        }




}
