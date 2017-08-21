<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<link rel="shortcut icon" href="http://mat1.gtimg.com/www/icon/favicon2.ico"/>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.2.min.js" type="text/javascript"></script>
<title>挑灯夜读网</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
  <style type="text/css">
<!--
body {
 margin: 0;
 padding: 0;
 font-size:12px;
}
#layout{
 background-color: #FFFFFF;
 border: 0;
 width: 400px;
 margin: 0 auto;
}
.list{
 margin: 0px 10px 20px;
 text-align: left; 
}

.list ul{
 list-style-type: none;
 margin: 0px;
 padding: 0px;
}

.list li{
 background: url(j20061214102450.gif) repeat-x bottom;
 /*列表底部的虚线*/
 width: 100%;
 list-style:none;
}
.list li a{
 color: #777777;
 display: block;
 padding: 0px 0px 4px 15px;
 background: url(q20061214102443.gif) no-repeat 0 6px;
 /*列表左边的箭头图片*/
}

.list li span{
 float: right;/*使span元素浮动到右面*/
 text-align: right;/*日期右对齐*/
}
.list li a:hover{
 color: #336699;
 background: url(d20061214102457.gif) repeat-x bottom;
}
-->
</style>
</head>
<body>
<div id="wrap">
  <div class="header">
    <div class="logo"><a href="index.htm"><img src="${pageContext.request.contextPath}/images/login.gif" alt="" width="100" height="40" border="0" title="" /></a></div>
    <div id="menu">
      <ul>
         <li class="selected"><a href="index">首页</a></li>
         
         <c:forEach items="${requestScope.menulist}" var="m" >
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
      <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>推荐</div>
      
      <div class="feat_prod_box">
      
      <c:forEach items="${requestScope.tjlist}" var="bookshow">
        <div class="prod_img"><a href="${pageContext.request.contextPath}/user/bookmessage?bookid=${bookshow.bookId}"><img src="${pageContext.request.contextPath}/bookfiles/${bookshow.bookId}/${bookshow.bookPhoto}" width="80" height="100" alt="12123" title="" border="0" /></a></div>
        <div class="prod_det_box">
          <div class="box_top"></div>
          <div class="box_center">
            <div class="prod_title">${bookshow.bookName }</div>
            <p class="details">${bookshow.bookCf}</p>
           <a href="#" class="more"> 作者：${bookshow.bookAuthor}</a>
            <div class="clear"></div>
          </div>
          <div class="box_bottom"></div>
        </div>
        <div class="clear"></div>
         </c:forEach>
      </div>

 
 
 
      <div class="title"><span class="title_icon"><img src="images/bullet2.gif" alt="" title="" /></span>站内动态：</div>
      <div class="new_products"><!--  新闻代码<script src="http://www.carnoc.com/js/carnocnews2.js"> </script> -->
         <div class="new_prod_box"> 
         <p>
         <div id="layout">
			<ul class="list" id="xiaoxin">
			<!-- <c:forEach items="${data}" var="m">
			<li><span>${m.messageCreateTime} </span><a href="${pageContext.request.contextPath}/user/${m.messageId}">${m.messageTitle}</a></li>
			</c:forEach> -->
			</ul>
	</div>
       <script type="text/javascript">
		$.getJSON("${pageContext.request.contextPath}/user/getMessageForJson",{},function(json){
			//jsonData = jQuery.parseJSON(list.d);
 		 	t="";
          $(json).each( function (i, m) {
           t+="<li><span>"+m.messageCreateTime+"</span><a href='${pageContext.request.contextPath}/user/"+m.messageId+"'>"+m.messageTitle+"</a></li>";
          });
          $("#xiaoxin").html(t);
    	 
 		
		},"json");
</script>
         
         
         
         
         
         
         
         
         
         </p>
        </div>
       
      </div>
      <div class="clear"></div>
    </div>
    <!--end of left content-->
    <div class="right_content">
    
    ${sblist}
   
    </div>
    <!--end of right content-->
    <div class="clear"></div>
  </div>
  <!--end of center content-->
  <div class="footer"></div>
</div>
</body>
</html>
