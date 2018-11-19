package com.pms.api.sys.user;

import com.pms.api.common.persistence.BaseService;
import com.pms.api.common.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class UserService extends BaseService<UserMapper, User> {
  private UserMapper mapper;
  private AuthenticationManager authenticationManager;
  private UserDetailsService userDetailsService;
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  UserService(
      UserMapper mapper,
      AuthenticationManager authenticationManager,
      @Qualifier("jwtUserDetailsServiceImpl") UserDetailsService userDetailsService,
      JwtTokenUtil jwtTokenUtil
  ) {
    this.mapper = mapper;
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtTokenUtil = jwtTokenUtil;
  }

 User getUserByUsername(String username) {
   return mapper.getUserByUsername(username);
  }
  public void save(User user) {
    super.save(user);
  }

  String login(String username, String password) {
    try {
      UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
      Authentication authentication = authenticationManager.authenticate(upToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      return jwtTokenUtil.generateToken(userDetails);
    } catch (Exception e) {
      return "";
    }
  }


  String register(User user) {
    String username = user.getUsername();
    if (mapper.getUserByUsername(username) != null) {
      return "用户已存在";
    }
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String rawPassword = user.getPassword();
    user.setPassword(encoder.encode(rawPassword));
    List<String> roles = new ArrayList<>();
    roles.add("ROLE_USER");
    user.setRoles(roles);
    user.preInsert();
    mapper.insert(user);
    return "success";
  }

  String refreshToken(String oldToken) {
    String token = oldToken.substring("Bearer ".length());
    if (!jwtTokenUtil.isTokenExpired(token)) {
      return jwtTokenUtil.refreshToken(token);
    }
    return "error";
  }
}
