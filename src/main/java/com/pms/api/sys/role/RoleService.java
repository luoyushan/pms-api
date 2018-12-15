package com.pms.api.sys.role;

import com.pms.api.common.persistence.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements BaseService<Role> {

  @Override
  public Role get(String id) {
    return null;
  }

  @Override
  public List<Role> findList(Role entity) {
    return null;
  }

  @Override
  public void save(Role entity) {

  }

  @Override
  public void delete(String id) {

  }
}
