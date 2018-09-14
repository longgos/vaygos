<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/index/include/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${ctx}/index/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/index/css/navmain.css" />
</head>
<body> 
    
     <div class="container">
        <div class="container-mid">
            <a class="container-logo"><img class="img-logo" src="${ctx}/index/images/vaygos (4).png"/> </a>
            <ul class="container-index">
                <li class="container-li container-one"><a href="">首页</a></li>
                <li class="container-li container-two"><a href="">发现</a></li>
                <li class="container-li container-three"><a href="">闲言碎语</a></li>
            </ul>
            <div class="container-input">
                <input class="input-index" type="text" placeholder="搜索你想看到的..." />
                <div class="container-glases">
                    <span class="glasses-a"></span>
                    <span class="glasses-b"></span>
                </div>
            </div>
            <c:if test="${userInfo!=null}">
	            <div class="container-img" onclick="message(this)" isUnfolded="true"><img class="head-img" src="${ctxStatic}/${userInfo.userImg}"/>
	            	<div id="myMessage" class="container-dot">
	            		<span class="item dot-top"></span>  
		            	<ul class="container-pop">
		            		<li><a>我的主页</a></li>
		            		<li><a>个人资料</a></li>
		            		<li><a>帮助及反馈</a></li>
		            		<li><a href="${ctx}/logout">退出</a></li>
		            	</ul>
	            	</div>
	            </div>
            </c:if>
        </div>
    </div>
    
 <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
 <script type="text/javascript">
 	function message(obj){
 		if($(obj).attr("isUnfolded")=="true"){
 			$("#myMessage").show();
 			$(obj).attr("isUnfolded","false")
 		}else{
 			$("#myMessage").hide();
 			$(obj).attr("isUnfolded","true")
 		}
 	}
 </script>
</body>
</html>