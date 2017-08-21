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

#nav { border: 1px solid #D4CD49; position:fixed;left:300;top:30%;
	   background: -webkit-gradient(linear, 0% 20%, 0% 92%, from(#f3f3f3), to(#fff), color-stop(.1,#f3f3f3));
    border-top: 1px solid #ccc;
    border-right: 1px solid #ccc;
    -webkit-border-bottom-right-radius: 60px 60px;
    -webkit-box-shadow: -1px 2px 2px rgba(0, 0, 0, 0.2);  margin: 20px auto;
    min-height: 150px;
    padding: 10px;
 }


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
            <form  id="myform" method="get" action="${pageContext.request.contextPath}/user/getMessagelist">
              <table class="search-tab">
                <tr>
                  <th width="120">查询方式:</th>
                  <td><select name="messageStatus" id="select">
                      <option value="10">全部查询</option>
                      <option value="0">已发布</option>
                      <option value="1">未发布</option>
                    </select>
                  </td>
                  <th width="70">输入项</th>
                  <td><input name="text" type="text" class="common-text" id="select"></td>
                  <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                </tr>
              </table>
            </form>
       
       
       
       
       
       
     
   <form name="myform" id="myform" method="post">
	<table class="result-tab" width="100%">
   	 <tr>
      <th width="8%">查看</th>
      <th width="10%">通知  标题</th>
      <th width="10%">通知时间</th>
      <th width="10%">创建人</th>
      <th width="10%">消息状态</th>
      <th width="15%"><center>修改</center></th>
      <th width="15%"><center>删除</center></th>

    </tr>
    <c:forEach items="${requestScope.messagelist}" var="u">
	    <tr>
	   		<td class='clis'>查看</td>
	   		<td>${u.messageTitle }</td>
	   		<td>${u.messageCreateTime }</td>
	   		<td>${u.user}</td>
	   		<td class="hide">${u.message }</td>
	   		<td>
	   			
	   			<c:if test="${u.messageStatus==0 }"><a href="${pageContext.request.contextPath}/master/exchangeMessage?messageId=${u.messageId}&status=1">已发布</a></c:if>
	   			<c:if test="${u.messageStatus==1 }"><a href="${pageContext.request.contextPath}/master/exchangeMessage?messageId=${u.messageId}&status=0">未发布</a></c:if>
	   		
	   		
	   		</td>
	   		<td><center><a href="${pageContext.request.contextPath}/master/updateMessage?messageId=${u.messageId }" >
	  		<img  src="${pageContext.request.contextPath}/images/update.jpg"/></a></center></td>
	  		 <td><center><a href="${pageContext.request.contextPath}/master/deketeMessage?messageId=${u.messageId }">
	  		<img  src="${pageContext.request.contextPath}/images/drop.jpg"/></a></center></td>
	   			
	    </tr>
    </c:forEach>
    
  </table>
<script type="text/javascript">
$(".hide").hide();
	$(".clis").mouseover(function(){
		var title=$(this).next().html();
		var createtime=$(this).next().next().html();
		var user=$(this).next().next().next().html();
		var message=$(this).next().next().next().next().html();
		$(this).append('<div id="nav" class="qq"><b>'+title+'</b><p>'+message+'</p>'+createtime+'<div style="algin:right;">user</div></div>');
	});
	$(".clis").mouseout(function(){
		$(".qq").remove();e
	});

</script>


    <div id="pagebar">
	
</div>
 </form>  

  </div> 
 </div>
</body>
</html>