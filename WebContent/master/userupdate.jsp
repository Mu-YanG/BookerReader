<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

<head>
    
    <title>挑灯夜读网后台管理系统</title>
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
            <div class="crumb-list"><em></em><a href="${pageContext.request.contextPath}/mindex/" color="#white">首页〉</a></div>
        </div>
        <div class="result-wrap">
        
        
        <!-- 该部分为当传入id值为null时，提供查询表单 传到master/message _controller-->
        
        
        
        <c:if test="${not empty emid}" >
        	
        	<form  id="myform" method="get" action="${pageContext.request.contextPath}/message">
              <table class="search-tab">
                <tr>
                  <th width="120">查询方式:</th>
                  <td><select name="search" id="select">
                      <option value="1">全部查询</option>
                      <option value="2">id查询</option>
                      
                      <option value="3"> 姓名查询</option>
                      <option value="4">用户类别查询</option>
                    
                    </select>
                  </td>
                  <th width="70">输入项</th>
                  <td><input name="text" type="text" class="common-text" id="select"></td>
                  <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                </tr>
              </table>
         
            </form>
        	
        
        
        
        </c:if>
        
         <c:if test="${not empty emid1}" >
          <c:forEach items="${requestScope.userlist}" var="u">
         <div class="contact_form">
            <div class="form_subtitle">信息修改</div>
          
           
            
            <form  action="${pageContext.request.contextPath}/master/userupdate">
               <div class="form_row">
        
                <div align="right">
                
                  <input  type="submit"  name="write" value="编辑"/>
                </div>
              </div>
            	<div class="form_row">
                <label class="contact"><strong>用户名:</strong></label>${u.id}
              	
              </div>
              <div class="form_row">
              <label class="contact"><strong>头像:</strong></label>
                 <img width="40" height="40" src="${pageContext.request.contextPath}/photos/userphoto/${u.photo}"/>
              	
              </div>
              <div class="form_row">
                <label class="contact"><strong>姓名:</strong></label>
              ${u.name}
              </div>
              <div class="form_row">
                <label class="contact"><strong>密码:</strong></label>
               ${u.password}
              </div>
           
              <div class="form_row">
                <label class="contact"><strong>性别:</strong></label>
                <c:if test="${u.sex==1}">男</c:if>
                <c:if test="${u.sex==0}">女</c:if>
                 </div>
              <div class="form_row">
                <label class="contact"><strong>生日:</strong></label>
              	${u.birthday}
             
              </div>
              <div class="form_row">
                <label class="contact"><strong>邮箱:</strong></label>
               ${u.emai}
              </div>
			  <div class="form_row">
                <label class="contact"><strong>用户类别:</strong></label>
                <c:if test="${u.userClass==0}"> 管理员   </c:if>
                 <c:if test="${u.userClass==1}"> 普通用户 </c:if>
             </div>
             
              
          
            </form>
          </div>
          </c:forEach>
         </c:if>
      
        <c:if test="${not empty emid2}" >
         
        <c:forEach items="${requestScope.userlist}" var="u">
        
        
             <div class="result-wrap">
          <div class="contact_form">
            <div class="form_subtitle">编辑</div>
           
           
           
            <form  action="${pageContext.request.contextPath}/master/useradd" method="post"  enctype="multipart/form-data">
              
              
              <div class="form_row">
                <label class="contact"><strong>用户名:</strong></label>
                <input type="text" name="userid" class="contact_input" value="${u.id}"/>${tishi}  ${tishi1}
              </div>
              <div class="form_row">
                <label class="contact"><strong>姓名:</strong></label>
                <input type="text" name="username"class="contact_input"  value="${u.name}" />
              </div>
              <div class="form_row">
                <label class="contact"><strong>密码:</strong></label>
                <input type="password" name="password"class="contact_input" value="${u.password}" />
              </div>
              <div class="form_row">
                <label class="contact"><strong>确认密码:</strong></label>
                <input type="password2" name="password2"  class="contact_input"  value="${u.password}"/>${tishi2}
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
                <input type="text" name="email"class="contact_input"  value="${u.emai}"/>${tishi3}
              </div>
			  <div class="form_row">
                <label class="contact"><strong>用户类别:</strong></label>
                 <input type="radio" name="userclass" value="1" checked="checked"/>
                普通用户
                <input type="radio" name="userclass" value="0" />
                管理员用户              </div>
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
        </div>
         </c:forEach> 
        </c:if>
          
          
          
          
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>