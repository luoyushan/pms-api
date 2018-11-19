package com.pms.api.sys.area;

import com.pms.api.common.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaService extends BaseService<AreaMapper, Area> {

  @Autowired
  AreaService(AreaMapper mapper) {
  }
}
