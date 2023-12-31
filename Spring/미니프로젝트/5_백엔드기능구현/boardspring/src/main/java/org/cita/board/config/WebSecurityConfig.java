package org.cita.board.config;

import org.cita.board.filter.JwtAuthencationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired JwtAuthencationFilter jwtAuthencationFilter;
	
	@Bean
	protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
		//cors정책 (현재는 Application에서 작업을 해둿으므로 기본 설정사용)
		.cors().and()
		//csrf 대책 (현재는 CSRF에 대한 대책을 비활성화)
		.csrf().disable()
		//Basic인증 (현재는 Bearer token 인증방법을 사용하기 때문에 비활성화)
		.httpBasic().disable()
		//세션 기반 인증( 현재는 Session기반인증을 사용하지 않기 때문에 상태를 없앰)
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		// '.' , '/api/auth' 모듈에 대해서는 모두 허용(인증을 하지않고 사용 가능하게 함)
		.authorizeHttpRequests().antMatchers("/","/api/auth/**").permitAll()
		//나머지 Request에 대해서는 모두 인증된 사용자만 사용가능하게 함
		.anyRequest().authenticated();
		
	httpSecurity.addFilterBefore(jwtAuthencationFilter, UsernamePasswordAuthenticationFilter.class);
	
	return httpSecurity.build();
	}	
}
