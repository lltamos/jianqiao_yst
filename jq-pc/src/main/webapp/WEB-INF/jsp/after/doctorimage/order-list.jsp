<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
<script src="${cxt }/js/jianqiaoindex.js"></script>

<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${cxt}/css/base.css"/>
    <link rel="stylesheet" href="${cxt}/css/yisheng_1.css"/>
</head>
<body id="all">
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
    <div class="bg_gray">
        <div class="content">
            <%@include file="/common/mycenterleft_docter.jsp"%>
            	<%@include file="/common/mycentertop.jsp"%>
                <div class="nav_doc">
                    <a class="nd_left" href="javascript:pageDoc();" >在线咨询</a>
                    <a class="nd_right" href="javascript:pagePro();">疑难杂症</a>
                </div>
                <div class="item">
                    <c:choose>
						<c:when test="${type==0}">
							<c:forEach items="${list}" var="doctor">
		                        <div class="doctor">
		                            <img src="${img_service }/${doctor.image}" alt=""/>
		                            <div class="doc_detailed">
		                                <ul>
		                                <input type="hidden" id="customerid" value="${doctor.customer_id}"/>
		                                    <li><p>客户:</p><span class="fon_1">${doctor.nick_name}</span>
		                                    <!-- <a class="online" href="javascript:;">在线咨询</a> -->
		                                    <b class="fon_5">￥<span>${doctor.price}</span>/次</b></li>
		                                    <li><p>项目类型:</p>
		                                    <span>在线咨询</span>
		                                    </li>
		                                    <li><p>付款状态:</p>
		                                    <c:if test="${doctor.pay_status == 0}">
													<i>未支付</i> 
												</c:if>
		                                    	<c:if test="${doctor.pay_status == 1}">
													<i>已支付</i> 
												</c:if>
		                                    	<c:if test="${doctor.pay_status == 3}">
													<i>等待确认</i> 
												</c:if>
		                                    </li>
		                                    <li>
		                                    	<p>时间:</p><b class="fon_4">${doctor.order_date}</b>
		                                    	
		                                    </li>
		                                    <li><p>订单号:</p><b class="fon_4">${doctor.order_id }</b>
		                                    <a class="btn_99" href="javascript:;" onclick="sayCustomer(${doctor.customer_id})">开始对话</a></li>
		                                </ul>
		                            </div>
		                        </div>
                        	</c:forEach>
                        </c:when>
						<c:when test="${type==1}">
							<c:forEach items="${list}" var="product">
		                        <div class="doctor">
		                            <img src="${img_service }/${product.address}" alt=""/>
		                            <div class="doc_detailed_1">
		                                <ul>
		                                    <li><p>客户:</p><span class="fon_1">${product.nick_name}</span></li>
		                                    <li><p>服务包:</p><span class="fon_1">${product.name}</span>
		                                    <b class="fon_5">￥<span>${product.total_price/100}</span>/次</b>
		                                    </li>
		                                    <li><p>项目类型:</p>
		                                    <a class="online" href="javascript:;">${product.product_type_name}</a>
		                                    </li>
		                                    <li><p>时间:</p><b class="fon_4">${product.created_time}</b></li>
		                                    <li><p>订单号:</p><b class="fon_4">${product.pay_relative_id}</b>
		                                    <c:if test="${product.pay_status == 0 }">
												<i class="btn_99">待支付</i> 
											</c:if>
											<c:if test="${product.pay_status == 1}">
													<i class="btn_99">已支付定金</i> 
											</c:if>
											<c:if test="${product.pay_status == 2 }">
													<i class="btn_99">已支付全款</i> 
											</c:if>
											
											<c:if test="${product.pay_status == 3}">
													<i class="btn_99">确认一期款</i> 
											</c:if>
											<c:if test="${product.pay_status == 4}">
													<i class="btn_99">确认二期款</i> 
											</c:if>
											<c:if test="${product.pay_status == 5}">
													<i class="btn_99">确认三期款</i> 
											</c:if>
											<c:if test="${product.pay_status == 6}">
													<i class="btn_99">确认四期款</i> 
											</c:if>
											<c:if test="${product.pay_status == 7}">
												<i class="btn_99">确认五期款</i> 
											</c:if>
											<c:if test="${product.pay_status == 13 }">
												<i class="btn_99">订单完成</i> 
											</c:if>
		                                    </li>
		                                </ul>
		                            </div>
		                        </div>
                        	</c:forEach>
						</c:when>
					</c:choose>
                        
                </div>
                <div class="page-location">
						<div id="customertransfersuccess-pager"
							class="ui-paging ui-paging1"></div>
				</div>
            </div>
        </div>
    </div>
    <script>
    
    function sayCustomer(customerId){
		  
		$.ajax({
			 type:"POST",
			 url:"${cxt}/goIm/verifyCustomer?customerId="+customerId,
			 success:function(result){
				 var sc=eval('(' + result + ')');
				 if(sc.code==3){
					 window.location.href="${cxt }/pc/view/customer/to-login";
				 } 
				if(sc.code==2){
					window.open('${cxt}/goIm/sayCustomer?customerId='+customerId,'jianqiao','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=750px,height=750px');
			   	 }else{
			   		alert(sc.msg);
			   	 }
				
				}
			
			});
	}  
    
        function open_1(){
            var s = document.getElementById("test");
            s.style.display = "block";
            var d=document.getElementById("phone_num");
            d.style.border='1px dashed #9C9AA5';
            var a =document.getElementById("shezhi");
            a.style.visibility = "hidden";
        }
        
        $(function() {
        	var type = ${type};
			if(type==0){
				getPage("${cxt }/pc/after/order/findAllDoctorOrderList?type=${type}&rows=4&page=");
			}
			if(type==1){
				getPage("${cxt }/pc/after/order/findAllDoctorOrderList?type=${type}&rows=4&page=");
			}
			
		}); 
		function getPage(url) {
				mypager.generPageHtml({
					pagerid : "customertransfersuccess-pager",
					//当前页码  provId="+$("#storeProId").val()+"&cityId="+$("#storeCityId").val()+"&industryId="+$("#storeIndustryId").val()+"&value="+$("#storeValue").val()+"&page_no="+(currPage-1)+"&page_size=6&time="+Math.random()
					pno : "${currPage}",
					total : "${totalPage}",
					click : function(currPage) {
							
						$("#all").load(
								url+ currPage);
					}
				});
		}
        function pageDoc(){
			 window.location.href="${cxt }/pc/after/order/findAllDoctorOrderList?type=0"
		}
		function pagePro(){
			 window.location.href="${cxt }/pc/after/order/findAllDoctorOrderList?type=1"
		} 
    </script>
  	<%@include file="/common/bottom.jsp"%>
</body>
</html>