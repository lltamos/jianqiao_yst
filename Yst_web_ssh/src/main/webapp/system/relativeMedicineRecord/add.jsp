<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
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
	// 添加相关人员用药病例记录
	function saveRelativeMedicineRecord() {
		if (check()) {
			var form = $("#doctorInfoForm");
			var options = {
				url : 'relativeMedicineRecord!addAjax', //提交给哪个执行
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
						pageGo('relativeMedicineRecord.action');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
$(function() {
	var start = {
			elem : '#start',//目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
			//event: 'focus', //响应事件。如果没有传入event，则按照默认的click
			//festival: true, //显示节日
			format : 'YYYY-MM-DD hh:mm:ss',
			min : laydate.now(), //设定最小日期为当前日期
			max : '2099-06-16 23:59:59', //最大日期
			istime : true,
			istoday : false
		};
		laydate(start);
	});
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>用药记录病例记录列表 <span class="divider">/</span>
		</li>
		<li class="active">添加用药记录：</li>
	</ul>
	<form method="post" id="doctorInfoForm">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>所属相关人员：</td>
				<td>
					<select name="relative_id">
						<s:iterator value="#customerRelativeList">
								<option value="<s:property value="relative_id"/>">
									<s:property value="name" />
								</option>
						</s:iterator>
					</select>
				</td>
			</tr>
			<tr>
				<td>症状及诊断：</td>
				<td><input type="text" name="des" value="" /></td>
			</tr>
			<tr>
				<td>医生名称：</td>
				<td><input type="text" name="doctor_name" value="" /></td>
			</tr>
			<tr>
				<td>科室：</td>
				<td>
					<input type="text" name="office_name" value="" />
				</td>
			</tr>
			<tr>
				<td>用药时长：</td>
				<td><input type="text" name="during" value="" /></td>
			</tr>
			<tr>
				<td>服用时间：</td>
				<td><input type="text" class="laydate-icon" name="start_date" id="start"  style="width:200px; margin-right:10px;"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="saveRelativeMedicineRecord()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('relativeMedicineRecord.action')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
</html>
