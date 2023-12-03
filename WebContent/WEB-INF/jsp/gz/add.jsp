<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>校园门户网站首页</title>
		<link rel="stylesheet" type="text/css" href="/ssm_xymh_sys/resource/css/style.css" />
		<link rel="stylesheet" type="text/css" href="/ssm_xymh_sys/resource/css/popup.css"/>
			<script src="/ssm_xymh_sys/resource/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<body>
		<%@ include file="/common/menu.jsp" %>
		<!--inside content start-->
	<div class="inside_wrap">
			<div class="inside_con">
				<div class="adr">
					<a href="../login/index.do">首页</a>&nbsp;>&nbsp;我的关注&nbsp;>&nbsp;
				</div>
				<div class="inside_box">
					<div class="ic_wrap fr">
				
						<div style="width: 100px;margin: 0 auto">
							<form action="/ssm_xymh_sys/gz/exAdd.do">
							  <ul>
				
	   <c:forEach items="${lise}" var="data" varStatus="l">
	       
	       <c:if test="${data.isCheck == 1 }">
	        <li> <input type="checkbox" name="mgz" value="${data.num }" checked="checked">${data.name }</li>
	       </c:if>
	        <c:if test="${data.isCheck != 1 }">
	        <li> <input type="checkbox" name="mgz" value="${data.num }">${data.name }</li>
	       </c:if>
       </c:forEach>
							  </ul>
						    <input type="submit" class="button" title="Sign In" value="提交" style="height: 20px;width: 80px;">
						</form>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<!--footer start-->
		<%@ include file="/common/footer.jsp" %>
		
			<script src="/ssm_xymh_sys/resource/js/jquery.SuperSlide.2.1.js" type="text/javascript"></script>
		<script src="/ssm_xymh_sys/resource/js/common.js"></script>
		<script src="/ssm_xymh_sys/resource/js/jquery.slides.min..js"></script>
	
	</body>
</html>
</head>