package com.pms.api.common.utils;

import com.pms.api.sys.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;

/**
 * Cache工具类
 *
 * 5-29
 */
@Cacheable
public class CacheUtils {
	@Autowired
	private CacheManager cacheManager;

	private static final String SYS_USER_ = "sys_user_";
	private static final String LOGIN_USER = "login_user";

	public User getLoginUser() {
		return (User) cacheManager.getCache(LOGIN_USER);
	}

	public void setLoginUser() {
//		cacheManager.
	}
}
