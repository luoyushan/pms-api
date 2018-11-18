package com.pms.api.sys.user;

import com.pms.api.common.web.ResponseResult;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("user")
public class UserController {
  private UserService userService;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  UserController(UserService userService) {
    this.userService = userService;
  }
  @ResponseBody
  @PostMapping(value = "getUsers")
  public List<User> getUsers(@RequestBody User user) {
    return userService.findList(user);
  }


  @ResponseBody
  @PostMapping("login")
  public ResponseResult login(@RequestBody User user, HttpServletResponse httpResponse) throws Exception{
    User loadUser = userService.getUserByUsername(user.getUsername());
    if (loadUser == null) {
     return ResponseResult.failure("用户不存在");
    }
    String token = userService.login(user.getUsername(), user.getPassword());
    if (token != null && !token.equals("")) {
      loadUser.setToken(token);
      ResponseResult result = ResponseResult.defaultSuccess();
      result.setData(loadUser);
      return result;
    } else {
      return ResponseResult.failure("密码错误");
    }
  }

  @ResponseBody
  @PostMapping("register")
  public ResponseResult register(@RequestBody User user, HttpServletResponse httpResponse) throws Exception{
    ResponseResult result = ResponseResult.defaultSuccess();
    String data =  userService.register(user);
    result.setData(data);
    return ResponseResult.defaultSuccess();
  }


  /**
   * 刷新密钥
   *
   * @param authorization 原密钥
   * @return 新密钥
   * @throws AuthenticationException 错误信息
   */
  @GetMapping(value = "/refreshToken")
  public String refreshToken(@RequestHeader String authorization) throws AuthenticationException {
    return userService.refreshToken(authorization);
  }
}
