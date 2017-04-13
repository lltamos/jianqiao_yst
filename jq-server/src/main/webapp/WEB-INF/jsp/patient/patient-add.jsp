<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	function check() {
		var name = $("input[name='patientName']");
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
		var $phone = $("input[name='patientPhone']");
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
		var $password = $("input[name='address']");
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
		var customer = $(".customer");
		if (customer.val() == "") {
			customer.tips({
				side : 2,
				msg : '请选择患者推荐人',
				bg : '#AE81FF',
				time : 2
			});

			customer.focus();
			return false;
		}
		var expertCustomer = $(".expertCustomer");
		if (expertCustomer.val() == "") {
			expertCustomer.tips({
				side : 2,
				msg : '请选择专家推荐人',
				bg : '#AE81FF',
				time : 2
			});

			expertCustomer.focus();
			return false;
		}
		return true;
	}
	function savePatient() {
		if (check()) {
			var form = $("#userInfoForm");
			var options = {
				url : '${ctx}/patient/patient-add', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					var appResult = eval("(" + result + ")");
					var message = appResult.error_info;
					var result = appResult.result;
					$("#save").tips({
						side : 2,
						msg : message,
						bg : '#68B500',
						time : 2
					});
					if (result == "SUCCESS") {
						pageGo('${ctx }/patient/to-patient-list');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
	jsonData = {"patientCases": 1, "patientHistorys": 1, "patientMedicationRecords": 1 , "imagePatients": 1  };
	function addRow(e) {
		var name = $(e).prev().attr("name");
		$.each(jsonData,function (key,value){
			if(name==key){
				value++;
				jsonData[name]=value;
				return;
			}
		});
		var span = $(e).parent().parent();
		if(name=="imagePatients"){
			span.append("<span><input type='file' name='"+name+"'/>"+
			            "<button class='btn' type='button' title='删除' onclick='deleteRow(this)'><i class='icon-minus'></i></button><br /></span>");
		}else{
			span.append("<span><textarea name='"+name+"' style='width:600px;height: 400px;' ></textarea>"+
			            "<button class='btn' type='button' title='删除' onclick='deleteRow(this)'><i class='icon-minus'></i></button><br /></span>");
		}
		
	}
	function deleteRow(e) {
		var row = $(e).parent();
		var name = $(e).prev().attr("name");
		$.each(jsonData,function (key,value){
			if(name==key){
				value--;
				jsonData[name]=value;
				return;
			}
		});
		row.remove();
	}
</script>
<body>
	<ul class="breadcrumb">
		<li class="active">添加患者信息：</li>
	</ul>
	<br>
	<form class="form-horizontal" id="userInfoForm" method="post" enctype="multipart/form-data">
		<div class="control-group">
			<label class="control-label" for="patientName">姓名：</label>
			<div class="controls">
				<input type="text" name="patientName" id="patientName" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patientPhone">手机号：</label>
			<div class="controls">
				<input type="text" name="patientPhone" id="patientPhone" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="address">家庭住址：</label>
			<div class="controls">
				<input type="text" name="address" id="address" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="customer.id">患者推荐人：</label>
			<div class="controls">
				<select class="customer" name="customer.id" id="customer.customer_id" style="width: 350px;">
					<option value="">请选择</option>
					<c:forEach items="${customers}" var="revar">
						<option value="${revar['id']}">
									手机号：${revar['phone']}，姓名：${revar['name']}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="expertCustomer.id">专家推荐人：</label>
			<div class="controls">
				<select class="expertCustomer" name="expertCustomer.id" id="expertCustomer.customer_id" style="width: 350px;">
					<option value="">请选择</option>
					<c:forEach items="${customers}" var="revar">
						<option value="${revar['id']}">
									手机号：${revar['phone']}，姓名：${revar['name']}</option>
					</c:forEach>
				</select>
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
			            <textarea name="patientCases" style="width:600px;height: 400px;" ></textarea>
			            <button class='btn' type='button' title='新增'
							onclick='addRow(this)'>
							<i class='icon-plus'></i>
						</button>
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
			            <textarea name="patientHistorys" style="width:600px;height: 400px;" ></textarea>
			            <button class='btn' type='button' title='新增'
							onclick='addRow(this)'>
							<i class='icon-plus'></i>
						</button>
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
			            <textarea name="patientMedicationRecords" style="width:600px;height: 400px;" ></textarea>
			            <button class='btn' type='button' title='新增'
							onclick='addRow(this)'>
							<i class='icon-plus'></i>
						</button>
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
			            <input type="file" name="imagePatients" />
			            <button class='btn' type='button' title='新增'
							onclick='addRow(this)'>
							<i class='icon-plus'></i>
						</button>
			            <br/></span>
			         </div>
			      </div>
			   </div>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button class="btn"
						type="button" onclick="savePatient()" id="save">保存</button>
					<button class="btn" type="button" onclick="pageGo('${ctx}/patient/to-patient-list')">返回</button>
			</div>
		</div>
	</form>
</body>
