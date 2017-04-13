<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">

	var gourl = '';
	var type = $("#type").val();
	if(type == 1){
		
		gourl = '${ctx}/authentication/to-savebrank-doctor.do?code=1';
	}else if(type == 2){
		
		gourl = '${ctx}/authentication/to-savebrank-merchant.do?code=1';
	}else if(type == 3){
		
		gourl = '${ctx}/authentication/to-savebrank-tuijianren.do?code=1';
	}
	
	function bangding(){
		pageGo(gourl);
	}

</script>
<body>

	<form style="padding-left: 20%; padding-top: 50px;">
		<input type="hidden" id="type" value="${type}">
		<table id="shenghe">
			<tr>
				<td>银行卡号：</td>
				<td><input type="text" name="branknum" id="branknum" value="${bankcard }" readonly="readonly"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center" style="margin: 10px  0;"><button class="btn"
						type="button" onclick="bangding()" id="save">修改银行卡</button>
			</tr>
		</table>

	</form>
	<style>
	#shenghe tr td{
		padding: 10px 0;
	}
	
	</style>
</body>
</html>