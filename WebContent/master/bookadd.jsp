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
      <div class="sidebar-content">
 <%@include file="/master/common.jsp" %>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><em></em><a href="${pageContext.request.contextPath}/mindex/" color="#white">首页〉</a></div>
        </div>
        <div class="result-wrap">
          <div class="contact_form">
            <div class="form_subtitle">图书上架</div>
           
           
            ${message}
            <form  action="${pageContext.request.contextPath}/master/bookaddfile" method="post"  enctype="multipart/form-data">
              
              
           
              <div class="form_row">
                <label class="contact"><strong>书名:</strong></label>
                <input type="text" name="bookname"  value=" "  class="contact_input"   />
              </div>
              <div class="form_row">
                <label class="contact"><strong>作者:</strong></label>
                <input type="text" name="author"   class="contact_input"  value=" " />
              </div>
               <div class="form_row">
                <label class="contact"><strong>分类:</strong></label>
                <select name ="bookclass">
              
               <c:forEach items="${bookclasslist}" var="m">
                <option value="${m.book_class_name}">${m.book_class_name}</option>
               
               
               </c:forEach>
               </select>
              </div>
        <div class="form_row">
                <label class="contact"><strong>简介:</strong></label>
                <textarea rows="" cols="" name="cf"class="contact_input"  value=" "></textarea>
                
              </div>
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
                
                  <input  type="submit" value="${sumbmit}" name="sub" onclick="check()"/>
                  <script type="text/javascript">
            function length(str) {
					  ///<summary>获得字符串实际长度，中文2，英文1</summary>
					  ///<param name="str">要获得长度的字符串</param>
					  var realLength = 0, len = str.length, charCode = -1;
					  for (var i = 0; i < len; i++) {
					    charCode = str.charCodeAt(i);
					    if (charCode >= 0 && charCode <= 128) realLength += 1;
					    else realLength += 2;
					  }
					  return realLength;
					};
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
		if(length(t3)>100){
			alter("请输入简介小于100个字");
		}
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
    <!--/main-->
</div>
</body>
</html>