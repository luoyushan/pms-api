package com.pms.api.common.security;

import com.pms.api.sys.user.User;
import com.pms.api.sys.user.UserMapper;
import com.pms.api.sys.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author hackyo
 * Created on 2017/12/8 9:18.
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
    List<String> roles = new ArrayList<>();
    roles.add("user");
    user.setRoles(roles);
    if (user == null) {
      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    } else {
      return new JwtUser(user.getUsername(), user.getPassword(), user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
  }

}
