package com.pms.api.common.persistence;

import com.pms.Global;

public interface IPage<T> {
  int pageNo = 1; // 当前页码
  int pageSize = Integer.valueOf(Global.getConfig("pageSize")); // 页面大小，设置为“-1”表示不进行分页（分页无效）

  long count=0;// 总记录数，设置为“-1”表示不查询总数

  int first=0;// 首页索引
  int last=0;// 尾页索引
  int prev=0;// 上一页索引
  int next=0;// 下一页索引

  boolean firstPage=false;//是否是第一页
  boolean lastPage=false;//是否是最后一页


  public void setPageNo(int pageNo);
  public int getPageNo();

  public void setPageSize(int pageSize);
  public int getPageSize();

  public void setCount(long count);
  public long getCount();

  public int getFirstResult();

  public int getMaxResults();

  public void initialize();

}
