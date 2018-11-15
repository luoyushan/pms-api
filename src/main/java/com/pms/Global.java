package com.pms;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置类
 */
@Component
@ConfigurationProperties(prefix = "config")
@PropertySource(value = "classpath:config/config.properties")
public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();

	/**
	 * 显示/隐藏
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "2";

	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";


	private String pageSize;

	public static Global getGlob() {
		return global;
	}
	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		key = (key.substring(0, 1)).toUpperCase() + key.substring(1);
		Method getMethod = null;
		try {
			getMethod = global.getClass().getMethod("get" + key);
		} catch (Exception e) {
			return "";
		}
		if (getMethod == null) {
			try {
				getMethod = global.getClass().getMethod("is" + key);
			} catch (Exception e) {
				return "";
			}
		}
		if (getMethod == null) {
			return  "";
		}
		String value = "";
		try {
			value = (String) getMethod.invoke(global);
		} catch (Exception e) {
			return "";
		}
		return value;
	}
}
