<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var type = $("input[name='type']:checked");
		if (type.length == 0) {
			$("#save").tips({
				side : 2,
				msg : '请选择要修改的订单状态',
				bg : '#AE81FF',
				time : 2
			});
			return false;
		}
		var divide = $("#divide");
		var price = $("input[name='totalPrice']");
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
				url : '${ctx}/productorder/productorder-save', //提交给哪个执行
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
						pageGo("${ctx}/productorder/productorder-info?id=${productOrder.id }");
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
				<input type="hidden" name="id" id="id"
					value="${productOrder.id }" /> ${productOrder.payRelativeId }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单支付时间：</label>
			<div class="controls">
				<fmt:formatDate value="${productOrder.payTime }"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户帐号：</label>
			<div class="controls">${productOrder.customerPhone }</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patientName">患者姓名：</label>
			<div class="controls">
				<c:if test="${productOrder.payStatus<=1 }">
				<input type="text" name="patientName" id="patientName"
					value="${productOrder.patientName }" />
				</c:if>
				<c:if test="${productOrder.payStatus>1 }">
				${productOrder.patientName }
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patientPhone">患者电话：</label>
			<div class="controls">
				<c:if test="${productOrder.payStatus<=1 }">
				<input type="text" name="patientPhone" id="patientPhone"
					value="${productOrder.patientPhone }" />
				</c:if>
				<c:if test="${productOrder.payStatus>1 }">
				${productOrder.patientPhone }
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patientAddress">患者地址：</label>
			<div class="controls">
				<c:if test="${productOrder.payStatus<=1 }">
				<input type="text" name="patientAddress" id="patientAddress"
					value="${productOrder.patientAddress }" />
				</c:if>
				<c:if test="${productOrder.payStatus>1 }">
				${productOrder.patientAddress }
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="recommName">推荐人姓名：</label>
			<div class="controls">
				<c:if test="${productOrder.payStatus<=1 }">
					<input type="text" name="recommName" id="recommName"
						value="${productOrder.recommName }" />
					</c:if>
					<c:if test="${productOrder.payStatus>1 }">
					${productOrder.recommName }
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="recommPhone">推荐人手机：</label>
			<div class="controls">
				<c:if test="${productOrder.payStatus<=1 }">
				<input type="text" name="recommPhone" id="recommPhone"
					value="${productOrder.recommPhone }" />
				</c:if>
				<c:if test="${productOrder.payStatus>1 }">
				${productOrder.recommPhone }
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="recommAddress">推荐人地址：</label>
			<div class="controls">
				<c:if test="${productOrder.payStatus<=1 }">
				<input type="text" name="recommAddress" id="recommAddress"
					value="${productOrder.recommAddress }" />
				</c:if>
				<c:if test="${productOrder.payStatus>1 }">
				${productOrder.recommAddress }
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">${productOrder.productName }</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属总院：</label>
			<div class="controls">${productOrder.merchantName }</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目推荐人：</label>
			<div class="controls">${productOrder.productRecommName }</div>
		</div>
		<div class="control-group">
			<label class="control-label">定金金额：</label>
			<div class="controls">${productOrder.depositePrice/100 }元</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="merchantAddress">总院地址：</label>
			<div class="controls">
				<c:if test="${productOrder.payStatus<=1 }">
				<input type="text" name="merchantAddress" id="merchantAddress"
					value="${productOrder.merchantAddress }" />
				</c:if>
				<c:if test="${productOrder.payStatus>1 }">
				${productOrder.merchantAddress }
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="merchantPhone">总院电话：</label>
			<div class="controls">
				<c:if test="${productOrder.payStatus<=1 }">
				<input type="text" name="merchantPhone" id="merchantPhone"
					value="${productOrder.merchantPhone }" />
				</c:if>
				<c:if test="${productOrder.payStatus>1 }">
				${productOrder.merchantPhone }
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单状态：</label>
			<div class="controls">
				<c:if test="${productOrder.payStatus==0 }">待支付定金</c:if>
				<c:if test="${productOrder.payStatus==1 }">已支付定金</c:if>
				<c:if test="${productOrder.payStatus==2 }">已支付全款</c:if>
				<c:if test="${productOrder.payStatus==3 }">确认一期款</c:if>
				<c:if test="${productOrder.payStatus==4 }">确认二期款</c:if>
				<c:if test="${productOrder.payStatus==5 }">确认三期款</c:if>
				<c:if test="${productOrder.payStatus==6 }">确认四期款</c:if>
				<c:if test="${productOrder.payStatus==7 }">确认五期款</c:if>
				<c:if test="${productOrder.payStatus==8 }">退一期款</c:if>
				<c:if test="${productOrder.payStatus==9 }">退二期款</c:if>
				<c:if test="${productOrder.payStatus==10 }">退三期款</c:if>
				<c:if test="${productOrder.payStatus==11 }">退四期款</c:if>
				<c:if test="${productOrder.payStatus==12 }">退五期款</c:if>
				<c:if test="${productOrder.payStatus==13 }">订单完成</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单金额：</label>
			<div class="controls">
				全款金额：<c:if test="${productOrder.payStatus<=1 }">
				<input type="text" name="totalPrice" value="${productOrder.totalPrice/100 }"/>
				</c:if>
				<c:if test="${productOrder.payStatus>1 }">
				${productOrder.totalPrice/100 }
				</c:if>元 <br>
				
				<input type="hidden" name="divide" id="divide"
					value="${productOrder.divide }" />
				<c:forEach begin="1" step="1" end="${productOrder.divide }" var="j">
				<c:set var="fee" value="fee${j}"></c:set>
				<c:set var="feeStatus" value="feePayStatus${j}"></c:set>
				 第${j}期金额：<c:if test="${productOrder.payStatus<=1 }">
				<input type="text" name="fee${j}" value="${productOrder[fee]/100 }"/>
				</c:if>
				<c:if test="${productOrder.payStatus>1 }">
				${productOrder[fee]/100 }
				</c:if>元
				，第${j}期款状态：
				<c:if test="${productOrder[feeStatus]==0 }">待支付</c:if>
				<c:if test="${productOrder[feeStatus]==1 }">已支付</c:if>
				<c:if test="${productOrder[feeStatus]==2 }">已确认</c:if>
				<c:if test="${productOrder[feeStatus]==3 }">分润完成</c:if>
				<c:if test="${productOrder[feeStatus]==4 }">退款中</c:if>
				<c:if test="${productOrder[feeStatus]==5 }">退款成功</c:if>
				<c:if test="${productOrder[feeStatus]==6 }">退款失败</c:if>
				<br>
				</c:forEach>
			</div>
		</div>
		<c:if test="${productOrder.payStatus>0 && productOrder.payStatus<8 }">
		<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set> 
		<div class="control-group">
			<label class="control-label" for="type">修改订单状态：</label>
			<div class="controls">
				<c:if test="${productOrder.payStatus<=1 }">
				<input  type="radio" name="type" id="type" value="2"  />已支付全款
				</c:if>
				<c:if test="${productOrder.divide>0}">
				<c:if test="${productOrder.payStatus==2 && productOrder.feePayStatus1==1}">
				<input  type="radio" name="type" id="type" value="3"  />确认一期款
				</c:if>
				<c:if test="${productOrder.payStatus==3 && productOrder.feePayStatus1==2 && nowDate-productOrder.feeUpdateTime1.getTime()<1296000000}">
				<input  type="radio" name="type" id="type" value="8"  />退一期款
				</c:if>
				</c:if>
				<c:if test="${productOrder.divide>1}">
				<c:if test="${productOrder.payStatus==3 && productOrder.feePayStatus2==1}">
				<input  type="radio" name="type" id="type" value="4"  />确认二期款
				</c:if>
				<c:if test="${productOrder.payStatus==4 && productOrder.feePayStatus2==2 && nowDate-productOrder.feeUpdateTime2.getTime()<1296000000}">
				<input  type="radio" name="type" id="type" value="9"  />退二期款
				</c:if>
				</c:if>
				<c:if test="${productOrder.divide>2}">
				<c:if test="${productOrder.payStatus==4 && productOrder.feePayStatus3==1}">
				<input  type="radio" name="type" id="type" value="5"  />确认三期款
				</c:if>
				<c:if test="${productOrder.payStatus==5 && productOrder.feePayStatus3==2 && nowDate-productOrder.feeUpdateTime3.getTime()<1296000000}">
				<input  type="radio" name="type" id="type" value="10"  />退三期款
				</c:if>
				</c:if>
				<c:if test="${productOrder.divide>3}">
				<c:if test="${productOrder.payStatus==5 && productOrder.feePayStatus4==1}">
				<input  type="radio" name="type" id="type" value="6"  />确认四期款
				</c:if>
				<c:if test="${productOrder.payStatus==6 && productOrder.feePayStatus4==2 && nowDate-productOrder.feeUpdateTime4.getTime()<1296000000}">
				<input  type="radio" name="type" id="type" value="11"  />退四期款
				</c:if>
				</c:if>
				<c:if test="${productOrder.divide>4}">
				<c:if test="${productOrder.payStatus==6 && productOrder.feePayStatus5==1}">
				<input  type="radio" name="type" id="type" value="7"  />确认五期款
				</c:if>
				<c:if test="${productOrder.payStatus==7 && productOrder.feePayStatus5==2 && nowDate-productOrder.feeUpdateTime5.getTime()<1296000000}">
				<input  type="radio" name="type" id="type" value="12"  />退五期款
				</c:if>
				</c:if>
				<c:if test="${productOrder.payStatus==productOrder.divide+2}">
				<input  type="radio" name="type" id="type" value="13"  />订单完成
				</c:if>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<div class="controls">
				<c:if test="${productOrder.payStatus>0 && productOrder.payStatus<8 }">
				<button class="btn" type="button" onclick="saveProductOrder()"
						id="save">保存</button>
				</c:if>
				<button class="btn" type="button"
					onclick="pageGo('${ctx }/productorder/productorder-list')">返回</button>
			</div>
		</div>
	</form>
</body>
</html>
