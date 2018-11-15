package com.pms.api.common.persistence;

import com.pms.Global;

import java.io.Serializable;
import java.util.Map;

public class BaseEntity<T> implements Serializable {
  private static final Long serialVersionUID = 1L;

  /**
   * 实体编号（唯一标识）
   */
  protected String id;

  /**
   * 当前实体分页对象
   */
  protected Page<T> page;

  /**
   * 自定义SQL（SQL标识，SQL内容）
   */
  protected Map<String, String> sqlMap;

  /**
   * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
   * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
   */
  protected boolean isNewRecord = false;

  /**
   * 全局变量对象
   */
  public Global getGlobal() {
    return Global.getGlob();
  }

  public BaseEntity() {
  }

  public BaseEntity(String id) {
    this();
    this.id = id;
  }

  @Override
  public boolean equals(Object obj) {
    if (null == obj) {
      return false;
    }
    if (this == obj) {
      return false;
    }
    if (!getClass().equals(obj.getClass())) {
      return false;
    }
    BaseEntity<?> that = (BaseEntity<?>) obj;
    if (this.getId() ==null || that.getId() == null) {
      return false;
    }
    return this.getId().equals(that.getId());
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Page<T> getPage() {
    return page;
  }

  public void setPage(Page<T> page) {
    this.page = page;
  }

  public Map<String, String> getSqlMap() {
    return sqlMap;
  }

  public void setSqlMap(Map<String, String> sqlMap) {
    this.sqlMap = sqlMap;
  }

  public boolean isNewRecord() {
    return isNewRecord;
  }

  public void setNewRecord(boolean newRecord) {
    isNewRecord = newRecord;
  }

  /**
   * 删除标记（0：正常；1：删除；2：审核；）
   */
  public static final String DEL_FLAG_NORMAL = "0";
  public static final String DEL_FLAG_DELETE = "1";
  public static final String DEL_FLAG_AUDIT = "2";
}
