package com.pms.api.sys.menu;

import com.pms.api.common.web.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("menu")
public class MenuController {
  private MenuService service;

  MenuController(MenuService service) {
    this.service= service;
  }

  @ResponseBody
  @PostMapping(value = "list")
  public ResponseResult findList(@RequestBody Menu menu) {
    ResponseResult result= ResponseResult.defaultSuccess();
    result.setData(service.findList(menu));
    return result;
  }

  @ResponseBody
  @GetMapping(value = "/{id}")
  public ResponseResult get(@PathVariable("id") String id) {
    ResponseResult result= ResponseResult.defaultSuccess();
    result.setData(service.get(id));
    return result;
  }

}
