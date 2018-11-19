package com.pms.api.sys.menu;

import com.pms.api.common.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService extends BaseService<MenuMapper, Menu> {

  @Autowired
  MenuService(MenuMapper mapper) {
  }
}
