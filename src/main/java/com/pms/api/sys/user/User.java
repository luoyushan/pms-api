package com.pms.api.sys.user;

import com.pms.api.common.persistence.Entity;
import java.util.Date;

public class User extends Entity<User> {
  private String companyId;	// 归属公司
  private String deptId;	// 归属部门
  private String username;// 登录名
  private String password;// 密码
  private String no;		// 工号
  private String sex;	//性别
  private String email;	// 邮箱
  private String phone;	// 电话
  private String mobile;	// 手机
  private String loginIp;	// 最后登陆IP
  private Date loginDate;	// 最后登陆日期
  private String loginFlag;	// 是否允许登陆
  private String photo;	// 头像

  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  public String getDeptId() {
    return deptId;
  }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getLoginIp() {
    return loginIp;
  }

  public void setLoginIp(String loginIp) {
    this.loginIp = loginIp;
  }

  public Date getLoginDate() {
    return loginDate;
  }

  public void setLoginDate(Date loginDate) {
    this.loginDate = loginDate;
  }

  public String getLoginFlag() {
    return loginFlag;
  }

  public void setLoginFlag(String loginFlag) {
    this.loginFlag = loginFlag;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }
}

