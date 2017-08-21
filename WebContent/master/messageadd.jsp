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
          <div class="contact_form" style="width: 1227px; ">
            <div class="form_subtitle">��Ϣ����</div>
            	
            
              
            	
              <c:if test="${ empty m.message}">
             <form id="mform" action="${pageContext.request.contextPath}/master/insertMessage" method="post">
            	
            	
            	
            	</c:if>
              <c:if test="${not empty m.message}">
             <form id="mform" action="${pageContext.request.contextPath}/master/updateMessage" method="post">
            	
            	
            	
            	</c:if>
           
             <div class="form_row"  >
                <label class="contact"><strong>����:</strong></label>
                <input id="title" type="text" name="messageTitle"  value="${m.messageTitle}"  class="contact_input"   />
                <input id="title" type="hidden" name="messageId"  value="${m.messageId}"  class="contact_input"   />
             </div><br>
        	 <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
                <textarea id="nr" rows="10" cols="20" name="message" class="contact_input"  value="${m.message}">${m.message}</textarea>  
             </div>
        	 <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
                <textarea id="url" rows="2" cols="20" name="url"class="contact_input"  value="${m.url} "></textarea>
            </div>
         <div class="form_row">
        
                <div align="right">
                
                  <input  type="submit" value="ȷ��" name="sub" id="sub"/>
                  <script type="text/javascript">
         			$("#sub").click(function(){
         				$(".tis").remove();
         				var title=$("#title").val();
         				var nr=$("#nr").val();
         				var url=$("#url").val();
         				if(title=="" || title==null || title.length>10){
         					$("#title").after("&nbsp;<b class='tis'>����Ϊ��</b>");
         					return false;
         				}else if(nr=="" || nr==null || nr.length>1000){
         					$("#nr").after("<b class='tis'>����Ϊ��,���ȴ���1000��</b>");
         					return false;
         				}else{
         					 $('form').submit();
         				}
         				
         			});
         			
         			
         			
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