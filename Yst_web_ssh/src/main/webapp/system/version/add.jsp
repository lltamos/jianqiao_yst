<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var app_name = $("input[name='app_name']");
		if (app_name.val() == "") {
			app_name.tips({
				side : 2,
				msg : '应用名称不得为空',
				bg : '#AE81FF',
				time : 3
			});

			app_name.focus();
			return false;
		} else {
			app_name.val(jQuery.trim(app_name.val()));
		}
		var version = $("input[name='version']");
		if (version.val() == "") {
			version.tips({
				side : 2,
				msg : '版本编号不得为空',
				bg : '#AE81FF',
				time : 3
			});

			version.focus();
			return false;
		} else {
			version.val(jQuery.trim(version.val()));
		}
		var image = $("input[name='image']");
		if (image.val() == "") {
			image.tips({
				side : 2,
				msg : '请选择上传文件',
				bg : '#AE81FF',
				time : 3
			});
			image.focus();
			return false;
		} else {
			var img_val = image.val();
			var extName = img_val.substring(img_val.lastIndexOf("."));
			if (extName != ".ipa" && extName != ".apk") {
				image.tips({
					side : 2,
					msg : '上传文件类型错误',
					bg : '#AE81FF',
					time : 3
				});
				image.focus();
				return false;
			}
		}
		return true;
	}
	function saveVersion() {
		if (check()) {
			p = 0;
			$("#Modal").modal({
				backdrop : "static"
			});
			$("#Modal").modal('show');
			run();
			var form = $("#versionInfoForm");
			var options = {
				url : 'version!addAjax', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var appResult = eval("(" + result + ")");
					var message = appResult.error_info;
					var success = appResult.result;
					if (success != "SUCCESS") {
						$("#upload_text").text(message);
						var timer = setTimeout("fail()", 1000);
					} else {
						var timer = setTimeout("success()", 1000);
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
	function run() {
		p += 1;
		$("div[class=bar]").css("width", p + "%");
		$("#bar").text(p + "%");
		if (p < 100) {
			var timer = setTimeout("run()", 1000);
		} else {
			$("#upload_text").text("上传成功！");
		}
	}
	function success() {
		if (p < 100) {
			var timer = setTimeout("success()", 1000);
		} else {
			$("#Modal").modal('hide');
			var timer = setTimeout("pageGo('version.action')", 1000);
		}
	}
	function fail() {
		p = 100;
		$("#Modal").modal('hide');
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>应用列表 <span class="divider">/</span>
		</li>
		<li class="active">上传新应用：<a href="1111"></a></li>
	</ul>
	<form method="post" id="versionInfoForm">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>应用平台：</td>
				<td><select name="platform">
						<option value="Android">Android</option>
						<option value="IOS">IOS</option>
				</select></td>
			</tr>
			<tr>
				<td>应用名称：</td>
				<td><input type="text" name="app_name" /></td>
			</tr>
			<tr>
				<td>版本编号：</td>
				<td><input type="text" name="version" /></td>
			</tr>
			<tr>
				<td>选择上传文件：</td>
				<td><input type="file" name="image" /></td>
			</tr>
			<tr>
				<td>更新说明：</td>
				<td><textarea name="des"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button class="btn" type="button" onclick="saveVersion()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('version.action')">返回</button>
				</td>
			</tr>
		</table>

	</form>
	<div class="modal small hide fade" id="Modal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-body">
			<div class="progress progress-striped">
				<div class="bar" style="width: 0%;">
					<span id="bar">0%</span>
				</div>
			</div>
			<p class="text-error" id="upload_text">上传中...请勿刷新页面</p>
		</div>
	</div>
</body>
</html>
