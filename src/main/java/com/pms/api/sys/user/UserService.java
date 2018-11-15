package com.pms.api.sys.user;

import com.pms.api.common.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.List;

@Service
public class UserService extends BaseService<UserMapper, User> {
  @Autowired
  private UserMapper userDao;

  public List<User> getUsers() {
    User user = new User();
    user.setName("luo");
    return userDao.findList(user);
  }

  @CachePut(value = "user", key = "login_user", condition = "user != null")
  public User getUserByUsername(String username) {
    User user = userDao.getUserByUsername(username);
    if (user!= null && user.getPassword() != null) {
      user.setPassword(passwordGuard(user.getPassword(), DECRYPT));
    }
    return user;
  }

  /**
   *  EDS的加密解密代码
   */
  private static final byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128, -65 };
  private static final String ENCRYPT = "encrypt"; //加密
  private static final String DECRYPT = "decrypt"; // 解密
  @SuppressWarnings("restriction")
  public static String passwordGuard(String data, String type) {
    String password = "";
    try {
      // DES算法要求有一个可信任的随机数源
      SecureRandom sr = new SecureRandom();
      DESKeySpec deskey = new DESKeySpec(DES_KEY);
      // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey key = keyFactory.generateSecret(deskey);
      // 解析对象
      Cipher cipher = Cipher.getInstance("DES");

      // 解析，并把字节数组编码成字符串
      if (ENCRYPT.equals(type)) {
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        password = new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
      }
      if (DECRYPT.equals(type)) {
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
        password = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(data)));
      }
    } catch (Exception e) {
      // log.error("解析错误，错误信息：", e);
      throw new RuntimeException("加密错误，错误信息：", e);
    }
    return password;
  }
}
