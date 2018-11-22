package com.pms.api.common.security;

import com.pms.api.sys.user.User;
import com.pms.api.sys.user.UserMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户验证方法
 *
 * @author luoyu
 * Created on 2018/11/22 9:28.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

  private UserMapper userMapper;

  public JwtUserDetailsServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userMapper.getUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    } else {
      return new JwtUser(user.getUsername(), user.getPassword(), user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
  }

}
