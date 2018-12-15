package com.pms.api.common.persistence;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface BaseService<T> {

  public T get(String id);


  public List<T> findList(T entity);

  @Transactional(readOnly = false)
  public void save(T entity);

  @Transactional(readOnly = false)
  public void delete(String id);

}
