package com.pms.api.common.config;

import com.pms.api.common.web.EntryPointUnauthorizedHandler;
import com.pms.api.common.filter.JwtAuthenticationTokenFilter;
import com.pms.api.common.web.RestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 安全模块配置
 *
 * @author luoyu
 * Created on 2018/11/22 9:28
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private UserDetailsService userDetailsService;
  private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
  private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
  private RestAccessDeniedHandler restAccessDeniedHandler;
  private PasswordEncoder passwordEncoder;

  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Autowired
  public WebSecurityConfig(
      @Qualifier("jwtUserDetailsServiceImpl")
      UserDetailsService userDetailsService,
      JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter,
      EntryPointUnauthorizedHandler entryPointUnauthorizedHandler,
      RestAccessDeniedHandler restAccessDeniedHandler
  ) {
    this.userDetailsService = userDetailsService;
    this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
    this.entryPointUnauthorizedHandler = entryPointUnauthorizedHandler;
    this.restAccessDeniedHandler = restAccessDeniedHandler;
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  @Autowired
  public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .cors().and()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().authorizeRequests()
        .antMatchers("/user/login").permitAll()
        .antMatchers("/user/register").permitAll()
        .anyRequest().authenticated()
        .and().headers().cacheControl();

    httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    httpSecurity.exceptionHandling().authenticationEntryPoint(entryPointUnauthorizedHandler).accessDeniedHandler(restAccessDeniedHandler);
  }

}
