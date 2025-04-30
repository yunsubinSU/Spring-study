package com.example.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.example.app.config.auth.PrincipalDetailsService;
import com.example.app.config.auth.exceptionHandler.CustomAccessDeniedHandler;
import com.example.app.config.auth.exceptionHandler.CustomAuthenticationEntryPoint;
import com.example.app.config.auth.loginHandler.CustomLoginFailureHandler;
import com.example.app.config.auth.loginHandler.CustomLoginSuccessHandler;
import com.example.app.config.auth.logoutHandler.CustomLogoutHandler;
import com.example.app.config.auth.logoutHandler.CustomLogoutSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PrincipalDetailsService principalDetailsService;

	@Autowired
	private DataSource dataSource3;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//CSRF 비활성화 (로그아웃)
		http.csrf().disable(); //+csrf 토큰 값 확인 x, get/logout 처리 가능
		
		//쿠키 형태로 전달하는 방법 취약해질 가능성이 있음
//		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
		
		//권한체크
		http.authorizeRequests()
			//나머지 요청은 인증이 필요하다라는 것을 표현하는중
			//로그인을 추가하면 리다렉션접속류 때문에 뜨는 오류를 해결할 수 있음
			.antMatchers("/","/join","/login").permitAll()
			.antMatchers("/user").hasRole("USER")
			.antMatchers("/manager").hasRole("MANAGER")
			.antMatchers("/admin").hasRole("ADMIN")
			.anyRequest()
//			.permitAll();
			.authenticated();
		//로그인
		http.formLogin()
			.loginPage("/login")
			.permitAll()
			.successHandler(new CustomLoginSuccessHandler()) //login Handler 안에 customloginsuccess와 관련되어 있음
			.failureHandler(new CustomLoginFailureHandler()); //인증 실패시
		//로그아웃
		http.logout()
			.permitAll()
			.addLogoutHandler(new CustomLogoutHandler())
			.logoutSuccessHandler(new CustomLogoutSuccessHandler());
		//예외처리
		http.exceptionHandling()
			.authenticationEntryPoint(new CustomAuthenticationEntryPoint()) //인증이 안된 계정이 인증이 필요한 위치를 찾을때 (미인증 계정 예외처리)
			.accessDeniedHandler(new CustomAccessDeniedHandler()); //권한이 부족할 경우 예외처리
		
		//REMEMBER-ME
		http.rememberMe()
			.key("rememberMeKey") 
			.rememberMeParameter("remember-me")
			.alwaysRemember(false) //체크를 하지 않아도 유지를 할거냐
			.tokenValiditySeconds(60*60) //로그인 상태 유지 시간
			.tokenRepository(tokenRepository());
		}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("user")
//			.password(passwordEncoder.encode("1234"))
//			.roles("USER");
//		
//		auth.inMemoryAuthentication()
//			.withUser("manager")
//			.password(passwordEncoder.encode("1234"))
//			.roles("MANAGER");
//		
//		auth.inMemoryAuthentication()
//			.withUser("admin")
//			.password(passwordEncoder.encode("1234"))
//			.roles("ADMIN");
		
			auth.userDetailsService(principalDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public PersistentTokenRepository tokenRepository(){
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource3);
		return repo;
	}
	
	
	
}