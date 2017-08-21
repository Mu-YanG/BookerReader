<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

<head>
    
    <title>挑灯夜读网后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style2.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/libs/modernizr.min.js"></script>
    
    
   
    
</head>
<body>

<div class="topbar-wrap white">
    <!--<div class="topbar-inner clearfix">-->
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="${pageContext.request.contextPath}/master/mindex.jsp" class="navbar-brand">绠＄悊鍛樹腑蹇?</a></h1>
        </div>
    </div>

<div class="container clearfix">
    <%@include file="/master/common.jsp" %>
    <!--/sidebar-->
    <!-- 判断id是否为空为空就跳转查询 -->
    <c:if test="${not empty bookidboolean}">
    <form  id="myform" method="get" action="${pageContext.request.contextPath}/master/bookmessage">
              <table class="search-tab">
                <tr>
                  <th width="120">查询方式:</th>
                  <td><select name="search" id="select">
                      <option value="1">全部查询</option>
                      <option value="2">书号查询</option>
                      
                      <option value="3">按书名查询</option>
                      <option value="4">按类别查询</option>
                    
                    </select>
                  </td>
                  <th width="70">输入项</th>
                  <td><input name="text" type="text" class="common-text" id="select"></td>
                  <td><input class="btn btn-primary btn2" name="sub" value="点击查询" type="submit"></td>
                </tr>
              </table>
              ${tishi}
            </form>
    
    
    </c:if>
    
    <!--id非空选择修信息-->
    <c:if test="${not empty bookshow}">
     <div class="result-wrap">
          <div class="contact_form">
            <div class="form_subtitle">信息展示</div>
               
           <c:forEach items="${requestScope.booklist}" var="b"> 
            <form  action="${pageContext.request.contextPath}/master/bookupdate">
                 <div class="form_row">
        
                <div align="right">
                
                  <input  type="submit" value="编辑" name="sub" />
                	
                </div>
              </div>
          
           <div class="form_row">
                <label class="contact"><strong>封皮：</strong></label>
               <img width="200" height="230" src="${pageContext.request.contextPath}/bookfiles/${b.bookId}/${b.bookPhoto}"/>
              </div>
              <div class="form_row">
                <label class="contact"><strong>书号:</strong></label>
                ${b.bookId}
               
              </div>
              <div class="form_row">
                <label class="contact"><strong>书名:</strong></label>
               ${b.bookName}
              </div>
              <div class="form_row">
                <label class="contact"><strong>作者:</strong></label>
                ${b.bookAuthor}
              </div>
               <div class="form_row">
                <label class="contact"><strong>分类:</strong></label>
                ${b.bookClass}
              </div>
              <div class="form_row">
                <label class="contact"><strong>简介:</strong></label>
                ${b.bookCf}
 
              </div>
                
                  
            </form>
             </c:forEach>
          </div>
        </div>
    
    
    </c:if>
    
    
    
  <!--判断是否要求修改-->
  <c:if test="${not empty bookupdate}">
  
  
   <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><em></em><a href="${pageContext.request.contextPath}/mindex/" color="#white">首页〉</a></div>
        </div>
        <div class="result-wrap">
          <div class="contact_form">
            <div class="form_subtitle">修改</div>
           
          
            ${message}
            <form  action="${pageContext.request.contextPath}/master/bookupdatefile" method="post" enctype="multipart/form-data" >
              
              
           <c:forEach items="${requestScope.booklist}" var="m">
              <div class="form_row">
                <label class="contact"><strong>书名:</strong></label>
                <input type="text" name="bookname"  value="${m.bookName}"  class="contact_input"   />
              </div>
              <div class="form_row">
                <label class="contact"><strong>作者:</strong></label>
                <input type="text" name="author"   class="contact_input"  value="${m.bookAuthor}" />
              </div>
               <div class="form_row">
                <label class="contact"><strong>分类:</strong></label>
                <select name ="bookclass">
              
               <c:forEach items="${bookclasslist}" var="c">
                <option value="${c.book_class_name}">${c.book_class_name}</option>
               
               
               </c:forEach>
               </select>
              </div>
        <div class="form_row">
                <label class="contact"><strong>简介:</strong></label>
                <textarea rows="" cols="" name="cf"class="contact_input"  >${m.bookCf}</textarea>
                
              </div>
          </c:forEach>  
              <div class="form_row">
                <label class="contact"><strong>上传封皮：</strong></label>
                <input type="file" name="file" class="contact_input"   value=" "/>
              </div>
              <div class="form_row">
                <label class="contact"><strong>上传文件：</strong></label>
                <input type="file" name="file" class="contact_input"   value=" "/>
              </div>
          <div class="form_row">
        
                <div align="right">
                
                  <input  type="submit" value="确认提交" name="sub" onclick="check()"/>
                  <script type="text/javascript">
                  
        function check()
		{
		var t1=document.getElementsByName('bookname')[0];
		var t2=document.getElementsByName('author')[0];
		var t3=document.getElementsByName('cf')[0];
	
		var t4=document.getElementsByName('file')[0];
		
		
		if(t1.value =="" && t2.value == "" && t3.value == "" && t4.value == "" )
		{
		alert("输入不能为空");
		document.getElementsByName('sub').valueOf("a");
	
		}
		else{ }
		Window.location.href=''
		}
                  
                  
                  
                  </script>
                </div>
              </div>
            </form>
          </div>
        </div>
    </div>
  
  
  
  
  
  </c:if>
    <!--/main-->
</div>
</body>
