package com.pms.api.sys.area;

import com.pms.api.common.web.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("area")
public class AreaController {
  private AreaService service;

  AreaController(AreaService service) {
    this.service= service;
  }

  @ResponseBody
  @PostMapping(value = { "list", "" })
  public ResponseResult getRoles(@RequestBody Area area) {
    ResponseResult result= ResponseResult.defaultSuccess();
    result.setData(service.findList(area));
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
