<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jqyidong.css">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script>
	$(function(){
		$(".lunde").each(function(){
			var s=$(this);
			var z=parseInt(s.css("z-index"));
			var dt=$(this).children(".er_1");
			var dd=$(this).children(".xiala");
			var _show=function(){dd.slideDown(200);dt.addClass("cur");s.css("z-index",z+1);};   //展开效果
			var _hide=function(){dd.slideUp(200);dt.removeClass("cur");s.css("z-index",z);};    //关闭效果
			dt.click(function(){dd.is(":hidden")?_show():_hide();});
			dd.find("a").click(function(){dt.html($(this).html());_hide();});     //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
			$("body").click(function(i){ !$(i.target).parents(".lunde").first().is(s) ? _hide():"";});
		});
	});
	function changeType(product_type_id){
		pageGo('product!wapPage?is_real=1&product_type_id='+product_type_id);
	}
	function reqi(order_type){
		pageGo('product!wapPage?is_real=1&order_type='+order_type);
	}
</script>
<script
	src='http://kefu.easemob.com/webim/easemob.js?tenantId=7555&hide=true'
	async='async'></script>
	<div class="header">
		<div class="hea_1 ">
			<span class="eade_1">疑难杂症导医</span> <img class="eade_2"
				src="img/yd_03.png">
		</div>
		 <ul class="hea_2">
            <li class="lunde">
                <span class="er_1">
                    <img class="er_2" src="img/yd_07.png">
                    离我最近
                </span>
            </li>
            <li  class="lunde">
                <span class="er_1">
                    <img class="er_2" src="img/yd_07.png">
                    人气最高
                </span>
                <div class="xiala">
                    <span onclick="reqi('2')">人气最高</span>
                    <span onclick="reqi('3')">销量最高</span>
                </div>
            </li>
            <li class="der_1 lunde">
                <span class="er_1">
                    <img class="er_2" src="img/yd_07.png">
                    全部分类
                </span>
                <div class="xiala">
                	<span onclick="changeType('')">全部分类</span>
                	<s:iterator value="#productTypeList" status="i">
                    	<s:if test="#i.index<=6"><span onclick="changeType('<s:property value="product_type_id" />')"><s:property value="name"/></span></s:if>
                    </s:iterator>
                </div>
            </li>
        </ul>
	</div>
	<div class="banner">
		<img class="banner" src="img/yd_10.png">
	</div>
	<div class="content">治疗疑难杂症高手在民间。依托专业医疗平台， 专家评估，平台签约式治疗，运用互联网思维，
		将治病透明化第三方支付信用保证，先治疗</div>
	<div class="tdnen">
		<img class="nen" src="img/yd_13.png">
	</div>
	<ul class="news">
		<s:iterator value="#appResult.data">
			<li class="ews_1 clearfix"><img class="ws_1"
				src="<s:property value="product.image1"/>"  onclick="pageGo('product!infoPage?product_id=<s:property value="product.product_id"/>')">
				<div class="ws_2">
					<span class="tan_1"  onclick="pageGo('product!infoPage?product_id=<s:property value="product.product_id"/>')">【<s:property
							value="product.productType.name" />】<s:property value="des1" /></span>
					<span class="tan_2"  onclick="pageGo('product!infoPage?product_id=<s:property value="product.product_id"/>')"><s:property value="merchant_name" /></span>
					<span class="tan_3 clearfix"  > 
						<img  onclick="pageGo('product!infoPage?product_id=<s:property value="product.product_id"/>')" class="gne_1" src="img/yd_17.png">
						<span class="gne_2"  onclick="pageGo('product!infoPage?product_id=<s:property value="product.product_id"/>')"><s:property value="product.productType.name" />
						</span>
					<a href="http://kefu.easemob.com/webim/im.html?tenantId=7555"><span class="gne_3" >咨询专家</span></a>
					</span>
				</div></li>
		</s:iterator>
	</ul>
	<div class="kefu"onclick="window.location.href='http://kefu.easemob.com/webim/im.html?tenantId=7555'">
        咨询专家
    </div>
