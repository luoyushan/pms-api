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
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

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

  /**
   *  EDS的加密解密代码
   */
  private static final byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128, -65 };
  private static final String ENCRYPT = "encrypt"; //加密
  private static final String DECRYPT = "decrypt"; // 解密

  private static String passwordGuard(String data, String type) {
    String password = "";
    try {
      // DES算法要求有一个可信任的随机数源
      SecureRandom sr = new SecureRandom();
      DESKeySpec deskey = new DESKeySpec(DES_KEY);
      // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey key = keyFactory.generateSecret(deskey);
      // 解析对象
      Cipher cipher = Cipher.getInstance("DES");

      // 解析，并把字节数组编码成字符串
      if (ENCRYPT.equals(type)) {
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        password = new BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
      }
      if (DECRYPT.equals(type)) {
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
        password = new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(data)));
      }
    } catch (Exception e) {
      // log.error("解析错误，错误信息：", e);
      throw new RuntimeException("密码解析错误，错误信息：", e);
    }
    return password;
  }
}
