<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>demo</title>
</head>
<body>
  <form action="/ssm_xymh_sys/manage/exUpdate.do" method="post" enctype="multipart/form-data">
  <div style="width: 800px;border: 1px solid red;margin: 0 auto;margin-top: 200px">
   <table width="100%" border="1" cellpadding="2" cellspacing="0">
   <tr>
     <td colspan="7" style="color: red;font-weight: bold;text-align: center;">demo测试数据添加</td>
   </tr>
   <!-- 主键id -->
	 <input type="hidden" name="id" value="${obj.id }"/>
   <tr>
     <td colspan="2" align="center">姓名</td> <td colspan="5"><input type="text" name="name" value="${obj.name}"/></td>
   </tr>
   <tr>
     <td colspan="2" align="center">昵称</td> <td colspan="5"><input type="text" name="nickname" value="${obj.nickname }"/></td>
   </tr>
   <tr>
    <td colspan="2" align="center">性别</td> <td colspan="5"><input type="text" name="sex" value="${obj.sex }"/></td>
   </tr>
   <tr>
    <td colspan="2" align="center">密码</td> <td colspan="5"><input type="text" name="password" value="${obj.password }"/></td>
   </tr>
   <tr>
    <td colspan="2" align="center">头像</td> <td colspan="5"><input type="file" name="file"/></td>
   </tr>
   <tr>
    <td colspan="7" align="center"><input type="submit" value="提交"/></td>
   </tr>
   </table>
  </div>    
  </form>              
</body>
</html>