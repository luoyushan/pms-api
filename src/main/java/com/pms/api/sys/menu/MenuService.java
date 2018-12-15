package com.pms.api.sys.menu;

import com.pms.api.common.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService implements BaseService<Menu> {

  @Override
  public Menu get(String id) {
    return null;
  }

  @Override
  public List<Menu> findList(Menu entity) {
    return null;
  }

  @Override
  public void save(Menu entity) {

  }

  @Override
  public void delete(String id) {

  }
}
