s<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'zzzz.jsp' starting page</title>
    
	<meta name="GENERATOR" Content="Microsoft Visual Studio .NET 7.1">
    <meta name="CODE_LANGUAGE" Content="C#">
    <meta name="vs_defaultClientScript" content="JavaScript">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

 <script language="javascript" src="js/area.js"></script>
 </HEAD>
 <body>
 
  
   
     <SELECT id="province" runat="server" NAME="province">
     </SELECT>
     <SELECT id="city" runat="server" NAME="city">
     </SELECT>
     <SELECT id="county" runat="server" NAME="city">
     </SELECT>
   
  
   <!--js初始化函数-->
   <SCRIPT language="javascript">
   setup();
   </SCRIPT>
 
 </body>
</HTML>