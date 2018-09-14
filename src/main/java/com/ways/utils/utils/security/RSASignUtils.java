package com.ways.utils.utils.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

/**
 * rsa签名工具
 * @author hufeng
 *
 */
public class RSASignUtils {
	
	private static Logger logger = LoggerFactory.getLogger(RSASignUtils.class);
	
	public static String MD5_RSA_SIGNATURE = "MD5WithRSA";
	public static String SHA1_RSA_SIGNATURE = "SHA1WithRSA";
	
	public static String signMap(Map<String, ?> map, String priKey) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException  {
		String sb = map2SignStr(map);
		return sign(sb, priKey);
	}
	
	public static boolean verifySignMap(Map<String, ?> map, String pubKey) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
		return verifySignMap(map, pubKey, "UTF-8");
	}
	
	public static boolean verifySignMap(Map<String, ?> map, String pubKey, String encoding) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
		String sign = String.valueOf(map.remove("sign"));
		String data = map2SignStr(map);
		return verifySign(data, sign, pubKey, encoding);
	}

	public static String signMap(Map<String, Object> map, byte[] priKey) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException  {
		String data = map2SignStr(map);
		return sign(data.getBytes("UTF-8"), priKey);
	}
	
	public static boolean verifySignMap(Map<String, Object> map,  byte[] pubKey) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
		String sign = String.valueOf(map.remove("sign"));
		String data = map2SignStr(map);
		return verifySign(data.getBytes("UTF-8"), Base64.decodeBase64(sign),pubKey);
	}
	
	public static boolean verifySignMap(Map<String, Object> map,  PublicKey key, String alg) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
		String sign = String.valueOf(map.remove("sign"));
		String data = map2SignStr(map);
		return verifySign(data.getBytes("UTF-8"), Base64.decodeBase64(sign), key, alg);
	}
	
	private static String map2SignStr(Map<String, ?> map) {
		Set<String> keySet = map.keySet();
		List<String> keyList = new ArrayList<String>(keySet);
		Collections.sort(keyList);
		
		StringBuilder sb = new StringBuilder(keyList.size() * 20);
		for (String key : keyList) {
			Object v = map.get(key);
			if (null == v || "".equals(v.toString())) {
				continue;
			}
			sb.append("&").append(key).append("=").append(v);
		}
		sb.deleteCharAt(0);
		String toSignStr = sb.toString();
		if (logger.isDebugEnabled()) {
			logger.debug("to ben signed string:{}", toSignStr);
		}
		return toSignStr;
	}
	
	static String pubkey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCK1PDnwH0xSfAqRp9+N8iLadaAAHuk4wtUHfWL5MCH487zbzW3/9XbKTbzB+uWLnzjq5azowI5bC2HW74AOJm0FHfQ3e9pRSn7Jk0FwOPppq4j6k6q+MNAROQ5MBShevIYtuSBu+TDn6/vQiqXfvB+TT+pUWrsZurxRWnhJ8OrhwIDAQAB";
	static String priKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIrU8OfAfTFJ8CpGn343yItp1oAAe6TjC1Qd9YvkwIfjzvNvNbf/1dspNvMH65YufOOrlrOjAjlsLYdbvgA4mbQUd9Dd72lFKfsmTQXA4+mmriPqTqr4w0BE5DkwFKF68hi25IG75MOfr+9CKpd+8H5NP6lRauxm6vFFaeEnw6uHAgMBAAECgYAyKTQNmBfjuiJDNBcmZINaE9iDkSkbb0lFkDmK3RDPSHkaraDMJWygbejDsKXnMk6/d9zQdOI12ycsE/K3Y7vss4fOjd4D6FnJQlFBzEkqydFxq9qGrOxSztBCja4sqNfmqne64WCAlSicHxqGXZSIHQOb6kBnqcK58/yx1S2CAQJBAL46Tjvkodoiv2RvxMou83563dbBimlENwufLghO9s2kuhZ/7tWk8tD62Tyu6vLkI7IP42xb1d0lrgMd5jgs2IcCQQC61WhdeIxXYzLC3ThXXCgxFarNPlQ2P83IKVdVCrbvzpzid5DB86PQiw9BtKe2mj6LiO8vO0kReCjtArFNPlUBAkBNnZSWM28mhc75t/DfXdDT5mXlXgqPcNnU0BoEIdPpjDCxRXm5ihfY1MEWVXhfql42JO1yJPZZySn685Qfj4y5AkBLyTVEdRm4eOymTsYD6cYD4x4dkyLlvovinaJnRKlgLRdGZem6QvI4LbBKntVJJNgqEam0JwBErdEsEuOIybgBAkEAtzEOhPn7NCYCsFdgfYmjNLOKObiDe9hTS25S5EDIahV0oy+IpKRLXpTgDkjnkJJjXKaNHdEVBKC3pr0DrRv37A==";
	public static void main(String[] args) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		map.put("aaa", "1");
		map.put("bbbb", "2");
		String str = map2SignStr(map);
		String sign = sign(str, priKey);
		
		System.out.println(verifySign(str, sign, pubkey, "utf-8"));
	}
	
	/*public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");  
	    generator.initialize(1024, new SecureRandom());  
	    KeyPair pair = generator.generateKeyPair();  
	    PublicKey pubKey = pair.getPublic();  
	    PrivateKey privKey = pair.getPrivate();  
	    byte[] pk = pubKey.getEncoded();  
	    byte[] privk = privKey.getEncoded();  
	    String strpk = new String(Base64.encodeBase64(pk));  
	    String strprivk = new String(Base64.encodeBase64(privk));  
	  
	    System.out.println("公钥:" + Arrays.toString(pk));  
	    System.out.println("私钥:" + Arrays.toString(privk));  
	    System.out.println("公钥Base64编码:" + strpk);  
	    System.out.println("私钥Base64编码:" + strprivk);  
	  
	    X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decodeBase64(strpk.getBytes()));  
	    PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(strprivk.getBytes()));  
	  
	    KeyFactory keyf = KeyFactory.getInstance("RSA");  
	    PublicKey pubkey2 = keyf.generatePublic(pubX509);  
	    
	    
	    PrivateKey privkey2 = keyf.generatePrivate(priPKCS8);  
	  
	    System.out.println(pubKey.equals(pubkey2));  
	    System.out.println(privKey.equals(privkey2));  
	}*/
	
	/**
	 * 签名
	 * @throws UnsupportedEncodingException 
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * */
	public static String sign(String data, String privateKey, String encoding) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException  {
		return sign(data.getBytes(encoding), Base64.decodeBase64(privateKey));
	}
	
	/**
	 * 签名
	 * @throws UnsupportedEncodingException 
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * */
	public static String sign(String data, String privateKey) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException  {
		return sign(data.getBytes("UTF-8"), Base64.decodeBase64(privateKey));
	}
	
	/**
	 * 签名
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 * @throws InvalidKeyException 
	 * @throws SignatureException 
	 * */
	public static String sign(byte[] data, byte[] privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException  {

		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey key = keyFactory.generatePrivate(pkcs8KeySpec);

		byte[] result = sign(data, key); 

		return Base64.encodeBase64String(result);
	}

	public static byte[] sign(byte[] data, PrivateKey key)
			throws NoSuchAlgorithmException, InvalidKeyException,
			SignatureException {
		return sign(data, key, MD5_RSA_SIGNATURE);
	}
	
	public static byte[] sign(byte[] data, PrivateKey key, String alg)
			throws NoSuchAlgorithmException, InvalidKeyException,
			SignatureException {
		Signature sign = Signature.getInstance(alg);
		sign.initSign(key);
		sign.update(data);

		/** 签名 */
		byte[] result = sign.sign();
		return result;
	}
	
	public static boolean verifySign(String data, String signData, String pubKey, String encoding) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
		if (logger.isDebugEnabled()) {
			logger.debug("verifySign, data:{}, signData:{}, pubKey:{}" , data, signData, pubKey);
		}
		if (null == encoding) {
			encoding = "UTF-8";
		}
		return verifySign(data.getBytes(encoding), Base64.decodeBase64(signData), Base64.decodeBase64(pubKey));
	}
	
	/**
	 * 验签
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 * @throws InvalidKeyException 
	 * @throws SignatureException 
	 * */
	public static boolean verifySign(byte[] data,byte[] signData, byte[] pubKey) throws NoSuchAlgorithmException, 
		InvalidKeySpecException, InvalidKeyException, SignatureException {

		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(pubKey);

		KeyFactory  keyfactory = KeyFactory.getInstance("RSA");	
		PublicKey key = keyfactory.generatePublic(publicKeySpec);

		return verifySign(data, signData, key);

	}

	public static boolean verifySign(byte[] data, byte[] signData,
			PublicKey key) throws NoSuchAlgorithmException,
			InvalidKeyException, SignatureException {
		return verifySign(data, signData, key, MD5_RSA_SIGNATURE);
	}
	
	
	public static boolean verifySign(byte[] data, byte[] signData,
			PublicKey key, String alg) throws NoSuchAlgorithmException,
			InvalidKeyException, SignatureException {
		Signature sign = Signature.getInstance(alg);
		sign.initVerify(key);
		sign.update(data);

		return sign.verify(signData);
	}
}
