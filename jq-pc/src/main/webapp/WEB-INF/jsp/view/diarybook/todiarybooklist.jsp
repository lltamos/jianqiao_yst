<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<link rel="stylesheet" href="${cxt }/css/jianqiaoindex.css">
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt }/css/diaryshareindex.css">
<link rel="stylesheet" href="${cxt }/css/personal_center_10.css"/>
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
			$(".rijixin").load("${cxt }/pc/view/diarybook/getDiaryBookList.do?rows=2&page="+currPage);
		}
	});
}
</script>
			<c:forEach items="${listDiaryAll }" var="book">
				<div class="riji_3">
                        <div class="riji_31">
                            <img width="120px" height="101px" src="${img_local }/${book.content }">
                        </div>
                        	<a href="${cxt }/pc/view/diary/toDiaryListByBookId.do?diarybookid=${book.id}">
                                    <span class="riji_32">
                                        <i>${book.diaryBookName }</i>
                                    </span>
                            </a>
                        <a href="${cxt }/pc/view/diary/toUpdateDiary.do?diarybookid=${book.id }" class="riji_33">更新日记</a>
                </div>
            </c:forEach>