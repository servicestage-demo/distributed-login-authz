package com.xxx.sso.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class AesCipherUtil {

  /**
   * AES密码加密私钥(Base64加密)
   */
  private static String encryptAESKey = "V2FuZzkyNjQ1NGRTQkFQSUpXVA==";


  public static String enCrypto(String str)
      throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException,
      BadPaddingException, IllegalBlockSizeException {
    try {
      Security.addProvider(new com.sun.crypto.provider.SunJCE());
      // 实例化支持AES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
      // KeyGenerator 提供对称密钥生成器的功能，支持各种算法
      KeyGenerator keygen = KeyGenerator.getInstance("AES");
      // 将私钥encryptAESKey先Base64解密后转换为byte[]数组按128位初始化
      SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
      secureRandom.setSeed(Base64ConvertUtil.decode(encryptAESKey).getBytes());
      keygen.init(128, secureRandom);
      // SecretKey 负责保存对称密钥 生成密钥
      SecretKey desKey = keygen.generateKey();
      // 生成Cipher对象,指定其支持的AES算法，Cipher负责完成加密或解密工作
      Cipher c = Cipher.getInstance("AES");
      // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
      c.init(Cipher.ENCRYPT_MODE, desKey);
      byte[] src = str.getBytes();
      // 该字节数组负责保存加密的结果
      byte[] cipherByte = c.doFinal(src);
      // 先将二进制转换成16进制，再返回Base64加密后的String
      return Base64ConvertUtil.encode(HexConvertUtil.parseByte2HexStr(cipherByte));
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      throw e;
    } catch (UnsupportedEncodingException e) {
      throw e;
    } catch (InvalidKeyException e) {
      throw e;
    } catch (IllegalBlockSizeException | BadPaddingException e) {
      throw e;
    }
  }


  public static String deCrypto(String str)
      throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException,
      BadPaddingException, IllegalBlockSizeException {
    try {
      Security.addProvider(new com.sun.crypto.provider.SunJCE());
      // 实例化支持AES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
      // KeyGenerator 提供对称密钥生成器的功能，支持各种算法
      KeyGenerator keygen = KeyGenerator.getInstance("AES");
      // 将私钥encryptAESKey先Base64解密后转换为byte[]数组按128位初始化
      SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
      secureRandom.setSeed(Base64ConvertUtil.decode(encryptAESKey).getBytes());
      keygen.init(128, secureRandom);
      // SecretKey 负责保存对称密钥 生成密钥
      SecretKey desKey = keygen.generateKey();
      // 生成Cipher对象,指定其支持的AES算法，Cipher负责完成加密或解密工作
      Cipher c = Cipher.getInstance("AES");
      // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示解密模式
      c.init(Cipher.DECRYPT_MODE, desKey);
      // 该字节数组负责保存解密的结果，先对str进行Base64解密，将16进制转换为二进制
      byte[] cipherByte = c.doFinal(HexConvertUtil.parseHexStr2Byte(Base64ConvertUtil.decode(str)));
      return new String(cipherByte);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      throw e;
    } catch (UnsupportedEncodingException e) {
      throw e;
    } catch (InvalidKeyException e) {
      throw e;
    } catch (IllegalBlockSizeException | BadPaddingException e) {
      throw e;
    }
  }

  public static void main(String[] args)
      throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException,
      UnsupportedEncodingException, InvalidKeyException {
    String password = enCrypto("wangwang");
    System.out.println(password);
    System.out.println(deCrypto(password));
  }
}
