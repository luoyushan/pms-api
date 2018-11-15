package com.pms.api.common.web;

import java.util.Map;

/**
 * 请求结果处理类
 */
public class ResultTools {
  /****
   * 错误码记录：
   * 0--------成功
   * 1001-----请求传参错误
   * 1002-----没有对应内容
   * 1003-----此用户已存在
   * 1004-----上传文件为空
   * 404------异常抛出错误
   *
   */

  /**
   * @param Errcode--返回码
   * @param Errmsg---404服务器内部异常时提示消息(返回码不是404时传空即可)
   * @param map------数据源
   * @return
   */
  public static ResponseResult result(String Errcode, String Errmsg, Map<String, Object> map) {
    ResponseResult result = new ResponseResult();
    result.setReplyCode(Errcode);
    switch (Errcode) {
      case "0":
        result.setReplyMsg("成功");
        if (map != null) {
          result.setData(map);
        }
        break;
      case "1001":
        result.setReplyMsg("请求传参错误 ");
        break;
      case "1002":
        result.setReplyMsg("没有对应内容 ");
        break;
      case "1003":
        result.setReplyMsg("此用户已存在");
        break;
      case "1004":
        result.setReplyMsg("上传文件为空");
        break;
      case "404":
        result.setReplyMsg(Errmsg);
        break;
      default:
        result.setReplyMsg("未知错误");
        break;
    }
    return result;
  }

}
