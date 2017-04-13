<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<link rel="stylesheet" href="${cxt }/css/jianqiaoindex.css">
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt }/css/diaryshareindex.css">
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="${cxt }/js/jianqiaoindex.js"></script>
<script type="text/javascript">
	$(function(){
		getPage();
	});
	function getPage(){
		mypager.generPageHtml({
			pagerid:"customertransfersuccess-pager",
			//当前页码  provId="+$("#storeProId").val()+"&cityId="+$("#storeCityId").val()+"&industryId="+$("#storeIndustryId").val()+"&value="+$("#storeValue").val()+"&page_no="+(currPage-1)+"&page_size=6&time="+Math.random()
			pno:"${currPage}",
			total:"${totalPage}",
			click:function(currPage){
				$(".diarylistbox").load("${cxt }/pc/view/diary/toDiaryAllPage.do?rows=9&page="+currPage);
			}
		});
	}
	function getDiaryDateils(id){
		window.location.href="${cxt }/pc/view/diary/getDiaryDateils.do?diaryid="+id;
	}
	function savediaryLike(id){
		alert(id);
	}
	function savediaryCommenter(id){
		alert(id);
	}
	function savediaryLookicon(id){
		alert(id);
	}
</script>
<style>
		.usercontrol{
			position:absolute;
		}
		.dropdown{
			border:1px solid #27B5EB;
			width:100px;
			height: 97px;
			margin: 6px;
			position:relative;
			z-index: 100;
			display: none;
			background-color: #F6F6F6;
			clear: both;
		}
		.dropdown_menu{
			height:32px;
			line-height:32px;
			overflow:hidden;
			color: #707070;
			padding-left: 15px;
			font-size: 18px;
			display: block;
			border-bottom: 1px solid #d3d3d3;
		}
		.dropdown_menu:hover{
			background-color: #333;
		}
	</style>
		
			
				<ul class="diarylistbox">
						<c:forEach items="${listDiaryAll}" var="diary">
						<li>
						<a href="javascript:;" onclick="getDiaryDateils(${diary.id})">
						<div class="diarypic">
							<c:forEach items="${diary.attachmentAddress }" var="diaryImage">
									<img
										<c:if test="${!empty diaryImage }">src="${img_service}/${diaryImage}@!pc-prod-list"></c:if>
										<c:if test="${empty diaryImage }">src="${cxt }/img/diarypic1.jpg" alt=""></c:if>
							</c:forEach>
						</div>
						<div class="satisfy">
							<div class="txt">满意：</div>
							<c:if test="${diary.satisfaction == null || diary.satisfaction == 0 }"></c:if>
							<c:if test="${diary.satisfaction ==  1 }">
								<div class="star"></div>
							</c:if>
							<c:if test="${diary.satisfaction ==  2 }">
								<div class="star"></div>
								<div class="star"></div>
							</c:if>
							<c:if test="${diary.satisfaction ==  3 }">
								<div class="star"></div>
								<div class="star"></div>
								<div class="star"></div>
							</c:if>
							<c:if test="${diary.satisfaction ==  4 }">
								<div class="star"></div>
								<div class="star"></div>
								<div class="star"></div>
								<div class="star"></div>
							</c:if>
							<c:if test="${diary.satisfaction ==  5 }">
								<div class="star"></div>
								<div class="star"></div>
								<div class="star"></div>
								<div class="star"></div>
								<div class="star"></div>
							</c:if>
						</div>
						<div class="project" >
							项目：<span class="content XianZhiheight">${diary.productTypeName }</span>
						</div>
						<div class="hospital">
							医院：${diary.merchantName }
						</div>
						<div class="hospital">
							医生：${diary.doctorName }
						</div>
						</a>
						</li>
						</c:forEach>
				</ul>