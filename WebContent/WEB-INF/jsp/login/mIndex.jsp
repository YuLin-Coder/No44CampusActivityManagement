<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
   <title>后台管理中心</title>  
    <link rel="stylesheet" href="${ctx}/resource/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/resource/css/admin.css">
    <script src="${ctx}/resource/js/jquery.js"></script>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="${ctx}/resource/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />
         <span style="color: black;">后台管理中心</span>
    </h1>
  </div>
  <div class="head-l">
<a class="button button-little bg-green" href="${ctx}/login/index.do" target="_blank"><span class="icon-home"></span> 前台首页</a> 
&nbsp;&nbsp;<a class="button button-little bg-red" href="${ctx}/login/tuichu.do"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span><span style="color: black;">菜单列表</span></strong></div>
  <h2><span class="icon-user"></span>人物管理</h2>
  <ul style="display:block">
   
    <c:if test="${isManage != null }">
      <li><a href="${ctx}/role/findByObj.do" target="right"><span class="icon-caret-right"></span>角色管理</a></li>
       <li><a href="${ctx}/action/findByObj.do" target="right"><span class="icon-caret-right"></span>权限管理</a></li>
    <!--  private Integer type;// 1老师 2 社团3.用户 -->
       <li><a href="${ctx}/user/findByMap.do?type=2" target="right"><span class="icon-caret-right"></span>社团管理</a></li>
       <li><a href="${ctx}/user/findByMap.do?type=1" target="right"><span class="icon-caret-right"></span>老师管理</a></li>
       <li><a href="${ctx}/user/findByMap.do?type=3" target="right"><span class="icon-caret-right"></span>用户管理</a></li>
    </c:if>
    <c:if test="${isManage == null }">
	     <c:forEach items="${actions}" var="data" varStatus="l">
	       <c:if test="${data.name == 'jsgl' }">
	           <li><a href="${ctx}/role/findByObj.do" target="right"><span class="icon-caret-right"></span>角色管理</a></li>
	       </c:if>
	       <c:if test="${data.name == 'qxgl' }">
	           <li><a href="${ctx}/action/findByObj.do" target="right"><span class="icon-caret-right"></span>权限管理</a></li>
	       </c:if>
	       <c:if test="${data.name == 'stgl' }">
	          <li><a href="${ctx}/user/findByMap.do?type=2" target="right"><span class="icon-caret-right"></span>社团管理</a></li>
	       </c:if>
	       <c:if test="${data.name == 'lsgl' }">
	           <li><a href="${ctx}/user/findByMap.do?type=1" target="right"><span class="icon-caret-right"></span>老师管理</a></li>
	       </c:if>
	       <c:if test="${data.name == 'yhgl' }">
	           <li><a href="${ctx}/user/findByMap.do?type=3" target="right"><span class="icon-caret-right"></span>用户管理</a></li>
	       </c:if>

	     </c:forEach>
    </c:if>
 
    <!--  private Integer type;// 1老师 2 社团3.用户 -->
    
  </ul>   
  <h2><span class="icon-envelope-o"></span>活动管理</h2>
  <ul style="display:block">
   <c:if test="${isManage != null }">
       <li><a href="${ctx}/notice/findByObj.do" target="right"><span class="icon-caret-right"></span>公告管理</a></li>
       <li><a href="${ctx}/news/findByObj.do" target="right"><span class="icon-caret-right"></span>新闻管理</a></li>
       <li><a href="${ctx}/schoolInfo/findByObj.do" target="right"><span class="icon-caret-right"></span>校园风采管理</a></li>
       <li><a href="${ctx}/schoolJob/findByObj.do" target="right"><span class="icon-caret-right"></span>活动发布管理</a></li>
       <li><a href="${ctx}/xl/findByObj.do" target="right"><span class="icon-caret-right"></span>校历管理</a></li>
       <li><a href="${ctx}/xkzy/findByObj.do" target="right"><span class="icon-caret-right"></span>学科资源管理</a></li>
    </c:if>
     <c:if test="${isManage == null }">
	     <c:forEach items="${actions}" var="data" varStatus="l">
  
	         <c:if test="${data.name == 'gggl' }">
	             <li><a href="${ctx}/notice/findByObj.do" target="right"><span class="icon-caret-right"></span>公告管理</a></li>
	       </c:if>
	        <c:if test="${data.name == 'xwgl' }">
	            <li><a href="${ctx}/news/findByObj.do" target="right"><span class="icon-caret-right"></span>新闻管理</a></li>
	       </c:if>
	        <c:if test="${data.name == 'xyfc' }">
	             <li><a href="${ctx}/schoolInfo/findByObj.do" target="right"><span class="icon-caret-right"></span>校园风采管理</a></li>
	       </c:if>
	        <c:if test="${data.name == 'hdfb' }">
	              <li><a href="${ctx}/schoolJob/findByObj.do" target="right"><span class="icon-caret-right"></span>活动发布管理</a></li>
	       </c:if>
	        <c:if test="${data.name == 'xlgl' }">
	             <li><a href="${ctx}/xl/findByObj.do" target="right"><span class="icon-caret-right"></span>校历管理</a></li>
	       </c:if>
	     </c:forEach>
    </c:if>
 
  </ul>
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
  <li><a href="##" id="a_leader_txt">首页</a></li>
<li><b>当前语言：</b><span style="color:red;">中文</php></span>
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="${ctx}/login/welcome.do" name="right" width="100%" height="100%"></iframe>
</div>
<div style="text-align:center;">
</div>
</body>
</html>