<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
  <title>登录</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/shopping-mall-index.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>
<script type="text/javascript">
function denglu(){
      
   frm.submit();
}
</script>
  </head>
  
  <body>
   	<!--top 开始-->
   <%@ include file="jsp/top.jsp" %>
    <!--logo search 开始-->
    <div class="sign-con w1200">
	<img src="images/logn-tu.gif" class="sign-contu f-l" />
    <div class="sign-ipt f-l">
    <form action="login.user"name="frm" id="frm" method="post">
    	<p>用户名</p>
        <input type="text" id="userName" name="userName" placeholder="手机号/邮箱" />
        <p>密码</p>
        <input type="text" id="password" name="password" placeholder="密码可见" /><br />
        <button class="slig-btn" onclick="denglu()">登录</button>
        <p>没有账号？请<a href="#">注册</a><a href="#" class="wj">忘记密码？</a></p>
        <div class="sign-qx">
        	<a href="#" class="f-r"><img src="images/sign-xinlan.gif" /></a>
        	<a href="#" class="qq f-r"><img src="images/sign-qq.gif" /></a>
            <div style="clear:both;"></div>
        </div>
        </form>
    </div>
    <div style="clear:both;"></div>
</div>
     <!--底部服务-->
   <%@ include file="jsp/footer.jsp"  %>
  </body>
</html>
