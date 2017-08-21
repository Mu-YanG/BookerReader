<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

<head>
    
    <title>����ҹ������̨����ϵͳ</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style2.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/libs/modernizr.min.js"></script>
    
    
   
    
</head>
<body>

<div class="topbar-wrap white">
    <!--<div class="topbar-inner clearfix">-->
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="${pageContext.request.contextPath}/master/mindex.jsp" class="navbar-brand">管理员中�?</a></h1>
        </div>
    </div>

<div class="container clearfix">
      <div class="sidebar-content">
 <%@include file="/master/common.jsp" %>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><em></em><a href="${pageContext.request.contextPath}/mindex/" color="#white">��ҳ��</a></div>
        </div>
        <div class="result-wrap">
          <div class="contact_form">
            <div class="form_subtitle">ͼ���ϼ�</div>
           
           
            ${message}
            <form  action="${pageContext.request.contextPath}/master/bookaddfile" method="post"  enctype="multipart/form-data">
              
              
           
              <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
                <input type="text" name="bookname"  value=" "  class="contact_input"   />
              </div>
              <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
                <input type="text" name="author"   class="contact_input"  value=" " />
              </div>
               <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
                <select name ="bookclass">
              
               <c:forEach items="${bookclasslist}" var="m">
                <option value="${m.book_class_name}">${m.book_class_name}</option>
               
               
               </c:forEach>
               </select>
              </div>
        <div class="form_row">
                <label class="contact"><strong>���:</strong></label>
                <textarea rows="" cols="" name="cf"class="contact_input"  value=" "></textarea>
                
              </div>
              <div class="form_row">
                <label class="contact"><strong>�ϴ���Ƥ��</strong></label>
                <input type="file" name="file" class="contact_input"   value=" "/>
              </div>
              <div class="form_row">
                <label class="contact"><strong>�ϴ��ļ���</strong></label>
                <input type="file" name="file" class="contact_input"   value=" "/>
              </div>
          <div class="form_row">
        
                <div align="right">
                
                  <input  type="submit" value="${sumbmit}" name="sub" onclick="check()"/>
                  <script type="text/javascript">
            function length(str) {
					  ///<summary>����ַ���ʵ�ʳ��ȣ�����2��Ӣ��1</summary>
					  ///<param name="str">Ҫ��ó��ȵ��ַ���</param>
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
		alert("���벻��Ϊ��");
		document.getElementsByName('sub').valueOf("a");
		if(length(t3)>100){
			alter("��������С��100����");
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