<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>Book Store</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" href="../css/lightbox.css" type="text/css" media="screen" />
<script src="../js/prototype.js" type="text/javascript"></script>
<script src="../js/scriptaculous.js?load=effects" type="text/javascript"></script>
<script src="../js/lightbox.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/java.js"></script></head>
<style>
.read{
margin-left:50px;
margin-top:50px;
left:50%;
width: 800px;
}
</style>
<body>

<div id="wrap">
   <div class="header">
    <div class="logo"><a href="../index.htm"><img src="${pageContext.request.contextPath}/images/login.gif" alt="" width="100" height="40" border="0" title="" /></a></div>
    <div id="menu">
      <ul>
         <li><a href="${pageContext.request.contextPath}/index">首页</a></li>
		 <c:forEach items="${requestScope.menulist}" var="m" >
           
           		<li><a href="${pageContext.request.contextPath}/show?id=${m.book_class_id}">${m.book_class_name}</a></li>
         
         </c:forEach>
         <li><a href="${pageContext.request.contextPath}/${tishi}">${message}</a></li>
         <li><a href="${pageContext.request.contextPath}/user/SelectBook">搜索</a></li>
         <li><a href="${pageContext.request.contextPath}/${zhuxiaolink}">${zhuxiao}</a></li>
      </ul>
    </div>
  </div>
  <!--end of center content-->
   <div class="read">
  <c:if test="${not empty mulu}">
  <p style="font-size: 20px"><a href="${pageContext.request.contextPath}/user/bookmessage?bookid=${bookid}">返回上一级</a></p>
  <p style="font-size: 30px">目录：</p><br>
    <table border="1" cellpadding="0" cellspacing="0">
   
  		<tr>
  		<c:forEach items="${listmulu}" var="mm">
  		<td>
  		<a style="color:#000 ;text-decoration:none;" href="${pageContext.request.contextPath}/user/bookReadPage?textid=${mm.arr}&bookid=${bookid}">${mm.text}</a> 
  		<c:if test="${mm.arr%4==0}"></td></tr><tr></c:if>
  		<c:if test="${mm.arr%4!=0}"></td></c:if>
  		</c:forEach>
  
  
   		
    </table>
  </c:if>
  <c:if test="${empty mulu}">
    <a href="${pageContext.request.contextPath}/user/bookReadPage?bookid=${bookid}">目录</a> <a href="${pageContext.request.contextPath}/user/bookReadPage?textid=${ar-1}&bookid=${bookid}">上一章</a>     <a href="${pageContext.request.contextPath}/user/bookReadPage?textid=${ar+1}&bookid=${bookid}">下一章</a>
  <center>${timu}</center>
  ${nei}
   <a href="${pageContext.request.contextPath}/user/bookReadPage?textid=${ar-1}&bookid=${bookid}">上一章</a>     <a href="${pageContext.request.contextPath}/user/bookReadPage?textid=${ar+1}&bookid=${bookid}">下一章</a>
  </c:if>
     </div>
<div class="footer">

  <div class="center_content">
   
    <!--end of left content-->
    <!--end of right content-->
    <div class="clear"></div>
  </div>
</div>
</div>
</body>


</html>
