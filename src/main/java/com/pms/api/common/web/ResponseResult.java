package com.pms.api.common.web;

import java.util.Map;

public class ResponseResult {

	private final static String SUCCESS_CODE = "0";
	private final static String FAILURE_CODE = "-1";
	private final static String ERROR_PARAM_CODE = "-2";
	private final static String INVALID_CODE = "-3";
	private final static String SUCCESS_MESSAGE = "操作成功";
	private final static String FAILURE_MESSAGE = "操作失败";
	private final static String INVALID_MESSAGE = "登陆过期";
	private final static String ERROR_PARAM_MESSAGE = "请求参数错误";

	private String replyCode; // 返回的状态代码
	private String replyMsg; // 返回的信息描述
	private Object data; // 返回的数据信息

	private ResponseResult() {
	}
	private ResponseResult(String replyCode, String replyMsg) {
		super();
		this.replyCode = replyCode;
		this.replyMsg = replyMsg;
	}

	public ResponseResult(String replyCode, String replyMsg, Object data) {
		super();
		this.replyCode = replyCode;
		this.replyMsg = replyMsg;
		this.setData(data);
	}

	public String getReplyCode() {
		return replyCode;
	}

	private void setReplyCode(String replyCode) {
		this.replyCode = replyCode;
	}

	public String getReplyMsg() {
		return replyMsg;
	}

	private void setReplyMsg(String replyMsg) {
		this.replyMsg = replyMsg;
	}

	public static ResponseResult defaultSuccess() {
		return new ResponseResult(ResponseResult.SUCCESS_CODE,
				ResponseResult.SUCCESS_MESSAGE);
	}

	public static ResponseResult success(String message) {
		return new ResponseResult(ResponseResult.SUCCESS_CODE,
				ResponseResult.SUCCESS_MESSAGE + " : " + message);
	}

	public static ResponseResult defaultFailure() {
		return new ResponseResult(ResponseResult.FAILURE_CODE,
				ResponseResult.FAILURE_MESSAGE);
	}

	public static ResponseResult failure(String message) {
		return new ResponseResult(ResponseResult.FAILURE_CODE,
				ResponseResult.FAILURE_MESSAGE + " : " + message);
	}

	public static ResponseResult defaultErrorParam(String message) {
		return new ResponseResult(ResponseResult.ERROR_PARAM_CODE,
				ResponseResult.ERROR_PARAM_MESSAGE + " : " + message);
	}

	public static ResponseResult errorParam() {
		return new ResponseResult(ResponseResult.ERROR_PARAM_CODE,
				ResponseResult.ERROR_PARAM_MESSAGE);
	}

	public static ResponseResult invalid() {
		return new ResponseResult(ResponseResult.INVALID_CODE,
				ResponseResult.INVALID_MESSAGE);
	}
	public static ResponseResult defaultInvalid(String message) {
		return new ResponseResult(ResponseResult.INVALID_CODE,
				ResponseResult.INVALID_MESSAGE + " : " + message);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


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
	 * @param errCode--返回码
	 * @param data------数据源
	 * @return result
	 */
	public static ResponseResult result(String errCode, Map<String, Object> data) {
		ResponseResult result = new ResponseResult();
		result.setReplyCode(errCode);
		switch (errCode) {
			case "0":
				result.setReplyMsg("操作成功");
				if (data != null) {
					result.setData(data);
				}
				break;
			case "1001":
				result.setReplyMsg("请求传参错误");
				break;
			case "1002":
				result.setReplyMsg("没有对应内容");
				break;
			case "1003":
				result.setReplyMsg("此用户已存在");
				break;
			case "1004":
				result.setReplyMsg("上传文件为空");
				break;
			case "404":
				result.setReplyMsg("异常抛出错误");
				break;
			default:
				result.setReplyMsg("未知错误");
				break;
		}
		return result;
	}
}
