<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!doctype html>
<html>
<head>
 
    <title>����ҹ������̨����ϵͳ</title>
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
            <div class="crumb-list"><em></em><a href="${pageContext.request.contextPath}/index" color="#white">��վ��ҳ��</a><a href="${pageContext.request.contextPath}/mindex/" color="#white">��ҳ��</div>
           
        </div>
        <div class="result-wrap">
            
              ��ӭ��¼����ҹ������̨����ϵͳ
           
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>