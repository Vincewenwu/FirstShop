<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title>支付界面</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/shopping-mall-index.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>
<script type="text/javascript">
  var bool=false;
  window.onload=function(){
   yzmisyz = false; //验证码验证
   yzmisyz=/^[4]{1}[0-9]{15}$/;
 $("#yzm").val(""); 
   yzm.onblur=function(){
     var haoma=$("#yzm").val(); 
         if(!yzmisyz.exec(haoma)){
           $("#vis").css('display','none');
            $("#vic").css('display','block');
            bool=false;
         }else{
          $("#vic").css('display','none');
            $("#vis").css('display','block');
            bool=true;	
         };
     };
  };
  function onlyNum(){
    if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
    if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)))
    event.returnValue=false;  //执行至该语句时，阻止输入；可类比阻止冒泡原理或者禁止右键功能；
    }
    function checkLen(x,y){
    if (y.length==x.maxLength)
    {
     var next=x.tabIndex;
     if(next!=1){
     var aa=$("#"+(next-1)+"aa").val();
       if(aa!=""){
     if (next<document.getElementById("myForm").length)
         {
            document.getElementById("myForm").elements[next].focus();
         }
       }else{
         $("#"+(next-1)+"aa").val(null);
       }
      }if(next==1){
       document.getElementById("myForm").elements[next].focus();
      }
     }
   }
  function lijizhifu(){
      if(bool){
      window.location="orderState.jsp";
      }else{
      alert("请填卡号");
      }
  }
</script>
</head>
  
  <body style="position:relative;">

	<!--top 开始-->
     <%@ include file="jsp/top.jsp" %>
    <!--切换城市-->
    <div class="switch-city w1200">
    	<a href="#" class="dianji-qh">切换城市</a>
        <span class="dqm">重庆市</span>
        <div class="select-city">
        	<div class="sl-city-top">
            	<p class="f-l">切换城市</p>
                <a class="close-select-city f-r" href="#">
                	<img src="images/close-select-city.gif" />
                </a>
            </div>
            <div class="sl-city-con">
            	<p>西北</p>
                <dl>
                	<dt>重庆市</dt>
                    <dd>
                    	<a href="#">长寿区</a>
                        <a href="#">巴南区</a>
                        <a href="#">南岸区</a>
                        <a href="#">九龙坡区</a>
                        <a href="#">沙坪坝区</a>
                        <a href="#">北碚</a>
                        <a href="#">江北区</a>
                        <a href="#">渝北区</a>
                        <a href="#">大渡口区</a>
                        <a href="#">渝中区</a>
                        <a href="#">万州</a>
                        <a href="#">武隆</a>
                        <a href="#">晏家</a>
                        <a href="#">长寿湖</a>
                        <a href="#">云集</a>
                        <a href="#">华中</a>
                        <a href="#">林封</a>
                        <a href="#">双龙</a>
                        <a href="#">石回</a>
                        <a href="#">龙凤呈祥</a>
                        <a href="#">朝天门</a>
                        <a href="#">中华</a>
                        <a href="#">玉溪</a>
                        <a href="#">云烟</a>
                        <a href="#">红塔山</a>
                        <a href="#">真龙</a>
                        <a href="#">天子</a>
                        <a href="#">娇子</a>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                <dl>
                	<dt>新疆</dt>
                    <dd>
                    	<a href="#">齐乌鲁木</a>
                        <a href="#">昌吉</a>
                        <a href="#">巴音</a>
                        <a href="#">郭楞</a>
                        <a href="#">伊犁</a>
                        <a href="#">阿克苏</a>
                        <a href="#">喀什</a>
                        <a href="#">哈密</a>
                        <a href="#">克拉玛依</a>
                        <a href="#">博尔塔拉</a>
                        <a href="#">吐鲁番</a>
                        <a href="#">和田</a>
                        <a href="#">石河子</a>
                        <a href="#">克孜勒苏</a>
                        <a href="#">阿拉尔</a>
                        <a href="#">五家渠</a>
                        <a href="#">图木舒克</a>
                        <a href="#">库尔勒</a>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                <dl>
                	<dt>甘肃</dt>
                    <dd>
                    	<a href="#">兰州</a>
                        <a href="#">天水</a>
                        <a href="#">巴音</a>
                        <a href="#">白银</a>
                        <a href="#">庆阳</a>
                        <a href="#">平凉</a>
                        <a href="#">酒泉</a>
                        <a href="#">张掖</a>
                        <a href="#">武威</a>
                        <a href="#">定西</a>
                        <a href="#">金昌</a>
                        <a href="#">陇南</a>
                        <a href="#">临夏</a>
                        <a href="#">嘉峪关</a>
                        <a href="#">甘南</a>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                <dl>
                	<dt>宁夏</dt>
                    <dd>
                    	<a href="#">银川</a>
                        <a href="#">吴忠</a>
                        <a href="#">石嘴山</a>
                        <a href="#">中卫</a>
                        <a href="#">固原</a>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                <dl>
                	<dt>青海</dt>
                    <dd>
                    	<a href="#">西宁</a>
                        <a href="#">海西</a>
                        <a href="#">海北</a>
                        <a href="#">果洛</a>
                        <a href="#">海东</a>
                        <a href="#">黄南</a>
                        <a href="#">玉树</a>
                        <a href="#">海南</a>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
            </div>
        </div>
    </div>
    
    <!--内容开始-->
    <div class="payment-interface w1200">
    	<div class="pay-info">
        	<div class="info-tit">
            	<h3>选择银行</h3>
            </div>
            <ul class="pay-yh">
            	<li>
                	<input type="checkbox" value="" name="hobby"></input>
                    <img src="images/jiangheng.gif" />
                	<div style="clear:both;"></div>
                </li>
                <li>
                	<input type="checkbox" value="" name="hobby"></input>
                    <img src="images/jiangheng.gif" />
                	<div style="clear:both;"></div>
                </li>
                <li>
                	<input type="checkbox" value="" name="hobby"></input>
                    <img src="images/jiangheng.gif" />
                	<div style="clear:both;"></div>
                </li>
                <li style="border-right:0; width:298px;">
                	<input type="checkbox" value="" name="hobby"></input>
                    <img src="images/jiangheng.gif" />
                	<div style="clear:both;"></div>
                </li>
            	<li>
                	<input type="checkbox" value="" name="hobby"></input>
                    <img src="images/jiangheng.gif" />
                	<div style="clear:both;"></div>
                </li>
                <li>
                	<input type="checkbox" value="" name="hobby"></input>
                    <img src="images/jiangheng.gif" />
                	<div style="clear:both;"></div>
                </li>
                <li>
                	<input type="checkbox" value="" name="hobby"></input>
                    <img src="images/jiangheng.gif" />
                	<div style="clear:both;"></div>
                </li>
                <li style="border-right:0; width:298px;">
                	<input type="checkbox" value="" name="hobby"></input>
                    <img src="images/jiangheng.gif" />
                	<div style="clear:both;"></div>
                </li>
            	<li style="border-bottom:0;">
                	<input type="checkbox" value="" name="hobby"></input>
                    <img src="images/jiangheng.gif" />
                	<div style="clear:both;"></div>
                </li>
                <li style="border-bottom:0;">
                	<input type="checkbox" value="" name="hobby"></input>
                    <img src="images/jiangheng.gif" />
                	<div style="clear:both;"></div>
                </li>
                <li style="border-bottom:0;">
                	<input type="checkbox" value="" name="hobby"></input>
                    <img src="images/jiangheng.gif" />
                	<div style="clear:both;"></div>
                </li>
                <li style="border-right:0;border-bottom:0; width:298px;">
                	<input type="checkbox" value="" name="hobby"></input>
                    <img src="images/jiangheng.gif" />
                	<div style="clear:both;"></div>
                </li>
                <div style="clear:both;"></div>
            </ul>
        </div>
    	<div class="pay-info">
        	<div class="info-tit">
            	<h3>输入卡号</h3>
            </div>
            <div class="pay-kahao">
           
            	<input type="text" id="yzm" placeholder="请输入银行卡号或支付宝账号"/>
                <p id="vis" style="display:none; margin-left: 400; margin-top: -45px;">输入正确</p>
                <p id="vic" style="display:none; margin-left: 400; margin-top: -45px; background:url('images/psw-cuo.gif') no-repeat left center;">输入有误</p>
          
            </div>
        </div>
    	<div class="pay-info">
        	<div class="info-tit">
            	<h3>输入密码</h3>
            </div>
            <div class="pay-mima">
            	<p class="mima-p1">你在安全的环境中，请放心使用！</p>
                <div class="mima-ipt">
                	<p>支付宝或银行卡密码：</p>
                 	 <form id="myForm" style="margin-left: 150px;margin-top: -33px;">
                     <input type="password" tabindex="1" size="1" id="1aa" class="phoneNum"   onkeyup="checkLen(this,this.value)" onkeydown="onlyNum();"  maxlength="1"  style="width:30px;height: 40px;text-align:center"/>
                     <input type="password" tabindex="2" size="1" id="2aa" class="phoneNum"  onkeyup="checkLen(this,this.value)" onkeydown="onlyNum();"  maxlength="1"  style="width:30px;height: 40px;text-align:center"/>
                     <input type="password" tabindex="3" size="1" id="3aa" class="phoneNum"   onkeyup="checkLen(this,this.value)" onkeydown="onlyNum();"  maxlength="1"  style="width:30px;height: 40px;text-align:center"/>
                     <input type="password" tabindex="4" size="1" id="4aa" class="phoneNum"   onkeyup="checkLen(this,this.value)" onkeydown="onlyNum();"  maxlength="1"  style="width:30px;height: 40px;text-align:center"/>
                     <input type="password" tabindex="5" size="1" id="5aa" class="phoneNum"   onkeyup="checkLen(this,this.value)" onkeydown="onlyNum();"  maxlength="1"  style="width:30px;height: 40px;text-align:center"/>
                     <input type="password" tabindex="6" size="1" id="6aa" class="phoneNum"   onkeyup="checkLen(this,this.value)" onkeydown="onlyNum();"  maxlength="1"  style="width:30px;height: 40px;text-align:center"/>
                  </form>
                    <span>请输入6位数字支付密码</span>
                </div>
                <button class="mima-btn" onclick="lijizhifu()">立即支付</button>
            </div>
        </div>
    </div>
    
   <!--底部服务-->
   <%@ include file="jsp/footer.jsp"  %>
</body>
</html>
