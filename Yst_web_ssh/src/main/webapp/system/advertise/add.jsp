<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	var editor = null;
	function check() {
		var detail_html = $("#detail_html");
		var save = $("#save");
		var $phone = $("input[name='name']");
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
	// 保存用户信息
	function saveAdvertise() {
		var detail = $("#detail_html");
		detail.val(editor.getData());
		if (check()) {
			var form = $("#advertiseInfoForm");
			var options = {
				url : 'advertise!addAjax', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var appResult = eval("(" + result + ")");
					var message = appResult.error_info;
					var success = appResult.result;
					if (success != "SUCCESS") {
						$("#save").tips({
							side : 2,
							msg : message,
							bg : '#68B500',
							time : 10
						});
					} else {
						pageGo('advertise.action');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
	$(function() {
		if (CKEDITOR.env.ie && CKEDITOR.env.version < 9)
			CKEDITOR.tools.enableHtml5Elements(document);
		// The trick to keep the editor in the sample quite small
		// unless user specified own height.
		editor = CKEDITOR.replace("detail_html");
		var start = {
			elem : '#start',//目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
			//event: 'focus', //响应事件。如果没有传入event，则按照默认的click
			//festival: true, //显示节日
			format : 'YYYY-MM-DD hh:mm:ss',
			min : laydate.now(), //设定最小日期为当前日期
			max : '2099-06-16 23:59:59', //最大日期
			istime : true,
			istoday : false,
			choose : function(datas) {
				end.min = datas; //开始日选好后，重置结束日的最小日期
				end.start = datas; //将结束日的初始值设定为开始日
			}
		};
		var end = {
			elem : '#end',
			format : 'YYYY-MM-DD hh:mm:ss',
			min : laydate.now(),
			max : '2099-06-16 23:59:59',
			istime : true,
			istoday : false,
			choose : function(datas) {
				start.max = datas; //结束日选好后，重置开始日的最大日期
			}
		};
		laydate(start);
		laydate(end);
	});
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>广告列表 <span class="divider">/</span>
		</li>
		<li class="active">添加广告信息：</li>
	</ul>

	<form method="post" id="advertiseInfoForm">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>排列顺序：</th>
				<td><input type="text" name="order_index" /></td>
			</tr>
			<tr>
				<th>广告标题：</th>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<th>广告图片：</th>
				<td><input type="file" name="image" /></td>
			</tr>
			<tr>
				<th>网络地址(如填写网络地址不需编辑本地页面)：</th>
				<td><textarea rows="2" cols="100" name="link_content"></textarea></td>
			</tr>
			<tr>
				<th>本地编辑广告详情：</th>
				<td><textarea name="detail_html" id="detail_html"></textarea></td>
			</tr>
			<tr>
				<th>开始时间：</th>
				<td><input type="text" name="start_date" class="laydate-icon" id="start" style="width:200px; margin-right:10px;" />
				</td>
			</tr>
			<tr>
				<th>结束时间：</th>
				<td><input type="text" name="end_date" id="end" class="laydate-icon"style="width:200px; margin-right:10px;" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn" type="button"
						onclick="saveAdvertise()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('advertise.action')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
</html>
