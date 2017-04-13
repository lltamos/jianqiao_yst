<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	var editor = null;
	function check() {
		var merchant = $("select[name='merchant.merchant_id']");
		if (merchant.val() == "") {
			merchant.tips({
				side : 2,
				msg : '所属总院不得为空',
				bg : '#AE81FF',
				time : 3
			});

			merchant.focus();
			return false;
		} else {
			merchant.val(jQuery.trim(merchant.val()));
		}
		var doctor = $("select[name='doctor_id']");
		if (doctor.val() == "") {
			doctor.tips({
				side : 2,
				msg : '主治医生不得为空',
				bg : '#AE81FF',
				time : 3
			});

			doctor.focus();
			return false;
		} else {
			doctor.val(jQuery.trim(doctor.val()));
		}
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

		var des1 = $("#des1");
		if (des1.val() == "") {
			des1.tips({
				side : 2,
				msg : '内容介绍不得为空',
				bg : '#AE81FF',
				time : 3
			});
			des1.focus();
			return false;
		} else {
			des1.val(jQuery.trim(des1.val()));
		}
		var price = $("input[name='price']");

		if (price.val() == "") {
			price.tips({
				side : 2,
				msg : '总价不得为空',
				bg : '#AE81FF',
				time : 3
			});
			price.focus();
			return false;
		} else {
			if (isNaN(price.val())) {
				price.tips({
					side : 2,
					msg : '请输入数字',
					bg : '#AE81FF',
					time : 3
				});
				price.focus();
				return false;
			} else {
				if(parseFloat(price.val())<=0){
					price.tips({
						side : 2,
						msg : '总价不能低于0',
						bg : '#AE81FF',
						time : 3
					});
					price.focus();
					return false;
				}
				price.val(jQuery.trim(price.val()));
			}
		}
		var deposite_price = $("input[name='deposite_price']");

		if (deposite_price.val() == "") {
			deposite_price.tips({
				side : 2,
				msg : '定金不得为空',
				bg : '#AE81FF',
				time : 3
			});
			deposite_price.focus();
			return false;
		} else {
			if (isNaN(deposite_price.val())) {
				deposite_price.tips({
					side : 2,
					msg : '请输入数字',
					bg : '#AE81FF',
					time : 3
				});
				deposite_price.focus();
				return false;
			} else {
				if(parseFloat(deposite_price.val())<=0){
					deposite_price.tips({
						side : 2,
						msg : '定金不能低于0',
						bg : '#AE81FF',
						time : 3
					});
					deposite_price.focus();
					return false;
				}
				if(parseFloat(deposite_price.val())>parseFloat(price.val())){
					deposite_price.tips({
						side : 2,
						msg : '定金不能超出总价',
						bg : '#AE81FF',
						time : 3
					});
					deposite_price.focus();
					return false;
				}else{
					deposite_price.val(jQuery.trim(deposite_price.val()));
				}
			}
		}
		var divide = $("#divide");
		var sum = 0;
		for (var i = 1; i <= divide.val(); i++) {
			var fee = $("input[name='fee" + i + "']");
			if (fee.val() == "") {
				fee.tips({
					side : 2,
					msg : i + '期价格不得为空',
					bg : '#AE81FF',
					time : 3
				});
				fee.focus();
				return false;
			} else {
				if (isNaN(fee.val())) {
					fee.tips({
						side : 2,
						msg : '请输入数字',
						bg : '#AE81FF',
						time : 3
					});
					fee.focus();
					return false;
				} else {
					sum += Number(fee.val());
				}
			}
		}
		sum = sum.toFixed(2);
		if (sum != Number(price.val()).toFixed(2)) {
			divide.tips({
				side : 2,
				msg : '分期价格总和与单价不相等',
				bg : '#AE81FF',
				time : 3
			});
			divide.focus();
			return false;
		}
		var freight = $("input[name='freight']");
		if (freight.val() == "") {
			freight.tips({
				side : 2,
				msg : '运费不得为空',
				bg : '#AE81FF',
				time : 3
			});
			freight.focus();
			return false;
		} else {
			if (isNaN(freight.val())) {
				freight.tips({
					side : 2,
					msg : '请输入数字',
					bg : '#AE81FF',
					time : 3
				});
				freight.focus();
				return false;
			} else {
				freight.val(jQuery.trim(freight.val()));
			}
		}
		var detail = $("#detail_html");
		if (detail.val() == "") {
			detail.tips({
				side : 2,
				msg : '图文详情不得为空',
				bg : '#AE81FF',
				time : 3
			});
			detail.focus();
			return false;
		} else {
			detail.val(jQuery.trim(detail.val()));
		}
		var service_process = $("#service_process");
		if (service_process.val() == "") {
			service_process.tips({
				side : 2,
				msg : '服务流程不得为空',
				bg : '#AE81FF',
				time : 3
			});
			service_process.focus();
			return false;
		} else {
			service_process.val(jQuery.trim(service_process.val()));
		}
		var des2 = $("#des2");
		if (des2.val() == "") {
			des2.tips({
				side : 2,
				msg : '如何预约不得为空',
				bg : '#AE81FF',
				time : 3
			});
			des2.focus();
			return false;
		} else {
			des2.val(jQuery.trim(des2.val()));
		}
		var refund_process = $("#refund_process");
		if (refund_process.val() == "") {
			refund_process.tips({
				side : 2,
				msg : '退款流程不得为空',
				bg : '#AE81FF',
				time : 3
			});
			refund_process.focus();
			return false;
		} else {
			refund_process.val(jQuery.trim(refund_process.val()));
		}
		var special = $("#special");
		if (special.val() == "") {
			special.tips({
				side : 2,
				msg : '特别提示不得为空',
				bg : '#AE81FF',
				time : 3
			});
			special.focus();
			return false;
		} else {
			special.val(jQuery.trim(special.val()));
		}
		price.val(Math.round(price.val() * 100));
		deposite_price.val(Math.round(deposite_price.val() * 100));
		freight.val(Math.round(freight.val() * 100));
		for (var i = 1; i <= divide.val(); i++) {
			var fee = $("input[name='fee" + i + "']");
			fee.val(Math.round(fee.val() * 100));
		}
		return true;
	}
	// 保存用户信息
	function saveProduct() {
		var detail = $("#detail_html");
		detail.val(editor.getData());
		if (check()) {
			var form = $("#productInfoForm");
			var options = {
				url : 'product!updateAjax', //提交给哪个执行
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
						pageGo('product.action');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
	function checkDivide() {
		var divide = $("#divide").val();
		for (var i = 1; i <= 5; i++) {
			if (i <= divide) {
				$("#fee" + i).show();
			} else {
				$("#fee" + i).hide();
				$("input[name='fee" + i + "']").val("0");
			}
		}
	}
	$(function() {
		var divide = <s:property value="divide"/>;
		$("#divide").val(divide);
		for (var i = 1; i <= 5; i++) {
			if (i <= divide) {
				$("#fee" + i).show();
			} else {
				$("#fee" + i).hide();
				$("input[name='fee" + i + "']").val("0");
			}
		}
		changeDoctor($("select[name='merchant.merchant_id']"),true);
		if (CKEDITOR.env.ie && CKEDITOR.env.version < 9)
			CKEDITOR.tools.enableHtml5Elements(document);
		// The trick to keep the editor in the sample quite small
		// unless user specified own height.
		editor = CKEDITOR.replace("detail_html");
	});
	function changeDoctor(e,flg) {
		$
				.ajax({
					url : "${ctx}/doctor!getMerchantDoctorList?merchant_id="
							+ $(e).val(),
					type : 'POST',
					success : function(result) {
						var data = eval("(" + result + ")");
						$("select[name='doctor_id']").empty().append(
								"<option value=''>请选择</option>");
						if (data.data != "") {
							$.each(data.data, function() {
								$("select[name='doctor_id']").append(
										"<option value='"+this.doctor_id+"'>"
												+ this.name + "</option>");
							});
						}
						if(flg){
							var doctor_id = "<s:property value="doctor.doctor_id"/>";
							$("select[name='doctor_id']").val(doctor_id);
						}
					}
				});
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>商店列表 <span class="divider">/</span>
		</li>
		<li class="active">修改商店信息：</li>
	</ul>

	<form method="post" id="productInfoForm">
		<input type="hidden" name="product_id"
			value="<s:property value="product_id"/>" />
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>所属商户：</th>
				<td><select name="merchant.merchant_id" onchange="changeDoctor(this,false)">
						<s:iterator value="#merchants">
							<option value="<s:property value="merchant_id"/>"
								<s:if test="merchant_id==merchant.merchant_id">selected</s:if>>
								<s:property value="name" /></option>
						</s:iterator>
				</select></td>
			</tr>
			<tr>
				<th>主治医生：</th>
				<td>
				<select name="doctor_id">
						<option value="">请选择</option>
				</select>
				</td>
			</tr>
			<tr>
				<th>客服账号：</th>
				<td><input type="text" name="customer_service_id"
					value="<s:property
							value="customer_service_id" />" /></td>
			</tr>
			<tr>
				<th>项目所属：</th>
				<td><select name="is_real">
						<option value="0" <s:if test="is_real==0">selected</s:if>>健康项目</option>
						<option value="1" <s:if test="is_real==1">selected</s:if>>疑难杂症</option>
				</select></td>
			</tr>
			<tr>
				<th>项目是否配送：</th>
				<td><select name="product_for">
						<option value="0" <s:if test="product_for==0">selected</s:if>>到店类</option>
						<option value="1" <s:if test="product_for==1">selected</s:if>>配送类</option>
				</select></td>
			</tr>
			<tr>
				<th>项目类别：</th>
				<td><select name="productType.product_type_id">
						<s:iterator value="#productTypes">
							<option value="<s:property value="product_type_id" />"
								<s:if test="product_type_id==productType.product_type_id">selected</s:if>><s:property
									value="name" /></option>
						</s:iterator>
				</select></td>
			</tr>
			<tr>
				<th>项目名称：</th>
				<td><input type="text" name="name"
					value="<s:property value="name"/>" /></td>
			</tr>
			<tr>
				<th>原主图：</th>
				<td><s:if test="image1!=null">
						<img alt="image1" src="<s:property value="image1"/>" width="100"
							height="100">
					</s:if> <s:if test="image2!=null">
						<img alt="image2" src="<s:property value="image2"/>" width="100"
							height="100">
					</s:if> <s:if test="image3!=null">
						<img alt="image3" src="<s:property value="image3"/>" width="100"
							height="100">
					</s:if> <s:if test="image4!=null">
						<img alt="image4" src="<s:property value="image4"/>" width="100"
							height="100">
					</s:if></td>
			</tr>
			<tr>
				<th>重新上传主图：</th>
				<td><input type="file" name="image1" /> <br> <input
					type="file" name="image2" /> <br> <input type="file"
					name="image3" /> <br> <input type="file" name="image4" /></td>
			</tr>
			<tr>
				<th>内容介绍：</th>
				<td><textarea name="des1"><s:property value="des1" /></textarea></td>
			</tr>
			<tr>
				<th>项目总价：</th>
				<td><input type="text" name="price"
					value="<fmt:formatNumber value="${price/100}" pattern="0.00" />" />元</td>
			</tr>
			<tr>
				<th>项目定金：</th>
				<td><input type="text" name="deposite_price"
					value="<fmt:formatNumber value="${deposite_price/100}" pattern="0.00" />" />元</td>
			</tr>
			<tr>
				<th>分期：</th>
				<td><select name="divide" id="divide" onchange="checkDivide()">
						<s:iterator value="{\"1\",\"2\",\"3\",\"4\",\"5\"}" id="number">
							<option value="<s:property value="number"/>"><s:property
									value="number" /></option>
						</s:iterator>
				</select><br> <span id="fee1" hidden="hidden"> 第一期价格：<input
						type="text" name="fee1"
						value="<s:if test="fee1!=null"><fmt:formatNumber value="${fee1/100}" pattern="0.00" /></s:if>" />元<br>
				</span> <span id="fee2" hidden="hidden"> 第二期价格：<input type="text"
						name="fee2"
						value="<s:if test="fee2!=null"><fmt:formatNumber value="${fee2/100}" pattern="0.00" /></s:if>" />元<br>
				</span> <span id="fee3" hidden="hidden"> 第三期价格：<input type="text"
						name="fee3"
						value="<s:if test="fee3!=null"><fmt:formatNumber value="${fee3/100}" pattern="0.00" /></s:if>" />元<br>
				</span> <span id="fee4" hidden="hidden"> 第四期价格：<input type="text"
						name="fee4"
						value="<s:if test="fee4!=null"><fmt:formatNumber value="${fee4/100}" pattern="0.00" /></s:if>" />
						元<br>
				</span> <span id="fee5" hidden="hidden"> 第五期价格：<input type="text"
						name="fee5"
						value="<s:if test="fee5!=null"><fmt:formatNumber value="${fee5/100}" pattern="0.00" /></s:if>" />
				</span></td>
			</tr>
			<tr>
				<th>运费：</th>
				<td><input type="text" name="freight"
					value="<fmt:formatNumber value="${freight/100}" pattern="0.00" />" /></td>
			</tr>
			<tr>
				<th>项目详情：</th>
				<td><textarea name="detail_html" id="detail_html"><s:property
							value="detail_html" /></textarea></td>
			</tr>
			<tr>
				<th>服务流程：</th>
				<td><textarea name="service_process" id="service_process"><s:property
							value="service_process" /></textarea></td>
			</tr>
			<tr>
				<th>如何预约：</th>
				<td><textarea name="des2" id="des2"><s:property
							value="des2" /></textarea></td>
			</tr>
			<tr>
				<th>退款流程：</th>
				<td><textarea name="refund_process" id="refund_process"><s:property
							value="refund_process" /></textarea></td>
			</tr>
			<tr>
				<th>特别提示：</th>
				<td><textarea name="special" id="special"><s:property
							value="special" /></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="saveProduct()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('product.action')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
</html>
