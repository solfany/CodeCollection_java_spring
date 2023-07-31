package com.study.springboot.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .antMatchers("/guest/**").permitAll()
                .antMatchers("/member/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();
 
        http.formLogin()
                .loginPage("/loginForm")             // default : /login
                .loginProcessingUrl("/j_spring_security_check")
                //.failureUrl("/loginForm?error")    // default : /login?error
                .failureHandler(authenticationFailureHandler)
                //.defaultSuccessUrl("/")
                .usernameParameter("j_username")     // default : j_username
                .passwordParameter("j_password")     // default : j_password
                .permitAll();
 
        http.logout()
                .logoutUrl("/logout")                // default
                .logoutSuccessUrl("/")
                .permitAll();
        
        // 개발중에는 꺼 놓는다.
        http.csrf().disable();
    }
 
//  @Autowired
//  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//      auth.inMemoryAuthentication()
//          .withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
//          .and()
//          .withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
//  }
  
    @Autowired
    private DataSource dataSource;
  
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        System.out.println(passwordEncoder().encode("123"));

        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select name as userName, password, enabled"
                                + " from user_list where name = ?")
            .authoritiesByUsernameQuery("select name as userName, authority "
                                      + " from user_list where name = ?")
            .passwordEncoder(new BCryptPasswordEncoder());
    }
  
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
