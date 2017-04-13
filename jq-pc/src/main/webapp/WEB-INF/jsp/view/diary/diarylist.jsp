<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<title>健康分享</title>
<link rel="stylesheet" href="${cxt }/css/jianqiaoindex.css">
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt }/css/diaryshareindex.css">
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
<script src="${cxt }/js/jquery-1.7.2.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="${cxt }/js/jianqiaoindex.js"></script>
<script type="text/javascript">
function toGoMyDiaryBook(){
	window.location.href="${cxt }/pc/view/diarybook/toDiaryListPage.do";
}
function toIndex(){
	window.location.href="${cxt }/pc/view/customer/toIndex";
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
	 <%@include file="/common/topindex.jsp"%>
	 <%@include file="/common/navigate.jsp"%>
	<div class="diaryshareoutbox">
		<div class="presentpagebox">
			<a href="javascript:;" onclick="toIndex()" style="color: #00b0fb;">首页</a>
			<span>>></span>
			<a href="javascript:;" class="checked">健康分享</a>
		</div>
		<div class="diaryshareinbox">
			<div class="shareleft">
				<div class="usercontrol">
					<div class="mydiary">
						<div class="diarybook">
							<div class="icon"></div>
						</div>
						<a href="javascript:;" onclick="toGoMyDiaryBook()"><div  class="txt">我的日记本</div></a>
					</div>
				</div>
				<div class="titleline"></div>
				<c:import url="/pc/view/diary/toDiaryAllPage.do?page=1&rows=9"></c:import>
			</div>
		</div>
		 <!-- 分页div --> 
		<div class="page-location">
				<div id="customertransfersuccess-pager" class="ui-paging ui-paging1"></div>  
		</div>
	</div>
	<%@include file="/common/bottom.jsp"%>