package com.pms.api.common.persistence;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T> {

  int getCurrval(@Param(value="seq")String seq);

  int getNextval(@Param(value="seq")String seq);

  /**
   * 获取单条数据
   */
  public T get(String id);

  /**
   * 获取单条数据
   */
  public T get(T entity);

  /**
   * 查询数据列表
   */
  public List<T> findList(T entity);

  /**
   * 插入数据
   */
  public int insert(T entity);

  /**
   * 更新数据
   */
  public int update(T entity);

  /**
   * 删除数据（一般为逻辑删除，更新del_flag字段为1）
   */
  public int delete(T entity);

  public int delete(String id);

}
