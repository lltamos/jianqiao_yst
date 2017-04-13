<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var $phone = $("input[name='customer.phone']");
		if ($phone.val() == "") {
			$phone.tips({
				side : 2,
				msg : '名称不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$phone.focus();
			return false;
		} else {
			$phone.val(jQuery.trim($phone.val()));
		}
		return true;
	}
	// 修改关系
	function updateDoctor() {
		if (check()) {
			var form = $("#doctorInfoForm");
			var options = {
				url : '${ctx }/dic/service/service-add', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var appResult = eval("(" + result + ")");
					var message = appResult.error_info;
					var success = appResult.result;
					if (success != "SUCCESS" ) {
						$("#save").tips({
							side : 2,
							msg : message,
							bg : '#68B500',
							time : 10
						});
					} else {
						pageGo('${ctx }/dic/service/to-service-list');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
	function pageGo(href) {
		$("#index_div").load(href);
	}
	$(function () {
		$("#selector").val("${service.departmentName }"); 
	})
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx }/user/user-index">主页</a> <span class="divider">/</span></li>
		<li>服务列表 <span class="divider">/</span>
		</li>
		<li class="active">修改服务：</li>
	</ul>
	<form method="post" id="doctorInfoForm">
		<table border="1" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>所属部门：</td>
				<td>
			          <select id="selector"  type="text" name="departmentName">
			          	<option value="内科" selected="true">内科</option>
			         	<option value="外科" >外科</option>
			         	<option value="骨科" >骨科</option>
			         	<option value="精神科" >精神科</option>
			         	<option value="男科" >男科</option>
			         	<option value="妇科" >妇科</option>
			         	<option value="儿科" >儿科</option>
			         	<option value="皮肤性病科" >皮肤性病科</option>
			         	<option value="口腔科" >口腔科</option>
			         	<option value="眼科" >眼科</option>
			         	<option value="耳鼻喉科" >耳鼻喉科</option>
			         	<option value="中医科" >中医科</option>
			         </select>
		        </td>
			</tr>
			<tr>
				<td>名称：</td>
				<td>
					<input type="hidden" name="id" value="${service.id }"/>
					<input type="text" name="serviceName" value="${service.serviceName }" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="updateDoctor()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('${ctx }/dic/service/to-service-list')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
</html>
