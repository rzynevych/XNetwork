package com.rz.xnetwork.security;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.rz.xnetwork.config.Config;
import com.rz.xnetwork.models.AppUser;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String CORS_ORIGIN = Config.SERVER_URL;

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.cors();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers("/", "welcome", "/login", "/register", "/authCheck").permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .anyRequest().hasRole("USER");
    
        http.authorizeRequests()
            .and()
                .formLogin()
                .loginProcessingUrl("/auth")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(successHandler())
                .failureHandler(failureHandler())
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler())
            .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint());
    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, 
                    HttpServletResponse httpServletResponse, 
                    Authentication authentication) throws IOException {
                
                httpServletResponse.setStatus(200);
                httpServletResponse.addHeader("Access-Control-Allow-Origin", CORS_ORIGIN);
                httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
                httpServletResponse.addHeader("Content-Type", "application/json");
                httpServletResponse.getWriter().append("{\"status\" : true}");
            }
        };
    }

    private AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, 
                    HttpServletResponse httpServletResponse, 
                    Authentication authentication) throws IOException, ServletException {
                
                httpServletResponse.setStatus(200);
                UserInfo userInfo = (UserInfo) authentication.getPrincipal();
                AppUser user = userInfo.getAppUser();
                user.setLastLogin(new Date());
                
                httpServletResponse.addHeader("Access-Control-Allow-Origin", CORS_ORIGIN);
                httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
                httpServletResponse.addHeader("Content-Type", "application/json");
                httpServletResponse.getWriter().append("{\"status\" : true}");
            }
        };
    }
    
    private AuthenticationFailureHandler failureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, 
                    HttpServletResponse httpServletResponse, 
                    AuthenticationException e) throws IOException, ServletException {
                
                httpServletResponse.getWriter().append("Authentication failure");
                httpServletResponse.setStatus(401);
            }
        };
    }
    
    private AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest httpServletRequest, 
                    HttpServletResponse httpServletResponse, 
                    AccessDeniedException e) throws IOException, ServletException {
                
                httpServletResponse.addHeader("Access-Control-Allow-Origin", CORS_ORIGIN);
                httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
                httpServletResponse.getWriter().append("Access denied");
                httpServletResponse.setStatus(403);
            }
        };
    }
    
    private AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest httpServletRequest, 
                    HttpServletResponse httpServletResponse, 
                    AuthenticationException e) throws IOException, ServletException {
            
                httpServletResponse.addHeader("Access-Control-Allow-Origin", CORS_ORIGIN);
                httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
                httpServletResponse.getWriter().append("Not authenticated");
                httpServletResponse.setStatus(401);
            }
        };
    }
}
