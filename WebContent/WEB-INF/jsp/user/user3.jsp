<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglibs.jsp"%>

<html>
<head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>  
    <link rel="stylesheet" href="/ssm_xymh_sys/resource/css/pintuer.css">
    <link rel="stylesheet" href="/ssm_xymh_sys/resource/css/admin.css">
    <script src="/ssm_xymh_sys/resource/js/jquery.js"></script>
    <script src="/ssm_xymh_sys/resource/js/pintuer.js"></script>  

<title>个人信息</title>
</head>
<!-- private String userName;//如果是用户 就是用户名 如果是社团就是社团名称
	private String passWord;
	private String nickName;
	private String code;//学号
	private String zy;//专业
	private String nj;//年级
	private Integer isDelete;//是否删除 0否 1 是
	private Integer type;// 1老师 2 社团3.用户
	private Integer roleId;
	private String phone; -->
<body>
<form method="post" action="/ssm_xymh_sys/user/findByMap.do?type=3" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder">学生列表</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
           <a class="button border-main icon-plus-square-o" href="${ctx }/user/add.do?type=3"> 添加</a> 
        </li>
         <li>
          <input type="text" placeholder="请输入名称" name="nickName"  value="${obj.nickName}" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a>
          </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="100" style="text-align:left; padding-left:20px;">ID</th>
           <th width="">名称</th>
           <th width="">昵称</th>
           <th width="">密码</th>
            <th width="">学号</th>
             <th width="">专业</th>
              <th width="">年级</th>
               <th width="">手机号</th>
        <th width="310">操作</th>
      </tr>
        <c:forEach items="${pagers.datas}" var="data" varStatus="l">
        <tr>
           <td>${data.id}</td>
           <td>${data.userName}</td>
            <td>${data.nickName}</td>
            <td>${data.passWord}</td>
            <td>${data.code}</td>
             <td>${data.zy}</td>
             <td>${data.nj}</td>
             <td>${data.phone}</td>
           <td>
              <div class="button-group"> 
              <a class="button border-main" href="/ssm_xymh_sys/user/update.do?type=3&id=${data.id}"><span class="icon-edit"></span>修改</a> 
               <a class="button border-main" href="/ssm_xymh_sys/user/jsFp.do?id=${data.id}"><span class="icon-edit"></span>角色分配</a>
              <a class="button border-red" href="javascript:void(0)" onclick="return del('${data.id}')"><span class="icon-trash-o"></span> 删除</a> </div>
           </td>
        </tr>
       </c:forEach>
      <tr>
        <td colspan="8"><div class="pagelist">
        <!-- 分页开始 -->
			      <pg:pager  url="/ssm_xymh_sys/user/findByMap.do" maxIndexPages="5" items="${pagers.total}"  maxPageItems="15" export="curPage=pageNumber" >
					 <pg:param name="nickName" value="${obj.nickName}"/>
					  <pg:param name="type" value="2"/>
					<pg:last>  
						共${pagers.total}记录,共${pageNumber}页,  
					</pg:last>  
						当前第${curPage}页 
			        <pg:first>  
			    		<a href="${pageUrl}">首页</a>  
					</pg:first>  
					<pg:prev>  
			    		<a href="${pageUrl}">上一页</a>  
					</pg:prev>  
			       	<pg:pages>  
			        	<c:choose>  
			            	<c:when test="${curPage eq pageNumber}">  
			                	<font color="red">[${pageNumber }]</font>  
			            	</c:when>  
			            	<c:otherwise>  
			               		<a href="${pageUrl}">${pageNumber}</a>  
			            	</c:otherwise>  
			        	</c:choose>  
			    	</pg:pages>
			             
			        <pg:next>  
			    		<a href="${pageUrl}">下一页</a>  
					</pg:next>  
					<pg:last>  
						<c:choose>  
			            	<c:when test="${curPage eq pageNumber}">  
			                	<font color="red">尾页</font>  
			            	</c:when>  
			            	<c:otherwise>  
			               		<a href="${pageUrl}">尾页</a>  
			            	</c:otherwise>  
			        	</c:choose> 
			    		  
					</pg:last>
				</pg:pager>
				 </div></td>
                    <!-- 分页结束 -->
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">

//搜索
function changesearch(){	
		$("#listform").submit();
}

//单个删除
function del(id){
	if(confirm("您确定要删除吗?")){
		window.location.href="/ssm_xymh_sys/user/delete.do?id="+id;
	}
}
function DelSelect(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		var t=confirm("您确认要删除选中的内容吗？");
		if (t==false) return false; 		
	}
	else{
		alert("请选择您要删除的内容!");
		return false;
	}
}

</script>
</body>
</html>