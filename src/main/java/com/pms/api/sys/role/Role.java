package com.pms.api.sys.role;

import com.pms.api.common.persistence.BaseEntity;

public class Role extends BaseEntity<Role> {

  private String name;
  private String title;
  private String remarks;
  private String ename;
  private String type;
  private String dataScope;
  private String useable;

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


  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }


  public String getEname() {
    return ename;
  }

  public void setEname(String ename) {
    this.ename = ename;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getDataScope() {
    return dataScope;
  }

  public void setDataScope(String dataScope) {
    this.dataScope = dataScope;
  }


  public String getUseable() {
    return useable;
  }

  public void setUseable(String useable) {
    this.useable = useable;
  }

}
