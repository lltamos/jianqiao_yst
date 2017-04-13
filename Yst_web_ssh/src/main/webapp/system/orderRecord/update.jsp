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
	// 修改订单信息
	function updateDoctorServiceOrder() {
		if (check()) {
			var form = $("#doctorInfoForm");
			var options = {
				url : 'doctorServiceOrder!updateAjax', //提交给哪个执行
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
						pageGo('doctorServiceOrder.action');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
	function pageGo(href) {
		$("#index_div").load(href);
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
		<li>订单记录列表 <span class="divider">/</span>
		</li>
		<li class="active">修改病订单信息：</li>
	</ul>
	<form method="post" id="doctorInfoForm">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>所属用户：</td>
				<td>
				<input type="hidden" name="order_id" value="<s:property value="order_id"/>"/>
				<s:property value="str_customer_name"/>
				</td>
			</tr>
			<tr>
				<td>所属医生：</td>
				<td><s:property value="str_doctor_name"/></td>
			</tr>
			<tr>
				<td>订单时间：</td>
				<td><input type="text" class="laydate-icon" name="order_date" id="start" value="<s:property value="order_date" />"  style="width:200px; margin-right:10px;"/></td>
			</tr>
			<tr>
				<td>服务类型：</td>
				<td>
					<select name="service_type_id">
						<s:iterator value="#dicServiceTypesList" var="ds">
								<option value="<s:property value="service_type_id"/>" <s:if test="dicServiceType.service_type_id == service_type_id">selected</s:if>> 
									<s:property value="str_service_type_name" />
								</option>
						</s:iterator>
					</select>
				</td>
			</tr>
			<tr>
				<td>价格：</td>
				<td>
				<input type="text" name="price" value="<s:property value="price"/>"/>
				</td>
			</tr>
			<tr>
				<td>执行状态：</td>
				<td>
					<select name="executed">
						<option value="0" <s:if test="executed == 0">selected</s:if>>未执行</option>
						<option value="1" <s:if test="executed == 1">selected</s:if>>正在执行中</option>
						<option value="2" <s:if test="executed == 2">selected</s:if>>执行完毕</option>
						<option value="3" <s:if test="executed == 3">selected</s:if>>评价完毕</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>支付状态：</td>
				<td>
					<select name="pay_status">
						<option value="0" <s:if test="pay_status == 0">selected</s:if>>未支付</option>
						<option value="1" <s:if test="pay_status == 1">selected</s:if>>已支付</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>支付类型：</td>
				<td>
					<select name="pay_type">
						<option value="0" <s:if test="pay_type == 0">selected</s:if>>系统内支付</option>
						<option value="1" <s:if test="pay_type == 1">selected</s:if>>支付宝</option>
						<option value="2" <s:if test="pay_type == 2">selected</s:if>>银联</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>支付时间：</td>
				<td><input type="text" class="laydate-icon" name="pay_time" id="start" value="<s:property value="pay_time" />"  style="width:200px; margin-right:10px;"/></td>
			</tr>
			<tr>
				<td>支付宝订单号：</td>
				<td>
				<input type="text" name="pay_relative_id" value="<s:property value="pay_relative_id"/>"/>
				</td>
			</tr>
			<tr>
				<td>是否同意：</td>
				<td>
				<select name="approved">
						<option value="0" <s:if test="approved == 0">selected</s:if>>开始</option>
						<option value="1" <s:if test="approved == 1">selected</s:if>>同意</option>
						<option value="2" <s:if test="approved == 2">selected</s:if>>拒绝</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="updateDoctorServiceOrder()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('doctorServiceOrder.action')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
</html>
