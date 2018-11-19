package com.pms.api.sys.role;

import com.pms.api.common.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<RoleMapper, Role> {

  @Autowired
  RoleService(RoleMapper mapper) {
  }
}
