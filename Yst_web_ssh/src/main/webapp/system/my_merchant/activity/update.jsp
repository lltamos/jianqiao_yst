<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	var editor = null;
	function check() {
		var name = $("input[name='name']");
		if (name.val() == "") {
			name.tips({
				side : 2,
				msg : '名称不得为空',
				bg : '#AE81FF',
				time : 3
			});

			name.focus();
			return false;
		} else {
			name.val(jQuery.trim(name.val()));
		}
		var short_desc = $("#short_desc");
		if (short_desc.val() == "") {
			short_desc.tips({
				side : 2,
				msg : '简介不得为空',
				bg : '#AE81FF',
				time : 3
			});

			short_desc.focus();
			return false;
		} else {
			short_desc.val(jQuery.trim(short_desc.val()));
		}
		var begin_time = $("input[name='begin_time']");
		if (begin_time.val() == "") {
			begin_time.tips({
				side : 2,
				msg : '开始时间不能为空',
				bg : '#AE81FF',
				time : 3
			});

			begin_time.focus();
			return false;
		} else {
			begin_time.val(jQuery.trim(begin_time.val()));
		}
		var end_time = $("input[name='end_time']");
		if (end_time.val() == "") {
			end_time.tips({
				side : 2,
				msg : '结束时间不能为空',
				bg : '#AE81FF',
				time : 3
			});

			end_time.focus();
			return false;
		} else {
			end_time.val(jQuery.trim(end_time.val()));
		}
		var activity_type = $("#activity_type").val();
		if (activity_type == 1) {
			var prod_acti_id = $("select[name='prod_acti_id']");
			if (prod_acti_id.val() == "") {
				prod_acti_id.tips({
					side : 2,
					msg : '促销商品不能为空',
					bg : '#AE81FF',
					time : 3
				});
				prod_acti_id.focus();
				return false;
			} else {
				prod_acti_id.val(jQuery.trim(prod_acti_id.val()));
			}
		} else if (activity_type == 2) {
			var content_desc = $("#content_desc");
			if (content_desc.val() == "") {
				$("#save").tips({
					side : 2,
					msg : '图文详情不得为空',
					bg : '#AE81FF',
					time : 3
				});
				content_desc.focus();
				return false;
			}
		}
		return true;
	}
	// 保存用户信息
	function saveActivity() {
		var detail = $("#content_desc");
		detail.val(editor.getData());
		if (check()) {
			var form = $("#activityInfoForm");
			var options = {
				url : 'merchantActivity!updateAjax', //提交给哪个执行
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
						pageGo('merchantActivity.action');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
	function pageGo(href) {
		$("#index_div").load(href);
	}
	function checkActivity_type() {
		var activity_type = $("#activity_type").val();
		for (var i = 1; i <= 2; i++) {
			if (i == activity_type) {
				$("#type" + i).show();
			} else {
				$("#type" + i).hide();
			}
		}
	}
	$(function() {
		if (CKEDITOR.env.ie && CKEDITOR.env.version < 9)
			CKEDITOR.tools.enableHtml5Elements(document);
		// The trick to keep the editor in the sample quite small
		// unless user specified own height.
		editor = CKEDITOR.replace("content_desc");
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
		checkActivity_type();
	});
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>活动列表 <span class="divider">/</span>
		</li>
		<li class="active">添加活动信息：</li>
	</ul>

	<form method="post" id="activityInfoForm">
		<input type="hidden" name="merchant_activity_id"
			value="<s:property value="merchant_activity_id"/>" />
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>活动名称：</th>
				<td><input type="text" name="name" value="<s:property value="name"/>" /></td>
			</tr>
			<tr>
				<th>简单描述：</th>
				<td><textarea name="short_desc" id="short_desc"><s:property value="short_desc"/></textarea></td>
			</tr>
			<tr>
				<th>活动图片：</th>
				<td><img alt="image" src="<s:property value="image"/>" width="100"
							height="100"><button class="btn"
						type="button" onclick="$('#image').show()">替换图片</button><input hidden="hidden" id="image" type="file" name="image" /></td>
			</tr>
			<tr>
				<th>开始时间：</th>
				<td><input type="text" name="begin_time" class="laydate-icon"
					id="start" style="width:200px; margin-right:10px;" value="<s:property value="begin_time"/>" /></td>
			</tr>
			<tr>
				<th>结束时间：</th>
				<td><input type="text" name="end_time" id="end"
					class="laydate-icon" style="width:200px; margin-right:10px;" value="<s:property value="end_time"/>"  /></td>
			</tr>
			<tr>
				<th>活动类型：</th>
				<td><select name="activity_type" id="activity_type"
					onchange="checkActivity_type()">
						<option value="1" <s:if test="activity_type==1">selected</s:if>>商品促销</option>
						<option value="2" <s:if test="activity_type==2">selected</s:if>>活动召集</option>
				</select></td>
			<tr id="type1">
				<th>促销商品：</th>
				<td><select name="prod_acti_id">
						<s:iterator value="#products">
							<option value="<s:property value="product_id"/>" <s:if test="prod_acti_id==product_id">selected</s:if>>
								<s:property value="name" /></option>
						</s:iterator>
				</select></td>
			</tr>
			<tr id="type2">
				<th>图文详情：</th>
				<td><textarea name="content_desc" id="content_desc"><s:property
							value="content_desc" /></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="saveActivity()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('merchantActivity.action')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
</html>
