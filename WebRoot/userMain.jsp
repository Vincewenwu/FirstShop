<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
  <title>我的订单</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/shopping-mall-index.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>
<script type="text/javascript" src="js/jquery-1.5.1.js"></script>

<script type="text/javascript">
$(function() {
     
	$("#selectAll").click(function() {
					/*
					1. 获取全选的状态
					*/
					showTotal();
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
					showTotal();
				});
				
			$(":checkbox[name=selecgoods]").click(function() {
					var all = $(":checkbox[name=selecgoods]").length;//所有条目的个数
					var select = $(":checkbox[name=selecgoods][checked=true]").length;//获取所有被选择条目的个数

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
					showTotal();
				});
				/* 代付款 */
				$(":checkbox[class=daifukuanselecgoods]").click(function() {
			    var bool = $(".daifukuanselecgoods").attr("checked");
			    good1(bool);
			    showTotal();
				});
				/* 代发货*/
				$(":checkbox[class=daifahuo]").click(function() {
			    var bool = $(".daifahuo").attr("checked");
			    good2(bool);
				});
				/* 待收货 */
				$(":checkbox[class=daishouhuo]").click(function() {
			    var bool = $(".daishouhuo").attr("checked");
			    good3(bool);
				});
				/* 待评价daipingjia */
				$(":checkbox[class=daipingjia]").click(function() {
			    var bool = $(".daipingjia").attr("checked");
			    good4(bool);
				});
   });
   
   
             function setItemCheckBox(bool) {
				$(":checkbox[name=checkboxBtn]").attr("checked", bool);
				$(":checkbox[name=selecgoods]").attr("checked", bool);
	         	}	
	    	 function good1(bool) {
				$(":checkbox[id=good1]").attr("checked", bool);
		
		    }
		     function good2(bool) {
				$(":checkbox[id=good2]").attr("checked", bool);
		
		    }
		     function good3(bool) {
				$(":checkbox[id=good3]").attr("checked", bool);
		
		    }
		     function good4(bool) {
				$(":checkbox[id=good4]").attr("checked", bool);
		
		    }
		
		
			function setJieSuan(bool) {
				if(bool) {
					$("#jiesuan").removeClass("btn_submit_pay_kill").addClass("btn_submit_pay");
					$("#jiesuan").click(checklogin);
				} else {
					$("#jiesuan").removeClass("btn_submit_pay").addClass("btn_submit_pay_kill");
					$("#jiesuan").unbind("click");//撤消当前元素止所有click事件
				}
				
			}
			function checklogin() {
				var uexist="${userName}";
				if(uexist){
				// 1. 获取所有被选择的条目的id，放到数组中
				var cartItemIdArray = new Array();  
				var collectArray = new Array();
				$(":checkbox[id=good1][checked=true]").each(function() {
					cartItemIdArray.push($(this).val());//把复选框的值添加到数组中
					var aa=$("#"+$(this).val()+"collect").val();
					collectArray.push(aa);
				});	
					
				// 2. 把数组的值toString()，然后赋给表单的cartItemIds这个hidden
				$("#orderId").val(cartItemIdArray.toString());
				
				$("#orderitemId").val(collectArray.toString());
				// 把总计的值，也保存到表单中
				
				$("#hiddenTotal").val($("#total").text());
				// 3. 提交这个表单
				$("#jieSuanForm").submit();
				}else{
					
				}
			}
				/*
			 * 计算总计
			 */
			function showTotal() {
				var total = 0;
				/*
				1. 获取所有的被勾选的条目复选框！循环遍历之
				*/
				$(":checkbox[id=good1][checked=true]").each(function() {
					//2. 获取复选框的值，即其他元素的前缀
					var id = $(this).val();
					//3. 再通过前缀找到小计元素，获取其文本
					  var text = $("#" + id + "hg").val();
				total+=text*1;
					//找到数量
				});
				// 5. 把总计显示在总计元素上
				$("#total").text(round(total, 2));//round()函数的作用是把total保留2位
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
			function tixingfahuo(){
			  alert("已提醒");
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
    <div class="personal w1200">
    	<div class="personal-left f-l">
        	<div class="personal-l-tit">
            	<h3>个人中心</h3>
            </div>
            <ul>
            	<li class="current-li per-li1"><a href="#">消息中心<span>></span></a></li>
            	<li class="per-li2"><a href="#">个人资料<span>></span></a></li>
            	<li class="per-li3"><a href="userMain.page?type=1">我的订单</a></li>
            	<li class="per-li4"><a href="#">我的预约<span>></span></a></li>
            	<li class="per-li5"><a href="index.cart">购物车<span>></span></a></li>
            	<li class="per-li6"><a href="address.order">管理收货地址<span>></span></a></li>
            	<li class="per-li7"><a href="#">店铺收藏<span>></span></a></li>
            	<li class="per-li8"><a href="#">购买记录<span>></span></a></li>
            	<li class="per-li9"><a href="#">浏览记录<span>></span></a></li>
            	<li class="per-li10"><a href="#">会员积分<span>></span></a></li>
            </ul>
        </div>
    	<div class="order-right f-r">
    	
        	<div class="order-hd">
            	<dl class="f-l">
                	<dt>
                    	<a href="#"><img src="images/data-tu2.gif" /></a>
                    </dt>
                    <dd>
                    	<h3><a href="#">RH了</a></h3>
                        <p>zhao601884596</p>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                <div class="ord-dai f-l">
                <c:forEach items="${ListOrder }" var="state" varStatus="aaa">
                  <c:if test="${aaa.index==0 }">
                    <p>待付款<span>${state }</span></p>
                    </c:if>
                    <c:if test="${aaa.index==1 }">
                    <p>代发货<span>${state }</span></p>
                    </c:if>
                    <c:if test="${aaa.index==2 }">
                    <p>待收货<span>${state }</span></p>
                    </c:if>
                    <c:if test="${aaa.index==3 }">
                    <p>待评价<span>${state }</span></p>
                    </c:if>
                </c:forEach>
                </div>
                <div style="clear:both;"></div>
            </div>
            <div class="order-md">
            	<div class="md-tit">
                	<h3>我的订单</h3>
                </div>
                <div class="md-hd">
                	<P class="md-p1"><input type="checkbox" id="selectAll" ></input><span>全选</span></P>
                    <p class="md-p2">商品信息</p>
                    <p class="md-p3">规格</p>
                    <p class="md-p4">单价（元）</p>
                    <p class="md-p5">金额（元）</p>
                    <p class="md-p6">操作</p>
                </div>
                <div class="md-info">
                 <div class="dai">
                  <input type="checkbox" id="selecgoods" class="daifukuanselecgoods" name="selecgoods"></input><span>待付款</span>
                   <c:forEach items="${PageDate.data }" var="ordrList">
                    <c:if test="${ordrList.state==1 }">
                    <c:forEach items="${ordrList.itemList }" var="goods">
                       <div class="dai-con" >
                    	<dl class="dl1">
                        	<dt>
                                <input type="checkbox" value="${goods.id}" name="checkboxBtn" id="good1"   class="f-l"></input>
                                <input type="hidden" value="${ordrList.id}"  id="${goods.id}collect"   class="f-l"></input>
                                <a href="goodsPageServlet?id=${goods.goodsId }" class="f-l"><img src="${goods.proPic }" width="80px" height="80px" /></a>
                                <div style="clear:both;"></div>
                            </dt>
                            <dd>
                            	<p> ${goods.goodsName }</p>
                                <span>+${goods.sum }</span>
                            </dd>
                            <div style="clear:both;"></div>
                        </dl>
                        <div class="dai-right f-l" >
                        	<P class="d-r-p1">颜色：<br />尺码：XL</P>
                            <p class="d-r-p2">¥ ${goods.goodsPrice }</p>
                            <p class="d-r-p3">¥ ${goods.goodsPrice*goods.sum }</p>
                            <p class="d-r-p4"><a href="#">移入收藏夹</a><br /><a href="#">删除</a><br /><a href="zfdd.order?orderId=${ordrList.id}&orderitem=${goods.id}">付款</a></p>
                        </div>
                         <input type="hidden" id="${goods.id }hg" value="${goods.goodsPrice*goods.sum }">
                        <div style="clear:both;"></div>
                    </div> 
                      </c:forEach>
                       </c:if>
                    </c:forEach>
                   </div>
                    <div class="dai">
                    	<input type="checkbox" id="selecgoods" class="daifahuo" name="selecgoods" ></input><span>待发货</span>
                    	 <c:forEach items="${PageDate.data }" var="ordrList">
                    <c:if test="${ordrList.state==2 }">
                    <c:forEach items="${ordrList.itemList }" var="goods">
                    <div class="dai-con">
                    	<dl class="dl1">
                        	<dt>
                                <input type="checkbox" value="${goods.goodsId }" name="checkboxBtn" id="good2"  class="f-l"></input>
                                <a href="goodsPageServlet?id=${goods.goodsId }" class="f-l"><img src="${goods.proPic }" width="80px" height="80px" /></a>
                                <div style="clear:both;"></div>
                            </dt>
                            <dd>
                            	<p> ${goods.goodsName }</p>
                                <span>+${goods.sum }</span>
                            </dd>
                            <div style="clear:both;"></div>
                        </dl>
                        <div class="dai-right f-l">
                        	<P class="d-r-p1">颜色：<br />尺码：XL</P>
                            <p class="d-r-p2">¥ ${goods.goodsPrice }</p>
                            <p class="d-r-p3">¥ ${goods.goodsPrice*goods.sum }</p>
                         
                            <p class="d-r-p4"><a href="#">移入收藏夹</a><br /><a href="#">删除</a><br /><a onclick="tixingfahuo()">提醒发货</a></p>
                        </div>
                        <div style="clear:both;"></div>
                    </div> 
                      </c:forEach>
                       </c:if>
             </c:forEach>
                   </div>
                    <div class="dai">
                    	<input type="checkbox" id="selecgoods" class="daishouhuo" name="selecgoods" ></input><span>待收货</span>
                  <c:forEach items="${PageDate.data }" var="ordrList">
                    <c:if test="${ordrList.state==3 }">
                    <c:forEach items="${ordrList.itemList }" var="goods">
                    <div class="dai-con">
                    	<dl class="dl1" >
                        	<dt>
                                <input type="checkbox" value="${goods.goodsId }" id="good3" name="checkboxBtn"  class="f-l"></input>
                                <a href="goodsPageServlet?id=${goods.goodsId }" class="f-l"><img src="${goods.proPic }" width="80px" height="80px" /></a>
                                <div style="clear:both;"></div>
                            </dt>
                            <dd>
                            	<p> ${goods.goodsName }</p>
                                <span>+${goods.sum }</span>
                            </dd>
                            <div style="clear:both;"></div>
                        </dl>
                        <div class="dai-right f-l">
                        	<P class="d-r-p1">颜色：<br />尺码：XL</P>
                            <p class="d-r-p2">¥ ${goods.goodsPrice }</p>
                            <p class="d-r-p3">¥ ${goods.goodsPrice*goods.sum }</p>
                            <p class="d-r-p4"><a href="#">移入收藏夹</a><br /><a href="#">删除</a><br /><a href="ddhh.order?orderNos=${ordrList.id}">确认收货</a></p>
                        </div>
                        <div style="clear:both;"></div>
                    </div> 
                      </c:forEach>
                       </c:if>
             </c:forEach>
                   </div>
                    <div class="dai">
                    	<input type="checkbox" id="selecgoods" class="daipingjia" name="selecgoods" ></input><span>待评价</span>
                   <c:forEach items="${PageDate.data }" var="ordrList">
                    <c:if test="${ordrList.state==5 }">
                    <c:forEach items="${ordrList.itemList }" var="goods">
                    <div class="dai-con">
                    	<dl class="dl1">
                        	<dt>
                                <input type="checkbox" value="${goods.goodsId }" id="good4" name="checkboxBtn"  class="f-l"></input>
                                <a href="goodsPageServlet?id=${goods.goodsId }" class="f-l"><img src="${goods.proPic }" width="80px" height="80px" /></a>
                                <div style="clear:both;"></div>
                            </dt>
                            <dd>
                            	<p> ${goods.goodsName }</p>
                                <span>+${goods.sum }</span>
                            </dd>
                            <div style="clear:both;"></div>
                        </dl>
                        <div class="dai-right f-l">
                        	<P class="d-r-p1">颜色：<br />尺码：XL</P>
                            <p class="d-r-p2">¥ ${goods.goodsPrice }</p>
                            <p class="d-r-p3">¥ ${goods.goodsPrice*goods.sum }</p>
                            <p class="d-r-p4"><a href="#">移入收藏夹</a><br /><a href="#">删除</a><br /><a href="#">评价</a></p>
                        </div>
                        <div style="clear:both;"></div>
                    </div> 
                      </c:forEach>
                       </c:if>
             </c:forEach>
                   </div>
                  
                <!--分页-->
             <!--    <div class="paging">
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
            </div> -->
                <div class="md-ft">
                	<P class="md-p1"><input type="checkbox" name="hobby" value=""></input><span>全选</span></P>
                    <a href="#">删除</a>
                    <a href="#">加入收藏夹<span>（此处始终在屏幕下方）</span></a>
                     <a colspan="5" style="margin-left: 260px;font-size:20px">总计（不含运费）：¥<em class="price"
                       id="total" > 0.00</em></a>
		
                    <button id="jiesuan">结算</button>
                </div>
            </div>
            
        </div>
        <div style="clear:both;"></div>
    </div>
  
     <!--底部服务-->
   <%@ include file="jsp/footer.jsp" %>
    <!-- 隐藏的提交表单 -->
	<form id="jieSuanForm" action="ljgm.order" method="post">
		<input type="hidden" name="orderId" id="orderId"/>
		<input type="hidden" name="orderitemId" id="orderitemId"/>
		<input type="hidden" name="total" id="hiddenTotal"/>
		<input type="hidden" name="method" value="loadCartItems"/>
	</form>
	
  </body>
</html>
