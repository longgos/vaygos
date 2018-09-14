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
            <div class="container-img"><img class="head-img" src="${ctx}/index/images/head.png"/></div>
        </div>
    </div>
  
</body>
</html>