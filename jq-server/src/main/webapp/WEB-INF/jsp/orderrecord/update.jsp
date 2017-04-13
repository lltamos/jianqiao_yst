<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var price = $("input[name='price']");
		if (price.val() == "") {
			price.tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#AE81FF',
				time : 3
			});
	
			price.focus();
			return false;
		} else {
			price.val(jQuery.trim(price.val()));
		}
		var pay_relative_id = $("input[name='pay_relative_id']");
		if (pay_relative_id.val() == "") {
			pay_relative_id.tips({
				side : 2,
				msg : '支付宝订单号不能为空',
				bg : '#AE81FF',
				time : 3
			});
	
			pay_relative_id.focus();
			return false;
		}else {
			pay_relative_id.val(jQuery.trim(pay_relative_id.val()));
		}
		return true;
	}
	// 修改订单信息
	function updateDoctorServiceOrder() {
		if (check()) {
			var form = $("#doctorInfoForm");
			var options = {
				url : '${ctx}/doctorserviceorder/doctorserviceorder-modify', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var appResult = eval("(" + result + ")");
					var message = appResult.error_info;
					var success = appResult.result;
					var result = JSON.parse(result);
					var message = result.msg;
					var code = result.code;
					if (success != "SUCCESS" ) {
						$("#save").tips({
							side : 2,
							msg : message,
							bg : '#68B500',
							time : 10
						});
						if (code==1) {
							pageGo('${ctx }/doctorserviceorder/to-doctorserviceorder-list');
						}
					} else {
						pageGo('${ctx }/doctorserviceorder/to-doctorserviceorder-list');
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
		<li><a href="${ctx }/user/user-index">主页</a> <span class="divider">/</span></li>
		<li>订单记录列表 <span class="divider">/</span>
		</li>
		<li class="active">修改订单信息：</li>
	</ul>
	<form class="form-horizontal" id="doctorInfoForm" method="post">
		<input type="hidden" name="id" value="${ dsorder.id}"/>
		<div class="control-group">
			<label class="control-label" for="str_customer_name">所属用户：</label>
			<div class="controls">
				${dsorder.str_customer_name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="str_doctor_name">所属医生：</label>
			<div class="controls">
				${dsorder.str_doctor_name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="order_date">订单时间：</label>
			<div class="controls">
				<fmt:formatDate value="${dsorder.order_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="serviceType">服务类型：</label>
			<div class="controls">
				<select name="serviceType">
						<option value="1" <c:if test="${dsorder.serviceType ==1 }">selected</c:if>>在线咨询</option>
						<option value="2" <c:if test="${dsorder.serviceType ==2 }">selected</c:if>>在线预约</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="price">价格：</label>
			<div class="controls">
				<input type="text" name="price" id="price" value="${dsorder.price }"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="executed">执行状态：</label>
			<div class="controls">
				<select name="executed">
						<option value="0" <c:if test="${dsorder.executed ==0 }">selected</c:if>>未执行</option>
						<option value="1" <c:if test="${dsorder.executed ==1 }">selected</c:if>>正在执行中</option>
						<option value="2" <c:if test="${dsorder.executed ==2 }">selected</c:if>>执行完毕</option>
						<option value="3" <c:if test="${dsorder.executed ==3 }">selected</c:if>>评价完毕</option> 
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="payStatus">支付状态：</label>
			<div class="controls">
				<select name="payStatus">
						<option value="0" <c:if test="${dsorder.payStatus ==0 }">selected</c:if>>未支付</option>
						<option value="1" <c:if test="${dsorder.payStatus ==1 }">selected</c:if>>已支付</option> 
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="pay_type">支付类型：</label>
			<div class="controls">
				<select name="pay_type">
						<option value="0" <c:if test="${dsorder.pay_type ==0 }">selected</c:if>>系统内支付</option>
						<option value="1" <c:if test="${dsorder.pay_type ==1 }">selected</c:if>>支付宝</option>
						<option value="2" <c:if test="${dsorder.pay_type ==2 }">selected</c:if>>银联</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="pay_time">支付时间：</label>
			<div class="controls">
				<input type="text" class="laydate-icon" name="pay_time" id="start" value="${dsorder.pay_time }"  style="width:200px; margin-right:10px;"/> 
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="pay_relative_id">支付宝订单号：</label>
			<div class="controls">
				<input type="text" name="pay_relative_id" id="pay_relative_id" value="${dsorder.pay_relative_id }"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="approved">是否同意：</label>
			<div class="controls">
				<select name="approved">
						<option value="0" <c:if test="${dsorder.approved ==0 }">selected</c:if>>开始</option>
						<option value="1" <c:if test="${dsorder.approved ==1 }">selected</c:if>>同意</option>
						<option value="2" <c:if test="${dsorder.approved ==2 }">selected</c:if>>拒绝</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
					<button class="btn"
						type="button" onclick="updateDoctorServiceOrder()" id="update">确定</button>
					<button class="btn" type="button"
						onclick="pageGo('${ctx}/doctorserviceorder/to-doctorserviceorder-list')">返回</button>
			</div>
		</div>
	</form>
</body>
</html>
