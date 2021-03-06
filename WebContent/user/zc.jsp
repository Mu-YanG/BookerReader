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
// 给年份、月份下拉菜单添加处理onchange事件的函数 
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
if (arguments.length == 4) // 如果传入参数个数为4，最后一个参数必须为Date对象 
this.InitSelector(arguments[3].getFullYear(), arguments[3].getMonth() + 1, arguments[3].getDate()); 
else if (arguments.length == 6) // 如果传入参数个数为6，最后三个参数必须为初始的年月日数值 
this.InitSelector(arguments[3], arguments[4], arguments[5]); 
else // 默认使用当前日期 
{ 
var dt = new Date(); 
this.InitSelector(dt.getFullYear(), dt.getMonth() + 1, dt.getDate()); 
} 
} 
// 增加一个最大年份的属性 
DateSelector.prototype.MinYear = 1900; 
// 增加一个最大年份的属性 
DateSelector.prototype.MaxYear = (new Date()).getFullYear(); 
// 初始化年份 
DateSelector.prototype.InitYearSelect = function () { 
// 循环添加OPION元素到年份select对象中 
for (var i = this.MaxYear; i >= this.MinYear; i--) { 
// 新建一个OPTION对象 
var op = window.document.createElement("OPTION"); 
// 设置OPTION对象的值 
op.value = i; 
// 设置OPTION对象的内容 
op.innerHTML = i; 
// 添加到年份select对象 
this.selYear.appendChild(op); 
} 
} 
// 初始化月份 
DateSelector.prototype.InitMonthSelect = function () { 
// 循环添加OPION元素到月份select对象中 
for (var i = 1; i < 13; i++) { 
// 新建一个OPTION对象 
var op = window.document.createElement("OPTION"); 
// 设置OPTION对象的值 
op.value = i; 
// 设置OPTION对象的内容 
op.innerHTML = i; 
// 添加到月份select对象 
this.selMonth.appendChild(op); 
} 
} 
// 根据年份与月份获取当月的天数 
DateSelector.DaysInMonth = function (year, month) { 
var date = new Date(year, month, 0); 
return date.getDate(); 
} 
// 初始化天数 
DateSelector.prototype.InitDaySelect = function () { 
// 使用parseInt函数获取当前的年份和月份 
var year = parseInt(this.selYear.value); 
var month = parseInt(this.selMonth.value); 
// 获取当月的天数 
var daysInMonth = DateSelector.DaysInMonth(year, month); 
// 清空原有的选项 
this.selDay.options.length = 0; 
// 循环添加OPION元素到天数select对象中 
for (var i = 1; i <= daysInMonth; i++) { 
// 新建一个OPTION对象 
var op = window.document.createElement("OPTION"); 
// 设置OPTION对象的值 
op.value = i; 
// 设置OPTION对象的内容 
op.innerHTML = i; 
// 添加到天数select对象 
this.selDay.appendChild(op); 
} 
} 
// 处理年份和月份onchange事件的方法，它获取事件来源对象（即selYear或selMonth） 
// 并调用它的Group对象（即DateSelector实例，请见构造函数）提供的InitDaySelect方法重新初始化天数 
// 参数e为event对象 
DateSelector.Onchange = function (e) { 
var selector = window.document.all != null ? e.srcElement : e.target; 
selector.Group.InitDaySelect(); 
} 
// 根据参数初始化下拉菜单选项 
DateSelector.prototype.InitSelector = function (year, month, day) { 
// 由于外部是可以调用这个方法，因此我们在这里也要将selYear和selMonth的选项清空掉 
// 另外因为InitDaySelect方法已经有清空天数下拉菜单，因此这里就不用重复工作了 
this.selYear.options.length = 0; 
this.selMonth.options.length = 0; 
// 初始化年、月 
this.InitYearSelect(); 
this.InitMonthSelect(); 
// 设置年、月初始值 
this.selYear.selectedIndex = this.MaxYear - year; 
this.selMonth.selectedIndex = month - 1; 
// 初始化天数 
this.InitDaySelect(); 
// 设置天数初始值 
this.selDay.selectedIndex = day - 1; 
} 
</script>
<body>
<div id="wrap">
  <div class="header">
    <div class="logo"><a href="../index.htm"><img src="../images/login.gif" alt="" width="100" height="40" border="0" title="" /></a></div>
    <div id="menu">
      <ul>
         <li ><a href="${pageContext.request.contextPath}/index">首页</a></li>
		 <c:forEach items="${requestScope.menulist}" var="m" >
         <li><a href="${pageContext.request.contextPath}/show?id=${m.book_class_id}">${m.book_class_name}</a></li>
         
         </c:forEach>  <li><a href="${pageContext.request.contextPath}/${tishi}">${message}</a></li>
         <li><a href="${pageContext.request.contextPath}/user/SelectBook">搜索</a></li>
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
                <label class="contact"><strong>用户名:</strong></label>
                <input type="text" name="userid" class="contact_input" />  ${tishi1}${tishi0}
              </div>
              <div class="form_row">
                <label class="contact"><strong>姓名:</strong></label>
                <input type="text" name="username"class="contact_input" />
              </div>
              <div class="form_row">
                <label class="contact"><strong>密码:</strong></label>
                <input type="password" name="password"class="contact_input" />
              </div>
              <div class="form_row">
                <label class="contact"><strong>确认密码:</strong></label>
                <input type="password2" name="password2"  class="contact_input" />${tishi2}
              </div>
              <div class="form_row">
                <label class="contact"><strong>性别:</strong></label>
                <input type="radio" name="sex" value="1" checked="checked"/>
                男
                <input type="radio" name="sex" value="0" />
                女 </div>
              <div class="form_row">
                <label class="contact"><strong>生日:</strong></label>
                <!--
                <input type="text" name="birthday" class="contact_input" size="5"/>
              -->
             
               <select id="selYear" name="year"></select> 年
				<select id="selMonth" name="mouth"></select> 月
				<select id="selDay" name="day"></select> 日
				<script type="text/javascript"> 
				var selYear = window.document.getElementById("selYear"); 
				var selMonth = window.document.getElementById("selMonth"); 
				var selDay = window.document.getElementById("selDay"); 
				// 新建一个DateSelector类的实例，将三个select对象传进去 
				new DateSelector(selYear, selMonth, selDay, 2004, 2, 29); 
				// 也可以试试下边的代码 
				// var dt = new Date(2004, 1, 29); 
				// new DateSelector(selYear, selMonth ,selDay, dt); 
				</script> 
              
              
              </div>
              <div class="form_row">
                <label class="contact"><strong>邮箱:</strong></label>
                <input type="text" name="email"class="contact_input" />${tishi3}
              </div>
			  
              <div class="form_row">
                <label class="contact"><strong>上传头像：</strong></label>
                <input type="file" name="file" class="contact_input" />
              </div>
          <div class="form_row">
        
                <div align="right">
                
                  <input  type="submit" value="点击注册"/>
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
                <label class="contact"><strong>用户名:</strong></label>
                <input type="text" name="userid" class="contact_input" value="${u.id}" />  ${tishi1}${tishi0}
              </div>
              <div class="form_row">
                <label class="contact"><strong>姓名:</strong></label>
                <input type="text" name="username"class="contact_input" value="${u.name}" />
              </div>
              <div class="form_row">
                <label class="contact"><strong>密码:</strong></label>
                <input type="password" name="password"class="contact_input"  value="${u.password}"/>
              </div>
              <div class="form_row">
                <label class="contact"><strong>确认密码:</strong></label>
                <input type="password" name="password2"  class="contact_input"  value="${u.password}"/>${tishi2}
              </div>
              <div class="form_row">
                <label class="contact"><strong>性别:</strong></label>
            
                <input type="radio" name="sex" value="1" checked="checked"/>
                   <c:if test="${u.sex==1}">男</c:if>
      <c:if test="${u.sex==0}">女</c:if>
                <input type="radio" name="sex" value="0" /> <c:if test="${u.sex==1}">女</c:if>
      <c:if test="${u.sex==0}">男</c:if>
                 </div>
              <div class="form_row">
                <label class="contact"><strong>生日:</strong></label>
                <!--
                <input type="text" name="birthday" class="contact_input" size="5"/>
              -->
             
               <select id="selYear" name="year"></select> 年
				<select id="selMonth" name="mouth"></select> 月
				<select id="selDay" name="day"></select> 日
				<script type="text/javascript"> 
				var selYear = window.document.getElementById("selYear"); 
				var selMonth = window.document.getElementById("selMonth"); 
				var selDay = window.document.getElementById("selDay"); 
				// 新建一个DateSelector类的实例，将三个select对象传进去 
				new DateSelector(selYear, selMonth, selDay, 2004, 2, 29); 
				// 也可以试试下边的代码 
				// var dt = new Date(2004, 1, 29); 
				// new DateSelector(selYear, selMonth ,selDay, dt); 
				</script> 
              
              
              </div>
              <div class="form_row">
                <label class="contact"><strong>邮箱:</strong></label>
                <input type="text" name="email"class="contact_input" value="${u.emai }" />${tishi3}
              </div>
			  
              <div class="form_row">
                <label class="contact"><strong>上传头像：</strong></label>
                <input type="file" name="file" class="contact_input" />
              </div>
          <div class="form_row">
        
                <div align="right">
                
                  <input  type="submit" value="保存"/>
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
