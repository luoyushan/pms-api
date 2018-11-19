package com.pms.api.sys.office;

import com.pms.api.common.web.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("office")
public class OfficeController {
  private OfficeService service;

  OfficeController(OfficeService service) {
    this.service= service;
  }

  @ResponseBody
  @PostMapping(value = { "list", "" })
  public ResponseResult getRoles(@RequestBody Office office) {
    ResponseResult result= ResponseResult.defaultSuccess();
    result.setData(service.findList(office));
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
