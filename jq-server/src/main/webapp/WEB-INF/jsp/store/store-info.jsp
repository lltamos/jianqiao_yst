<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx}/user/user-index">主页</a></li>
		<li>分院列表 
		</li>
		<li class="active">分院信息：</li>
	</ul>
	<form class="form-horizontal" id="storeInfoForm" method="post">
		<input type="hidden" name="id" id="id" value="${store.id }"/>
		<div class="control-group">
			<label class="control-label" for="merchantId">所属总院：</label>
			<div class="controls">
				${store.merchantName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">分院名称：</label>
			<div class="controls">
				${store.name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="hospitalTypeName">分院等级：</label>
			<div class="controls">
				${store.hospitalTypeName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="des">分院介绍：</label>
			<div class="controls">
				${store.des }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="storePhone">分院电话：</label>
			<div class="controls">
				${store.storePhone }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="province">省：</label>
			<div class="controls">
				${store.province}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="city">市：</label>
			<div class="controls">
				${store.city}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="address">详细地址：</label>
			<div class="controls">
				${store.address }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="latitude">经度：</label>
			<div class="controls">
				${store.latitude }
				<input type="hidden" name="latitude" id="latitude" value="${store.latitude }"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="longitude">纬度：</label>
			<div class="controls">
				${store.longitude }
				<input type="hidden" name="longitude" id="longitude" value="${store.longitude }"/>
			</div>
		</div>
			<iframe name="mapIframe" id="mapIframe" width="500px" height="300px" src="${ctx}/store/store-map">
			</iframe>
		<div class="control-group">
			<div class="controls">
					<button class="btn" type="button"
						onclick="pageGo('${ctx }/store/store-list')">返回</button>
			</div>
		</div>
	</form>
</body>
</html>
