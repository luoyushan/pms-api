package com.pms.api.common.persistence;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pms.api.sys.user.User;
import org.hibernate.validator.constraints.Length;

public abstract class Entity<T> extends BaseEntity<T> {
  protected String name;	// 名称
  protected String title;	// 标题
  protected String remarks;	// 备注
  protected User createBy;	// 创建者
  protected Date createDate;	// 创建日期
  protected User updateBy;	// 更新者
  protected Date updateDate;	// 更新日期
  protected String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）

  public Entity() {
    super();
    this.delFlag = DEL_FLAG_NORMAL;
  }

  public Entity(String id) {
    super(id);
  }


  public static String uuid() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
  /**
   * 插入之前执行方法，需要手动调用
   */
  public void preInsert(){
    // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
    if (!this.isNewRecord){
      setId(uuid());
    }
    User user = this.getCurrentUser();
    if (user != null && "".equals(user.getId())){
      this.updateBy = user;
      this.createBy = user;
    } else {
      this.updateBy = new User("1");
      this.createBy = new User("1");
    }
    this.updateDate = new Date();
    this.createDate = this.updateDate;
  }


  /**
   * 更新之前执行方法，需要手动调用
   */
  public void preUpdate(){
    User user = this.getCurrentUser();
    if (user != null && "".equals(user.getId())){
      this.updateBy = user;
    }
    this.updateDate = new Date();
  }

  private User getCurrentUser() {
    return new User("1");
  }
  @Length(min=0, max=255)
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @JsonIgnore
  public User getCreateBy() {
    return createBy;
  }

  public void setCreateBy(User createBy) {
    this.createBy = createBy;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  @JsonIgnore
  public User getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(User updateBy) {
    this.updateBy = updateBy;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @JsonIgnore
  @Length(min=1, max=1)
  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }
}
