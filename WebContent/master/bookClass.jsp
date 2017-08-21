<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/libs/modernizr.min.js">
    
    
    </script>
     
</head>
<body>
<div class="topbar-wrap white">
    <!--<div class="topbar-inner clearfix">-->
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="${pageContext.request.contextPath}/master/mindex.jsp" class="navbar-brand">管理员中�?</a></h1>
        </div>
    </div>

<div class="container clearfix">
     <%@include file="/master/common.jsp" %>
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><em></em><a href="${pageContext.request.contextPath}/mindex/" color="#white">��ҳ��</a></div>
        </div>
        <div class="result-wrap">
          <div class="contact_form">
            <div class="form_subtitle">�������</div>
           
           
           
            
               <table class="result-tab" width="100%">
                
             
				   	 <tr>
				      <th width="8%">ID</th>
				      <th width="5%">����</th>
				       <th width="5%">�޸�</th>
				        <th width="5%">ɾ��</th>
				    </tr>
				      <c:forEach items="${bookclass}" var="m">
             			<tr>
             			<td>${m.book_class_id}</td>
             			<td>${m.book_class_name}</td>
             			<td><a href="${pageContext.request.contextPath}/master/bookclass?sub=xx&classid=${m.book_class_id}&classname=${m.book_class_name}">�޸�</a></td>
             			<td><a href="${pageContext.request.contextPath}/master/bookclass?sub=dd&classid=${m.book_class_id}">ɾ��</a></td>
             			
             			</tr>
             			
              		  </c:forEach>
                	<c:if test="${not empty add}">
                	<form  action="${pageContext.request.contextPath}/master/bookclass" >
                	<tr>
                	<td>${classid}</td>
                	<td><input type="text" name="classname"/></td>
                	<td><input type="submit" name="sub" value ="����"/></td>
                	
                	
                	</tr>
                	 </form>
                	
                	</c:if>
                	<c:if test="${not empty xxx}">
                	<form  action="${pageContext.request.contextPath}/master/bookclass" >
                	<tr>
                	<td>${classid}</td>
                	<td><input type="text" name="classname" value="${classname}"/></td>
                	<td><input type="submit" name ="sub" value="ȷ���޸�" /></td>
                	
                	
                	</tr>
                	</form>
                	</c:if>
                	
				    </table>
  				<a href="${pageContext.request.contextPath}/master/bookclass?sub=add">���</a>
                    
              </div>
     
          </div>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>