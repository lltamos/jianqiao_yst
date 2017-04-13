<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	function check() {
		var $phone = $("input[name='phone']");
		if ($phone.val() == "") {
			$phone.tips({
				side : 2,
				msg : '手机号不得为空',
				bg : '#AE81FF',
				time : 2
			});

			$phone.focus();
			return false;
		} else {
			$phone.val(jQuery.trim($phone.val()));
		}
		var name = $("input[name='name']");
		if (name.val() == "") {
			name.tips({
				side : 2,
				msg : '姓名不得为空',
				bg : '#AE81FF',
				time : 2
			});

			name.focus();
			return false;
		} else {
			name.val(jQuery.trim(name.val()));
		}
		var $password = $("input[name='customerAddress.address']");
		if ($password.val() == "") {
			$password.tips({
				side : 2,
				msg : '地址不得为空',
				bg : '#AE81FF',
				time : 2
			});

			$password.focus();
			return false;
		} else {
			$password.val(jQuery.trim($password.val()));
		}
		return true;
	}
	function savePatient() {
		if (check()) {
			var form = $("#userInfoForm");
			var options = {
				url : '${baseurl}/jianqiao/patient/ajaxAddPatient.do', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var appResult = eval("(" + result + ")");
					var message = appResult.error_info;
					var success = appResult.result;
					$("#save").tips({
						side : 2,
						msg : message,
						bg : '#68B500',
						time : 2
					});
					if (success == "SUCCESS") {
						pageGo('${baseurl}/jianqiao/patient/patientPage.do');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
</script>
<body>
	<ul class="breadcrumb">
		<li class="active">查看档案详情：</li>
	</ul>
	<br>
	<form class="form-horizontal" id="userInfoForm" method="post">
		<div class="control-group">
			<label class="control-label" for="patientName">姓名：</label>
			<div class="controls">
				${result.patient.patientName}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patientPhone">手机号：</label>
			<div class="controls">
				${result.patient.patientPhone}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="address">家庭住址：</label>
			<div class="controls">
				${result.patient.address}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="customer.name">患者推荐人姓名：</label>
			<div class="controls">
				${result.patient.customer.name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="customer.phone">患者推荐人手机：</label>
			<div class="controls">
				${result.patient.customer.phone }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="expertCustomer.name">专家推荐人姓名：</label>
			<div class="controls">
				${result.patient.expertCustomer.name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="expertCustomer.phone">专家推荐人手机：</label>
			<div class="controls">
				${result.patient.expertCustomer.phone }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patientName">病例档案：</label>
			<div class="panel-group controls" id="accordion">
			   <div class="panel success">
			      <div class="panel-heading">
			         <h4 class="panel-title">
			            <a data-toggle="collapse" data-parent="#accordion" 
			               href="#demo1">
			               展开+
			            </a>
			         </h4>
			      </div>
			      <div id="demo1" class="panel-collapse collapse">
			         <div class="panel-body">
			            <span>
						<c:forEach items="${result.patientCase}" var="patientCase" >
			           	 	<span>${patientCase['content']}</span><br/><br/>
			            </c:forEach>
						<br/></span>
			         </div>
			      </div>
			   </div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patientName">用药记录：</label>
			<div class="panel-group controls" id="accordion">
			   <div class="panel warning">
			      <div class="panel-heading">
			         <h4 class="panel-title">
			            <a data-toggle="collapse" data-parent="#accordion" 
			               href="#demo2">
			               展开+
			            </a>
			         </h4>
			      </div>
			      <div id="demo2" class="panel-collapse collapse">
			         <div class="panel-body">
			            <span>
						<c:forEach items="${result.patientHistory}" var="patientHistory" >
			           	 	<span>${patientHistory['content']}</span><br/><br/>
			            </c:forEach>
			            <br/></span>
			         </div>
			      </div>
			   </div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patientName">病史及过敏史：</label>
			<div class="panel-group controls" id="accordion">
			   <div class="panel panel-info">
			      <div class="panel-heading">
			         <h4 class="panel-title">
			            <a data-toggle="collapse" data-parent="#accordion" 
			               href="#demo3">
			               展开+
			            </a>
			         </h4>
			      </div>
			      <div id="demo3" class="panel-collapse collapse">
			         <div class="panel-body">
			            <span>
						 <c:forEach items="${result.patientMedicationRecord}" var="patientMedicationRecord" >
			           	 	<span>${patientMedicationRecord['content']}</span><br/><br/>
			            </c:forEach>
			            <br/></span>
			         </div>
			      </div>
			   </div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patientName">影像资料：</label>
			<div class="panel-group controls" id="accordion">
			   <div class="panel panel-info">
			      <div class="panel-heading">
			         <h4 class="panel-title">
			            <a data-toggle="collapse" data-parent="#accordion" 
			               href="#demo4">
			               展开+
			            </a>
			         </h4>
			      </div>
			      <div id="demo4" class="panel-collapse collapse">
			         <div class="panel-body">
			            <span>
			            <c:forEach items="${result.patientImage}" var="patientImage" >
			           	 	<img src="${patientImage['address']}" /><br/>
			            </c:forEach>
			            <br/></span>
			         </div>
			      </div>
			   </div>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
					<button class="btn" type="button" onclick="pageGo('${ctx }/patient/to-patient-list')">返回</button>
			</div>
		</div>
	</form>
</body>
