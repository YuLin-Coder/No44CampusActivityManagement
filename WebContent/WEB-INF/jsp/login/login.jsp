<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" href="/ssm_xymh_sys/resource/css/xgxt_login.css">
    <script src="/ssm_xymh_sys/resource/js/jquery.js"></script>
<title>管理登陆</title>
</head>
<body>

<div id="header">
	<div class="header_title">
		<span class="title_con">校园活动发布和组织管理系统</span>
	</div>
</div>

<div id="content">
	<center>
		<div class="con">
		<div class="con_title">
			<span class="con_title_sp">校园活动发布和组织管理系统</span>
		</div>
		<div class="con_panel">
		   <form action="/ssm_xymh_sys/login/toLogin.do" method="Post">
			<div class="con_input">
				<span>用户名：</span><input type="text" placeholder="用户名" name="userName"/>
			</div>
			<div class="con_input">
				<span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span><input type="text" placeholder="密码" name="passWord"/>
			</div>
			<div class="con_select">
			<!-- type == 管理员 2 社团 3 教师 -->
				<input type="radio" name="type" value="3" checked/>教师
				<input type="radio" name="type" value="2" />社团
				<input type="radio" name="type" value="1" />管理员
			</div>
			<input type="submit" value="登    录" class="submit-btn"/>
			 </form>
		</div>
	</div>
	</center>
</div>


</body>
</html>