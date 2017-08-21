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
         <li ><a href="${pageContext.request.contextPath}/${tishi}">${message}</a></li>
         <li class="selected"><a href="${pageContext.request.contextPath}/user/SelectBook">搜索</a></li>
      </ul>
    </div>
  </div>
  <div class="center_content">
    <div class="left_content">
      <div class="title"></div>
      <div class="feat_prod_box_details">
        <form action="${pageContext.request.contextPath}/user/SelectBook">
      		搜索：<input placeholder="请输入书号/书名"    name="key"  />
      		
      		<input type="submit" value="查找">
      		
      	</form>
      	<br />
      	<c:if test="${not empty selectbook }">
           <table class="cart_table">
       
          <tr class="cart_title">
            <td>&#22270;&#20070;</td>
            <td>&#20070;&#21517;</td>
            <td>&#20316;&#32773;</td>
            <td>&#19979;&#36733;</td>
          </tr>
          <c:forEach items="${selectbook}" var="b">
          <tr>
            <td><a href="${pageContext.request.contextPath}/user/bookmessage?bookid=${b.bookId}"><img width="40" height="40" src="${pageContext.request.contextPath}/bookfiles/${b.bookId}/${b.bookPhoto}"/></a></td>
            <td><a href="${pageContext.request.contextPath}/user/bookmessage?bookid=${b.bookId}">${b.bookName}</a></td>
            <td>${b.bookAuthor}</td>
            <td><a href="${pageContext.request.contextPath}/usermessage/?dw=1&bookid=${b.bookId}">&#19979;&#36733;</a></td>
          </tr>
          </c:forEach>
        </table>
      </c:if>
    	
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
