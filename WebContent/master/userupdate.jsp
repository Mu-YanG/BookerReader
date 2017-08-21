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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/libs/modernizr.min.js">
    
    
    </script>
    <script type="text/javascript"> 
function DateSelector(selYear, selMonth, selDay) { 
this.selYear = selYear; 
this.selMonth = selMonth; 
this.selDay = selDay; 
this.selYear.Group = this; 
this.selMonth.Group = this; 
// ����ݡ��·������˵���Ӵ���onchange�¼��ĺ��� 
if (window.document.all != null) // IE 
{ 
this.selYear.attachEvent("onchange", DateSelector.Onchange); 
this.selMonth.attachEvent("onchange", DateSelector.Onchange); 
} 
else // Firefox 
{ 
this.selYear.addEventListener("change", DateSelector.Onchange, false); 
this.selMonth.addEventListener("change", DateSelector.Onchange, false); 
} 
if (arguments.length == 4) // ��������������Ϊ4�����һ����������ΪDate���� 
this.InitSelector(arguments[3].getFullYear(), arguments[3].getMonth() + 1, arguments[3].getDate()); 
else if (arguments.length == 6) // ��������������Ϊ6�����������������Ϊ��ʼ����������ֵ 
this.InitSelector(arguments[3], arguments[4], arguments[5]); 
else // Ĭ��ʹ�õ�ǰ���� 
{ 
var dt = new Date(); 
this.InitSelector(dt.getFullYear(), dt.getMonth() + 1, dt.getDate()); 
} 
} 
// ����һ�������ݵ����� 
DateSelector.prototype.MinYear = 1900; 
// ����һ�������ݵ����� 
DateSelector.prototype.MaxYear = (new Date()).getFullYear(); 
// ��ʼ����� 
DateSelector.prototype.InitYearSelect = function () { 
// ѭ�����OPIONԪ�ص����select������ 
for (var i = this.MaxYear; i >= this.MinYear; i--) { 
// �½�һ��OPTION���� 
var op = window.document.createElement("OPTION"); 
// ����OPTION�����ֵ 
op.value = i; 
// ����OPTION��������� 
op.innerHTML = i; 
// ��ӵ����select���� 
this.selYear.appendChild(op); 
} 
} 
// ��ʼ���·� 
DateSelector.prototype.InitMonthSelect = function () { 
// ѭ�����OPIONԪ�ص��·�select������ 
for (var i = 1; i < 13; i++) { 
// �½�һ��OPTION���� 
var op = window.document.createElement("OPTION"); 
// ����OPTION�����ֵ 
op.value = i; 
// ����OPTION��������� 
op.innerHTML = i; 
// ��ӵ��·�select���� 
this.selMonth.appendChild(op); 
} 
} 
// ����������·ݻ�ȡ���µ����� 
DateSelector.DaysInMonth = function (year, month) { 
var date = new Date(year, month, 0); 
return date.getDate(); 
} 
// ��ʼ������ 
DateSelector.prototype.InitDaySelect = function () { 
// ʹ��parseInt������ȡ��ǰ����ݺ��·� 
var year = parseInt(this.selYear.value); 
var month = parseInt(this.selMonth.value); 
// ��ȡ���µ����� 
var daysInMonth = DateSelector.DaysInMonth(year, month); 
// ���ԭ�е�ѡ�� 
this.selDay.options.length = 0; 
// ѭ�����OPIONԪ�ص�����select������ 
for (var i = 1; i <= daysInMonth; i++) { 
// �½�һ��OPTION���� 
var op = window.document.createElement("OPTION"); 
// ����OPTION�����ֵ 
op.value = i; 
// ����OPTION��������� 
op.innerHTML = i; 
// ��ӵ�����select���� 
this.selDay.appendChild(op); 
} 
} 
// ������ݺ��·�onchange�¼��ķ���������ȡ�¼���Դ���󣨼�selYear��selMonth�� 
// ����������Group���󣨼�DateSelectorʵ����������캯�����ṩ��InitDaySelect�������³�ʼ������ 
// ����eΪevent���� 
DateSelector.Onchange = function (e) { 
var selector = window.document.all != null ? e.srcElement : e.target; 
selector.Group.InitDaySelect(); 
} 
// ���ݲ�����ʼ�������˵�ѡ�� 
DateSelector.prototype.InitSelector = function (year, month, day) { 
// �����ⲿ�ǿ��Ե�������������������������ҲҪ��selYear��selMonth��ѡ����յ� 
// ������ΪInitDaySelect�����Ѿ���������������˵����������Ͳ����ظ������� 
this.selYear.options.length = 0; 
this.selMonth.options.length = 0; 
// ��ʼ���ꡢ�� 
this.InitYearSelect(); 
this.InitMonthSelect(); 
// �����ꡢ�³�ʼֵ 
this.selYear.selectedIndex = this.MaxYear - year; 
this.selMonth.selectedIndex = month - 1; 
// ��ʼ������ 
this.InitDaySelect(); 
// ����������ʼֵ 
this.selDay.selectedIndex = day - 1; 
} 
</script>
    
    
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="${pageContext.request.contextPath}/master/mindex.jsp" class="navbar-brand">231312312</a></h1>
        </div>
    </div>
</div>
<div class="container clearfix">
    <%@include file="/master/common.jsp" %>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><em></em><a href="${pageContext.request.contextPath}/mindex/" color="#white">��ҳ��</a></div>
        </div>
        <div class="result-wrap">
        
        
        <!-- �ò���Ϊ������idֵΪnullʱ���ṩ��ѯ�� ����master/message _controller-->
        
        
        
        <c:if test="${not empty emid}" >
        	
        	<form  id="myform" method="get" action="${pageContext.request.contextPath}/message">
              <table class="search-tab">
                <tr>
                  <th width="120">��ѯ��ʽ:</th>
                  <td><select name="search" id="select">
                      <option value="1">ȫ����ѯ</option>
                      <option value="2">id��ѯ</option>
                      
                      <option value="3"> ������ѯ</option>
                      <option value="4">�û�����ѯ</option>
                    
                    </select>
                  </td>
                  <th width="70">������</th>
                  <td><input name="text" type="text" class="common-text" id="select"></td>
                  <td><input class="btn btn-primary btn2" name="sub" value="��ѯ" type="submit"></td>
                </tr>
              </table>
         
            </form>
        	
        
        
        
        </c:if>
        
         <c:if test="${not empty emid1}" >
          <c:forEach items="${requestScope.userlist}" var="u">
         <div class="contact_form">
            <div class="form_subtitle">��Ϣ�޸�</div>
          
           
            
            <form  action="${pageContext.request.contextPath}/master/userupdate">
               <div class="form_row">
        
                <div align="right">
                
                  <input  type="submit"  name="write" value="�༭"/>
                </div>
              </div>
            	<div class="form_row">
                <label class="contact"><strong>�û���:</strong></label>${u.id}
              	
              </div>
              <div class="form_row">
              <label class="contact"><strong>ͷ��:</strong></label>
                 <img width="40" height="40" src="${pageContext.request.contextPath}/photos/userphoto/${u.photo}"/>
              	
              </div>
              <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
              ${u.name}
              </div>
              <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
               ${u.password}
              </div>
           
              <div class="form_row">
                <label class="contact"><strong>�Ա�:</strong></label>
                <c:if test="${u.sex==1}">��</c:if>
                <c:if test="${u.sex==0}">Ů</c:if>
                 </div>
              <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
              	${u.birthday}
             
              </div>
              <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
               ${u.emai}
              </div>
			  <div class="form_row">
                <label class="contact"><strong>�û����:</strong></label>
                <c:if test="${u.userClass==0}"> ����Ա   </c:if>
                 <c:if test="${u.userClass==1}"> ��ͨ�û� </c:if>
             </div>
             
              
          
            </form>
          </div>
          </c:forEach>
         </c:if>
      
        <c:if test="${not empty emid2}" >
         
        <c:forEach items="${requestScope.userlist}" var="u">
        
        
             <div class="result-wrap">
          <div class="contact_form">
            <div class="form_subtitle">�༭</div>
           
           
           
            <form  action="${pageContext.request.contextPath}/master/useradd" method="post"  enctype="multipart/form-data">
              
              
              <div class="form_row">
                <label class="contact"><strong>�û���:</strong></label>
                <input type="text" name="userid" class="contact_input" value="${u.id}"/>${tishi}  ${tishi1}
              </div>
              <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
                <input type="text" name="username"class="contact_input"  value="${u.name}" />
              </div>
              <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
                <input type="password" name="password"class="contact_input" value="${u.password}" />
              </div>
              <div class="form_row">
                <label class="contact"><strong>ȷ������:</strong></label>
                <input type="password2" name="password2"  class="contact_input"  value="${u.password}"/>${tishi2}
              </div>
              <div class="form_row">
                <label class="contact"><strong>�Ա�:</strong></label>
                <input type="radio" name="sex" value="1" checked="checked"/>
                ��
                <input type="radio" name="sex" value="0" />
                Ů </div>
              <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
                <!--
                <input type="text" name="birthday" class="contact_input" size="5"/>
              -->
             
               <select id="selYear" name="year"></select> ��
				<select id="selMonth" name="mouth"></select> ��
				<select id="selDay" name="day"></select> ��
				<script type="text/javascript"> 
				var selYear = window.document.getElementById("selYear"); 
				var selMonth = window.document.getElementById("selMonth"); 
				var selDay = window.document.getElementById("selDay"); 
				// �½�һ��DateSelector���ʵ����������select���󴫽�ȥ 
				new DateSelector(selYear, selMonth, selDay, 2004, 2, 29); 
				// Ҳ���������±ߵĴ��� 
				// var dt = new Date(2004, 1, 29); 
				// new DateSelector(selYear, selMonth ,selDay, dt); 
				</script> 
              
              
              </div>
              <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
                <input type="text" name="email"class="contact_input"  value="${u.emai}"/>${tishi3}
              </div>
			  <div class="form_row">
                <label class="contact"><strong>�û����:</strong></label>
                 <input type="radio" name="userclass" value="1" checked="checked"/>
                ��ͨ�û�
                <input type="radio" name="userclass" value="0" />
                ����Ա�û�              </div>
              <div class="form_row">
                <label class="contact"><strong>�ϴ�ͷ��</strong></label>
                <input type="file" name="file" class="contact_input" />
              </div>
          <div class="form_row">
        
                <div align="right">
                
                  <input  type="submit" value="����"/>
                </div>
              </div>
            </form>
          </div>
        </div>
         </c:forEach> 
        </c:if>
          
          
          
          
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>