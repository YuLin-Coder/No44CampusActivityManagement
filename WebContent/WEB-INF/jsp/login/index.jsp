<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>校园活动发布和组织管理首页</title>
		<link rel="stylesheet" type="text/css" href="/ssm_xymh_sys/resource/css/style.css" />
		<link rel="stylesheet" type="text/css" href="/ssm_xymh_sys/resource/css/popup.css"/>
		<style type="text/css">
		.banner{width:1100px;margin:0 auto;}
		#focus{width:1100px;height:280px;overflow:hidden;position:relative;}
#focus ul{height:380px;position:absolute;}
#focus ul li{float:left;width:1100px;height:280px;overflow:hidden;position:relative;background:#000;}
#focus ul li div{position:absolute;overflow:hidden;}
#focus .btnBg{position:absolute;width:1100px;height:20px;left:0;bottom:0;background:#000;}
#focus .btn{position:absolute;width:1080px;height:10px;padding:5px 10px;right:0;bottom:0;text-align:right;}
#focus .btn span{display:inline-block;_display:inline;_zoom:1;width:25px;height:10px;_font-size:0;margin-left:5px;cursor:pointer;background:#fff;}
#focus .btn span.on{background:#fff;}
#focus .preNext{width:45px;height:100px;position:absolute;top:90px;background:url(img/sprite.png) no-repeat 0 0;cursor:pointer;}
#focus .pre{left:0;}
#focus .next{right:0;background-position:right top;}
		
		</style>
			<script src="/ssm_xymh_sys/resource/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	var sWidth = $("#focus").width(); //获取焦点图的宽度（显示面积）
	var len = $("#focus ul li").length; //获取焦点图个数
	var index = 0;
	var picTimer;
	
	//以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
	var btn = "<div class='btnBg'></div><div class='btn'>";
	for(var i=0; i < len; i++) {
		btn += "<span></span>";
	}
	btn += "</div><div class='preNext pre'></div><div class='preNext next'></div>";
	$("#focus").append(btn);
	$("#focus .btnBg").css("opacity",0.5);

	//为小按钮添加鼠标滑入事件，以显示相应的内容
	$("#focus .btn span").css("opacity",0.4).mouseover(function() {
		index = $("#focus .btn span").index(this);
		showPics(index);
	}).eq(0).trigger("mouseover");

	//上一页、下一页按钮透明度处理
	$("#focus .preNext").css("opacity",0.2).hover(function() {
		$(this).stop(true,false).animate({"opacity":"0.5"},300);
	},function() {
		$(this).stop(true,false).animate({"opacity":"0.2"},300);
	});

	//上一页按钮
	$("#focus .pre").click(function() {
		index -= 1;
		if(index == -1) {index = len - 1;}
		showPics(index);
	});

	//下一页按钮
	$("#focus .next").click(function() {
		index += 1;
		if(index == len) {index = 0;}
		showPics(index);
	});

	//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
	$("#focus ul").css("width",sWidth * (len));
	
	//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
	$("#focus").hover(function() {
		clearInterval(picTimer);
	},function() {
		picTimer = setInterval(function() {
			showPics(index);
			index++;
			if(index == len) {index = 0;}
		},4000); //此4000代表自动播放的间隔，单位：毫秒
	}).trigger("mouseleave");
	
	//显示图片函数，根据接收的index值显示相应的内容
	function showPics(index) { //普通切换
		var nowLeft = -index*sWidth; //根据index值计算ul元素的left值
		$("#focus ul").stop(true,false).animate({"left":nowLeft},300); //通过animate()调整ul元素滚动到计算出的position
		//$("#focus .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
		$("#focus .btn span").stop(true,false).animate({"opacity":"0.4"},300).eq(index).stop(true,false).animate({"opacity":"1"},300); //为当前的按钮切换到选中的效果
	}
});

</script>
</head>
<body>
<!-- model.addAttribute("phs", phs);
		model.addAttribute("news", news);
		model.addAttribute("notices", notices);
		model.addAttribute("sjs", sjs);
		model.addAttribute("xls", xls); -->

		<%@ include file="/common/menu.jsp" %>
		<!--banner start-->
		<div class="banner">
			<div id="focus">
			<ul>
			 <c:forEach items="${phs}" var="data" varStatus="l">
			    <li><a><img src="${ctx }/${data.content}"  style="height: 280px;width: 1100px;"/></a></li>
             </c:forEach>
			</ul>
		</div><!--focus end-->
		</div>
		<!--notice start-->
		<div class="notice_wrap">
			<div class="notice">
				<div class="bd fl">
					<b>通知公告：</b>
					<ul class="infoList">
					 <c:forEach items="${notices}" var="data" varStatus="l">
					<li><span class="date">[<fmt:formatDate value="${data.addTime}" type="both"/>]</span><a href="${ctx }/notice/detail.do?id=${data.id}">${data.title}</a></li>
		             </c:forEach>
					</ul>
				</div>
				<a href="/ssm_xymh_sys/notice/findByObj2.do" class="more fr">更多+</a>
				<div class="clear"></div>
			</div>
		</div>
		<!--content start-->
		<div class="content">
			<!--about、news and hot-->
			<div class="box1">
				<div class="fl">
					<div class="news">
						<div class="b_title">
							<h3>新闻快讯</h3>
							<a href="/ssm_xymh_sys/news/findByObj2.do" class="more">更多+</a>
						</div>
						<div class="b_wrap">
							<div class="fl n_list">
								<ul>
								 <c:forEach items="${news}" var="data" varStatus="l">
								 <li><a href="${ctx }/news/detail.do?id=${data.id}">${data.title }</a> <span style="display: inline-block;float: right;"><fmt:formatDate value="${data.addTime}" type="both"/></span></li>
		                          </c:forEach>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
				<div class="fl">
					<div class="news" style="margin-left: 30px">
						<div class="b_title">
							<h3>活动列表</h3>
							<a href="/ssm_xymh_sys/schoolJob/findByObj2.do" class="more">更多+</a>
						</div>
						<div class="b_wrap">
							<div class="fl n_list">
								<ul>
								 <c:forEach items="${sjs}" var="data" varStatus="l">
								 <li><a href="${ctx }/schoolJob/detail.do?id=${data.id}">${data.title }</a> <span style="display: inline-block;float: right;"><fmt:formatDate value="${data.addTime}" type="both"/></span></li>
		                          </c:forEach>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<!--resource and exam-->
			<div class="box3">
			<div class="fl">
					<div class="news" style="height: 250px;margin-top: 0px">
						<div class="b_title">
							<h3>通知公告</h3>
							<a href="/ssm_xymh_sys/notice/findByObj2.do" class="more">更多+</a>
						</div>
						<div class="b_wrap">
							<div class="fl n_list">
								<ul>
								 <c:forEach items="${notices}" var="data" varStatus="l">
								 <li><a href="${ctx }/notice/detail.do?id=${data.id}">${data.title }</a> <span style="display: inline-block;float: right;"><fmt:formatDate value="${data.addTime}" type="both"/></span></li>
		                          </c:forEach>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
				<div class="exam fr">
					<div class="b_title">
						<h3>校历</h3>
						<a href="/ssm_xymh_sys/xl/findByObj2.do" class="more">更多+</a>
					</div>
					<div class="b_wrap">
						<div class="n_list">
							<ul>
							 <c:forEach items="${xls}" var="data" varStatus="l">
							 <li><a>${data.content}</a><span class="plan">${data.riqi }</span></li>
				             </c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<!--friendly link-->
			<div class="friendlink2" style="margin-top: 20px">
			</div>
		</div>
		
		<%@ include file="/common/footer.jsp" %>
	
		<script src="/ssm_xymh_sys/resource/js/jquery.SuperSlide.2.1.js" type="text/javascript"></script>
		<script src="/ssm_xymh_sys/resource/js/common.js"></script>
		<script src="/ssm_xymh_sys/resource/js/jquery.slides.min..js"></script>
		<script type="text/javascript">
		//	jQuery(".banner").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"fold",  autoPlay:true, autoPage:true, trigger:"click" });
			jQuery(".notice").slide({mainCell:".bd ul",autoPage:true,effect:"top",autoPlay:true,vis:1});
			jQuery(".schoolmate .b_wrap").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"left",autoPlay:true,vis:5,trigger:"click"});
		</script>
	</body>
	<script type="text/javascript">
	$(function(){  
		$("#slides").slidesjs({ width: 940, height: 528 });  
		});  
	
	</script>
</html>