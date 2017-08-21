<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    
    <title>挑灯夜读网后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/libs/modernizr.min.js"></script></head>
    <style type="text/css">
* { margin:0; padding:0; text-decoration:none;}
#pagebar { width:500px; height:32px;  font-size:13px;margin:5px 0px -1px 400px; }
#pagebar a,#pagebar .page_now { display:block; float:left; margin-right:4px; padding:2px 5px; border:1px solid #000; color:#fw0; }



</style>
<body>
<div class="topbar-wrap white">
    <!--<div class="topbar-inner clearfix">-->
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">222</a></h1>
        </div>
</div>


     <%@include file="/master/common.jsp" %>

    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><em><a href="${pageContext.request.contextPath}/master/mindex.jsp" >首页〉</a></em></div>
        </div>
        <div class="result-wrap">
            <form  id="myform" method="get" action="${pageContext.request.contextPath}/master/bookmessage">
              <table class="search-tab">
                <tr>
                  <th width="120">查询方式:</th>
                  <td><select name="search" id="select">
                      <option value="1">全部查询</option>
                      <option value="2">书号查询</option>
                      
                      <option value="3">按书名查询</option>
                      <option value="4">按类别查询</option>
                    	<option value="5">按作者查询</option>
                    </select>
                  </td>
                  <th width="70">输入项</th>
                  <td><input name="text" type="text" class="common-text" id="select"></td>
                  <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                </tr>
              </table>
              ${tishi}
            </form>
       
    ${mess2}
        
        
       <c:if test="${not empty mess}">
       
       
       
       
       
       
       
   <form name="myform" id="myform" method="post">
	<table class="result-tab" width="100%">
   	 <tr>
      <th class="tc" width="11%">封皮</th>
      <th width="8%">书号</th>
      <th width="5%">书名</th>
      <th width="10%">作者</th>
      <th width="4%">分类 </th>
      <th width="10%">简介</th>
      <th width="15%"><center>修改</center></th>
      <th width="15%"><center>删除</center></th>

    </tr>
    <c:forEach items="${requestScope.booklist}" var="b">
    <tr>
      <td height="10" class="tc"><img width="40" height="40" src="${pageContext.request.contextPath}/bookfiles/${b.bookId}/${b.bookPhoto}"/></td>
      <td>${b.bookId}</td>
     
      <td>${b.bookName } </td>
      <td>${b.bookAuthor}</td>
       
      
      <td>${b.bookClass}</td>
      <td>${b.bookCfa }</td>
	 
	  <td><center><a href="${pageContext.request.contextPath}/master/bookupdate?bookid=${b.bookId}" >
	  		<img  src="${pageContext.request.contextPath}/images/update.jpg"/></a></center></td>
	   <td><center><a href="${pageContext.request.contextPath}/master/bookmessage?bookid=${b.bookId}&search=${search}&text=${text}&delete=1&a=${a}">
	  		<img  src="${pageContext.request.contextPath}/images/drop.jpg"/></a></center></td>
	  	
    </tr>
    
    
    </c:forEach>
    
  </table>



   <div id="pagebar">
<a href="${pageContext.request.contextPath}/master/bookmessage?a=${a-1}&search=${search}&text=${text}">上</a>
<c:forEach items="${requestScope.arr}" var="rr">
<a href="${pageContext.request.contextPath}/master/bookmessage?a=${rr-1}&search=${search}&text=${text}" ><c:out value="${rr}"/></a>

</c:forEach>



<a href="${pageContext.request.contextPath}/master/bookmessage?a=${a+1}&search=${search}&text=${text}">下</a>
</div>
 </form>  

 </c:if> 
  </div> 
 </div>
</body>
</html>