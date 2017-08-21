<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!doctype html>
<html>
<head>
 
    <title>挑灯夜读网后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
    <script type="text/javascript" src="../js/libs/modernizr.min.js"></script></head>
<body>


<div class="container clearfix">
<div class="topbar-wrap white">
    <!--<div class="topbar-inner clearfix">-->
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"></h1>
        </div>
     
    </div>
</div>
  <%@include file="/master/common.jsp" %>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><em></em><a href="${pageContext.request.contextPath}/index" color="#white">网站首页〉</a><a href="${pageContext.request.contextPath}/mindex/" color="#white">首页〉</div>
           
        </div>
        <div class="result-wrap">
            
              欢迎登录挑灯夜读网后台管理系统
           
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>