package com.zent.util;

import org.mindrot.jbcrypt.BCrypt;

public class SecurityUtil {
	public static String encrypt(String password){
		return BCrypt.hashpw(password, BCrypt.gensalt(10));
	}
	public static Boolean checkPassword(String plainText, String encryptedText ){
		return BCrypt.checkpw(plainText, encryptedText);
	}
	public static void main(String[] args) {
		System.out.println(SecurityUtil.encrypt("123456")) ;
		System.out.println(SecurityUtil.checkPassword("123456", "$2a$10$regWZX/xXbXuR3F9v25nzuDHQi8Bmh9WecjCPrw3a.lvQJH2ooKhS"));
	}
} 
