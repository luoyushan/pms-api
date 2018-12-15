package com.pms.api.sys.user;

import com.pms.api.common.persistence.BaseService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements BaseService<User> {

  @Override
  public User get(String id) {
    return null;
  }

  @Override
  public List<User> findList(User entity) {
    return null;
  }

  @Override
  public void save(User entity) {

  }

  @Override
  public void delete(String id) {

  }
}
