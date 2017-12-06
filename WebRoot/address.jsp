<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理收货地址</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/shopping-mall-index.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>
<script type="text/javascript" src="js/jquery-1.5.1.js"></script>
 <script language="javascript" src="js/area.js"></script>
 <script type="text/javascript">
 
 function savemsg() {
	//判断是否是默认地址 
	if (document.getElementById("isdefault").checked) {
	       var info="1"; //是默认
	    }
	    else {
	      var info="0"; //不是默认
	    }
	    var userName = "${userName }";
	    var pca=$("#province").val()+$("#city").val()+$("#county").val();
	    var address=$(".textarea1").val();
	    var username=$("#username").val();
	    var userphone=$("#userphone").val();
	  if(pca=="" || address=="" || username=="" || userphone==""){
		alert("请填完整");
		}else{
	 	$.ajax({
		type:'post', //传送的方式,get/post
	    url:"add.address",//发送数据的地址
		data:{province:$("#province").val(),city:$("#city").val(),area:$("#county").val(),posi:address,pho:userphone,rel:username,userName:userName,msg:info},
		dataType: "json",
		success:function(data){
		if(data.success){
		if(info==1){
			alert("添加成功");
		window.location=window.location;
		    }else{
		     alert("添加失败");
		    }
		  }
		   }
		});
	}
	}
 </script>
</head>
  
  <body>
	<!--top 开始-->
   
    
    <%@ include file="jsp/top.jsp" %>
    
    <!--内容开始-->
    <div class="personal w1200">
    	<div class="personal-left f-l">
        	<div class="personal-l-tit">
            	<h3>个人中心</h3>
            </div>
            <ul>
            	<li class="current-li per-li1"><a href="#">消息中心<span>></span></a></li>
            	<li class="per-li2"><a href="#">个人资料<span>></span></a></li>
            	<li class="per-li3"><a href="#">我的订单<span>></span></a></li>
            	<li class="per-li4"><a href="#">我的预约<span>></span></a></li>
            	<li class="per-li5"><a href="#">购物车<span>></span></a></li>
            	<li class="per-li6"><a href="#">管理收货地址<span>></span></a></li>
            	<li class="per-li7"><a href="#">店铺收藏<span>></span></a></li>
            	<li class="per-li8"><a href="#">购买记录<span>></span></a></li>
            	<li class="per-li9"><a href="#">浏览记录<span>></span></a></li>
            	<li class="per-li10"><a href="#">会员积分<span>></span></a></li>
            </ul>
        </div>
    	<div class="management f-r">
            <div class="tanchuang-con">
                <div class="tc-title">
                    <h3>我的收货地址</h3>
                </div>
                <font class="xinxi">请认真填写以下信息！</font>
                <ul class="tc-con2">
                    <li class="tc-li1">
                        <p class="l-p">所在地区<span>*</span></p>
                        <div class="xl-dz">
                            <div class="dz-left f-l">
                              <div>
                    	       <SELECT id="province" runat="server" NAME="province"> </SELECT>
                    	      </div>
                            </div>
                            <div class="dz-right f-l">
                              <div>
                        	 <SELECT id="city" runat="server" NAME="city"></SELECT>
                        	  </div>
                            </div>
                             <div class="dz-right f-l">
                        	<div>
                        	 <SELECT id="county" runat="server" NAME="city"> </SELECT>
                        	</div>
                              </div>
                            <div style="clear:both;"></div>
                        </div>
                        <div style="clear:both;"></div>
                    </li>
                   	<li class="tc-li1">
                	<p class="l-p">详细地址<span>*</span></p>
                    <textarea class="textarea1" id="manyaddress" placeholder="请如实填写您的详细信息，如街道名称、门牌号、楼层号和房间号。"></textarea>
                    <div style="clear:both;"></div>
                </li>
            	
            	<li class="tc-li1">
                	<p class="l-p">收货人姓名<span>*</span></p>
                    <input type="text" id="username" />
                    <div style="clear:both;"></div>
                </li>
            	<li class="tc-li1">
                	<p class="l-p">联系电话<span>*</span></p>
                    <input type="text" id="userphone" />
                    <div style="clear:both;"></div>
                </li>
                <li>
                 <input id="isdefault" checked="checked" name="default" type="checkbox" class="cart_n_box" />设为默认地址
                </li>
                </ul>
                <button class="btn-pst2" onclick="savemsg()">保存</button>
            </div>
            <div class="man-info">
            	<font>您已经保存${count}个地址！</font>
                <div class="man-if-con">
               
                	<div class="man-tit">
                        <p class="p1">收货人</p>
                        <p class="p2">所在地区</p>
                        <p class="p3">详细地址</p>
                        <p class="p4">邮编</p>
                        <p class="p5">电话/手机</p>
                        <p class="p6">操作</p>
                    </div>
                    <ul class="man-ul1">
                     <c:forEach items="${addressList }" var="ads">
                    	<li>
                        	<p class="p1">${ads.username }</p>
                        	<p class="p2">${ads.province }${ads.city }${ads.area }</p>
                        	<p class="p3">${ads.address }</p>
                        	<p class="p4">525233</p>
                        	<p class="p5">${ads.phone }</p>
                        	<p class="p6">
                            	<a href="#">修改</a> | 
                            	<a href="#">删除</a>
                            </p>
                            <p class="p7"><a href="#">默认地址</a></p>
                            <div style="clear:both;"></div>
                        </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <div style="clear:both;"></div>
    </div>
     <SCRIPT language="javascript">
      setup();
   </SCRIPT>
    <!--底部服务-->
   <%@ include file="jsp/footer.jsp" %>
</body>
</html>
