<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

<title>购物车</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/shopping-mall-index.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>
<script type="text/javascript" src="js/jquery-1.5.1.js"></script>
   <script type="text/javascript">
			$(function() {
			$("#jiesuan").click(checklogin); //添加点击事件
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
					setJieSuan(bool);
					/*
					4. 重新计算总计
					*/
					showTotal();
				});
				/*
				给所有条目的复选框添加click事件
				*/
				$(":checkbox[name=checkboxBtn]").click(function() {
					var all = $(":checkbox[name=checkboxBtn]").length;//所有条目的个数
					var select = $(":checkbox[name=checkboxBtn][checked=true]").length;//获取所有被选择条目的个数

					if(all == select) {//全都选中了
						$("#selectAll").attr("checked", true);//勾选全选复选框
						setJieSuan(true);//让结算按钮有效
					} else if(select == 0) {//谁都没有选中
						$("#selectAll").attr("checked", false);//取消全选
						setJieSuan(false);//让结算失效
					} else {
						$("#selectAll").attr("checked", false);//取消全选
						setJieSuan(true);//让结算有效				
					}
					showTotal();//重新计算总计
				});
			});
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
			/*
			 * 统一设置所有条目的复选按钮
			 */
			function setItemCheckBox(bool) {
				$(":checkbox[name=checkboxBtn]").attr("checked", bool);
			}
			/*
			 * 计算总计
			 */
			function showTotal() {
				var total = 0;
				/*
				1. 获取所有的被勾选的条目复选框！循环遍历之
				*/
				$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
					//2. 获取复选框的值，即其他元素的前缀
					var id = $(this).val();
					//3. 再通过前缀找到小计元素，获取其文本
					var text = $("#" + id + "jiage").val();
					//找到数量
					var num = $("#" + id + "num").val();
					//4. 累加计算
					total += text*num;
				});
				// 5. 把总计显示在总计元素上
				$("#total").text(round(total, 2));//round()函数的作用是把total保留2位
			}
			
			/*
			 * 设置结算按钮样式
			 */
			function setJieSuan(bool) {
				if(bool) {
					$("#jiesuan").removeClass("btn_submit_pay_kill").addClass("btn_submit_pay");
					$("#jiesuan").click(checklogin);
				} else {
					$("#jiesuan").removeClass("btn_submit_pay").addClass("btn_submit_pay_kill");
					$("#jiesuan").unbind("click");//撤消当前元素止所有click事件
				} 
				
			}
			/*
				结算
			*/
			function checklogin() {
				var uexist="${userName}";
				if(uexist){
				// 1. 获取所有被选择的条目的id，放到数组中
				var cartItemIdArray = new Array();
				$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
					cartItemIdArray.push($(this).val());//把复选框的值添加到数组中
				});	
				// 2. 把数组的值toString()，然后赋给表单的cartItemIds这个hidden
				$("#cartItemIds").val(cartItemIdArray.toString());
				// 把总计的值，也保存到表单中
				$("#hiddenTotal").val($("#total").text());
				// 3. 提交这个表单
				$("#jieSuanForm").submit();
				}else{
					showBg();
				}
			} 
				//显示灰色 jQuery 遮罩层
			function showBg() {
					var bh = $("body").height();
					var bw = $("body").width();
					$("#fullbg").css({
						height : bh,
						width : bw,
						display : "block"
					});
					$("#dialog").show();
				} 
				//关闭灰色 jQuery 遮罩
				 function closeBg() {
					$("#fullbg,#dialog").hide();
				}
			</script>
			
</head>
  
  <body>
    	<!--top 开始-->
   <%@ include file="jsp/top.jsp" %>
    <!--logo search 开始-->
  <div class="cart-content w1200">
    	<ul class="cart-tit-nav">
        	<li class="current"><a href="#">全部商品   21</a></li>
        	<li><a href="#">降价商品   1</a></li>
        	<li><a href="#">进口商品   1</a></li>
            <div style="clear:both;"></div>
        </ul>
        <div class="cart-con-tit">
        	<p class="p1">
            	<input type="checkbox" id="selectAll" checked="checked" type="checkbox" class="checkbox check-all"></input>
				<span>全选</span>
            </p>
            <p class="p2">商品信息</p>
            <p class="p3">规格</p>
            <p class="p4">数量</p>
            <p class="p5">单价（元）</p>
            <p class="p6">金额（元）</p>
            <p class="p7">操作</p>
        </div>
        
    <c:forEach items="${gwcGoodsList }" var="g">
        <div class="cart-con-info">
        	<div class="info-top">
            	<!-- <input type="checkbox" value="" name="hobby"></input> -->
				<span>${g.brand }</span>
            </div>
          
            <div class="info-mid">
            	<input type="checkbox" value="${g.id }" name="checkboxBtn" checked="checked" class="mid-ipt f-l" ></input>
                <div class="mid-tu f-l">
                	<a href="#"><img src="${g.proPic }" width="90px" height="80px" /></a>
                </div>
                <div class="mid-font f-l"style="width: 220px" >
                	<a href="#">${g.name }</a>
                    <span>满赠</span>
                </div>
                <div class="mid-guige f-l">
                	<p>颜色：${g.color }</p>
                   <!--  <p>尺码：XL</p> -->
                    <a href="JavaScript:;" class="xg" xg="xg1">修改</a>
                    <div class="guige-xiugai" xg-g="xg1">
                    	<div class="xg-left f-l">
                        	<dl>
                            	<dt>颜  色</dt>
                                <dd>
                                	<a href="JavaScript:;" class="current">黑色</a>
                                    <a href="JavaScript:;">白色</a>
                                </dd>
                                <div style="clear:both;"></div>
                            </dl>
                            <dl>
                            	<dt>尺  码</dt>
                                <dd>
                                	<a href="JavaScript:;" class="current">M</a>
                                    <a href="JavaScript:;">L</a>
                                    <a href="JavaScript:;">XL</a>
                                </dd>
                                <div style="clear:both;"></div>
                            </dl>
                            <a href="JavaScript:;" class="qd">确定</a>
                            <a href="JavaScript:;" class="qx" qx="xg1">取消</a>
                        </div>
                    	<div class="xg-right f-l">
                        	<a href="#"><img src="images/dai2.gif" /></a>
                        </div>
                        <div style="clear:both;"></div>
                    </div>
                </div>
                <div class="mid-sl f-l">
                	<a href="JavaScript:;" gid="${g.id }" class="sl-left" >-</a>
                    <input type="text" class="num" id="${g.id }num"  value=${g.num } style="width: 53px"/>
                    <a href="JavaScript:;" gid="${g.id }" class="sl-right">+</a>
                </div>
                <p class="mid-dj f-l">¥ ${g.price }</p>
                <input type=hidden  id="${g.id }jiage" value="${g.price }" >
                <p class="mid-je f-l" id="${g.id }total" >${g.price*g.num }</p>
                <div class="mid-chaozuo f-l">
                	<a href="#">移入收藏夹</a>
                    <a href="#">删除</a>
                    <a href="JavaScript:;" class="ordering" style="color:red;">购买</a>
                    <input type="hidden" value="${g.id }"   />
                </div>
                <div style="clear:both;"></div>
            </div>
        </div>
        </c:forEach>
        <div colspan="5" style="margin-left: 980px;font-size:15px" align="left">总计（不含运费）：¥<em class="price"
        id="total" > 0.00</em></div>
			<div colspan="5"  style="margin-left: 1100px;color: red;font-size: 20px" align="left"><a id="jiesuan" class="btn_submit_pay"
			id="jiesuan" href="javascript:">去结算</a>
			</div>
		
        <!--分页-->
        <div class="paging">
            	<div class="pag-left f-l">
                	<a href="#" class="about left-r f-l"><</a>
                    <ul class="left-m f-l">
                    	<li><a href="#">1</a></li>
                        <li class="current"><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">6</a></li>
                        <li><a href="#">...</a></li>
                        <li><a href="#">100</a></li>
                        <div style="clear:both;"></div>
                    </ul>
                	<a href="#" class="about left-l f-l">></a>
                    <div style="clear:both;"></div>
                </div>
            	<div class="pag-right f-l">
                	<div class="jump-page f-l">
                        到第<input type="text" />页
                    </div>
                    <button class="f-l">确定</button>
                    <div style="clear:both;"></div>
                </div>
                <div style="clear:both;"></div>
            </div>
       	<div class="cart-con-footer">
        	<input type="checkbox" value="" name="hobby"></input>
			<span>全选</span>
            <a href="#">删除</a>
            <a href="#">加入收藏夹</a>
            <p>（此处始终在屏幕下方）</p>
        </div>
    </div>

 <!--底部服务-->
   <%@ include file="jsp/footer.jsp"  %>  
   
   <!-- 隐藏的提交表单 -->
	<form id="jieSuanForm" action="tijiao.cart" method="post">
		<input type="hidden" name="cartItemIds" id="cartItemIds"/>
		<input type="hidden" name="total" id="hiddenTotal"/>
		<input type="hidden" name="method" value="loadCartItems"/>
	</form>
  </body>
</html>
