<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<script type="text/javascript">
	function checkDivide() {
		var divide = $("#divide").val();
		for (var i = 1; i <= 5; i++) {
			if (i <= divide) {
				$("#fee" + i).show();
			} else {
				$("#fee" + i).hide();
			}
		}
	}
	$(function(){
		checkDivide();
	});
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx}/user/user-index">主页</a></li>
		<li>项目列表</li>
		<li class="active">项目信息：</li>
	</ul>
	<form class="form-horizontal" id="productInfoForm" method="post"  enctype="multipart/form-data">
		<input type="hidden" name="id" id="id" value="${product.id }"/>
		<input type="hidden" name="detailHtml" id="detailHtml" value=""/>
		<div class="control-group">
			<label class="control-label" for="merchantId">所属总院：</label>
			<div class="controls">
					<c:forEach items="${merchants }" var="merchant" >
						<c:if test="${product.merchantId==merchant.id }">${merchant.name }</c:if>
					</c:forEach>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="doctorId">项目医生：</label>
			<div class="controls">
				<c:forEach items="${doctors }" var="doc">
						<div><span>${doc.customerPhone }&nbsp;&nbsp;&nbsp;&nbsp;${doc.name }&nbsp;&nbsp;&nbsp;&nbsp;</span><input type='hidden' name='doctorId' id='doctorId' value='${doc.id }'/><br/></div>
				</c:forEach>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="customerServiceId">客服帐号：</label>
			<div class="controls">
				${product.customerServiceId }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="serviceTypeId">项目类别：</label>
			<div class="controls">
					<c:forEach items="${productTypes }" var="type" >
						<c:if test="${product.serviceTypeId==type.id }">${type.serviceName }</c:if>
					</c:forEach>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">项目名称：</label>
			<div class="controls">
				${product.name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="customerPhone">项目推荐人手机：</label>
			<div class="controls">
				${product.customerPhone }
			</div>
		</div>
		<c:if test="${!empty images }">
		<div class="control-group">
			<label class="control-label" for="image">项目原主图：</label>
			<div class="controls">
				<c:forEach items="${images }" var="img">
				<img src="${img_service }/${img.address }" style="height: 200px; width: 200px"/><br/>
				</c:forEach>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label" for="des1">内容介绍：</label>
			<div class="controls">
				${product.des1 }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="marketPrice">项目市场价：</label>
			<div class="controls">
				<fmt:formatNumber pattern="0.00" value="${product.marketPrice/100 }"/>元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="totalPrice">项目总价：</label>
			<div class="controls">
				<fmt:formatNumber pattern="0.00" value="${product.totalPrice/100 }"/>元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="depositePrice">项目定金：</label>
			<div class="controls">
				<fmt:formatNumber pattern="0.00" value="${product.depositePrice/100 }"/>元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="divide">分期：</label>
			<div class="controls">
				<input type="hidden" name="divide" id="divide" value="${product.divide}"/>
				${product.divide} <br> <span id="fee1" hidden="hidden"> 第一期价格：<fmt:formatNumber pattern="0.00" value="${product.fee1/100 }"/>元<br>
				</span> <span id="fee2" hidden="hidden"> 第二期价格：<fmt:formatNumber pattern="0.00" value="${product.fee2/100 }"/>元<br>
				</span> <span id="fee3" hidden="hidden">第三期价格：<fmt:formatNumber pattern="0.00" value="${product.fee3/100 }"/>元<br>
				</span> <span id="fee4" hidden="hidden">第四期价格：<fmt:formatNumber pattern="0.00" value="${product.fee4/100 }"/>元<br>
				</span> <span id="fee5" hidden="hidden"> 第五期价格：<fmt:formatNumber pattern="0.00" value="${product.fee5/100 }"/>元
				</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="detailHtml">图文详情：</label>
			<div class="controls">
				${product.detailHtml }
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label" for="serviceProcess">服务流程：</label>
			<div class="controls">
				${product.serviceProcess }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="appointment">如何预约：</label>
			<div class="controls">
				${product.appointment }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="refundProcess">退款流程：</label>
			<div class="controls">
				${product.refundProcess }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="special">特别提示：</label>
			<div class="controls">
				${product.special }
			</div>
		</div> --%>
		<div class="control-group">
			<div class="controls">
				<button class="btn" type="button" onclick="pageGo('${ctx}/product/product-list')">返回</button>
			</div>
		</div>
	</form>
</body>
</html>
