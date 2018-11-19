package com.pms.api.sys.role;

import com.pms.api.common.web.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("role")
public class RoleController {
  private RoleService service;

  RoleController(RoleService service) {
    this.service= service;
  }

  @ResponseBody
  @PostMapping(value = { "list", "" })
  public ResponseResult getRoles(@RequestBody Role role) {
    ResponseResult result= ResponseResult.defaultSuccess();
    result.setData(service.findList(role));
    return result;
  }

  @ResponseBody
  @GetMapping(value = "${id}")
  public ResponseResult getRole(@PathVariable("id") String id) {
    ResponseResult result= ResponseResult.defaultSuccess();
    result.setData(service.get(id));
    return result;
  }

}
