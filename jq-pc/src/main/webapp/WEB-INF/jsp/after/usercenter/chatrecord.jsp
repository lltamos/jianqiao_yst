<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>

<link rel="stylesheet" href="${cxt }/css/base.css" />
<link rel="stylesheet" href="${cxt }/css/personal_center_9.css" />
<script type="text/javascript" src="${cxt }/js/mypager.js"></script>
<script src="${cxt }/js/jianqiaoindex.js"></script>
<head lang="en">
<meta charset="UTF-8">
<title></title>
<%@include file="/common/topindex.jsp"%>
<%@include file="/common/navigate.jsp"%>
</head>
<body id="all">
	<div class="bg_gray">
		<div class="content">
			<%@include file="/common/mycenterleft.jsp"%>
			<div class="con_right">
				<%@include file="/common/mycentertop2.jsp"%>
				<div class="log">

					<c:forEach items="${list}" var="item">
						<div class="log_content">
						 <input type="hidden" id="doctorid" name="doctorid" value="${item.doctor_id}"/>
						<a onclick="paySay(${item.doctor_id})">
							<img src="${img_service }/${item.image}" alt="" />
							</a>
							<div class="log_right">
								<p>
									<span>${item.name}</span><b>${item.created_time}</b>
								</p>
							<!-- 	<i></i>
								<h1>可以啊，那我明天去看一下，在决定可行性...</h1> -->
							</div>
						</div>

					</c:forEach>

				</div>
				<div class="page-location">
					<div id="customertransfersuccess-pager"
						class="ui-paging ui-paging1"></div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			getPage("${cxt }/pc/after/chat/to-chatrecord-page?rows=4&page=");
		});
		function getPage(url) {
			mypager.generPageHtml({
				pagerid : "customertransfersuccess-pager",
				//当前页码  provId="+$("#storeProId").val()+"&cityId="+$("#storeCityId").val()+"&industryId="+$("#storeIndustryId").val()+"&value="+$("#storeValue").val()+"&page_no="+(currPage-1)+"&page_size=6&time="+Math.random()
				pno : "${currPage}",
				total : "${totalPage}",
				click : function(currPage) {
					$("#all").load(url + currPage);
				}
			});
		}
		
		function saypay(doctorid){
			//var doctorid = $("input[id=doctorid]").val();
			
			window.location.href="${cxt}/service/to-serverpay?doctorid="+doctorid;
		} 


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
</body>
</html>