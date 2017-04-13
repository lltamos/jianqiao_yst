<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx}/user/user-index">主页</a></li>
		<li>资讯列表</li>
		<li class="active">资讯信息：</li>
	</ul>
	<form class="form-horizontal" id="messageInfoForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" id="id" value="${message.id }"/>
		<div class="control-group">
			<label class="control-label" for="title">资讯标题：</label>
			<div class="controls">
				${message.title }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="des">文字描述：</label>
			<div class="controls">
				${message.des }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="image">资讯图片：</label>
			<div class="controls">
				<img alt="资讯图片" src="${ctx }/${message.image }" width="200" height="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="address">跳转地址：</label>
			<div class="controls">
				${message.address }
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
					<button class="btn" type="button"
						onclick="pageGo('${ctx }/message/to-message')">返回</button>
			</div>
		</div>
	</form>
</body>
</html>
