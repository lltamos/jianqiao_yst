<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int a = 1;
%>
<%@include file="/common/commonPublic.jsp"%>
<head>
<meta charset="UTF-8">
<title>医生详情</title>
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt}/css/gerenzhongxin.css">
<script src="${cxt}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
<script src="${cxt}/js/jianqiaoindex.js"></script>
<style>
.yuer_32 .jilu {
	background-color: #E9E9E9;
}

.yuer_32 .jilu .yellow {
	background-color: #D9E4B6;
}
</style>
</head>
<script>
		$(function() {
			$('.yszhuye').click(function() {
				$('.ycdw').hide();
				$('.yszhu').show();
			});
			$('.gerenzhong').click(function() {
				$('.ycdw').hide();
				$('.zhanghuyel').show();
			});
			$('.select').click(function() {
				$('.select').removeClass('solidw');
				$(this).addClass('solidw');
			});
		});
		function dirayNextPage(page, id) {
			$("#bob").load(
					"${cxt }/pc/view/doctor/showdoctorinfo?page=" + page
							+ "&id=" + id);
		}
		
		function pay(){
			var doctorid = $("input[id=doctorid]").val();
			
			window.location.href="${cxt}/service/to-pay?doctorid="+doctorid;
		}
		 function saypay(){
			var doctorid = $("input[id=doctorid]").val();
			
			window.location.href="${cxt}/service/to-serverpay?doctorid="+doctorid;
		} 
		
			
	/* 		
			function paySay(){
				$.ajax({
					 type:"POST",
					 url:"${cxt}/goIm/verifyCustomerPay",
					 success:function(result){
						 var sc=eval('(' + result + ')');
						 if(sc.code==3){
							 window.location.href="${cxt }/pc/view/customer/to-login";
						 }; 
						if(sc.code==2){
							window.open('${cxt}/goIm/sayDoctor?doctorId=${doctorInfo.id }','jianqiao','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=750px,height=750px');
					   	 }
						if(sc.code==1){
							saypay();
						}
						}
					   	 
				});
			} */
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
		
		
</script>

<div id="bob">
	<%@include file="/common/topindex.jsp"%>
	<%@include file="/common/navigate.jsp"%>
	<div class="yszhu ycdw" style="display: block;">
		<div class="gezhongxli">
			<div class="personal_2 clearfix">
				<div class="sonal_21">
					<img src="${img_service }/${doctorInfo.imageHeader }">
				</div>
				<div class="sonal_22">
					<p class="mingzi">${doctorInfo.name }</p>

					<p class="zhuzhiyi">
						<span>${doctorInfo.titleName }</span><i>${doctorInfo.hospital }</i>
					</p>

					<p class="shanchang">
						<span>擅长：</span><i>${doctorInfo.specName }</i>
					</p>
				</div>
				<div class="zixunl_1 clearfix">
					<div class="zixunl_12">
						<img src="${cxt }/img/jianj_22.jpg">
					</div>
					<%-- <a style=display: inline-block; href='#' onclick=window.open('${cxt}/goIm/sayDoctor?doctorId=${doctorInfo.id }','jianqiao','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=750px,height=750px')> --%>
					<a onclick="paySay(${doctorInfo.id })">
					<div class="zixunl_13">
						<p class="unl_1">
							<span>在线咨询</span> <i>￥<b>${doctorInfo.price1/100 }</b>/次
							</i>
						</p>
						<p>通过文字、图片、语音进行咨询</p>
					</div>
					</a>
				</div>
				<div class="zixunl_1 clearfix">
					<div class="zixunl_12">
						<img src="${cxt }/img/jianj_22.jpg">
					</div>
					<a href="#" onclick ="pay()">	
					<div class="zixunl_13">
						<p class="unl_1">
							<span>在线预约</span> <i>￥<b>${doctorInfo.price2/100 }</b>/次
							</i>
						</p>
						<li>
						<!-- <a class="hz_32" onclick="pay()" href="javascript:;"></a> -->
						<input type="button"  class="hz_32" value="通过线上预约进行线下咨询">
						</li>
						<!-- <p>通过线上预约进行线下咨询</p> -->
					</div>
					</a>
				</div>
				<div class="erweima" >
     				<img class="tupian" id= "imagePath" src="" alt="" />
       			</div>
			</div>
		</div>
		<div id="xiangcez" class="xiangcez">
			<div class="cez_1">
				<span>相册</span>
			</div>
			<c:forEach items="${doctorImages }" var="doctorImage">
				<div class="cez_2 clearfix">
					<div class="cez_21">
						<img class="ez_211"
							<c:if test="${!empty doctorImage.image }">src="${img_service }/${doctorImage.image }"></c:if>
							<c:if test="${empty doctorImage.image }">src="${cxt }/img/jianj_27.jpg" alt=""</c:if>
					</div>
					<div class="ez_212">
						<p>${doctorImage.des }</p>
					</div>
				</div>
			</c:forEach>
			<%-- <p class="yonghupj_3">
				<a href="javascript:dirayNextPage(${page},${doctorInfo.id } );"
					class="blue">${page}</a> <a
					href="javascript:dirayNextPage(${page+1},${doctorInfo.id } );">${page+1}</a>
				<a href="javascript:dirayNextPage(${page+1},${doctorInfo.id } );">&gt;</a>
			</p> --%>
		</div>
		<div id="yonghubjt" class="yonghubjt">
			<div class="yonghupj clearfix">
				<div class="yonghupj_1">
					<p>用户评价</p>
					<img src="${cxt }/img/jianp_5.png">
				</div>
				<c:forEach items="${doctorDiarys }" var="doctorDiary">
					<div class="yonghupj_2 clearfix">
						<div class="hupj_21">
							<div class="pj_1">
								<img class="ez_211"
									<c:if test="${!empty doctorDiary.image }">src="${img_service }/${doctorDiary.image }"></c:if>
									<c:if test="${empty doctorDiary.image }">src="${cxt }/img/jianj_27.jpg" alt=""></c:if>
							</div>
							<p>${doctorDiary.name }</p>
						</div>
						<div class="hupj_22">
							<div class="pj_2">
								<c:if
									test="${doctorDiary.satisfaction == null || diary.satisfaction == 0 }"></c:if>
								<c:if test="${doctorDiary.satisfaction ==  1 }">
									<span class="pj_21"> <img src="${cxt }/img/jianp_10.png"></span>
								</c:if>
								<c:if test="${doctorDiary.satisfaction ==  2 }">
									<span class="pj_21"> <img src="${cxt }/img/jianp_10.png">
										<img src="${cxt }/img/jianp_10.png">
									</span>
								</c:if>
								<c:if test="${doctorDiary.satisfaction ==  3 }">
									<span class="pj_21"> <img src="${cxt }/img/jianp_10.png">
										<img src="${cxt }/img/jianp_10.png"> <img
										src="${cxt }/img/jianp_10.png">
									</span>
								</c:if>
								<c:if test="${doctorDiary.satisfaction ==  4 }">
									<span class="pj_21"> <img src="${cxt }/img/jianp_10.png">
										<img src="${cxt }/img/jianp_10.png"> <img
										src="${cxt }/img/jianp_10.png"> <img
										src="${cxt }/img/jianp_10.png"> <img
										src="${cxt }/img/jianp_10.png">
									</span>
								</c:if>
								<c:if test="${doctorDiary.satisfaction ==  5 }">
									<span class="pj_21">
										<img src="${cxt }/img/jianp_10.png"> <img
										src="${cxt }/img/jianp_10.png"> <img
										src="${cxt }/img/jianp_10.png"> <img
										src="${cxt }/img/jianp_10.png"> <img
										src="${cxt }/img/jianp_10.png">
									</span>
								</c:if>
							</div>
							<div class="pj_3">${doctorDiary.fabulousval } <br> ${doctorDiary.steponval}</div>
							<div class="pj_4">
								<span><%=a++%>楼</span><i>${doctorDiary.createTime }</i>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
			<c:if test="${pageMap.totalPage != 0}">
		<p class="yonghupj_3">
		
				
					<c:if test="${pageMap.page-1 != 0}"> <a href="javascript:dirayNextPage(${pageMap.page-1},${doctorInfo.id } );"> < </a></c:if>
					<a href="javascript:dirayNextPage(${pageMap.page},${doctorInfo.id } );"
						class="blue"> ${pageMap.page}</a>
					<c:if test="${pageMap.totalPage > pageMap.page }">
					 <a href="javascript:dirayNextPage(${pageMap.page+1},${doctorInfo.id } );">${pageMap.page+1}</a>
					 <a href="javascript:dirayNextPage(${pageMap.page+1},${doctorInfo.id } );">></a>
					 </c:if>
				</p>
			</c:if>
			<div>
			<input name="doctorid" id="doctorid" value="${doctorInfo.id }"
				type="hidden"/>
			<%-- <input name="productid" id="productid" value="${doctorInfo.productId }"
				type="hidden"/>
			<input name="hospitalid" id="hospitalid" value="${doctorInfo.hospitalypeId }"
				type="hidden" />
			<input name="productname" id="productname" value="${doctorInfo.productName}"
				type="hidden" />
			<input name="doctorname" id="doctorname" value="${doctorInfo.name }"
				type="hidden" />
			<input name="depositeprice" id="depositeprice" value="${doctorInfo.price2 }"
				type="hidden" />
				<input name="sayprice" id="sayprice" value="${doctorInfo.price1 }"
				type="hidden"/> --%>
	</div>
	    
    </div>
	<%@include file="/common/bottom.jsp"%>
</div>