<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>Book Store</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
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
<body>
<div id="wrap">
  <div class="header">
    <div class="logo"><a href="../index.htm"><img src="../images/login.gif" alt="" width="100" height="40" border="0" title="" /></a></div>
    <div id="menu">
      <ul>
         <li ><a href="${pageContext.request.contextPath}/index">��ҳ</a></li>
		 <c:forEach items="${requestScope.menulist}" var="m" >
         <li><a href="${pageContext.request.contextPath}/show?id=${m.book_class_id}">${m.book_class_name}</a></li>
         
         </c:forEach>  <li><a href="${pageContext.request.contextPath}/${tishi}">${message}</a></li>
         <li><a href="${pageContext.request.contextPath}/user/SelectBook">����</a></li>
      </ul>
    </div>
  </div>
  <div class="center_content">
    <div class="left_content">
    <c:if test="${empty xg}">
    <div class="contact_form">
            <div class="form_subtitle">${title}</div>
           
           
           
            <form  action="${pageContext.request.contextPath}/user/zcfile" method="post"  enctype="multipart/form-data">
              
              
              <div class="form_row">
                <label class="contact"><strong>�û���:</strong></label>
                <input type="text" name="userid" class="contact_input" />  ${tishi1}${tishi0}
              </div>
              <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
                <input type="text" name="username"class="contact_input" />
              </div>
              <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
                <input type="password" name="password"class="contact_input" />
              </div>
              <div class="form_row">
                <label class="contact"><strong>ȷ������:</strong></label>
                <input type="password2" name="password2"  class="contact_input" />${tishi2}
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
                <input type="text" name="email"class="contact_input" />${tishi3}
              </div>
			  
              <div class="form_row">
                <label class="contact"><strong>�ϴ�ͷ��</strong></label>
                <input type="file" name="file" class="contact_input" />
              </div>
          <div class="form_row">
        
                <div align="right">
                
                  <input  type="submit" value="���ע��"/>
                </div>
              </div>
            </form>
          </div>
            </c:if>
    </div>
   
    <c:if test="${ not empty xg}">
    <c:forEach items="${userlist}" var="u" >
   		<div class="contact_form" s style="width: 433px; ">
            <div class="form_subtitle">${title}</div>
           
           
           
            <form  action="${pageContext.request.contextPath}/user/zcfile" method="post"  enctype="multipart/form-data">
              
              
              <div class="form_row">
                <label class="contact"><strong>�û���:</strong></label>
                <input type="text" name="userid" class="contact_input" value="${u.id}" />  ${tishi1}${tishi0}
              </div>
              <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
                <input type="text" name="username"class="contact_input" value="${u.name}" />
              </div>
              <div class="form_row">
                <label class="contact"><strong>����:</strong></label>
                <input type="password" name="password"class="contact_input"  value="${u.password}"/>
              </div>
              <div class="form_row">
                <label class="contact"><strong>ȷ������:</strong></label>
                <input type="password" name="password2"  class="contact_input"  value="${u.password}"/>${tishi2}
              </div>
              <div class="form_row">
                <label class="contact"><strong>�Ա�:</strong></label>
            
                <input type="radio" name="sex" value="1" checked="checked"/>
                   <c:if test="${u.sex==1}">��</c:if>
      <c:if test="${u.sex==0}">Ů</c:if>
                <input type="radio" name="sex" value="0" /> <c:if test="${u.sex==1}">Ů</c:if>
      <c:if test="${u.sex==0}">��</c:if>
                 </div>
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
                <input type="text" name="email"class="contact_input" value="${u.emai }" />${tishi3}
              </div>
			  
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
    
    </c:forEach>
    </c:if>
    
    
    
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
