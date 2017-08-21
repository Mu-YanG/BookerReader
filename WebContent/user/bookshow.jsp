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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<div id="wrap">
  <div class="header">
    <div class="logo"><a href="../index.htm"><img src="${pageContext.request.contextPath}/images/login.gif" alt="" width="100" height="40" border="0" title="" /></a></div>
    <div id="menu">
      <ul>
         <li><a href="${pageContext.request.contextPath}/index">首页</a></li>
		  <c:forEach items="${menulist}" var="m" >
           
           		<li><a href="${pageContext.request.contextPath}/show?id=${m.book_class_id}">${m.book_class_name}</a></li>
         
         </c:forEach>
         <li><a href="${pageContext.request.contextPath}/${tishi}">${message}</a></li>
         <li><a href="${pageContext.request.contextPath}/user/SelectBook">搜索</a></li>
         <li><a href="${pageContext.request.contextPath}/${zhuxiaolink}">${zhuxiao}</a></li>
      </ul>
    </div>
  </div>
  <div class="center_content">
    <div class="left_content">
  
      <div class="title"><span class="title_icon"><img src="${pageContext.request.contextPath}/images/bullet1.gif" alt="" title="" /></span>${bc}&#31867;&#20070;&#31821;</div>
      
     
        
      
      <div class="new_products" >
      <c:if test="${not empty  bookshowlist }">
      <a href="${pageContext.request.contextPath}/show?id=${book_class_id}&num=${num-1}" class="continue">上一页</a> <a href="${pageContext.request.contextPath}/show?id=${book_class_id}&num=${num+1}" class="checkout">下一页&gt;</a> </div>
 		<br><br><br><br>
       <c:forEach items="${bookshowlist}" var="bookshow">
       
        <div class="new_prod_box"> <a href="${pageContext.request.contextPath}/user/bookmessage?bookid=${bookshow.bookId}">${bookshow.bookName }</a>
          <div class="new_prod_bg"> <a href="${pageContext.request.contextPath}/user/bookmessage?bookid=${bookshow.bookId}"><img src="${pageContext.request.contextPath}/bookfiles/${bookshow.bookId}/${bookshow.bookPhoto}"      width="90" height="100"     class="thumb" border="0" /></a> </div>
        </div>
   
       </c:forEach>
   </c:if>
      </div>
       
    </div>
    
    <!--end of left content-->
    <div class="right_content">
      
    ${sblist}
    </div>
    <div class="clear"></div>
  </div>
  <!--end of center content-->
    <!--end of center content--><div align="center"> 
  </div><div class="footer">
    <div class="left_footer"><br />
    </div>
</div>
</div>
</body>
</html>

</html>