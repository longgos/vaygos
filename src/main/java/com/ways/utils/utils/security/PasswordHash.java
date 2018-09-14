package com.ways.utils.utils.security;

import com.ways.utils.utils.Encodes;

public class PasswordHash {

    public static final int HASH_INTERATIONS = 1023;
    public static final int SALT_SIZE = 8;

    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String entryptPassword(String plainPassword) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }
    
    public static boolean validatePassword(String plainPassword, String password) {
        byte[] salt = Encodes.decodeHex(password.substring(0,16));
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }
    
    public static void main(String[] args) {
		String a = entryptPassword("123456");
		System.out.println(a);
		System.out.println(validatePassword("123456" , "21b3c414778215dbab8056e904c61f62d919ac06336b9336664542a4"));
	}
}
