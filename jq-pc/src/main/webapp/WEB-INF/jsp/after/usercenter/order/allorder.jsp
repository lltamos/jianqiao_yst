<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>

<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt }/css/common.css">
<link rel="stylesheet" href="${cxt }/css/personal_center_1.css" />
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>

<head lang="en">
<meta charset="UTF-8">

</head>

<body id="bobo">

	<%@include file="/common/topindex.jsp"%>
	<%@include file="/common/navigate.jsp"%>

	<div class="bg_gray">
		<div class="content">
			<%@include file="/common/mycenterleft.jsp"%>
			<div class="con_right">
				<%@include file="/common/mycentertop2.jsp"%>
				<div class="orderform">
					<span id="order_all"><a href="javascript:pageall();">全部订单</a></span>
					<span id="order_nopaid"><a href="javascript:;pageNoPaid()">待支付</a></span>
					<span id="order_paid"><a href="javascript:pagepaid();">已支付</a></span>
				</div>
				<div class="nav_doc">
					<a id="zaixian" class="nd_left" href="javascript:pageDoc();">在线咨询</a>
					<a id="zazhen" class="nd_right" href="javascript:pagePro();">疑难杂症</a>
				</div>
				<div class="item">

					<c:choose>
						<c:when test="${type==0}">
							<c:forEach items="${list}" var="doctor">
								<div class="doctor">
									<img src="${img_service }/${doctor.image}" alt="" />
									<div class="doc_detailed">
										<ul>
											<li><p>医生:</p> <span class="fon_1">${doctor.doctorname}
											</span><a class="online" href="javascript:;">${doctor.str_service_name}</a><b
												class="fon_5">￥<span>${doctor.price/100}</span>/次
											</b></li>
											<li>
												<p>医院:</p> <span class="fon_2">${doctor.hospital}</span>
											</li>
											<li>
												<p>擅长:</p> <span class="fon_3">${doctor.spec_name}</span>
											</li>
											<li>
												<p>时间:</p> <b class="fon_4">${doctor.created_time}</b> <c:if
													test="${doctor.pay_status ==  0 }">
													<a class="btn_99 btn_93"
														href="javascript:saypay1(${doctor.id});">立即支付</a>
												</c:if>
											</li>
											<li><p>订单号:</p> <b class="fon_4">${doctor.order_id }</b>
											<c:if test="${doctor.pay_status !=  0 }">
													<a class="btn_99 btn_92" href="javascript:;"
														target="_blank" onclick="paySay(${doctor.id})">开始对话</a></li>
											</c:if>
										</ul>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:when test="${type==1}">
							<c:forEach items="${list}" var="product">
								<div class="doctor">
									<img src="${img_service	 }/${product.image}" alt="" />
									<div class="doc_detailed_1">
										<ul>
											<li><p>服务包:</p> <span class="fon_1">${product.name}</span></li>
											<li><b class="fon_5">￥<span>${product.total_price/100}</span>/次
											</b></li>
											<li><p>项目类型:</p> <span class="fon_2">${product.product_type_name}
											</span></li>
											<li>
												<p>时间:</p> <b class="fon_4">${product.created_time}</b> <c:if
													test="${product.pay_status == 0 }">
													<a class="btn_99 btn_93"
														href="javascript:saypay2(${product.doctor_id});">立即支付</a>
												</c:if>
											</li>
											<li>
												<p>订单号:</p> <b class="fon_4">${product.pay_relative_id}</b>
												<c:if test="${product.pay_status == 0 }">
													<i class="btn_99">待支付</i>
												</c:if> <c:if test="${product.pay_status == 1}">
													<i class="btn_99">已支付定金</i>
												</c:if> <c:if test="${product.pay_status == 2 }">
													<i class="btn_99">已支付全款</i>
												</c:if> <c:if test="${product.pay_status == 3}">
													<i class="btn_99">确认一期款</i>
												</c:if> <c:if test="${product.pay_status == 4}">
													<i class="btn_99">确认二期款</i>
												</c:if> <c:if test="${product.pay_status == 5}">
													<i class="btn_99">确认三期款</i>
												</c:if> <c:if test="${product.pay_status == 6}">
													<i class="btn_99">确认四期款</i>
												</c:if> <c:if test="${product.pay_status == 7}">
													<i class="btn_99">确认五期款</i>
												</c:if> <c:if test="${product.pay_status == 13 }">
													<i class="btn_99">订单完成</i>
												</c:if>


											</li>
										</ul>
									</div>

								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>


				</div>
				<div class="page-location">
					<div id="customertransfersuccess-pager"
						class="ui-paging ui-paging1"></div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

//服务包订单
function saypay2(doctorid){
	//var doctorid = $("input[id=doctorid]").val();
	
	window.location.href="${cxt}/service/to-pay?doctorid="+doctorid;
} 
//咨询订单
function saypay1(doctorid){
	window.location.href="${cxt}/service/to-serverpay?doctorid="+doctorid;
} 

function deleteOrder(id) {
	$.ajax({
		 url:"${cxt }/pc/after/order/delete?id="+id,
		 type:"POST",
		 success:function(data){
			
			 var jdada=JSON.parse(data);
			 
			 if(jdada.code==1){
				 history.go(0) ;
			 }else{
				 alert("删除失败！")
			 }
			 
			 
		 }
	});
}


function paySay(doctorid){
	  
	$.ajax({
		 type:"POST",
		 url:"${cxt}/goIm/verifyCustomerPay?doctorId="+doctorid,
		 success:function(result){
			 var sc=eval('(' + result + ')');
			 if(sc.code==3){
				 window.location.href="${cxt }/pc/view/customer/to-login";
			 } 
			if(sc.code==2){
				window.open('${cxt}/goIm/sayDoctor?doctorId='+doctorid,'jianqiao','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=750px,height=750px');
		   	 }
			if(sc.code==1){
				saypay(doctorid);
			}
			}
		
		});
}  

function pay(doctorid){
	//var doctorid = $("input[id=doctorid]").val();
	/* var id = $("input[id=id]").val(); */
	window.location.href="${cxt}/service/to-pay?doctorid="+doctorid
}

	function pageDoc() {

		if ("${ordertype}" == 0) {
			window.location.href = "${cxt }/pc/after/order/findOrdersList.do?type=0"

		} else if ("${ordertype}" == 1) {

			window.location.href = "${cxt }/pc/after/order/findPaidOrderList.do?type=0"
		} else if ("${ordertype}" == 2) {

			window.location.href = "${cxt }/pc/after/order/findNoPaidOrderList.do?type=0"
		}

	}

	function pagePro() {

		if ("${ordertype}" == 0) {
			window.location.href = "${cxt }/pc/after/order/findOrdersList.do?type=1";

		} else if ("${ordertype}" == 1) {

			window.location.href = "${cxt }/pc/after/order/findPaidOrderList.do?type=1";

		} else if ("${ordertype}" == 2) {

			window.location.href = "${cxt }/pc/after/order/findNoPaidOrderList.do?type=1"
		}

	}

	function pagepaid() {

		window.location.href = "${cxt }/pc/after/order/findPaidOrderList.do?type=0"

	}

	function pageNoPaid() {

		window.location.href = "${cxt }/pc/after/order/findNoPaidOrderList.do?type=0"
	}

	function pageall() {

		window.location.href = "${cxt }/pc/after/order/findOrdersList.do?type=0"

	}

	$(function() {

		if ("${ordertype}" == 0) {

			getPage("${cxt }/pc/after/order/findOrdersList.do?type=${type}&rows=4&page=");
			$("#order_all").css("background-color", "#00B1FB");

		} else if ("${ordertype}" == 1) {

			getPage("${cxt }/pc/after/order/findPaidOrderList.do?type=${type}&rows=4&page=");
			$("#order_paid").css("background-color", "#00B1FB");

		} else if ("${ordertype}" == 2) {

			getPage("${cxt }/pc/after/order/findNoPaidOrderList.do?type=${type}&rows=4&page=");

			$("#order_nopaid").css("background-color", "#00B1FB");
		}

		if ("${type}" == 0) {
			$("#zaixian").css("background-color", "#00B1FB");

			$("#zazhen").css("background-color", "white");
		} else if ("${type}" == 1) {
			$("#zaixian").css("background-color", "white");

			$("#zazhen").css("background-color", "#00B1FB");
		}

	});
	function getPage(url) {

		mypager.generPageHtml({
			pagerid : "customertransfersuccess-pager",

			pno : "${currPage}",
			total : "${totalPage}",

			click : function(currPage) {

				$("#bobo").load(url + currPage);
			}
		});

	}
</script>
</html>