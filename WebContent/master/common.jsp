<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.2.min.js" type="text/javascript"></script>

 <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>�˵�</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a  id="clicklink11" href="#">�û�����</a>
                    <ul class="sub-menu" id="clicklink1">
                        <li><a href="${pageContext.request.contextPath}/master/useradd.jsp">�û����</a></li>
                        <li><a href="${pageContext.request.contextPath}/master/usermessage.jsp">�û���ѯ</a></li>
						<li><a href="${pageContext.request.contextPath}/master/userupdate">�û��޸�</a></li>
                    </ul>
                </li>
                <li>
                    <a  id="clicklink12" href="#">�鼮����</a>
                    <ul class="sub-menu" id="clicklink2">
                        <li><a href="${pageContext.request.contextPath}/bookadd">�鼮�ϼ�</a></li>
                        <li><a href="${pageContext.request.contextPath}/master/bookmessage">�����޸�</a></li>
                         <li><a href="${pageContext.request.contextPath}/master/bookclass">�������</a></li>
                    </ul>
                    <a id="clicklink13"  href="#">��̬����</a>
                    <ul class="sub-menu" id="clicklink3">
                        <li><a href="${pageContext.request.contextPath}/user/getMessagelist">��̬����</a></li>
                        <li><a href="${pageContext.request.contextPath}/master/messageadd.jsp?">���֪ͨ</a></li>
                    </ul>
                    <a href="${pageContext.request.contextPath}/index">�˳�</a>
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