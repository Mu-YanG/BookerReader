<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>Book Store</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body>
<div id="wrap">
  <div class="header">
    <div class="logo"><a href="../index.htm"><img src="../images/login.gif" alt="" width="100" height="40" border="0" title="" /></a></div>
    <div id="menu">
      <ul>
        <li> <a href="${pageContext.request.contextPath}/index">首页</a></li>
		  <c:forEach items="${requestScope.menulist}" var="m" >
           <li><a href="${pageContext.request.contextPath}/show?id=${m.book_class_id}">${m.book_class_name}</a></li>
         
         </c:forEach>
          <li class="selected"><a href="${pageContext.request.contextPath}/${tishi}">${message}</a></li>
         <li><a href="${pageContext.request.contextPath}/user/SelectBook">搜索</a></li>
        <li><a href="${pageContext.request.contextPath}/${zhuxiaolink}">${zhuxiao}</a></li>
      </ul>
    </div>
  </div>
  <div class="center_content">
    <div class="left_content">
      <div class="title"><span class="title_icon"><img src="../images/bullet1.gif" alt="" title="" /></span>&#25105;&#30340;&#25910;&#34255;</div>
      <div class="feat_prod_box_details">
        <table class="cart_table">
       
          <tr class="cart_title">
            <td>&#22270;&#20070;</td>
            <td>&#20070;&#21517;</td>
            <td>&#20316;&#32773;</td>
            <td>&#19979;&#36733;</td>
            <td>&#21462;&#28040;&#25910;&#34255;</td>
          </tr>
          <c:forEach items="${bookcollection}" var="b">
          <tr>
            <td><a href="${pageContext.request.contextPath}/user/bookmessage?bookid=${b.bookId}"><img width="40" height="40" src="${pageContext.request.contextPath}/bookfiles/${b.bookId}/${b.bookPhoto}"/></a></td>
            <td><a href="${pageContext.request.contextPath}/user/bookmessage?bookid=${b.bookId}">${b.bookName}</a></td>
            <td>${b.bookAuthor}</td>
            <td><a href="${pageContext.request.contextPath}/usermessage/?dw=1&bookid=${b.bookId}">&#19979;&#36733;</a></td>
            <td><a href="${pageContext.request.contextPath}/usermessage/?sc=1&bookid=${b.bookId}" class="more"><img src="${pageContext.request.contextPath}/images/ych.jpg" alt="" width="95" height="26" border="0" title="" /></a></td>
            
          </tr>
          </c:forEach>
        </table>
        <a href="#" ></a> <a href="#" ></a> </div>
      <div class="clear"></div>
    </div>
    <!--end of left content-->
    <div class="right_content">
      <div class="right_box">
        <div class="title"><span class="title_icon"><img src="../images/bullet5.gif" alt="" title="" /></span>个人信息</div>
        <ul class="list">
        <c:forEach items="${user}" var="u">
        	<img width="40" height="40" src="${pageContext.request.contextPath}/photos/userphoto/${u.photo}"/><br>
          <li><a href="#">姓名：${u.name }</a></li><br>
          <li><a href="#">生日：${u.birthday}</a></li><br>
          <li><a href="#">性别：<c:if test="${u.sex==1}">男</c:if><c:if test="${u.sex==0}">女</c:if></a></li><br>
          <li><a href="#">邮箱:${u.emai}</a></li>
   
          </c:forEach>
         
        </ul>
        <a href="${pageContext.request.contextPath}/user/zc?xg=1">点击修改</a>
     
      </div>
    </div>
    <!--end of right content-->
    <div class="clear"></div>
  </div>
  <!--end of center content--><div align="center"> 
  </div><div class="footer">
    <div class="left_footer"><br />
    </div>
</div>
</body>
</html>
