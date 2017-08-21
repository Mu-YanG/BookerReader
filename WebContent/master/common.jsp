<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.2.min.js" type="text/javascript"></script>

 <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a  id="clicklink11" href="#">用户管理</a>
                    <ul class="sub-menu" id="clicklink1">
                        <li><a href="${pageContext.request.contextPath}/master/useradd.jsp">用户添加</a></li>
                        <li><a href="${pageContext.request.contextPath}/master/usermessage.jsp">用户查询</a></li>
						<li><a href="${pageContext.request.contextPath}/master/userupdate">用户修改</a></li>
                    </ul>
                </li>
                <li>
                    <a  id="clicklink12" href="#">书籍管理</a>
                    <ul class="sub-menu" id="clicklink2">
                        <li><a href="${pageContext.request.contextPath}/bookadd">书籍上架</a></li>
                        <li><a href="${pageContext.request.contextPath}/master/bookmessage">更新修改</a></li>
                         <li><a href="${pageContext.request.contextPath}/master/bookclass">书类管理</a></li>
                    </ul>
                    <a id="clicklink13"  href="#">动态管理</a>
                    <ul class="sub-menu" id="clicklink3">
                        <li><a href="${pageContext.request.contextPath}/user/getMessagelist">动态查阅</a></li>
                        <li><a href="${pageContext.request.contextPath}/master/messageadd.jsp?">添加通知</a></li>
                    </ul>
                    <a href="${pageContext.request.contextPath}/index">退出</a>
                </li>
            </ul>
        </div>
        
    </div>

<script type="text/javascript" >
	$("#clicklink1").hide();
	$("#clicklink2").hide();
	$("#clicklink3").hide();
	$("#clicklink11").click(function(){
	$("#clicklink1").toggle();
	});
	$("#clicklink12").click(function(){
	$("#clicklink2").toggle();
	});
	$("#clicklink13").click(function(){
	$("#clicklink3").toggle();
	});
	function check(boolean) {
		return false;
	}
</script>