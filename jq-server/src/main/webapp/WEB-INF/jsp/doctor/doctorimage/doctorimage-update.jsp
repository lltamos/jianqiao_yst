<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	function check() {
		return true;
	}
	// 修改图文介绍信息
	function updateDoctorImage() {
		if (check()) {
			var form = $("#doctorInfoForm");
			var options = {
				url : 'doctorImage!updateAjax', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var appResult = eval("(" + result + ")");
					var message = appResult.msg;
					var success = appResult.code;
					if (success == 0 ) {
						$("#save").tips({
							side : 2,
							msg : message,
							bg : '#68B500',
							time : 10
						});
					} else {
						pageGo('${ctx}/doctorimage/to-doctorimage-list?doctorid=${doctorid}');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
	function pageGo(href) {
		$("#index_div").load(href);
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>图文介绍列表 <span class="divider">/</span>
		</li>
		<li class="active">修改图文介绍信息：</li>
	</ul>
	<form method="post" id="doctorInfoForm">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<input type="hidden" name="id" value="${doctorimage.id }"/>
				<input type="hidden" name="doctorId" value="${doctorid }"/>
				<td>图片描述：</td>
				<td>
					<input type="text" name="des" value="${doctorimage.des }" />
				</td>
			</tr>
			<tr>
				<td>上传医生图片：</td>
				<td>
				<input type="file" name="image" value="${doctorimageimage}" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="updateDoctorImage()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('${ctx}/doctorimage/to-doctorimage-list?doctorid=${doctorid}')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
