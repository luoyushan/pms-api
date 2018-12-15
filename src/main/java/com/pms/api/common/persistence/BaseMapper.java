package com.pms.api.common.persistence;

import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public interface BaseMapper<T> {

  T get(String id);

  List<T> findList(T entity);

  int insert(T entity);

  int update(T entity);

  int delete(String id);
}
