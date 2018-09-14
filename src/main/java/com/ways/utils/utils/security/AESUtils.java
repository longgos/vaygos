package com.ways.utils.utils.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.ways.utils.utils.GtlUtil;



public class AESUtils {

	public static byte[] encrypt(byte[] content, String key, String ivs) throws Exception {
		byte[] enCpdeFormat = Base64.decodeBase64(key);
		SecretKeySpec secretKeySpec = new SecretKeySpec(enCpdeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] byteContent = content;
		if (GtlUtil.isEmpty(ivs)) {
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		} else {
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(ivs.getBytes()));
		}
		byte[] byteRresult = cipher.doFinal(byteContent);
		return byteRresult;
	}

	public static byte[] encrypt(byte[] content, String key) throws Exception {
		return encrypt(content, key, null);
	}

	public static byte[] decrypt(byte[] content, String key, String ivs) throws Exception {
		byte[] enCpdeFormat = Base64.decodeBase64(key);
		SecretKeySpec secretKeySpec = new SecretKeySpec(enCpdeFormat, "AES");
		Cipher ciper = Cipher.getInstance("AES/CBC/PKCS5Padding");
		if (GtlUtil.isEmpty(ivs)) {
			ciper.init(Cipher.DECRYPT_MODE, secretKeySpec);
		} else {
			ciper.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(ivs.getBytes()));
		}
		return ciper.doFinal(content);
	}

	public static byte[] decrypt(byte[] content, String key) throws Exception {
		return decrypt(content, key, null);
	}

	public static void main(String[] args) throws Exception {
		String content = "aaaaaa";
		String password = "+TaWiu6Fj52L0vVUhss10Q==";
		System.out.println("密　钥：" + password);
		System.out.println("加密前：" + content);
		// 加密
		byte[] encryptResult = encrypt(content.getBytes("utf-8"), password);
		System.out.println("加密后：" + Base64.encodeBase64String(encryptResult));

		String unde = "y5c6GucnMq7yWeBITNSBlA==";
		byte[] undebyte = Base64.decodeBase64(unde);
		// 解密
		String decryptResult = new String(decrypt(undebyte, password), "utf-8");
		System.out.println("解密后：" + decryptResult);
	}
}
