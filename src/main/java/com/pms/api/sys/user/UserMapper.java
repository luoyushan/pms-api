package com.pms.api.sys.user;

import com.pms.api.common.persistence.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMapper extends BaseDao<User> {
  User getUserByUsername(@Param("username") String username);
}
