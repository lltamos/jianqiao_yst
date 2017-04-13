<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<link rel="stylesheet" href="${cxt }/css/jianqiaoindex.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="${cxt }/js/jianqiaoindex.js"></script>
<script type="text/javascript">
function toDiaryAllPage(){
	window.location.href="${cxt }/pc/view/diary/toDiaryListPage.do";
}
function getDiaryDateils(id){
	window.location.href="${cxt }/pc/view/diary/getDiaryDateils.do?diaryid="+id;
}
</script>
	<div class="healthshare">
		<h2 class="title">健康分享</h2>
		<div class="stitleline"></div>
		<div class="sharebox">
			<div class="webbershare">
				<div class="wstitle">
					<span class="left">网友分享</span>
					<a href="javascript:;" onclick="toDiaryAllPage()"><span class="right">更多+</span></a>
				</div>
				<div class="webberlist">
					<c:forEach items="${listDiary}" var="diary">
						<div class="webberimg">
							<a>
                        	<img width="40px" height="40px" <c:if test="${!empty diary.customerlogoimg }">src="${img_service}/${diary.customerlogoimg}"></c:if>
                        	<c:if test="${empty diary.customerlogoimg }">src="${cxt }/img/webber.jpg" alt=""</c:if></a>
						</div>
						<div class="webbertright">
							<div class="sstitle">
								${diary.diaryBookName }
							</div>
							<div class="authortime">
								<span>${diary.customerName }</span>&nbsp;&nbsp;&nbsp;<span><fmt:formatDate  value="${diary.createdTime}" type="both" pattern="yyyy.MM.dd" /></span>
							</div>
						</div>
						<div class="webberimgbox">
							<c:forEach items="${diary.attachmentAddress }" var="diaryImage">
								<a href="javascript:;" onclick="getDiaryDateils(${diary.id})">
								<div class="webberpic webberpic1">
									<img width="90px", height="52px"<c:if test="${!empty diaryImage }">src="${img_service}/${diaryImage}"></c:if>
                        			<c:if test="${empty diaryImage }">src="${cxt }/img/webberimg1.jpg" alt=""></c:if>
								</div>
								</a>
							</c:forEach>
						</div>
						<div class="webbernote">
						<a href="javascript:;" onclick="getDiaryDateils(${diary.id})">${diary.content }</a>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="recommenditem">
				<div class="wstitle">
					<span class="left">推荐项目</span>
					<a href="${cxt }/pc/view/product/todisease"><span class="right">更多+</span></a>
				</div>
				<ul class="recommendlist">
					<c:forEach items="${productIndx }" var="product">
						<a href="${cxt }/service/to-package?id=${product.id}">
							<li>
								<div class="recommendpic">
									<img src="${img_local }/${product.address }" alt="">
								</div>
								<div class="recommendr">
									<div class="retitle">
										【${product.productTypeName }】&nbsp;&nbsp;&nbsp;&nbsp;${product.name }
									</div>
									<div class="redetail">
										${product.des1 }
									</div>
									<!-- <div class="remoney">
										￥<span>400.00</span>
									</div> -->
								</div>
							</li>
						</a>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>