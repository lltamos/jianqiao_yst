<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<title>健康分享</title>
<link rel="stylesheet" href="${cxt }/css/jianqiaoindex.css">
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt }/css/diaryshareindex.css">
<link rel="stylesheet" href="${cxt }/css/personal_center_10.css"/>
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="${cxt }/js/jianqiaoindex.js"></script>
<script type="text/javascript">
function toGoMyDiaryBook(){
	window.location.href="${cxt }/pc/view/diarybook/getDiaryBookList.do";
}
</script>

<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
<div class="bg_gray">
    <div class="content">
       <%@include file="/common/mycenterleft.jsp"%>
        <div class="con_right">
            <%@include file="/common/mycentertop2.jsp"%>
            <div class="rijiben dwq">
                <div class="riji_1">
                    <div class="riji_14">
                        <a href="${cxt }/pc/view/diarybook/toSaveDiaryBook.do"><img src="${cxt }/img/jianj_10.jpg"></a>
                    </div>
                                <span class="riji_12">
                                    <i>添加新日记</i>
                                    <b>开启健康之旅</b>
                                </span>
                    <a href="${cxt }/pc/view/diarybook/toSaveDiaryBook.do" class="riji_13">创建日记本</a>
                </div>

                <div class="riji_2">
                    <p>我的日记本</p>
                    <img src="${cxt }/img/jianp_5.png">
                </div>
                <div class="rijixin">
					<c:import url="/pc/view/diarybook/getDiaryBookList.do"></c:import> 
                </div>
					 <!-- 分页div --> 
					<div class="page-location">
						<div id="customertransfersuccess-pager" class="ui-paging ui-paging1"></div>  
					</div>                   
            </div>
        </div>
    </div>
</div>