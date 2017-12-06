<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:if test="${stateOK!=0 }">
	<jsp:forward page="main.jsp"></jsp:forward>
</c:if>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
// 打开新标签
	function openTab(text,url,iconCls){
		if($("#tabs").tabs("exists",text)){
			$("#tabs").tabs("select",text);
		}else{
			var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/admin/"+url+"'></iframe>"
			$("#tabs").tabs("add",{
				title:text,
				iconCls:iconCls,
				closable:true,
				content:content
			});
		}
	}
</script>
  </head>
  

  
<body class="easyui-layout">
<div region="north" style="height: 78px;background-color: #E0ECFF">
<table style="padding: 5px;" width="100%">
	<tr>
		<%-- <td width="50%"><img src="${pageContext.request.contextPath}/images/main.png"/></td> --%>
		<td valign="bottom" align="right" width="50%"><font size="3">&nbsp;&nbsp;<strong>欢迎：</strong>${userName }</font></td>
	</tr>
</table>
</div>
<div region="center">
	<div class="easyui-tabs" fit="true" border="false" id="tabs">
		<div title="首页" data-options="iconCls:'icon-home'">
			<div align="center" style="padding-top: 100px;"><font color="red" size="10">欢迎使用宅商城后台管理系统</font></div>
		</div>
	</div>
</div>
<div region="west" style="width: 200px;" title="导航菜单" split="true">
<div class="easyui-accordion" data-options="fit:true,border:false">
		<div title="用户管理"  data-options="selected:true,iconCls:'icon-user'" style="padding:10px;">
			<a href="javascript:openTab('用户管理','userManage.jsp','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理用户</a>
		</div>
		<div title="商品管理"  data-options="iconCls:'icon-product'" style="padding:10px;">
			<a href="javascript:openTab('商品管理','productManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品</a>
			<a href="javascript:openTab('商品大类管理','productBigTypeManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品大类</a>
			<a href="javascript:openTab('商品小类管理','productSmallTypeManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品小类</a>
		</div>
		<div title="订单管理"  data-options="iconCls:'icon-order'" style="padding:10px">
			<a href="javascript:openTab('订单管理','orderManage.jsp','icon-order')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理订单</a>
		</div>
		<!-- <div title="留言管理" data-options="iconCls:'icon-comment'" style="padding:10px">
			<a href="javascript:openTab('留言管理','commentManage.jsp','icon-comment')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理留言</a>
		</div> -->
		<div title="首页管理"  data-options="iconCls:'icon-notice'" style="padding:10px">
			<a href="javascript:openTab('幻灯管理','slide.jsp','icon-notice')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理幻灯</a>
			<!-- <a href="javascript:openTab('公告管理','noticeManage.jsp','icon-notice')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理公告</a>
			<a href="javascript:openTab('新闻管理','newsManage.jsp','icon-news')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理新闻</a> -->
		</div>
		<!-- <div title="标签管理"  data-options="iconCls:'icon-tag'" style="padding:10px">
			<a href="javascript:openTab('标签管理','tagManage.jsp','icon-tag')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理标签</a>
		</div> -->
		<div title="系统管理"  data-options="iconCls:'icon-item'" style="padding:10px">
			<a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
			<a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a>
			<a href="javascript:refreshSystem()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-refresh'" style="width: 150px;">刷新系统缓存</a>
		</div>
</div>
</div>
<div region="south" style="height: 25px;padding: 5px;" align="center">
	Copyright &copy; 2014  All Rights Reserved.<a href="http://ilt.me" target="_blank">ilt.me</a>
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 220px;padding: 10px 20px" 
 closed="true" buttons="#dlg-buttons" >
 <form id="fm" method="post">
 	<table cellspacing="4px;">
 		<tr>
 			<td>用户名：</td>
 			<td><input type="text" name="adminName" id="userName" readonly="readonly" value="${adminName }" style="width: 200px;" /></td>
 		</tr>
 		<tr>
 			<td>原密码：</td>
 			<td><input type="password" class="easyui-validatebox" name="oldPassword" id="oldPassword" style="width: 200px;" required="true" /></td>
 		</tr>
 		<tr>
 			<td>新密码：</td>
 			<td><input type="password" class="easyui-validatebox" id="newPassword" style="width: 200px;" required="true"  /></td>
 		</tr>
 		<tr>
 			<td>确认新密码：</td>
 			<td><input type="password" class="easyui-validatebox" name="newPassword" id="newPassword2" style="width: 200px;" required="true" /></td>
 		</tr>
 	</table>
 </form>
</div>
<div id="dlg-buttons">
	<a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
  </body>
</html>
