<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/index/include/taglib.jsp"%>
<html>
<head>
<title>注册</title>
<link rel="stylesheet" href="${ctxStatic}/css/index.css" />
<link rel="stylesheet" href="${ctxStatic}/css/register.css"/>
<link rel="stylesheet" href="${ctxStatic}/layer/skin/default/layer.css"/>
</head>
	<body class="bak">
       <!-- <iframe src="/jsp/nav.html"  width="100%" height="50px"></iframe> -->
        <div id = "wrapper">
            <div class="head">
                <div class="head-header-rig">
                    <a href="/jsp/index.html">logo</a>
                    <label class="user-barrier">|</label>
                    <label class="user-barrier">用户注册</label>
                    <a id="forget" href=""></a>
                </div>
                <div class="head-header-lef">
                    <label>我已注册，现在就 </label><button class="login_btn" onclick="javascrtpt:window.location.href='index.html'">登录</button>
                </div>
            </div>
            <div class="content">
                <div class="con_register">
                    <form:form id="reg_from" modelAttribute="userInfoParam" action="${ctx}/doRegist" method="post" onsubmit="return beforeSubmit(this);">
                        <p class="reg_from_p0"></p>
                        <p  class="reg_from_p">
                            <label for="" class="from_lable_name">用户名</label>
                            <form:input type="text" id="userName" path="nickName" class="from_imput_name" autocomplete="off" placeholder="最长14个字符（一个汉字等于2个字符）" onblur="blur_user()" onfocus="focus_user()"/>
                            <span id="from_spen_userNameTip" class="from_spen_Tip"  >
                                <span id="susername" style="display:none" class="from_spen_susername"></span>
                            </span>
                        </p>
                        
                        <p  class="reg_from_p">
                            <label for="" class="from_lable_name">邮箱</label>
                            <form:input type="text" id="email" path="email" class="from_imput_name" autocomplete="off" placeholder="请输入邮箱" onblur="blur_email()" onfocus="focus_email()"/>
                            <span id="from_spen_userNameTip" class="from_spen_Tip" >
                                <span id="semail" class="from_spen_semail"></span>
                            </span>
                        </p>
                         <p  class="reg_from_p">
                            <label for="" class="from_lable_name">手机号码</label>
                            <form:input type="text" id="phone" path="phone" class="from_imput_name" autocomplete="off" placeholder="请输入手机号" onblur="blur_phone()" onfocus="focus_phone()"/>
                            <span id="from_spen_userNameTip" class="from_spen_Tip" >
                                <span id="sphone" style="display:none" class="from_spen_phone">请输入中国大陆手机号</span>
                            </span>
                        </p>
                         <p  class="reg_from_p">
                            <label for="" class="from_lable_name">性别</label>
                            <label class="from_lable_name"><form:radiobutton path="sex" class="from_imput_sex" autocomplete="off" value=""/>男</label>
                            <label class="from_lable_name"><form:radiobutton path="sex" class="from_imput_sex" autocomplete="off" value=""/>女</label>
                        </p>
                         <p  class="reg_from_p">
                            <label for="" class="from_lable_name">密码</label>
                            <form:input id="pwd" type="password"  path="loginPassword" class="from_imput_name" autocomplete="off" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"  placeholder="请输入密码" onblur="blur_pwd()" onfocus="focus_pwd()"/>
                            <span id="from_spen_userNameTip" class="from_spen_Tip" >
                                <span id="spwd" style="display:none" class="from_spen_spwd">请输入4-20位用户密码<br>支持数字，大小写字码和下划线<br>不允许有空格</span>
                            </span>
                        </p>
                         <p  class="reg_from_p">
                            <label for="" class="from_lable_name">确认密码</label>
                            <input id="rpwd" type="password"  name="rpwd" class="from_imput_name" autocomplete="off" placeholder="请再次输入密码" onblur="blur_rpwd()" onfocus="focus_rpwd()">
                            <span id="from_spen_userNameTip" class="from_spen_Tip" >
                                <span id="srpwd" style="display:none" class="from_spen_srpwd">与密码不一致</span>
                            </span>
                        </p>
                        <p class="reg_from_p_sub"> 
                            <input id="reg_submit" class="from_p_sub" type="submit" value="注册" onclick="doRegister()"/>
                        </p>
                    </form:form>
                </div>
            </div>
        </div>
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/layer/layer.js"></script>
<script type="text/javascript">
	//用户名失去焦点事件
    function blur_user(){
        var regname=/^\w{4,10}$/;
        var userName=document.getElementById("userName");
        var susername=document.getElementById("susername");
        if(userName.value == null || "" ==userName.value){
            susername.className="frred";
            susername.style.display = "block";
            susername.innerHTML="请输入用户名"; 
            return false;
        }else if(userName.value.length>14){
            susername.className="frred";
            susername.innerHTML="用户名不能超过14个字符";
            return false;
        }else{
            if(userName.value.match(regname)){
                susername.className="ture";
                susername.innerHTML="用户名可用"
               	return true;
            }
            else{
                susername.className="frred";
                susername.innerHTML="用户名格式输入错误，请检查";
                return false;
            }
        }
 
    }
    //验证邮箱
    function blur_email(){
//         var =/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*(;\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*)*$/;
        var regemail =' ^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$';
        var femail=document.getElementById("email");
        var ftemail=document.getElementById("semail");
        if(""==femail.value){
            ftemail.style.display = "block";
            ftemail.className="frred";
            ftemail.innerHTML = "请输入邮箱地址";
            return false;
        }
        else{
            if(!femail.value.match(regemail)){
                ftemail.className="ture";
                ftemail.style.display = "block";
                ftemail.innerHTML="邮箱格式输入正确";
                return true;
            }
            else{
                ftemail.className="frred";
                ftemail.style.display = "block";
                ftemail.innerHTML="邮箱格式输入错误，请检查";
                return false;
            }
        }
    }
    //手机号码失去焦点事件
    function blur_phone(){
        var regPhone = /^1[34578]\d{9}$/;
        var phone = document.getElementById("phone");
        var sphone = document.getElementById("sphone");
        if(""==phone.value){
            sphone.style.display = "block";
            sphone.className="frred";
            sphone.innerHTML="请输入手机号";
            return false;
        }else{
            if(phone.value.match(regPhone)){
                sphone.className="ture";
                sphone.innerHTML="手机号正确";
                return true;
            }else{
                sphone.className="frred";
                sphone.innerHTML="手机号输入错误";
                return false;
            }
        }
    }
    //验证密码
    function blur_pwd(){
        var regpwd = /^(\w){6,20}$/;
        var pwd=document.getElementById("pwd");
        var spwd=document.getElementById("spwd");
        if(""==pwd.value){
            spwd.className="frred";
            spwd.style.display = "block";
            spwd.innerHTML="请输入密码";
        }else if(pwd.value.length<4||pwd.value.length>20){
            spwd.className="frred";
            spwd.innerHTML="密码不能低于4个字符或者高于20个字符";
            return false;
        }else{
            if(pwd.value.match(regpwd)){
                spwd.className="ture";
                spwd.innerHTML="密码可用";
                return true;
            }else{
                spwd.className="fgren";
                spwd.innerHTML="密码格式输入错误";
                return false;
            }
            
        }
    }
    //密码获得焦点事件
    function focus_pwd(){
        var spwd=document.getElementById("spwd");
        spwd.style.display = "block";
        spwd.innerHTML="请输入4-20位用户密码";
        return false;
    }
    //确认密码是否真确
    function blur_rpwd(){
        var fpwd=document.getElementById("pwd");
        var frpwd=document.getElementById("rpwd");
        var ftrpwd=document.getElementById("srpwd");
        if(""==frpwd.value){
            ftrpwd.style.display = "block";
            ftrpwd.className="frred";
            ftrpwd.innerHTML="请再次输入密码";
        }else{
            if(frpwd.value!=fpwd.value){
                ftrpwd.className="frred";
                ftrpwd.innerHTML="两次密码输入不一致，请重新输入";
                return false;
            }else{
            	ftrpwd.className="ture";
            	ftrpwd.innerHTML="验证正确";
                return true;
            }
        }
    }
    //密码获得焦点事件
    function focus_rpwd(){
        var srpwd=document.getElementById("srpwd");
        srpwd.style.display = "block";
        srpwd.innerHTML="请再次输入密码";
        return false;
    }
    function beforeSubmit(obj){
    	if(blur_user()){
    		if(blur_email()){
    			if(blur_phone()){
    				if(blur_pwd()){
    					if(blur_rpwd()){
    						$("#reg_from").submit();
    					}else{
    			    		return false;
    			    	}	
    				}else{
    		    		return false;
    		    	}
    			}else{
    	    		return false;
    	    	}
    		}else{
        		return false;
        	}
    	}else{
    		return false;
    	}
    }
    
</script>
</body>
</html>