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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.min.js"></script>
    </head>



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
            <form  id="myform" method="get" action="${pageContext.request.contextPath}/message">
              <table class="search-tab">
                <tr>
                  <th width="120">查询方式:</th>
                  <td><select name="search" id="select">
                      <option value="1">全部查询</option>
                      <option value="2">id查询</option>
                      
                      <option value="3"> 姓名查询</option>
                      <option value="4">用户类别查询</option>
                    
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
      <th class="tc" width="11%">照片</th>
      <th width="8%">用户ID</th>
      <th width="5%">用户名</th>
      <th width="10%">用户密码</th>
      <th width="4%">性别 </th>
      <th width="10%">出生日期</th>
      <th width="18%">电子邮件</th>
      <th width="10%">用户类别</th>
      <th width="15%"><center>修改</center></th>
      <th width="15%"><center>删除</center></th>

    </tr>
    <c:forEach items="${requestScope.userlist}" var="u">
    <tr>
      <td height="10" class="tc"><img width="40" height="40" src="${pageContext.request.contextPath}/photos/userphoto/${u.photo}"/></td>
      <td>${u.id}</td>
      <!--鏍囩ID-->
      <td>${u.name } </td>
      <td>${u.password}</td>
       
      <td><c:if test="${u.sex==1}">男</c:if>
      <c:if test="${u.sex==0}">女</c:if>
      </td>
      <td>${u.birthday}</td>
      <td>${u.emai }</td>
	  <td class="t2"><c:if test="${u.userClass==0}">管理员</c:if><c:if test="${u.userClass==1}">普通用户</c:if></td>
	  <td><center><a class="t1" href="${pageContext.request.contextPath}/master/userupdate?userid=${u.id}" >
	  		<img  src="${pageContext.request.contextPath}/images/update.jpg"/></a></center></td>
	   <td><center><a class="t1" href="${pageContext.request.contextPath}/message?userid=${u.id}&search=${search}&text=${text}&delete=1&a=${a}">
	  		<img  src="${pageContext.request.contextPath}/images/drop.jpg"/></a></center></td>
	  	
    </tr>
    
    
    </c:forEach>
    
  </table>



   <div id="pagebar">
<a href="${pageContext.request.contextPath}/message?a=${a-1}&search=${search}&text=${text}">上</a>
<c:forEach items="${requestScope.arr}" var="rr">
<a href="${pageContext.request.contextPath}/message?a=${rr-1}&search=${search}&text=${text}" ><c:out value="${rr}"/></a>

</c:forEach>



<a href="${pageContext.request.contextPath}/message?a=${a+1}&search=${search}&text=${text}">下</a>
</div>
 </form>  

 </c:if> 
  </div> 
 </div>
</body>
</html>