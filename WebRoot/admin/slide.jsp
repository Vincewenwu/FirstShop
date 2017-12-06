<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>幻灯片管理</title>
    
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

    function openNoticeModifyDialog(){
     	var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑公告信息");
		$("#name").val(row.name);
		$("#url").val(row.url);
	url="update.slide?id="+row.id;
    }

      function formatProPic(val,row){
         return "<img width=100 height=100 src='${pageContext.request.contextPath}/"+val+"'/>";
    }
    function saveNotice(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
			},
			success:function(result){
				var result = eval('('+result+')');
				if(result.success){
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","保存失败");
					return;
				}
			}
		});
	}
	function resetValue(){
		$("#title").val("");
		$("#content").val("");
	}
	
	function closeNoticeDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
</script>
  </head>
  
  <body style="margin:1px;">
 <table id="dg" title="幻灯管理" class="easyui-datagrid" fitColumns="true" 
    pagination="true" rownumbers="true" url="sel.slide" fit="true" toolbar="#tb">
    <thead>
    	<tr>
    		<th field="cb" checkbox="true" align="center"></th>
    		<th field="id" width="50" align="center">编号</th>
    		<th field="name" width="80" align="center" >幻灯名称</th>
    		<th field="proPic" width="100" align="center" formatter="formatProPic" >幻灯图片</th>
    		<th field="url" width="200" align="center">链接</th>
    	</tr>
    </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openNoticeModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
	</div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 570px;height: 300px;padding: 10px 20px"
  closed="true" buttons="#dlg-buttons">
  <form id="fm" method="post" enctype="multipart/form-data">
  	<table cellspacing="8px;">
  		<tr>
  			<td>幻灯名称：</td>
  			<td colspan="4"><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/></td>
  		</tr>
  		<tr>
  			<td>幻灯图片：</td>
  			<td colspan="4"><input id="pP"   name="proPic"  type="file"/></td>
  		</tr>
  		<tr>
  			<td valign="top">链接地址：</td>
  			<td colspan="4">
  				<textarea rows="2" cols="50" name="url" id="url"></textarea>
  			</td>
  		</tr>
  	</table>
  </form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveNotice()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
	<a href="javascript:closeNoticeDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
</div>

</body>
</html>