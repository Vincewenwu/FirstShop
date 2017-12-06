nameisyz = false; //用户名验证
yzmisyz = false; //验证码验证
mmyz = false; //密码验证
rmmyz = false; //重复密码验证
window.onload=function(){
	yhmzz = /^[a-zA-z][a-zA-Z0-9_]{3,15}$/; //用户名正则
	//用户名失去焦点事件  验证用户名格式是否正确 及用户名是否可用
	tusername.onblur=function(){
		var username=frm.username.value; //昵称
		//验证用户名格式是否正确
		if(!yhmzz.exec(username)){
			regejc.innerHTML="<font color='red' size='-1'>格式有误</font>";
			tusername.style.border="1px solid red";
			nameisyz = false;
			return;
		}
		
		//发送异步请求验证当前用户名是否可用
		$.ajax({
		type:'post',
		url:'rege.user', //发送数据的地址
		 data:{
			name:username
		 },
		 dataType: "json",
		success:function(result){
			if(result.success=="true"){
					regejc.innerHTML="<font color='green' size='-1'>可以使用</font>";
					tusername.style.border="1px solid #EEE";
					nameisyz = true;
				}else{
					regejc.innerHTML="<font color='red' size='-1'>已被占用</font>";
					tusername.style.border="1px solid red";
					nameisyz = false;
				}
		}
		});
	}
	
	//密码失去焦点事件
	tpassword1.onblur=function(){
		if(tpassword1.value!=""){
			mm.innerHTML="<font color='green' size='-1'>ok</font>";
			tpassword1.style.border="1px solid #EEE";
			rmmyz = true;
		}else{
			mm.innerHTML="<font color='red' size='-1'>请输入密码</font>";
			tpassword1.style.border="1px solid red";
			rmmyz = false;
		}
	}
	
	//确认密码失去焦点事件
	tpassword2.onblur=function(){
		if(tpassword1.value==""){
			//请先填写上方密码
			mm2.innerHTML="<font color='red' size='-1'>请输入上方密码</font>";
			tpassword1.style.border="1px solid red";
			mmyz = false;
		}else{
			//验证两次密码是否一致
			if(tpassword1.value!=tpassword2.value){
				//两次密码输入不一致
				mm2.innerHTML="<font color='red' size='-1'>两次密码不一致</font>";
				tpassword2.style.border="1px solid red";
				mmyz = false;
			}else{
				mm2.innerHTML="<font color='green' size='-1'>ok</font>";
				tpassword2.style.border="1px solid #EEE";
				mmyz = true;
			}
		}
	}
	
	//验证图像验证码
	yzm.onblur=function(){
		yz = yzm.value;
		$.ajax({
			type:'post',
			url:'yzm.user', //发送数据的地址
			 data:{jyzm:yz},
			 dataType: "json",
			success:function(result){
				if(result.yzm=="false"){
					tyzm.innerHTML="<font color='red' size='-1'>验证码输入有误</font>";
					yzm.style.border="1px solid red";
					changeImage();//验证码输入错误刷新图片
					yzmisyz = false;
				}else{
					tyzm.innerHTML="<font color='green' size='-1'>ok</font>";
					yzm.style.border="1px solid #EEE";
					yzmisyz = true;
				}
			}});
	}
}

//注册表单的submit处理事件
function bdtj(){
	if(nameisyz && yzmisyz && mmyz && rmmyz){
		frm.submit(); //验证通过  提交
	}else{
		alert("请填写完整");
	}
}