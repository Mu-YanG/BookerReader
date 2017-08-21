<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>Book Store</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/pl.css" />
<link rel="stylesheet" type="text/css" href="../css/smohan.face.css" />
<link rel="stylesheet" href="../css/lightbox.css" type="text/css" media="screen" />
<script src="../js/prototype.js" type="text/javascript"></script>
<script src="../js/scriptaculous.js?load=effects" type="text/javascript"></script>
<script src="../js/lightbox.js" type="text/javascript"></script>
<script src="../js/jquery-1.8.2.min.js" type="text/javascript"></script>
<script src="../js/smohan.face.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/java.js"></script></head>
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
  
  
  
  
  
  
  
  
  
  <div class="center_content">
    <div class="left_content">
      <div class="title"><span class="title_icon"><img src="../images/bullet1.gif" alt="" title="" /></span>图书介绍</div>
      <div class="feat_prod_box_details">
      <c:forEach items="${requestScope.bookshow}" var="j">
        <div class="prod_img"><a href="#"><img src="${pageContext.request.contextPath}/bookfiles/${j.bookId}/${j.bookPhoto}"  width="90" height="105" alt="" title="" border="0" /></a> <br />
          <br />
          <a href="${pageContext.request.contextPath}/user/bookReadPage?bookid=${j.bookId}"><img src="../images/zoom.jpg" alt="" width="53" height="33" border="0" title="" /></a></div>
        <div class="prod_det_box">
        
          <div class="box_center">
            <div class="prod_title">${j.bookName}</div>
            <p class="details">${j.bookCf}</p>
            
            <div class="price"><strong>收藏量:${count}</strong></div>
            
            <a href="${pageContext.request.contextPath}/${collectionurl}&${j.bookId}" class="more"><img src="${pageContext.request.contextPath}/images/${collection}" alt="" width="95" height="26" border="0" title="" /></a>
            ${message1}
            </c:forEach>
            <div class="clear">
          
            </div>
          </div>
          
        </div>
        <div class="clear">
        	<!-- Duoshuo Comment BEGIN -->
		<div class="ds-thread">
		<br>
		
		<div id="Smohan_FaceBox">
 <div class="price"><strong>书籍评论</strong></div>
   <textarea name="text" id="Smohan_text" class="smohan_text"></textarea>
   <p>
   <button class="button" id="b1">发表评论</button><br>
   <input type="hidden" id="hid" value="${sessionScope.id}" />
  <script type="text/javascript">

  	$("#b1").click(function(){
  		var test=$("#Smohan_text").val();
  		var test2=$("#hid").val();
  		if(test2==null || test2==""){
  			var boolean= confirm("对不起，您还未登录是否跳转到登录页面");
  			if(boolean==true){
  				window.location.href="${pageContext.request.contextPath}/login/";
  				return false;
  			}else{
  				return false;
  			}
  		}
  		if(test==null || test==""){
  		  		
  			alert("评论不能为空");
  			return false;
  		}else{
  			window.location.href="${pageContext.request.contextPath}/user/inTalk?bookId=${requestScope.bookid2}&message="+test;
  		}
  	});
 
  </script>
  
  
  
  
  <section class="comments">
  <c:if test="${empty requestScope.talkList }"><strong>还没有评论快来评论吧！</strong></c:if>
  <c:forEach items="${requestScope.talkList}" var="p">
	  <article class="comment">
		  <a class="comment-img" href="#non">
		  </a>
		  <div class="comment-body">
		    <p class="attribution">by&nbsp;<a href="#non">${p.userId }</a> at    ${p.createTimeshow } 
		    <!-- 管理员和本用户拥有评论删除功能 -->
		    	<c:if test="${p.userId==sessionScope.id || sessionScope.userclass==0}">
		    	<a href="${pageContext.request.contextPath}/user/deTalk?talkId=${p.talkId}&bookId=${requestScope.bookid2}">删除</a>
		    	</c:if>
		    </p>
		    <div class="text">
		      <p>${p.message}</p>
		    </div>
		  </div>
	</article>
  
  </c:forEach>
</section>
  
  
   
   
   
   
   
   
   
   
   
   </p>
</div>
		
		</div>
		
        </div>
      </div>
      <div class="clear"></div>
    </div>
    <!--end of left content-->
    <div class="right_content">
      
     ${sblist}

    </div>
    <!--end of right content-->
    <div class="clear">
  
    </div>
  </div>
  <!--end of center content-->
  <div class="footer"></div>
</div>
</body>
<script type="text/javascript">

var tabber1 = new Yetii({
id: 'demo'
});

</script>
</html>

