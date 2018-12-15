package com.pms.api.sys.user;

import com.pms.api.common.persistence.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
  User getUserByUsername(@Param("username") String username);

  void resetPassword(User user);
}
