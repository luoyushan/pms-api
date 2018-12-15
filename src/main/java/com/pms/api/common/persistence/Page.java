package com.pms.api.common.persistence;

import com.pms.Global;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@SuppressWarnings("FieldCanBeLocal")
public class Page<T> {
  private int pageNo = 1; // 当前页码
  private int pageSize = Integer.valueOf(Global.getConfig("pageSize")); // 页面大小，设置为“-1”表示不进行分页（分页无效）

  private long count;// 总记录数，设置为“-1”表示不查询总数

  private int first;// 首页索引
  private int last;// 尾页索引
  private int prev;// 上一页索引
  private int next;// 下一页索引

  private boolean firstPage;//是否是第一页
  private boolean lastPage;//是否是最后一页

  private List<T> list = new ArrayList<T>();

  private String orderBy = ""; // 标准查询有效， 实例： updatedate desc, name asc

  private String message = ""; // 设置提示消息，显示在“共n条”之后

  public Page() {
    this.pageSize = -1;
  }

  /**
   * 构造方法
   * @param request 传递 repage 参数，来记住页码
   * @param response 用于设置 Cookie，记住页码
   */
  public Page(HttpServletRequest request, HttpServletResponse response){
    this(request, response, -2);
  }

  /**
   * 构造方法
   * @param request 传递 repage 参数，来记住页码
   * @param response 用于设置 Cookie，记住页码
   * @param defaultPageSize 默认分页大小，如果传递 -1 则为不分页，返回所有数据
   */
  public Page(HttpServletRequest request, HttpServletResponse response, int defaultPageSize){

  }

  /**
   * 构造方法
   * @param pageNo 当前页码
   * @param pageSize 分页大小
   */
  public Page(int pageNo, int pageSize) {
    this(pageNo, pageSize, 0);
  }

  /**
   * 构造方法
   * @param pageNo 当前页码
   * @param pageSize 分页大小
   * @param count 数据条数
   */
  public Page(int pageNo, int pageSize, long count) {
    this(pageNo, pageSize, count, new ArrayList<T>());
  }

  /**
   * 构造方法
   * @param pageNo 当前页码
   * @param pageSize 分页大小
   * @param count 数据条数
   * @param list 本页数据对象列表
   */
  public Page(int pageNo, int pageSize, long count, List<T> list) {
    this.setCount(count);
    this.setPageNo(pageNo);
    this.pageSize = pageSize;
    this.list = list;
  }

  /**
   * 初始化参数
   */
  public void initialize(){

    //1
    this.first = 1;

    this.last = (int)(count / (this.pageSize < 1 ? 20 : this.pageSize) + first - 1);

    if (this.count % this.pageSize != 0 || this.last == 0) {
      this.last++;
    }

    if (this.last < this.first) {
      this.last = this.first;
    }

    if (this.pageNo <= 1) {
      this.pageNo = this.first;
      this.firstPage=true;
    }

    if (this.pageNo >= this.last) {
      this.pageNo = this.last;
      this.lastPage=true;
    }

    if (this.pageNo < this.last - 1) {
      this.next = this.pageNo + 1;
    } else {
      this.next = this.last;
    }

    if (this.pageNo > 1) {
      this.prev = this.pageNo - 1;
    } else {
      this.prev = this.first;
    }

  }


  /**
   * 获取分页HTML代码
   * @return String
   */
  public String getHtml(){
    return toString();
  }


  /**
   * 获取设置总数
   * @return count
   */
  public long getCount() {
    return count;
  }

  /**
   * 设置数据总数
   */
  public void setCount(long count) {
    this.count = count;
    if (pageSize >= count){
      pageNo = 1;
    }
  }

  /**
   * 获取当前页码
   * @return pageNo
   */
  public int getPageNo() {
    return pageNo;
  }

  /**
   * 设置当前页码
   */
  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  /**
   * 获取页面大小
   */
  public int getPageSize() {
    return pageSize;
  }

  /**
   * 设置页面大小（最大500）
   */
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize <= 0 ? 10 : pageSize;
  }

  /**
   * 首页索引
   * @return first
   */
  public int getFirst() {
    return first;
  }

  /**
   * 尾页索引
   * @return last
   */

  public int getLast() {
    return last;
  }

  /**
   * 获取页面总数
   * @return getLast();
   */
  public int getTotalPage() {
    return getLast();
  }

  /**
   * 是否为第一页
   * @return firstPage
   */
  public boolean isFirstPage() {
    return firstPage;
  }

  /**
   * 是否为最后一页
   * @return lastPage
   */
  public boolean isLastPage() {
    return lastPage;
  }

  /**
   * 上一页索引值
   * @return int
   */
  public int getPrev() {
    if (isFirstPage()) {
      return pageNo;
    } else {
      return pageNo - 1;
    }
  }

  /**
   * 下一页索引值
   * @return int
   */
  public int getNext() {
    if (isLastPage()) {
      return pageNo;
    } else {
      return pageNo + 1;
    }
  }

  /**
   * 获取本页数据对象列表
   * @return list
   */
  public List<T> getList() {
    return list;
  }

  /**
   * 设置本页数据对象列表
   */
  public Page<T> setList(List<T> list) {
    this.list = list;
    initialize();
    return this;
  }

  /**
   * 获取查询排序字符串
   * @return orderBy
   */
  public String getOrderBy() {
    // SQL过滤，防止注入
    String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
        + "(\\b(select|update|and|or|delete|insert|trancate|char|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
    Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
    if (sqlPattern.matcher(orderBy).find()) {
      return "";
    }
    return orderBy;
  }

  /**
   * 设置查询排序，标准查询有效， 实例： updatedate desc, name asc
   */
  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }


  /**
   * 设置提示消息，显示在“共n条”之后
   * @param message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * 分页是否有效
   * @return this.pageSize==-1
   */
  public boolean isDisabled() {
    return this.pageSize==-1;
  }

  /**
   * 是否进行总数统计
   * @return this.count==-1
   */
  public boolean isNotCount() {
    return this.count==-1;
  }

  /**
   * 获取 Hibernate FirstResult
   */
  public int getFirstResult(){
    int firstResult = (getPageNo() - 1) * getPageSize();
    if (firstResult >= getCount()) {
      firstResult = 0;
    }
    return firstResult;
  }
  /**
   * 获取 Hibernate MaxResults
   */
  public int getMaxResults(){
    return getPageSize();
  }
}
