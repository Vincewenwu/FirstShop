<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
<title>商品小分类</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
var url;


	function searchProductSmallType(){
		$('#dg').datagrid('load',{
			"s_productSmallTypeName":$("#s_productSmallTypeName").val()
		});
	}
function openProductSmallTypeAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加商品小类信息");
		    resetValue();
		url="add.small";
	}

	function saveProductSmallType(){
	
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				if($('#bName').combobox("getValue")==""){
					$.messager.alert("系统提示","请选择商品大类");
					return false;
				}
				return $(this).form("validate");
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
		$("#bName").combobox("setValue","");
		$("#name").val("");
		$("#remarks").val("");
	}
	
	function closeProductSmallTypeDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}

function formatBigTypeName(val,row){

		return row.bname;
	}
	
	function formatBigTypeId(val,row){
		return row.bigTypeId;
	}
	function openProductSmallTypeModifyDialog(){
	  var selectedRows=$("#dg").datagrid('getSelections');
	  if(selectedRows.length!=1){
	  $.messager.alert("系统提示","请选择一条数据");
	       return;
	  }else{
	  var row=selectedRows[0];
	  $("#dlg").dialog("open").dialog("setTitle","编辑商品小类信息");
	  $("#name").val(row.sname);
		$("#remarks").val(row.remarks);
		$("#bName").combobox("setValue",row.bigTypeId);
		url="update.small?productSmallTypeId="+row.id;
	  }
	}
	function deleteProductSmallType(){
	   var selectedRows=$("#dg").datagrid("getSelections");
	   if(selectedRows.length==0){
	   $.messager.alert("系统提示","请选择一行、或者多行数据");
	   return;
	   }else{
	   var strIns=[];
	   for(var i=0;i<selectedRows.length;i++){
	   strIns.push(selectedRows[i].id);
	   }
	   var ids=strIns.join(",");
	   $.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function (r){
	 	if(r){
				$.post("del.small",{ids:ids},function(result){
					if(result.success){
						if(result.exist){
							$.messager.alert("系统提示",result.exist);
						}else{
							$.messager.alert("系统提示","数据已成功删除！");							
						}
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示',result.errorMsg);
					}
				},"json");
			}
	     });
	   }
	}
</script>
  </head>
  
<body style="margin: 1px;">
<table id="dg" title="商品小分类" class="easyui-datagrid" fitColumns="true"
     pagination="true" rwonumber="true" url="sel.small" fit="true" toolbar="#tb">
     <thead>
     <tr>
            <th field="cb" checkbox="true" align="center"></th>
    		<th field="id" width="50" align="center">编号</th>
    		<th field="sname" width="100" align="center" >商品小类名称</th>
    		<th field="bigTypeId" width="100" align="center"  hidden="true">所属商品大类ID</th>
    		<th field="bname" width="100" align="center" formatter="formatBigTypeName" >所属商品大类</th>
    		<th field="remarks" width="200" align="center">备注</th>
     </tr>
     </thead>
</table>

<div id="tb">
	<div>
		<a href="javascript:openProductSmallTypeAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:openProductSmallTypeModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		<a href="javascript:deleteProductSmallType()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div>
		&nbsp;商品小类名称：&nbsp;<input type="text" name="s_productSmallTypeName"  id="s_productSmallTypeName"  size="20" onkeydown="if(event.keyCode==13) searchProductSmallType()"/>
		<a href="javascript:searchProductSmallType()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	</div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 670px;height: 350px;padding: 10px 20px"
  closed="true" buttons="#dlg-buttons">
  <form id="fm" method="post">
  	<table cellspacing="8px;">
  	    <tr>
  			<td>所属大类：</td>
  			<td colspan="4"><input class="easyui-combobox" id="bName" name="productSmallType.bigTypeId"  data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'bname',url:'selList.big'"/></td>
  		</tr>
  		<tr>
  			<td>小类名称：</td>
  			<td colspan="4"><input type="text" id="name" name="productSmallType.name" class="easyui-validatebox" required="true"/></td>
  		</tr>
  		<tr>
  			<td valign="top">备注：</td>
  			<td colspan="4">
  				<textarea rows="7" cols="50" name="productSmallType.remarks" id="remarks"></textarea>
  			</td>
  		</tr>
  	</table>
  </form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveProductSmallType()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
	<a href="javascript:closeProductSmallTypeDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
</div>
  </body>
</html>
