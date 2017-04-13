<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/details.css">
<script
	src='http://kefu.easemob.com/webim/easemob.js?tenantId=7555&hide=true'
	async='async'></script>
	<div class="top">
		<img src="img/go_03.png" onclick="pageGo('product!wapPage?is_real=1')">
		<div class="top_1">项目详情</div>
	</div>
	<div class="banner">
		<img src="<s:property value="#appResult.data.image1"/>">
	</div>
	<div class="content">
		<div class="cont_1">
			<fmt:formatNumber value="${appResult.data.price/100}" pattern="0.00" />元 <span>分<s:property value="#appResult.data.divide"/>期</span>
		</div>
		<div class="cont_2 clearfix">
			<b>项目简介</b> <i><s:property value="#appResult.data.des1"/></i>
		</div>
		<div class="cont_3">
			<b><img src="img/go_09.png"> 随时退款 </b> <i><img
				src="img/go_09.png"> 先行赔付</i>
		</div>
	</div>
	<div class="column">
		<span class="umn_1"> <img src="img/go_29.png"> <s:property value="#appResult.data.merchant.name"/>
		</span> <span class="umn_2">北京市朝阳区樱花园东街2号</span> <span class="umn_3">购买须知</span>
	</div>
	<div class="nrjs">
		<i>内容介绍</i> <b><s:property value="#appResult.data.des1"/></b> <i>项目原价</i> <b><fmt:formatNumber value="${appResult.data.price/100}" pattern="0.00" />元</b> <i>建桥美价</i> <b><fmt:formatNumber value="${appResult.data.price/100}" pattern="0.00" />元</b> <i>消费流程</i>
		<b><s:property value="#appResult.data.service_process"/></b> <span>服务信息</span>
	</div>
	<ul class="msg">
		<li class="msg_1">
			<div class="sg_1">
				如何预约<img class="gln" src="img/go_33.png">
			</div>
			<div class="sg_2"><s:property value="#appResult.data.des2"/></div>
		</li>
		<li class="msg_1">
			<div class="sg_1">
				如何退款<img class="gln" src="img/go_37.png">
			</div>
			<div class="sg_2 nae"><s:property value="#appResult.data.refund_process"/></div>
		</li>
		<li class="msg_1">
			<div class="sg_1">
				如何分期<img class="gln" src="img/go_37.png">
			</div>
			<div class="sg_2 nae">下单后，点击专家头像进入专家主页，点击 “在线咨询”联系在线医生</div>
		</li>
		<li class="msg_1">
			<div class="sg_1">
				特别提示<img class="gln" src="img/go_37.png">
			</div>
			<div class="sg_2 nae"><s:property value="#appResult.data.special"/></div>
		</li>
	</ul>
	<div class="botn">
        <div class="botn_1" onclick="window.location.href='http://kefu.easemob.com/webim/im.html?tenantId=7555'" >咨询专家</div>
    </div>
	<div class="kefu" onclick="window.location.href='http://kefu.easemob.com/webim/im.html?tenantId=7555'">
       咨询专家
    </div>