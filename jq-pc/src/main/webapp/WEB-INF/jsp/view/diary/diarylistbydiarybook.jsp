<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt }/css/common.css">
<link rel="stylesheet" href="${cxt }/css/diaryshare.css">	
<script type="text/javascript">
var bookid = ${diaryBook.id }
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
			$(".diarylistbybookid").load("${cxt }/pc/view/diary/getDiaryListByBookId.do?diarybookid=${diaryBook.id }&rows=3&page="+currPage);
		}
	});
}
function getDiaryDateils(id){
	window.location.href="${cxt }/pc/view/diary/getDiaryDateils.do?diaryid="+id;
}
</script>
	
	<c:forEach items="${listDiary }" var="diary">
		<a href="#" onclick="getDiaryDateils(${diary.id})">
			<div class="diarybox">
						<div class="diarynumber">
							<img src="${cxt }/img/diarynumber.png" alt="">
						</div>
						<div class="diarymain">
							<div class="article">
								${diary.content }
							</div>
							<div class="picbox">
								<c:forEach items="${diary.attachmentAddress }" var="diaryImage">
									<div class="diarypic">
										<img width="99px" height="99px"<c:if test="${!empty diaryImage }">src="${img_service}/${diaryImage}"></c:if>
	                        			<c:if test="${empty diaryImage }">src="${cxt }/img/webberimg1.jpg" alt=""></c:if>
									</div>
								</c:forEach>
							</div>
							<div class="timeandrank">
								<div class="time">
									<fmt:formatDate  value="${diary.createdTime}" type="both" pattern="yyyy-MM-dd    HH:mm:ss" />
								</div>
								<div class="rank">
									<div class="likeicon">
										<div class="icon"></div>
									</div>
									<div class="like">${diary.satisfaction }</div>
									<div class="commenticon">
										<div class="icon"></div>
									</div>
									<div class="comment">${diary.commenterNum }</div>
									<div class="lookicon">
										<div class="icon"></div>
									</div>
									<div class="look">${diary.browNum }</div>
								</div>
							</div>
						</div>
			</div>
		</a>
	</c:forEach>