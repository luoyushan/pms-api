package com.pms.api.sys.menu;


import com.pms.api.common.persistence.BaseEntity;

public class Menu extends BaseEntity<Menu> {
  private String name;
  private String title;
  private String remarks;
  private String pid;
  private long sort;
  private String href;
  private String target;
  private String icon;
  private String isShow;
  private String permission;


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


  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }


  public long getSort() {
    return sort;
  }

  public void setSort(long sort) {
    this.sort = sort;
  }


  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }


  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }


  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }


  public String getIsShow() {
    return isShow;
  }

  public void setIsShow(String isShow) {
    this.isShow = isShow;
  }


  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }

}
