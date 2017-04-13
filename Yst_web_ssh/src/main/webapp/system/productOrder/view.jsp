<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var type = $("input[name='type']:checked");
		/* if (type.length == 0) {
			$("#save").tips({
				side : 2,
				msg : '请选择要修改的订单状态',
				bg : '#AE81FF',
				time : 2
			});
			return false;
		} */
		var divide = $("#divide");
		var price = $("input[name='price']");
		price.val(Math.round(price.val() * 100));
		for (var i = 1; i <= divide.val(); i++) {
			var fee = $("input[name='fee" + i + "']");
			fee.val(Math.round(fee.val()*100));
		}
		return true;
	}
	function saveProductOrder() {
		if (check()) {
			var form = $("#userInfoForm");
			var options = {
				url : '${baseurl}/ydmvc/main/after/getUpdateProductOrderStatus.do', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var jsonObj = JSON.parse(result);
					var message = jsonObj.msg;
					var code = jsonObj.code;
					$("#save").tips({
						side : 2,
						msg : message,
						bg : '#68B500',
						time : 2
					});
					if (code == 1) {
						pageGo("${baseurl}/ydmvc/main/after/viewProductOrderPage.do?id=${result.product_order_id }");
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
</script>
<body>
	<ul class="breadcrumb">
		<li class="active">查看订单详情：</li>
	</ul>
	<br>
	<form class="form-horizontal" id="userInfoForm" method="post">
		<div class="control-group">
			<label class="control-label">订单编号：</label>
			<div class="controls">
				<input type="hidden" name="product_order_id" id="product_order_id"
					value="${result.product_order_id }" /> ${result.pay_relative_id }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单支付时间：</label>
			<div class="controls">
				<fmt:formatDate value="${result.pay_time }"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户帐号：</label>
			<div class="controls">${result.customer.phone }</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient_name">患者姓名：</label>
			<div class="controls">
				<c:if test="${result.pay_status<=1 }">
				<input type="text" name="patient_name" id="patient_name"
					value="${result.patient_name }" />
				</c:if>
				<c:if test="${result.pay_status>1 }">
				${result.patient_name }
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient_phone">患者电话：</label>
			<div class="controls">
				<c:if test="${result.pay_status<=1 }">
				<input type="text" name="patient_phone" id="patient_phone"
					value="${result.patient_phone }" />
				</c:if>
				<c:if test="${result.pay_status>1 }">
				${result.patient_phone }
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient_address">患者地址：</label>
			<div class="controls">
				<c:if test="${result.pay_status<=1 }">
				<input type="text" name="patient_address" id="patient_address"
					value="${result.patient_address }" />
				</c:if>
				<c:if test="${result.pay_status>1 }">
				${result.patient_address }
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">推荐人姓名：</label>
			<div class="controls">${result.customer.recomm_customer.name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="recomm_phone">推荐人手机：</label>
			<div class="controls">
				<c:if test="${result.pay_status<=1 }">
				<input type="text" name="recomm_phone" id="recomm_phone"
					value="${result.recomm_phone }" />
				</c:if>
				<c:if test="${result.pay_status>1 }">
				${result.recomm_phone }
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="recomm_address">推荐人地址：</label>
			<div class="controls">
				<c:if test="${result.pay_status<=1 }">
				<input type="text" name="recomm_address" id="recomm_address"
					value="${result.recomm_address }" />
				</c:if>
				<c:if test="${result.pay_status>1 }">
				${result.recomm_address }
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">${result.product.name }</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属总院：</label>
			<div class="controls">${result.merchant.name }</div>
		</div>
		<div class="control-group">
			<label class="control-label">定金金额：</label>
			<div class="controls">${result.deposite_price/100 }元</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="merchant_address">总院地址：</label>
			<div class="controls">
				<c:if test="${result.pay_status<=1 }">
				<input type="text" name="merchant_address" id="merchant_address"
					value="${result.merchant_address }" />
				</c:if>
				<c:if test="${result.pay_status>1 }">
				${result.merchant_address }
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="merchant_phone">总院电话：</label>
			<div class="controls">
				<c:if test="${result.pay_status<=1 }">
				<input type="text" name="merchant_phone" id="merchant_phone"
					value="${result.merchant_phone }" />
				</c:if>
				<c:if test="${result.pay_status>1 }">
				${result.merchant_phone }
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单状态：</label>
			<div class="controls">
				<c:if test="${result.pay_status==0 }">待支付定金</c:if>
				<c:if test="${result.pay_status==1 }">已支付定金</c:if>
				<c:if test="${result.pay_status==2 }">已支付全款</c:if>
				<c:if test="${result.pay_status==3 }">确认一期款</c:if>
				<c:if test="${result.pay_status==4 }">确认二期款</c:if>
				<c:if test="${result.pay_status==5 }">确认三期款</c:if>
				<c:if test="${result.pay_status==6 }">确认四期款</c:if>
				<c:if test="${result.pay_status==7 }">确认五期款</c:if>
				<c:if test="${result.pay_status==8 }">退一期款</c:if>
				<c:if test="${result.pay_status==9 }">退二期款</c:if>
				<c:if test="${result.pay_status==10 }">退三期款</c:if>
				<c:if test="${result.pay_status==11 }">退四期款</c:if>
				<c:if test="${result.pay_status==12 }">退五期款</c:if>
				<c:if test="${result.pay_status==13 }">订单完成</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单状态：</label>
			<div class="controls">
				全款金额：<c:if test="${result.pay_status<=1 }">
				<input type="text" name="price" value="${result.price/100 }"/>
				</c:if>
				<c:if test="${result.pay_status>1 }">
				${result.price/100 }
				</c:if>元 <br>
				
				<input type="hidden" name="divide" id="divide"
					value="${result.divide }" />
				<c:forEach begin="1" step="1" end="${result.divide }" var="j">
				<c:set var="str" value="fee${j}"></c:set>
				 第${j}期金额：<c:if test="${result.pay_status<=1 }">
				<input type="text" name="fee${j}" value="${result[str]/100 }"/>
				</c:if>
				<c:if test="${result.pay_status>1 }">
				${result[str]/100 }
				</c:if>元<br>
				</c:forEach>
			</div>
		</div>
		<c:if test="${result.pay_status==1 || (result.pay_status>2 && result.pay_status<8) }">
		<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set> 
		<div class="control-group">
			<label class="control-label" for="type">订单状态：</label>
			<div class="controls">
				<c:if test="${result.pay_status<=1 }">
				<input  type="radio" name="type" id="type" value="2"  />已支付全款
				</c:if>
				<c:if test="${result.pay_status==3 && result.fee1_pay_status==2 && nowDate-result.fee1_update_time.getTime()<1296000000}">
				<input  type="radio" name="type" id="type" value="8"  />退一期款
				</c:if>
				<c:if test="${result.pay_status==4 && result.fee2_pay_status==2 && nowDate-result.fee2_update_time.getTime()<1296000000}">
				<input  type="radio" name="type" id="type" value="9"  />退二期款
				</c:if>
				<c:if test="${result.pay_status==5 && result.fee3_pay_status==2 && nowDate-result.fee3_update_time.getTime()<1296000000}">
				<input  type="radio" name="type" id="type" value="10"  />退三期款
				</c:if>
				<c:if test="${result.pay_status==6 && result.fee4_pay_status==2 && nowDate-result.fee4_update_time.getTime()<1296000000}">
				<input  type="radio" name="type" id="type" value="11"  />退四期款
				</c:if>
				<c:if test="${result.pay_status==7 && result.fee5_pay_status==2 && nowDate-result.fee5_update_time.getTime()<1296000000}">
				<input  type="radio" name="type" id="type" value="12"  />退五期款
				</c:if>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<div class="controls">
				<c:if test="${result.pay_status==1 || (result.pay_status>2 && result.pay_status<8) }">
				<button class="btn" type="button" onclick="saveProductOrder()"
						id="save">保存</button>
				</c:if>
				<button class="btn" type="button"
					onclick="pageGo('productOrder.action')">返回</button>
			</div>
		</div>
	</form>
</body>
</html>
