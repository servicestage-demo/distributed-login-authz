package com.xxx.sso.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xxx.sso.constants.Constants;

/**
 * JAVA-JWT工具类
 * @author Wang926454
 * @date 2018/8/30 11:45
 */
public class JwtUtil {

  /**
   * LOGGER
   */

  /**
   * 过期时间改为从配置文件获取
   */
  private static String accessTokenExpireTime="300";

  /**
   * JWT认证加密私钥(Base64加密)
   */
  private static String encryptJWTKey="U0JBUElKV1RkV2FuZzkyNjQ1NA==";

//  @Value("${accessTokenExpireTime}")
//  public void setAccessTokenExpireTime(String accessTokenExpireTime) {
//    JwtUtil.accessTokenExpireTime = accessTokenExpireTime;
//  }
//
//  @Value("${encryptJWTKey}")
//  public void setEncryptJWTKey(String encryptJWTKey) {
//    JwtUtil.encryptJWTKey = encryptJWTKey;
//  }

  /**
   * 校验token是否正确
   * @param token Token
   * @return boolean 是否正确
   * @author Wang926454
   * @date 2018/8/31 9:05
   */
  public static boolean verify(String token) throws UnsupportedEncodingException {
    // 帐号加JWT私钥解密
    String secret = getClaim(token, Constants.ACCOUNT) + Base64ConvertUtil.decode(encryptJWTKey);
    Algorithm algorithm = Algorithm.HMAC256(secret);
    JWTVerifier verifier = JWT.require(algorithm)
        .build();
    DecodedJWT jwt = verifier.verify(token);
    return true;
  }

  /**
   * 获得Token中的信息无需secret解密也能获得
   * @param token
   * @param claim
   * @return java.lang.String
   * @author Wang926454
   * @date 2018/9/7 16:54
   */
  public static String getClaim(String token, String claim) {
    DecodedJWT jwt = JWT.decode(token);
    // 只能输出String类型，如果是其他类型返回null
    return jwt.getClaim(claim).asString();
  }

  /**
   * 生成签名
   * @param account 帐号
   * @return java.lang.String 返回加密的Token
   * @author Wang926454
   * @date 2018/8/31 9:07
   */
  public static String sign(String account, String currentTimeMillis) throws UnsupportedEncodingException {
    // 帐号加JWT私钥加密
    String secret = account + Base64ConvertUtil.decode(encryptJWTKey);
    // 此处过期时间是以毫秒为单位，所以乘以1000
    Date date = new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpireTime) * 1000);
    Algorithm algorithm = Algorithm.HMAC256(secret);
    // 附带account帐号信息
    return JWT.create()
        .withClaim("account", account)
        .withClaim("currentTimeMillis", currentTimeMillis)
        .withExpiresAt(date)
        .sign(algorithm);
  }
}
