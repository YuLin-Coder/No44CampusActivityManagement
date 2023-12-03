<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
   <title>易购后台管理中心</title>  
    <link rel="stylesheet" href="/ssm_xymh_sys/resource/css/pintuer.css">
    <link rel="stylesheet" href="/ssm_xymh_sys/resource/css/admin.css">
    <script src="/ssm_xymh_sys/resource/js/jquery.js"></script>
     <script src="/ssm_xymh_sys/resource/js/pintuer.js"></script>  
      <script type="text/javascript" src="/ssm_xymh_sys/resource/js/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="/ssm_xymh_sys/resource/js/ueditor/ueditor.all.min.js"></script> 
</head>
<style>
    .file1 {
        position: relative;
        display: inline-block;
        background: #D0EEFF;
        border: 1px solid #99D3F5;
        border-radius: 4px;
        padding: 4px 12px;
        overflow: hidden;
        color: #1E88C7;
        text-decoration: none;
        text-indent: 0;
        line-height: 20px;
    }
    .file1 input {
        position: absolute;
        font-size: 100px;
        right: 0;
        top: 0;
        opacity: 0;
    }
    .file1:hover {
        background: #AADFFD;
        border-color: #78C3F3;
        color: #004974;
        text-decoration: none;
    }
</style>
<!-- //电子，女装，男装，皮包
	private int id;
	private int itemType;//商品类型 1电子 2 女装 3 男装 4 皮包
	private int category;//商品类别      5是一般商品。 1.推荐商品 2.特价商品.3.打折商品4.二手货
	private String name;//名称
	private double price;//价格
	private String remark;//描述
	private String url;//图片路径
	private int isDelete;//是否删除
	private int status;//如果是二手货。那么就看这个属性是否审批通过  1是 2  -->
<body>
<!-- 	private String name;
	private String passWord;
	private String realName;//真实名
	private int type;//管理员类型 1 是超级管理员 2 是普通管理员 -->
<script>
function del(id){
	if(confirm("您确定要删除吗?")){
		
	}
}
</script>
<div class="panel admin-panel margin-top">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改权限</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="/ssm_xymh_sys/notice/exUpdate.do" enctype="multipart/form-data"> 
    <input type="hidden" name="id" value="${obj.id}">  
       <div class="form-group">
        <div class="label">
          <label>名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="title"  data-validate="required:请输入名称" value="${obj.title }"/>         
          <div class="tips"></div>
        </div>
      </div> 
     <div class="form-group">
								<!-- 加载编辑器的容器 -->
							        <script id="remark_txt_1" name="content" type="text/plain" style="width:1100px;height:500px;">${obj.content }</script>
								    <!-- 实例化编辑器 -->
								    <script type="text/javascript">
								        var editor = UE.getEditor('remark_txt_1');
								    </script> 
								    </div>
     <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
<script type="text/javascript">
$("#zp1").on("change","input[type='file']",function(){
    var filePath = $(this).val();
    var filePath = filePath.split('\\');
    var filePath = filePath[filePath.length-1];
    $('#file').val(filePath);
})

</script>
</html>