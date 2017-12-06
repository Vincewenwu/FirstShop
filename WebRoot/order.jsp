<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
  <head>
    <base href="<%=basePath%>">
    <meta name="GENERATOR" Content="Microsoft Visual Studio .NET 7.1">
    <meta name="CODE_LANGUAGE" Content="C#">
    <meta name="vs_defaultClientScript" content="JavaScript">
    
<title>确认订单</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/shopping-mall-index.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>
<script type="text/javascript" src="js/jquery-1.5.1.js"></script>
 <script language="javascript" src="js/area.js"></script>

<script type="text/javascript">
           var url;
           var id;
	function xinzeng(){
	resetValue();
	 url="add.address";
	$("#title").text("新增地址");
	}
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
		url:url, //发送数据的地址
		data:{province:$("#province").val(),city:$("#city").val(),area:$("#county").val(),posi:address,pho:userphone,rel:username,userName:userName,msg:info,id:id},
		dataType: "json",
		success:function(data){
		if(data.success){
		if(info==1){
			alert("添加成功");
			$("[xgdz1]").hide();
			resetValue();
		window.location=window.location;
		    }else{
		     alert("添加失败");
		    }
		  }
		   }
		});
	}
	}
	function xiugai(id1) {
	id=id1;
	 url="update.address";
	$("[xgdz1]").show();
	$("#title").text("修改地址");
	
	var upname=  $("#"+id+"upname").val();
	var upphone=  $("#"+id+"upphone").val();
	var manyaddress2=  $("#"+id+"manyaddress2").val();
	$("#username").val(upname);
	$("#userphone").val(upphone);
	$("#manyaddress").val(manyaddress2);
	
	}  
	function resetValue(){
	$("#username").val("");
	$("#userphone").val("");
    $("#manyaddress").val("");
	}
	$(function() {
	
		
				/*
				给全选添加click事件
				*/ 
				showTotal();//计算总计
				$("#selectAll").click(function() {
					/*
					1. 获取全选的状态
					*/
						var bool = $("#selectAll").attr("checked");
					/*
					2. 让所有条目的复选框与全选的状态同步
					*/
					setItemCheckBox(bool);
					/*
					3. 让结算按钮与全选同步
					*/
					
					/*
					4. 重新计算总计
					*/
					showTotal();
				});
				/*
				给所有条目的复选框添加click事件
				*/
				$(":checkbox[name=checkboxBtn]").click(function() {
				var mb=0;
				var id = $(this).val();
				 mb = $("." +id+"Btn").val();
					var all = $(":checkbox[name=checkboxBtn]").length;//所有条目的个数
					var select = $(":checkbox[name=checkboxBtn][checked=true]").length;//获取所有被选择条目的个数

					if(all == select) {//全都选中了
						$("#selectAll").attr("checked", true);//勾选全选复选框
						
					} else if(select == 0) {//谁都没有选中
						$("#selectAll").attr("checked", false);//取消全选
					
					} else {
						$("#selectAll").attr("checked", false);//取消全选
									
					}
					showTotal(mb);//重新计算总计
				});
			});
			/*
				结算
			*/
			function checklogin(c) {
				var uexist="${userName}";
				if(uexist){
				// 1. 获取所有被选择的条目的id，放到数组中
				var cartItemIdArray = new Array();
				$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
				var dangqian=$(this).val();
				alert(dangqian);
				var cc=$("."+dangqian+"Btn").val();
			     	if(c==cc){
			        	cartItemIdArray.push($(this).val());//把复选框的值添加到数组中
			       	}
				});	
				// 2. 把数组的值toString()，然后赋给表单的cartItemIds这个hidden
				$("#cartItemIds").val(cartItemIdArray.toString());
				// 把总计的值，也保存到表单中
				/* $("#hiddenTotal").val($("#total").text()); */
				// 3. 提交这个表单
			 var bool=$("#sender").val();
			    if(bool){
			    $("#jieSuanForm").submit();
			    }else{
			  alert("请选择地址");
			    }
				
				}else{
				}
			} 
			/*
			 * 统一设置所有条目的复选按钮
			 */
			function setItemCheckBox(bool) {
				$(":checkbox[name=checkboxBtn]").attr("checked", bool);
			    }
			
				function showTotal(mb) {
				var total = 0;
				var c=0;
				$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
					//2. 获取复选框的值，即其他元素的前缀
					var id = $(this).val();
					//3. 再通过前缀找到小计元素，获取其文本
					var text = $("#" + id + "totle").val();
					 c = $("." +id+"Btn").val();
					 if(mb==c){
					 total += text*1;
					  }else{
					  
					   }
				});
				$("#"+c+"total").text(round(total, 2));//round()函数的作用是把total保留2位
			}
			
			function round(num,dec){ 
			    var strNum = num + '';/*把要转换的小数转换成字符串*/
			    var index = strNum.indexOf("."); /*获取小数点的位置*/
			    if(index < 0) {
			        return num;/*如果没有小数点，那么无需四舍五入，返回这个整数*/
			    }
			    var n = strNum.length - index -1;/*获取当前浮点数，小数点后的位数*/
			    if(dec < n){ 
			    	/*把小数点向后移动要保留的位数，把需要保留的小数部分变成整数部分，只留下不需要保留的部分为小数*/ 
			        var e = Math.pow(10, dec);
			        num = num * e;
			        /*进行四舍五入，只保留整数部分*/
			        num = Math.round(num);
			        /*再把原来小数部分还原为小数*/
			        return num / e;
			    } else { 
			        return num;/*如果当前小数点后的位数等于或小于要保留的位数，那么无需处理，直接返回*/
			    } 
			} 
			function jiesuan(c){
		     alert(c);
	     	 $("#jiesuan").click(checklogin(c)); //添加点击事件
			}
			function requestAddreddid(addressid){
			$("#sender").val(addressid);
			}
</script>
</head>

<body style="position:relative;">
     	<!--top 开始-->
   <%@ include file="jsp/top.jsp" %>
    <!--logo search 开始-->
     <!--nav 开始-->
    <div style="border-bottom:2px solid #F09E0B;">
    	<div class="nav w1200">
    	<a href="JavaScript:;" class="sp-kj" kj="">
        	商品分类快捷
        </a>
        <div class="kj-show2 none" kj-sh="">
            <c:forEach items="${floor }" var="bigType">
               <div class="kj-info1" mg="${bigType.id}">
                 <dl class="kj-dl1">
                    <dt><a>${bigType.name}</a></dt>
                    <dd></dd>
                 </dl>
               <div class="kj-if-show" mg2="${bigType.id}">
	                 <c:forEach items="${bigType.smallTypeList }" var="smallType">
                  <dl>
					<dt>${smallType.name}</dt>
				  </dl>
					 <div style="clear:both;"></div>
				</c:forEach>
                </div>
               </div>
           </c:forEach>
        </div>
        <ul>
        	<li><a href="#">在线商城</a></li>
        	<li><a href="#">餐饮娱乐</a></li>
        	<li><a href="#">家政服务</a></li>
        	<li><a href="#">美容美发</a></li>
        	<li><a href="#">教育培训</a></li>
        	<li><a href="#">汽车房产</a></li>
        	<li><a href="#">家居建材</a></li>
        	<li><a href="#">二手市场</a></li>
            <div style="clear:both;"></div>
        </ul>
        <div style="clear:both;"></div>
    </div>
    </div>
       
    <!--内容开始-->
    
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
            	<h3>选择收货地址</h3>
            </div>
            <ul class="pay-dz">
            <c:forEach items="${addressList }" var="ord">
            	<li class="" onclick="requestAddreddid(${ord.id })" value="">
       		       <h3><span class="sp1">${ord.province }</span><span class="sp2">${ord.city }</span>（<span class="sp3">${ord.username}</span> 收）</h3>
                    <p><span class="sp1" id="${ord.id }manyaddress">${ord.province }${ord.city }${ord.area }${ord.address } </span><span class="sp2">${ord.phone }</span></p>
                    <a href="JavaScript:;" xiugai="" gid="${ord.id }" onclick="xiugai(${ord.id })">修改</a>
                    
                     <input type="hidden" id="${ord.id }manyaddress2" value="${ord.province }${ord.city }${ord.area }${ord.address }"> 
                    <input type="hidden" id="${ord.id }province" value="${ord.province}"> 
                     <input type="hidden" id="${ord.id }city" value="${ord.city}"> 
                       <input type="hidden" id="${ord.id }area" value="${ord.area}"> 
                    <input type="hidden" id="${ord.id }upname" value="${ord.username}"> 
                     <input type="hidden" id="${ord.id }upphone" value="${ord.phone}"> 
                </li>
             </c:forEach>
             <div style="clear:both;"></div>
            </ul>
            <P class="pay-xgdz">修改地址和使用新地址样式一样。</P>
            <button class="pay-xdz-btn" onclick="xinzeng()">使用新地址</button>
        </div>
        <div class="pay-info">
        	<div class="info-tit" style="border-bottom:0;">
            	<h3>订单信息</h3>
            </div>
        </div>
        <div class="cart-con-info">
        	<div class="cart-con-tit" style="margin:20px 0 5px;">
                <p class="p1" style="width:400px;">
                    <input type="checkbox" value="" id="selectAll" name="hobby"></input>
                    <span>商家：名称 </span>
                </p>
                <p class="p3" style="width:145px;">规格</p>
                <p class="p4" style="width:130px;">数量</p>
                <p class="p8" style="width:75px;">运费</p>
                <p class="p5">单价（元）</p>
                <p class="p6" style="width:173px;">金额（元）</p>
                <p class="p7">配送方式</p>
            </div>
            <c:forEach items="${orderitemId }" var="c">
              <div class="info-mid">
              <c:forEach items="${gwcGoodsList }" var="g">
                    <c:if test="${c==g.ordetId }"> 
              <div>
               <input type="checkbox" value="${g.order }" name="checkboxBtn"></input>
                <input type="hidden" value="${c }" class="${g.order}Btn"></input>
                    <span>商家：${g.brand } </span>
             </div>
                <div class="mid-tu f-l" >
                	<a href="#"><img src="${g.proPic }" width="80px" height="80px" /></a>
                </div>
                <div class="mid-font f-l" style="margin-right:40px;width: 232.3px">
                	<a href="#">${g.name }</a>
                </div>
                <div class="mid-guige1 f-l" style="margin-right:40px;">
                	<p>颜色：蓝色</p>
                    <p>尺码：XL</p>
                </div>
                <div class="mid-sl f-l" style="margin:10px 54px 0px 0px;">
                	<a href="JavaScript:;" class="sl-left">-</a>
                    <input type="text" value="${g.num }" style="width: 55px" />
                    <a href="JavaScript:;" class="sl-right">+</a>
                </div>
                <p class="mid-yunfei f-l">¥ 0.00</p>
                <p class="mid-dj f-l">¥ ${g.price }</p>
                <p class="mid-je f-l" style="margin:10px 120px 0px 0px;width: 45.09px" >${g.total }</p>
                <input type="hidden" value="${g.total }" id="${g.order }totle" />
                <div class="mid-chaozuo f-l">
                	<select>
                    	<option>送货上门</option>
                    	<option>快递包邮</option>
                    </select>
                </div>
                <div style="clear:both;"></div>
                </c:if> 
                
              </c:forEach> 
                </div>
                 <div class="info-heji">
                <p class="f-r">店铺合计(含运费)：<span>0.00</span></p>
            	<h3 class="f-r">订单号：${c }</h3>
            </div>
            <div class="info-tijiao">
            	<p class="p1">实付款：￥<span id="${c }total"></span></p>
                <button  id="jiesuan" value="${c }" onclick="jiesuan(${c })" style="width:106px; height:37px; background:#F34737; color:#fff; font-size:14px; border:none; cursor:pointer;">提交订单</button>
            </div>
               </c:forEach>
           
        </div>
    </div>
 
     <!--底部服务-->
   <%@ include file="jsp/footer.jsp"  %>
  
  <!--确认订单（新增地址）-->
    <div class="confirmation-tanchuang" xgdz1="">
    	<div class="tanchuang-bg"></div>
    	<div class="tanchuang-con">
        	<div class="tc-title">
            	<h3 id="title"></h3>
                <a href="JavaScript:;" dz-guan=""><img src="images/close-select-city.gif" /></a>
                <div style="clear:both;"></div>
            </div>
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
    </div>
     <!--js初始化函数-->
   <SCRIPT language="javascript">
      setup();
   </SCRIPT>
 <!-- 隐藏的提交表单 -->
	<form id="jieSuanForm" action="tjdd.order" method="post">
		<input type="hidden" name="cartItemIds" id="cartItemIds"/>
		<input type="hidden" name="sender" id="sender"/>
		<input type="hidden" name="method" value="loadCartItems"/>
	</form>
  </body>
 
    
</html>
