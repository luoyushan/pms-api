package com.pms.api.sys.office;

import com.pms.api.common.persistence.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService implements BaseService<Office> {


  @Override
  public Office get(String id) {
    return null;
  }

  @Override
  public List<Office> findList(Office entity) {
    return null;
  }

  @Override
  public void save(Office entity) {

  }

  @Override
  public void delete(String id) {

  }
}
