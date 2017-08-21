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
         <li ><a href="${pageContext.request.contextPath}/index">首页</a></li>
		 <c:forEach items="${requestScope.menulist}" var="m" >
         <li><a href="${pageContext.request.contextPath}/show?id=${m.book_class_id}">${m.book_class_name}</a></li>
         
         </c:forEach>
         <li><a href="${index}">${message}</a></li>
         <li><a href="${pageContext.request.contextPath}/user/SelectBook">搜索</a></li>
      </ul>
    </div>
  </div>
  <div class="center_content">
    <div class="left_content">
      <div class="title"><span class="title_icon"><img src="../images/bullet1.gif" alt="" title="" /></span>用户登录</div>
      <div class="feat_prod_box_details">
        <p class="details">提示：请输入正确的用户名或密码，点击登录即可</p>
         <p class="details">${tishi}</p>
        <div class="contact_form">
          <div class="form_subtitle">用户登录</div>
          <form action=""  method="get">
          
          <div class="form_row">
            <label class="contact"><strong>用户名:</strong></label>
           		    <input type="text" name="id" class="contact_input" />
          </div>
          <div class="form_row">
            <label class="contact"><strong>密码:</strong></label>
            <input type="password" name="password" class="contact_input" />
          </div>
       
          <div class="form_row">   <div align="right"><input type="submit" value="登录"/></div> </div>
		  <a href="${pageContext.request.contextPath}/user/zc">立即注册</a> </form>
        </div>
      </div>
      <div class="clear"></div>
    </div>
    <!--end of left content-->
    <div class="right_content">
      ${sblist}
    </div>
    <!--end of right content-->
    <div class="clear"></div>
  </div>
  <!--end of center content-->
  <div class="footer"></div>
</div>
</body>
</html>
