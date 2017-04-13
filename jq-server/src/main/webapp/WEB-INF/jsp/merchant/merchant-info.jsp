<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%><%@ include file="/common/taglibs.jsp"%>
<html>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx}/user/user-index">主页</a></li>
		<li>总院列表</li>
		<li class="active">总院信息：</li>
	</ul>
	<form class="form-horizontal" id="merchantInfoForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" id="id" value="${merchant.id }"/>
		<div class="control-group">
			<label class="control-label" for="name">总院名称：</label>
			<div class="controls">
				${merchant.name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="image_cred_1">营业执照：</label>
			<div class="controls">
				<img style="width:100px; height:100px;" alt="营业执照" src="${ctx }/${merchant.imageCred1 }"  />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="customerPhone">所属用户手机号：</label>
			<div class="controls">
				${merchant.customerPhone }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="des">总院简介：</label>
			<div class="controls">
				${merchant.des }
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
					<button class="btn" type="button"
						onclick="pageGo('${ctx }/merchant/merchant-list')">返回</button>
			</div>
		</div>
	</form>
</body>
</html>
