<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
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
					<a href="../login/index.do">首页</a>&nbsp;>&nbsp;活动列表&nbsp;>&nbsp;
				</div>
				<div class="inside_box">
					<div class="ic_wrap fr">
						<h3 class="n_title">${obj.title}<span class="n_date"><fmt:formatDate value="${data.addTime}" type="both"/></span></h3>
						<p>${obj.content}</p>
						<div>
							<h3>评论信息</h3>
							<c:forEach items="${comments}" var="comment">
								<p>用户:${comment.username}&nbsp;&nbsp;&nbsp;&nbsp;内容:${comment.comment}&nbsp;&nbsp;&nbsp;&nbsp;时间:<fmt:formatDate value="${comment.time}" type="both"/></p>
							</c:forEach>
							<form action="${pageContext.request.contextPath}/schoolJob/joinJob.do">
								<input hidden name="jid" value="${obj.id}">
								<input hidden name="title" value="${obj.title}">
								<button type="submit" class="btn btn-success">报名活动</button>
							</form>
						</div>
						<div>
							<form action="${pageContext.request.contextPath}/schoolJob/addJobComment.do" method="post">
								<input hidden name="jid" value="${obj.id}">
								评论内容:<input name="comment" class="form-control">
								<button type="submit" class="btn btn-success">添加</button>
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
	</body>
</html>
</head>