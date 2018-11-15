package com.pms.api.common.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseService<D extends BaseDao<T>, T extends Entity<T>> {
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private D dao;

  /**
   * 获取单条数据
   */
  public T get(String id) {
    return dao.get(id);
  }

  /**
   * 获取单条数据
   */
  public T get(T entity) {
    return dao.get(entity);
  }

  /**
   * 查询列表数据
   */
  public List<T> findList(T entity) {
    return dao.findList(entity);
  }

  /**
   * 查询分页数据
   */
  public Page<T> findPage(Page<T> page, T entity) {
    entity.setPage(page);
    page.setList(dao.findList(entity));
    return page;
  }

  /**
   * 保存数据（插入或更新）
   */
  @Transactional(readOnly = false)
  public void save(T entity) {
    if (entity.isNewRecord()){
      entity.preInsert();
      dao.insert(entity);
    }else{
      entity.preUpdate();
      dao.update(entity);
    }
  }

  /**
   * 删除数据
   */
  @Transactional(readOnly = false)
  public void delete(T entity) {
    dao.delete(entity);
  }

  /**
   * 删除数据
   */
  @Transactional(readOnly = false)
  public void delete(String id) {
    dao.delete(id);
  }


  public int getCurrval(String seq){
    return dao.getCurrval(seq);
  }

  public int getNextval(String seq){
    return dao.getNextval(seq);
  }

}
