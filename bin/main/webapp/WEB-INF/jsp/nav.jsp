<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/index/include/taglib.jsp"%>
<link rel="stylesheet" href="/index/css/slide.css" />
<link rel="stylesheet" href="${ctxStatic}/css/nav.css" />
<style type="text/css">
	
</style>
	<div class="vay-nav">
		<div class="contailer">
<%-- 		<c:if test="${not empty member }"> --%>
			<ul class= "contailer-left">
				<li class="vay-item contailer-left-welcome">欢迎您，<a class="vay-item nickname">Longway</a></li>
			</ul>
<%-- 		</c:if> --%>
			<ul class="contailer-right">
				<li class="vay-item-right link"><a class="">首页</a></li>
				<li class= "vay-item-right vertical">|</li>
				<li class="vay-item-right link"><a class="">About US</a></li>
				<li class= "vay-item-right vertical">|</li>
				<li class="vay-item-right link"><a class="">有问题？投诉</a></li>
				<li class= "vay-item-right vertical">|</li>
				<li class="vay-item-right link"><a class="">Language</a></li>
				<li class="vay-item-right"></li>
			</ul>
		</div>
	</div>
