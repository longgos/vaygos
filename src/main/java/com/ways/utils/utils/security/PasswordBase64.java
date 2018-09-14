package com.ways.utils.utils.security;

import com.ways.utils.utils.Encodes;


public class PasswordBase64 {
    public static final String SALT = "HZR";
    
    public static String encodePassword(String password){
        String source = Encodes.encodeBase64(password);
        return Encodes.encodeBase64((SALT + source).getBytes());
    }
   
    public static String decodePassword(String encodePassword){
        
        String dest = Encodes.decodeBase64String(encodePassword);
        
        String source = new String(dest).substring(3);
        
        return Encodes.decodeBase64String(source);
    }
    
      
}
