<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var type = $("input[name='type']:checked");
		if (type.length ==0) {
			$("#save").tips({
				side : 2,
				msg : '请选择要修改的订单状态',
				bg : '#AE81FF',
				time : 2
			});
			return false;
		}
		if(type.val()==1){
			if($("#uploadFile").val()==""){
				$("#uploadFile").tips({
					side : 2,
					msg : '签约请选择要上传的合同',
					bg : '#AE81FF',
					time : 2
				});
				return false;
			}
		}else if(type.val()==2){
			if($("#amountPaid").val()==""){
				$("#amountPaid").tips({
					side : 2,
					msg : '部分支付请输入支付金额',
					bg : '#AE81FF',
					time : 2
				});
				return false;
			}
		}
		return true;
	}
	function savePatientOrder() {
		if (check()) {
			var form = $("#userInfoForm");
			var options = {
				url : '${baseurl}/ydmvc/main/after/ajaxUpdatePatientOrder.do', //提交给哪个执行
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
						pageGo('${baseurl}/ydmvc/main/after/patientOrderPage.do?patientId=${result.patient.id }');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
	function pageGo(href) {
		$("#index_div").load(href);
	}
	$(function(){
		$("input[name='type']").on('change',function(){
			if(this.value==1){
				$("#amountPaidSpan").hide();
				$("#fileSpan").show();
			}else if(this.value==2){
				$("#amountPaidSpan").show();
				$("#fileSpan").hide();
			}else{
				$("#amountPaidSpan").hide();
				$("#fileSpan").hide();
			}
		});
	});
	
</script>
<body>
	<ul class="breadcrumb">
		<li class="active">查看订单详情：</li>
	</ul>
	<br>
	<form class="form-horizontal" id="userInfoForm" method="post" enctype="multipart/form-data">
		
		<div class="control-group">
			<label class="control-label" for="type">订单记录：</label>
			<div class="controls">
				<table border="1">
					<tr>
						<td>时间</td>
						<td>操作员</td>
						<td>修改状态</td>
						<td>备注</td>
					</tr>
					<c:forEach var="revar" items="${result.orderRecord }">
					<tr>
						<td><fmt:formatDate value="${revar['createdTime'] }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${revar['name'] }</td>
						<td>
						<c:if test="${revar['type']==0 }">未支付</c:if>
						<c:if test="${revar['type']==1 }">已签约</c:if>
						<c:if test="${revar['type']==2 }">部分支付</c:if>
						<c:if test="${revar['type']==3 }">全额支付</c:if>
						<c:if test="${revar['type']==4 }">阶段治疗中</c:if>
						<c:if test="${revar['type']==5 }">最终治疗中</c:if>
						<c:if test="${revar['type']==6 }">订单完成</c:if>
						</td>
						<td>${revar['describer'] }</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patientName">患者姓名：</label>
			<div class="controls">
				<input type="hidden" name="id" id="id" value="${result.patientOrder.id }" />
				${result.patientOrder.patientName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="phone">电话：</label>
			<div class="controls">
				${result.patientOrder.phone }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="address">地址：</label>
			<div class="controls">
				${result.patientOrder.address }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="customer.name">介绍人姓名：</label>
			<div class="controls">
				${result.patient.customer.name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="customer.phone">介绍人手机：</label>
			<div class="controls">
				${result.patient.customer.phone }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="productId">项目编号：</label>
			<div class="controls">
					${result.patientOrder.productId }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="productName">项目名称：</label>
			<div class="controls">
				<a href="${baseurl}/ydmvc/main/after/model.do?tourl=product!productPage?product_id=${result.patientOrder.productId }" target="_blank">${result.patientOrder.productName }</a>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="money">价格：</label>
			<div class="controls">
				${result.patientOrder.money/100 }元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="divide">项目可分期数量：</label>
			<div class="controls">
				${result.product.divide }期
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="amountPaid">已付期数：</label>
			<div class="controls">
				${result.patientOrder.dividePaid }期
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="amountPaid">已付金额：</label>
			<div class="controls">
				${result.patientOrder.amountPaid/100 }元
			</div>
		</div>
		<c:if test="${result.patientOrder.type>0 }">
		<div class="control-group">
			<label class="control-label" for="contractAddress">合同附件：</label>
			<div class="controls">
				<a href="${baseurl}/${result.patientOrder.contractAddress }" target="_blank"><img alt="合同附件" src="${result.patientOrder.contractAddress }" height="300px;" width="400px;"></a>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label" for="type">当前订单状态：</label>
			<div class="controls"><!--  0未支付 1已签约 2部分支付 3全额支付 4阶段治疗中5最终治疗中 6完成； -->
				<c:if test="${result.patientOrder.type==0 }">未支付</c:if>
				<c:if test="${result.patientOrder.type==1 }">已签约</c:if>
				<c:if test="${result.patientOrder.type==2 }">部分支付</c:if>
				<c:if test="${result.patientOrder.type==3 }">全额支付 </c:if>
				<c:if test="${result.patientOrder.type==4 }">阶段治疗中</c:if>
				<c:if test="${result.patientOrder.type==5 }">最终治疗中</c:if>
				<c:if test="${result.patientOrder.type==6 }">完成</c:if>
			</div>
		</div>
		<%-- <c:if test="${result.patientOrder.type!=5 }">
		<div class="control-group">
			<label class="control-label" for="type">订单状态：</label>
			<div class="controls">
				<c:if test="${result.patientOrder.type==0 }">
				<input  type="radio" name="type" id="type" value="1"  />已签约
				<span id="fileSpan" style="margin-left:73px;display: none;">请上传合同：<input  type="file" name="uploadFile" id="uploadFile" value=""  /></span>
				<br/><br/>
				</c:if>
				<c:if test="${result.patientOrder.type!=0 }">
				<c:if test="${result.product.divide>1 }">
						<input  type="radio" name="type" id="type" value="2"  />部分支付
						<span id="amountPaidSpan" style="margin-left:60px;display: none;">请输入金额：<input type="text" name="amountPaid" id="amountPaid" value=""  />元</span>
						<br/><br/>
				</c:if>
						<input type="radio" name="type" id="type" value="3"  />全额支付
						<br/><br/>
				<input type="radio" name="type" id="type" value="4"  />治疗中
				<br/><br/>
				<input type="radio" name="type" id="type" value="5"  />完成
				<br/><br/>
				</c:if>
			</div>
		</div>
		</c:if> --%>
		 <div class="control-group">
			<div class="controls">
				<!-- <button class="btn"
						type="button" onclick="savePatientOrder()" id="save">保存</button> -->
					 <%-- <button class="btn" type="button" onclick="pageGo('${baseurl}/ydmvc/shareMoney/shareMoneyPage.do">返回</button>  --%>
					 <a class="btn" id="back"href="#" onclick="back();">返回</a>
			</div>
		</div> 
	</form>
</body>
</html>
<script>
function back()
{
   var type="${type}";
   if(type==1)//管理员
   {
     pageGo("${baseurl}/ydmvc/shareMoney/shareMoneyPage.do");
   }else if(type==2)//商户
   {
   pageGo("${baseurl}/ydmvc/shareMoney/MerchantsShareMoneyPage.do");
   }
}


</script>