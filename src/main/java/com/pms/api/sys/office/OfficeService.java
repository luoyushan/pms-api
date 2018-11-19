package com.pms.api.sys.office;

import com.pms.api.common.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeService extends BaseService<OfficeMapper, Office> {

  @Autowired
  OfficeService(OfficeMapper mapper) {
  }
}
