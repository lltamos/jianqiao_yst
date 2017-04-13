<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>

<head>
<meta charset="UTF-8">
<title>名医高手</title>
<link rel="stylesheet" href="${cxt }/css/base.css">
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<link rel="stylesheet" href="${cxt}/css/mingyigaoshou.css">
<script src="${cxt}/js/jianqiaoindex.js"></script>
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
</head>
<script>
$(function(){
	getPage();
});
function getPage(){
	mypager.generPageHtml({
		pagerid : "customertransfersuccess-pager",
		pno : "${currPage}",
		total : "${totalPage}",
		click:function(currPage){
			$("#bob").load("${cxt }/pc/view/doctor/finddoctorbycity?officeName="+${officeName }+"&rows=5&page="+currPage);
		}
	});
}
function doctorInfo(id){
	location.href="${cxt }/pc/view/doctor/showdoctorinfo?id="+id;
}
function toIndex(){
	window.location.href="${cxt }/pc/view/customer/toIndex";
}

</script>

<body id="bob">
	<%@include file="/common/topindex.jsp"%>
	<%@include file="/common/navigate.jsp"%>
	<div class="chaohanglan">
		<a href="javascript:;" onclick="toIndex()">首页</a>>><i>名医高手</i>
	</div>
	<div class="mingyigs clearfix">
		<div class="mingyigs clearfix">
		<div class="mingyi_1">
				 <div id="divselect">
				<cite class="ming_12 xuanzong">${officeName }</cite>
				<ul class="xiala" style="display: none">
					<li><a id="1" href="javascript:;" selectid="1"  >内科</a></li>
					<li><a id="2" href="javascript:;" selectid="2"  >外科</a></li>
					<li><a id="3" href="javascript:;" selectid="3"  >骨科</a></li>
					<li><a id="4" href="javascript:;" selectid="4" >男科</a></li>
					<li><a id="5" href="javascript:;" selectid="5"  >妇科</a></li>
					<li><a id="6" href="javascript:;" selectid="6"  >儿科</a></li>
					<li><a id="7" href="javascript:;" selectid="7"  >精神科</a></li>
					<li><a id="8" href="javascript:;" selectid="8"  >眼科</a></li>
					<li><a id="9" href="javascript:;" selectid="9"  >口腔科</a></li>
					<li><a id="10" href="javascript:;" selectid="10"  >耳鼻喉科</a></li>
					<li><a id="11" href="javascript:;" selectid="11"  >中医科</a></li>
					<li><a id="12" href="javascript:;" selectid="12"  >皮肤性病科</a></li>
				</ul>
			</div>
			<script>
			jQuery.divselect = function(divselectid,inputselectid) {
				var inputselect = $(inputselectid);
				$(divselectid+" cite").click(function(){
					var ul = $(divselectid+" ul");
					if(ul.css("display")=="none"){
						ul.slideDown("fast");
					}else{
						ul.slideUp("fast");
					}
				});
				$(divselectid+" ul li a").click(function(){
					var txt = $(this).text();
					$(divselectid+" cite").html(txt);
					var value = $(this).attr("selectid");
					inputselect.val(value);
					$(divselectid+" ul").hide();
					window.location.href="${cxt }/pc/view/doctor/finddoctorbycity?officeName="+txt;
				});
			};
			$(function(){
				$.divselect("#divselect","#inputselect");
			});
			</script>
			<a class="city_11" href="javascript:;"><i>北京市</i></a>
			<!-- <a href="javascript:;" class="ming_12 xuanzong" onclick="sreach()">科室</a> --> 
			
		</div>
		<div class="zixun-left">
			<p class="goledl"></p>
			<c:forEach items="${doctorList }" var="doctor">
				<div class="zixun_1">
					<div class="zi_logo">
						<img
							<c:if test="${!empty doctor.imageHeader }">src="${img_service }/${doctor.imageHeader}"></c:if>
							<c:if test="${empty doctor.imageHeader }">src="${cxt }/img/jian_14.jpg" alt=""></c:if>
					</div>
					<div class="zi_ziliao">
						<p class="ziliao_1">
							<span class="ziliao_12">${doctor.name }</span> <span
								class="ziliao_14" onclick="doctorInfo(${doctor.id});">查看医生主页</span>
						</p>
						<p class="ziliao_2">
							<span>${doctor.titleName }</span><i>${doctor.hospital}</i>
						</p>
						<p class="ziliao_3">
							<b>擅长 ： </b><i>${doctor.specName}</i>
						</p>
						<div class="ziliao_4">
							<span> <i>${doctor.onlineDate}</i> <b>在线咨询</b>
							</span> <span> <i>${doctor.onlineAsk}</i> <b>在线预约</b>
							</span>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="page-location">
			<div id="customertransfersuccess-pager" class="ui-paging ui-paging1"></div>
		</div>
		<%@include file="/common/bottom.jsp"%>
</body>