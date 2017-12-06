<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  <title>注册</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/shopping-mall-index.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>

 <script type="text/javascript">
 window.onload=function(){
 
  	var state = "${state}";
  	if(state==1){
  		alert("验证码输入有误！");
  		
  	}else if(state==2){
  		alert("用户存在！");
  	}if(state==3){
  		alert("密码错误或用户不能为空！");
  	}
  	state=0;
  	};
  	
  	
  function bdtj(){
    
   var hobby=$("#hobby").is(':checked');
    alert(hobby);
    if(hobby==true){
     frm.submit(); //验证通过  提交
    }else{
    return false;
    }
     
}
  
  function checkuser(){
    yhmzz = /^[a-zA-z][a-zA-Z0-9_]{3,15}$/; //用户名正则
      var userName=$("#userName").val();
      alert(userName);
    if(!yhmzz.exec(userName)){
      $("#user").attr('class','cuo');
      	return;
      }else{
      $.ajax({
        type:"post",
        url:"regeuser.user",
        data:{
        name:userName
        },
        dataType:"json",
        success:function(result){
           if(result.success=="true"){
            $("#user").attr('class','dui');
           }else{
           $("#user").attr('class','cuo');
           }
        }
      });
     }   
       
  }
  function checkpassword(){
   yzmm = /^[0-9_]{6,16}$/; //mia
        var password=$("#password").val();
         alert(password);
   if(!yzmm.exec(password)){
      $("#mima").attr('class','cuo');
      	return;
      }else{
        $("#mima").attr('class','dui');
        }
  }
  function checkqueren(){
    var password=$("#password").val();
    var querenmima=$("#querenmima").val();
    if(querenmima!=password){
      $("#queren").attr('class','cuo');
      	return;
      }else{
        $("#queren").attr('class','dui');
        }
  }
  function changeImage(){
var url=document.getElementById("CreateCheckCode");
url.src="genImage.user?"+new Date().getMilliseconds();
}
  </script>
  </head>
 
  <body>
   	<!--top 开始-->
   <%@ include file="jsp/top.jsp" %>
    <!--内容开始-->
    <div class="password-con registered">
      <form name="frm" class="form-horizontal" role="form" action="rege.user" method="post"> 
    	<div class="psw"> 
        	<p class="psw-p1">用户名</p>
            <input type="text" name="userName" id="userName" onblur="checkuser()" placeholder="请输入用户名" />
            <span class="cuo" id="user"></span>
        </div>
    	<div class="psw">
        	<p class="psw-p1">输入密码</p>
            <input type="text" name="password" id="password" onblur="checkpassword()" placeholder="请输入密码" />
            <span class="cuo" id="mima" >密码由6-16的字母、数字、符号组成</span>
        </div>
    	<div class="psw">
        	<p class="psw-p1">确认密码</p>
            <input type="text" id="querenmima" name="querenmima" onblur="checkqueren()" placeholder="请再次确认密码" />
            <span class="cuo" id="queren">密码不一致，请重新输入</span>
        </div>
    	<div class="psw psw3">
        	<p class="psw-p1">验证码</p>
            <input type="text" id="yzm" name="yzm" placeholder="请输入验证码" />
        </div>
        <div class="yanzhentu">
        	<div class="yz-tu f-l">
            	<img id="CreateCheckCode" src="genImage.user" />
            </div>
            <p class="f-l">看不清？<a href="javascript:;" onclick="changeImage()">换张图</a></p>
            <div style="clear:both;"></div>
        </div>
        
        <div class="agreement">
        	<input type="checkbox" id="hobby" name="hobby"></input>
            <p>我有阅读并同意<span>《宅客微购网站服务协议》</span></p>
        </div>
        <button class="psw-btn" id="button" style="" onclick="bdtj()" >立即注册</button>
        <p class="sign-in">已有账号？请<a href="login.jsp">登录</a></p>
        </form>
    </div>
   
    <!--底部服务-->
   <%@ include file="jsp/footer.jsp"  %>
  </body>
  
</html>
