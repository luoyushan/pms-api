package com.pms.api.common.web;
public class ResponseResult {

	public final static String SUCCESS_CODE = "0";
	public final static String FAILURE_CODE = "-1";
	public final static String ERROR_PARAM_CODE = "-2";
	public final static String INVALID_CODE = "-3";
	public final static String SUCCESS_MESSAGE = "操作成功";
	public final static String FAILURE_MESSAGE = "操作失败";
	public final static String INVALID_MESSAGE = "登陆过期";
	public final static String ERROR_PARAM_MESSAGE = "请求参数错误";

	private String replyCode; // 返回的状态代码
	private String replyMsg; // 返回的信息描述
	private Object data; // 返回的数据信息

	public ResponseResult() {
	}
	public ResponseResult(String replyCode, String replyMsg) {
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

	public void setReplyCode(String replyCode) {
		this.replyCode = replyCode;
	}

	public String getReplyMsg() {
		return replyMsg;
	}

	public void setReplyMsg(String replyMsg) {
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

}
