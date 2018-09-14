<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/index/include/taglib.jsp"%>
<html>
<head>
<title>Vaygos</title>
<link rel="stylesheet" href="${ctxStatic}/css/slide.css" />
<link rel="stylesheet" href="${ctxStatic}/css/index.css" />
<link rel="stylesheet" href="${ctxStatic}/css/logininput.css" />
<link rel="icon" href="${ctx}/index/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/index/images/favicon.ico" type="image/x-icon" />
<style type="text/css">
.bak1 {
	background-image: url("${ctx}/index/images/faus.jpeg");
}
.bak2 {
	background-image: url("${ctx}/index/images/corner.jpg");
}
.bak3 {
	background-image: url("${ctx}/index/images/43.jpg");
}
.bak4 {
	background-image: url("${ctx}/index/images/train-road.jpg");
}
</style>
</head>
	<body>
		<%@ include file="/WEB-INF/jsp/nav.jsp" %>
		<div class="ck-slide">
			<ul class="ck-slide-wrapper">
				<li class="bak1"></li>
				<li class="bak2" style="display: none;"></li>
				<li class="bak3" style="display: none;"></li>
				<li class="bak4" style="display: none;"></li>
			</ul>
			<div class="back-mid"><img src="${ctx}/index/images/word.png"></div>
			<div class="login-input">
				<div class="login-text">账户登录</div>
				<div class="login-two">
					<!-- 登录注册框 -->
					<div class="the-user">
						<label class="img-user" onclick="asd()"><img src="${ctx}/index/images/login_user.png" /></label> 
						<input id="user" type="text" class="input-user" placeholder="请输入用户名"/>
						<div id="colse-user" class="colse" onmouseover="init(this)" onmouseout="out(this)">
							<span class="cha-left"></span> <span class="cha-right"></span>
						</div>
					</div>
					<div class="the-pasd">
						<label class="img-pas"><img src="${ctx}/index/images/login_pas.png" /></label> 
						<input id="password" type="password" class="input-password" placeholder="请输入密码"/>
						<div id="close-psd" class="colse" onmouseover="init(this)" onmouseout="out(this)">
							<span class="cha-left"></span> <span class="cha-right"></span>
						</div>
					</div>
					<div class="user-ind"><a id ="registe" href="#">注册</a><label class="user-barrier">|</label><a id="forget" href="">密码搞忘了？</a></div>
					<div class="user_buttion" ><a href="${ctx}/user/doLogin">登  录</a></div>
				</div>
			</div>
		</div>
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/slide.js"></script>
<script type="text/javascript">
	//循环轮播
	$('.ck-slide').ckSlide({
		autoPlay: true,
	});
	function init(obj){
		$(obj).css("background-color","#6d6d6d");
	}
	function out(obj){
		$(obj).css("background-color","#ababab");
	}
	$("#colse-user").click(function(){
		$("#user").val("");
	})
	$("#close-psd").click(function(){
		$("#password").val("");
	})
</script>
</body>
</html>