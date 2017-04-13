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
	// 修改病例记录信息
	function updateDoctor() {
		if (check()) {
			var form = $("#doctorInfoForm");
			var options = {
				url : 'customerIllnessRecord!updateAjax', //提交给哪个执行
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
						pageGo('customerIllnessRecord.action');
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
		<li>病例列表 <span class="divider">/</span>
		</li>
		<li class="active">修改病例记录信息：</li>
	</ul>
	<form method="post" id="doctorInfoForm">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>所属相关人员：</td>
				<td>
					<input type="hidden" name="record_id" value="<s:property value="record_id"/>"/>
					<select name="relative_id">
						<s:iterator value="#customerRelativeList">
								<option value="<s:property value="relative_id"/>" <s:if test="relative_id == customerRelative.relative_id">selected</s:if>> 
									<s:property value="name" />
								</option>
						</s:iterator>
					</select>
				</td>
			</tr>
			<tr>
				<td>症状：</td>
				<td><input type="text" name="symptom" value="<s:property value="symptom" />" /></td>
			</tr>
			<tr>
				<td>医生诊断：</td>
				<td><input type="text" name="diagnose" value="<s:property value="diagnose" />" /></td>
			</tr>
			<tr>
				<td>发病过程：</td>
				<td><input type="text" name="proce" value="<s:property value="proce" />" /></td>
			</tr>
			<tr>
				<td>相关图片：</td>
				<td>
					<input type="file" name="image1" />
					<br/>
					<input type="file" name="image2" />
					<br/>
					<input type="file" name="image3" />
					<br/>
					<input type="file" name="image4" />
					<br/>
					<input type="file" name="image5" />
					<br/>
					<input type="file" name="image6" />
					<br/>
					<input type="file" name="image7" />
					<br/>
					<input type="file" name="image8" />
					<br/>
					<input type="file" name="image9" />
					<br/>
					<input type="file" name="image10" />
					<br/>
					<input type="file" name="image11" />
					<br/>
					<input type="file" name="image12" />
					<br/>
					<input type="file" name="image13" />
					<br/>
					<input type="file" name="image14" />
					<br/>
					<input type="file" name="image15" />
					<br/>
					<input type="file" name="image16" />
					<br/>
					<input type="file" name="image17" />
					<br/>
					<input type="file" name="image18" />
					<br/>
					<input type="file" name="image19" />
					<br/>
					<input type="file" name="image20" />
					<br/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="updateDoctor()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('customerIllnessRecord.action')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
</html>
