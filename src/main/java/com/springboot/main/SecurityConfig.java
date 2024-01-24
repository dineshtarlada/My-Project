package com.springboot.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.main.model.Hr;
import com.springboot.main.service.UserService;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getProvider());
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		/*.antMatchers(HttpMethod.POST,"/employees/address/add/{hid}/{mid}").hasAuthority("HR")
				.antMatchers(HttpMethod.POST,"/transferpoints/employeetoemployee/{eid1}/{eid2}/{points}").hasAuthority("EMPLOYEE")
				
				.antMatchers(HttpMethod.GET,"/employees/purchasedproducts/all/{eid}").hasAuthority("EMPLOYEE")
				.antMatchers(HttpMethod.DELETE,"/purchasedproducts/delete/{cartid}").hasAuthority("EMPLOYEE")
				.antMatchers(HttpMethod.GET,"/employees/purchasedproducts/all/{eid}").hasAuthority("EMPLOYEE")
				.antMatchers(HttpMethod.GET,"/employees/user/{uid}").hasAuthority("EMPLOYEE")
				.antMatchers(HttpMethod.GET,"/employees/all/history/{uid}").hasAuthority("EMPLOYEE")
				.antMatchers(HttpMethod.GET,"/transaction/employee/all/{eid}").hasAuthority("EMPLOYEE")
				.antMatchers(HttpMethod.DELETE,"/employees/history/delete/{historyId}").hasAuthority("EMPLOYEE")
				.antMatchers(HttpMethod.DELETE,"/transaction/delete/{id}").hasAnyAuthority("EMPLOYEE","MANAGER","HR")
				.antMatchers(HttpMethod.GET,"/employees/leaderboard").hasAuthority("EMPLOYEE")
				.antMatchers(HttpMethod.POST,"/purchasedproducts/add/{eid}").hasAuthority("EMPLOYEE")
				.antMatchers(HttpMethod.GET,"/product/search/{qStr}").hasAnyAuthority("EMPLOYEE","MANAGER","HR")
				.antMatchers(HttpMethod.GET,"/product/all").hasAnyAuthority("EMPLOYEE","MANAGER","HR")
				.antMatchers(HttpMethod.PUT,"/employees/update/{id}").hasAuthority("EMPLOYEE")
				.antMatchers(HttpMethod.GET,"/employees/all/withoutThisUserId/{uid}").hasAuthority("EMPLOYEE")
				.antMatchers(HttpMethod.GET,"/managers/all").hasAuthority("MANAGER")
				.antMatchers(HttpMethod.POST,"/product/add/{hid}").hasAuthority("HR")
				.antMatchers(HttpMethod.GET,"/hr/allemployees/{hid}").hasAuthority("HR")
				.antMatchers(HttpMethod.DELETE,"/employees/delete/{id}").hasAuthority("HR")
				.antMatchers(HttpMethod.GET,"/managers/employees/all/{muid}").hasAuthority("MANAGER")
				.antMatchers(HttpMethod.GET,"/transaction/manager/all/{mid}").hasAuthority("MANAGER")
	*/
		.antMatchers(HttpMethod.GET,"/user/login").authenticated()
		.anyRequest().permitAll()
		
		.and().httpBasic()
		.and()
		.csrf().disable()
		.cors().disable();
		
	}

	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	public DaoAuthenticationProvider getProvider() {
		System.out.println("getprovide called............");
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		//also,i want spring to know that i have encrypted password in DB
		dao.setPasswordEncoder(getEncoder());
		//from here..iwnt spring to go to my db and fetch users
		dao.setUserDetailsService(userService);//UserDetailsService:UserService
		return dao;
	}

	@Bean
	public Logger getLogger() {
		return LoggerFactory.getLogger("records");
	}
}
