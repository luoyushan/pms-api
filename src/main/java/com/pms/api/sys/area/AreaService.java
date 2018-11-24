package com.pms.api.sys.area;

import com.pms.api.common.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AreaService extends BaseService<AreaMapper, Area> {

  @Autowired
  AreaService(AreaMapper mapper) {
  }

  @Transactional(readOnly = false)
  public void save(Area area) {
    area.setNewRecord(true);
    super.save(area);
  }
}
