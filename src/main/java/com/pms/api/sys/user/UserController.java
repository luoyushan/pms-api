package com.pms.api.sys.user;

import com.pms.api.common.security.CookieUtils;
import com.pms.api.common.web.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
@RestController
@RequestMapping("/account")
public class UserController {
  @Autowired
  private UserService userService;

  @RequestMapping(value = "getUsers")
  public List<User> getUsers(String id) {
    User user = new User();
    user.setName("luo");
    return userService.findList(user);
  }


  @ResponseBody
  @PostMapping("login")
  public ResponseResult login(@RequestBody User user) {
    if(user.getPassword() == null || "".equals(user.getPassword())) {
      return ResponseResult.failure("请输入密码");
    }
    User loadUser = userService.getUserByUsername(user.getUsername());
    if (loadUser == null) {
      return ResponseResult.failure("找不到该用户");
    }
    if (!user.getPassword().equals(loadUser.getPassword())) {
      return ResponseResult.failure("密码错误");
    }
    ResponseResult result = ResponseResult.defaultSuccess();
    result.setData(loadUser);
    return result;
  }


  final String TOKENX = "1234";

  @ResponseBody
  @PostMapping("login2")
  public String queryPoolList(@RequestBody User user, HttpServletResponse response,
      @CookieValue(value = "token", required = false) String token) {
    if (token == null) {
      CookieUtils.writeCookie(response, "token", TOKENX);
    } else {
      System.out.println(token);
    }
    //返回前台
    return "成功";
  }
}
