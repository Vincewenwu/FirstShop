<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
     <link rel="stylesheet" href="assets/css/reset.css">
     <link rel="stylesheet" href="assets/css/supersized.css">
     <link rel="stylesheet" href="assets/css/style.css">

	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">


	window.onload=function(){
  	var state = "${state}";
  	if(state==1){
  		alert("验证码输入有误！");
  	}else if(state==2){
  		alert("密码错误或用户不存在！");
  	}
  	};
  	
function bdtj(){
var username=$("#userName").val();
var password=$("#password").val();
var dlyzm=$("#dlyzm").val();
   if(userName && password && dlyzm){
      frm.submit(); //验证通过  提交
    }else{
    alert("请填完整");
   var frm=document.getElementById("frm");
   frm.action=" ${pageContext.request.contextPath}/admin/main.jsp";
   };
}
function changeImage(){
var url=document.getElementById("CreateCheckCode");
url.src="genImage.user?"+new Date().getMilliseconds();
}
 /* 
function usernameFocus(ele){
        $(ele).next().html("请填写用户名！"); 
        $(ele).next().css("color","#999");
        $(ele).next().show();              
      } 
      
      function usernameBlur(ele){
      var val =  $(ele).val();
         var $p = $(ele).next();
        if(!val){
          $p.css("color","red");
          $p.html("账号不能为空！");
          $p.show();
        }else{
          $p.hide();
        }
      } */
 /*      
function passwordFocus(ele){
        $(ele).next().html("请填写密码！"); 
        $(ele).next().css("color","#999");
        $(ele).next().show();              
      } 
      
      function passwordBlur(ele){
        var val =  $(ele).val();
         var $p = $(ele).next();
        if(!val){
          $p.css("color","red");
          $p.html("密码不能为空！");
          $p.show();
        }else{
          $p.hide();
        }
      }  */
    /*        
function dlyzmFocus(ele){
        $(ele).next().html("请填写验证码！"); 
        $(ele).next().css("color","#999");
        $(ele).next().show();              
      } 
      
      function dlyzmBlur(ele){
        var val =  $(ele).val();
         var $p = $(ele).next();
        if(!val){
          $p.css("color","red");
          $p.html("验证码不能为空！");
          $p.show();
        }else{
          $p.hide();
        }
      }  */
</script>
  
  <body>
<!--   <form name="frm" id="frm" action="adminlogin.user" method="post">
         用户名：<input type="text" name="userName" id="userName" onfocus="usernameFocus(this)" onblur="usernameBlur(this)"/><p class="prompt"></p><br>
        密       码：<input type="password" name="password" id="password" onfocus="passwordFocus(this)" onblur="passwordBlur(this)"/><p class="prompt"></p><br>
               验证码：<input  type="text" name="dlyzm" id="dlyzm" onfocus="dlyzmFocus(this)" onblur="dlyzmBlur(this)"/><p class="prompt" ></p><br>
        <img  id="CreateCheckCode" src="genImage.user" onclick="changeImage()">
      <a href="javascript:;" onclick="changeImage()"> 看不清</a>  <br>
    <button onclick="bdtj()">登录</button>
   
       </form>  -->
       
         <div class="page-container">
            <h1>登录(Login)</h1>
            <form action="adminlogin.user" name="frm" id="frm" method="post">
                <input type="text" name="userName" class="userName" id="userName" placeholder="请输入您的用户名！">
                <input type="password" name="password" class="password" id="password" placeholder="请输入您的用户密码！">
             <!--    <input type="Captcha" class="Captcha" name="Captcha" placeholder="请输入验证码！"> -->
               <input  type="Captcha" class="Captcha" name="dlyzm" id="dlyzm" placeholder="请输入验证码！" onfocus="dlyzmFocus(this)" onblur="dlyzmBlur(this)"/><p class="prompt" ></p><br>
        <img  id="CreateCheckCode" src="genImage.user" onclick="changeImage()">
      <a href="javascript:;" onclick="changeImage()"> 看不清</a>  <br>
 
                <button type="submit" class="submit_button" onclick="bdtj()">登录</button>
                <div class="error"><span>+</span></div>
            </form>
           
        </div>
		
        <!-- Javascript -->
        <script src="assets/js/jquery-1.8.2.min.js" ></script>
        <script src="assets/js/supersized.3.2.7.min.js" ></script>
        <script src="assets/js/supersized-init.js" ></script>
        <script src="assets/js/scripts.js" ></script>
  </body>
</html>
