<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 患者详情 -->
<html>
<script type="text/javascript">
	//alert("${ patient.patientName}");
</script>
<body>
	<div class="form-horizontal">
		<div class="control-group">
			<label class="control-label" for="patient.patientName"
				style="line-height: 20px;">填写人：</label>
			<div class="controls">
				<c:if test="${!empty patient.patientName}">
					${patient.patientName }
				</c:if>
				<c:if test="${empty patient.patientName}">
					<c:out value="暂无"></c:out>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient.patientName"
				style="line-height: 20px;">填写人手机号：</label>
			<div class="controls">
				<c:if test="${patient.patientName !=null }">
					${patient.patientName }
				</c:if>
				<c:if test="${patient.patientName eq null }">
					<c:out value="暂无"></c:out>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient.patientName"
				style="line-height: 20px;">患者姓名：</label>
			<div class="controls">
				<c:if test="${patient.patientName !=null }">
					${patient.patientName }
				</c:if>
				<c:if test="${patient.patientName eq null }">
					<c:out value="暂无"></c:out>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient.patientPhone"
				style="line-height: 20px;">患者手机号：</label>
			<div class="controls">
				<c:if test="${patient.patientPhone !=null }">
					${patient.patientPhone }
				</c:if>
				<c:if test="${patient.patientPhone eq null }">
					<c:out value="暂无"></c:out>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient.address"
				style="line-height: 20px;">家庭住址：</label>
			<div class="controls">
				<c:if test="${patient.address !=null }">
					${patient.address }
				</c:if>
				<c:if test="${empty patient.address }">
					<c:out value="暂无"></c:out>
				</c:if>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient.relationId"
				style="line-height: 20px;">病患关系：</label>
			<div class="controls">
				<c:if test="${patient.relationId == 1}">
					<c:out value="家庭成员"></c:out>
				</c:if>
				<c:if test="${patient.relationId == 2}">
					<c:out value="亲戚"></c:out>
				</c:if>
				<c:if test="${patient.relationId == 3}">
					<c:out value="亲戚"></c:out>
				</c:if>
				<c:if test="${patient.relationId == 4}">
					<c:out value="其他"></c:out>
				</c:if>
				<c:if test="${patient.relationId == 5}">
					<c:out value="本人"></c:out>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient.sex"
				style="line-height: 20px;">患者性别：</label>
			<div class="controls">
				<c:if test="${!empty patient.sex}">
					<c:out value="${patient.sex }"></c:out>
				</c:if>
				<c:if test="${empty patient.sex}">
					<c:out value="暂无"></c:out>
				</c:if>
			</div>
		</div>
		<!-- -----1111 -->
		<%-- <div class="control-group">
			<label class="control-label" for="patient.item_status"
				style="line-height: 20px;">主要症状：</label>
			<div class="controls">
				<c:if test="${patient.item_status eq 1 }">
					<c:out value="未支付"></c:out>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient.pay_time"
				style="line-height: 20px;">病症详情：</label>
			<div class="controls">
				<c:if
					test="${(patient.pay_time != null) or (!empty patient.pay_time) }">
					<s:date name="patient.pay_time" format="yyyy-MM-dd HH:mm:ss" />
				</c:if>
				<c:if
					test="${(patient.pay_time == null) or (empty patient.pay_time) }">
					<c:out value="暂无"></c:out>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient.item_delivery_time"
				style="line-height: 20px;">发病过程：</label>
			<div class="controls">
				<c:if test="${patient.item_delivery_time !=null }">
					<s:date name="patient.item_delivery_time"
						format="yyyy-MM-dd HH:mm:ss" />
				</c:if>
				<c:if test="${patient.item_delivery_time eq null}">
					<c:out value="暂无"></c:out>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient.take_time_item"
				style="line-height: 20px;">用药记录：</label>
			<div class="controls">
				<c:if test="${patient.take_time_item !=null }">
					<s:date name="patient.take_time_item" format="yyyy-MM-dd HH:mm:ss" />
				</c:if>
				<c:if test="${patient.take_time_item eq null}">
					<c:out value="暂无"></c:out>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient.comm_content"
				style="line-height: 20px;">医生姓名：</label>
			<div class="controls">
				<c:if test="${patient.comm_content !=null }">
					<c:out value="${patient.comm_content }"></c:out>
				</c:if>
				<c:if test="${patient.comm_content eq null}">
					<c:out value="暂无"></c:out>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient.cancelTimeItem"
				style="line-height: 20px;">医院科室：</label>
			<div class="controls">
				<c:if test="${patient.cancelTimeItem != null}">
					<s:date name="patient.cancelTimeItem" format="yyyy-MM-dd HH:mm:ss" />
				</c:if>
				<c:if test="${patient.cancelTimeItem eq null}">
					<c:out value="暂无"></c:out>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient.order_from"
				style="line-height: 20px;">药盒照片：</label>
			<div class="controls">
				<c:if test="${patient.order_from eq 1}">
					<c:out value="手机端"></c:out>
				</c:if>
				<c:if test="${patient.order_from eq 2}">
					<c:out value="PC端"></c:out>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient.express_name" style="line-height: 20px;">处方照片：</label>
			<div class="controls">
				<c:if test="${patient.express_name != null && patient.express_name!='undefined'}">
					<s:property value="patient.express_name" />
				</c:if>
				<c:if test="${patient.express_num != null && patient.express_num!='undefined'}">
					<s:property value="patient.express_num" />
				</c:if>
				<c:if test="${patient.express_name eq null && patient.express_num eq null}">
					<c:out value="暂无"></c:out>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="patient.express_name" style="line-height: 20px;">患者信息建立时间：</label>
			<div class="controls">
				<c:if test="${patient.express_name != null && patient.express_name!='undefined'}">
					<s:property value="patient.express_name" />
				</c:if>
				<c:if test="${patient.express_num != null && patient.express_num!='undefined'}">
					<s:property value="patient.express_num" />
				</c:if>
				<c:if test="${patient.express_name eq null && patient.express_num eq null}">
					<c:out value="暂无"></c:out>
				</c:if>
			</div>
		</div> --%>
		<div class="control-group">
			<div class="controls">
				<button class="btn" type="button" onclick="pageGo('${baseurl}/ydmvc/main/after/toPatientManagerPage.do');">返回</button>
			</div>
		</div>
		</div>
</body>
</html>
