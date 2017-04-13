<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<title>健康分享</title>
<link rel="stylesheet" href="${cxt }/css/base.css">
<link rel="stylesheet" href="${cxt }/css/common.css">
<link rel="stylesheet" href="${cxt }/css/diaryshare.css">
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
<script type="text/javascript">

function toIndex(){
	window.location.href="${cxt }/pc/view/customer/toIndex";
}
</script>
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
<div class="diarysharelistbox">
		<div class="menu">
			<a href="javascript:;" onclick="toIndex()" style="color: #00b0fb;">首页</a>
			<span>>></span>
			<a href="${cxt }/pc/view/diary/toDiaryListPage.do">健康分享</a>
			<span>>></span>
			<a href="javascript:;" class="presentpage">分享列表</a>
		</div>
		<div class="diaryshare">
			<div class="diaryshareleft">
				<div class="patientbox">
					<div class="patienttitle">
						<span>${customer.nickName }的日记</span>【${product.name }】<p style="width: 22em; font-size:20px; overflow: hidden; white-space:nowrap; text-overflow:ellipsis; float: right; margin-left: 10px;">${product.des1 }</p>
					</div>
					<div class="patientmain">
						<div class="patientleft">
							<div class="patientpic">
								<img width="155px" height="155px" src="${img_service }/${customer.image }" alt="">
							</div>
							<div class="patientname">${customer.nickName }</div>
						</div>
						<div class="patientright">
							<div class="project">项目：${product.name }</div>${product.des1 }
							<div class="time">日期：<fmt:formatDate  value="${diaryBook.createdTime}" type="both" pattern="yyyy.MM.dd" /></div>
							<div class="hospital">医院：<span>${doctors.merchantName }</span></div>
							<div class="doc">
								医生：<span>${doctors.name }</span>
								<a target='_blank' onclick="window.open('${cxt}/goIm/sayadmin','jianqiao','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=684px,height=700px')"><div class="consult"></div></a>
							</div>
							<div class="tag1">
								<div class="iconbox">
									<div class="icon"></div>
								</div>
								<div class="tag1right">
									<span>${product.name }</span>
								</div>
							</div>
							<div class="tag2">
								<div class="iconbox">
									<div class="icon"></div>
								</div>
								<div class="tag2right">
									<c:forEach items="${steponvals }" var="cai">
										<span>${cai }</span>
									</c:forEach>
								</div>
							</div>
							<div class="tag3">
								<div class="iconbox">
									<div class="icon"></div>
								</div>
								<div class="tag3right">
									<c:forEach items="${fabulousvals }" var="zan">
										<span>${zan }</span>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="diarylistbybookid">
					<c:import url="${cxt }/pc/view/diary/getDiaryListByBookId.do?diarybookid=${diaryBook.id }&page=1&rows=3"></c:import>
				</div>
			</div>
			<div class="diaryshareright">
				
			</div>
		</div>
		<!-- 分页 -->
		<div class="page-location">
				<div id="customertransfersuccess-pager" class="ui-paging ui-paging1"></div>  
		</div>
	</div>
<%@include file="/common/bottom.jsp"%>