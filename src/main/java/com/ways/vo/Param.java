package com.ways.vo;

public interface Param {

    interface SmsCode {
        int length = 6;
        String message = "动态码长度为6";
    }
    
    interface Password {
        int minlength = 6;
        int maxlength = 20;
        String message = "密码长度为6-20";
    }
    
    interface RealName {
        int minlength = 2;
        int maxlength = 32;
        String message = "姓名长度为2-32";
    }
    
    interface LoginName {
        int minlength = 3;
        int maxlength = 20;
        String message = "用户名长度为3-20";
    }
    
    interface LoginPassword {
        int minlength = 6;
        int maxlength = 20;
        String message = "密码长度为6-20";
    }
    
    interface VerifyCode {
        int length = 4;
        String message = "验证码长度为4";
    }
    
    interface Mobile {
        int length = 11;
        String message = "手机号长度为11";
    }
}
