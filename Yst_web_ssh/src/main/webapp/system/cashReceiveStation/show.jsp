<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<ul class="breadcrumb">
		<li><a href="storeInfo!index">主页</a> <span class="divider">/</span></li>
		<li>提现记录列表 <span class="divider">/</span>
		</li>
		<li class="active">查看提现记录信息：</li>
	</ul>
	<div class="form-horizontal">
		<div class="control-group">
				<label class="control-label" for="name">金额：</label>
				<div class="controls" id="money">
					${cash.money }
				</div>
		</div>
		<div class="control-group">
				<label class="control-label" for="login_code">银行卡号：</label>
				<div class="controls">
					 ${cash.cardNo }
				</div>
		</div>
		<div class="control-group">
				<label class="control-label" for="prov">开户人姓名：</label>
				<div class="controls">
					 ${cash.personName }
				</div>
		</div>
		<div class="control-group">
				<label class="control-label" for="prov">开户银行：</label>
				<div class="controls">
					 ${cash.bankName }
				</div>
		</div>
		<div class="control-group">
				<label class="control-label" for="prov">所在省份：</label>
				<div class="controls">
					 ${cash.bankPrv }
				</div>
		</div>
		<div class="control-group">
				<label class="control-label" for="city">所在城市：</label>
				<div class="controls">
				    ${cash.bankCity }
				</div>
		</div>
		<div class="control-group">
				<label class="control-label" for="city">开户支行：</label>
				<div class="controls">
					 ${cash.bankSub }
				</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button class="btn" type="button" onclick="pageGo('ydmvc/cashReceiveStation/cashReceiveStationPage.do')">返回</button>
			</div>
		</div>
	</div>
</body>
</html>
<script>
$(function()
{
var val="${cash.money }";
var str = (val/100).toFixed(2) + '';
var intSum = str.substring(0,str.indexOf(".")).replace( /\B(?=(?:\d{3})+$)/g, ',' );//取到整数部分
var dot = str.substring(str.length,str.indexOf("."))//取到小数部分
var ret = intSum + dot;
$("#money").html(ret);

});

</script>
