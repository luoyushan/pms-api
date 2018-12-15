package com.pms.api.common.persistence;

import com.pms.Global;
import com.pms.api.sys.user.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("WeakerAccess")
public class BaseEntity<T> implements Serializable {

  // 删除标记（0：正常；1：删除；2：审核；）
  public static final String DEL_FLAG_NORMAL = "0";
  public static final String DEL_FLAG_DELETE = "1";
  public static final String DEL_FLAG_AUDIT = "2";

  protected static final Long serialVersionUID = 1L;
  protected String id;
  protected String name;	// 名称
  protected String remarks;	// 备注
  protected User createBy;	// 创建者
  protected Date createDate;	// 创建日期
  protected User updateBy;	// 更新者
  protected Date updateDate;	// 更新日期
  private String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）

  public BaseEntity(String id) {
    this();
    this.id = id;
  }

  public BaseEntity() {

  }

  private static String uuid() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
  /**
   * 插入之前执行方法，需要手动调用
   */
  public void preInsert(){
    setId(uuid());
  }

  /**
   * 更新之前执行方法，需要手动调用
   */
  public void preUpdate(){
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public User getCreateBy() {
    return createBy;
  }

  public void setCreateBy(User createBy) {
    this.createBy = createBy;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public User getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(User updateBy) {
    this.updateBy = updateBy;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }
}
