<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	
	//提交表单
	function submitUserForm(){
		//获取input选择参数
		var roleId = $("input[name='roleId']:checked").val();
		$('#roleId').attr("value", roleId);//赋值到隐藏表单
		//提交表单
    	$('#userform').form("submit", {
	        success:function(data){
	        	 data = $.parseJSON(data);
	        	 $.messager.show({
	                 title:'操作提示',
	                 msg:data.msg,
	                 showType:'show'
	             });
	        	 if(data.code==1){
	           		  $('#userWindow').window('close');
	          		  $('#userTable').datagrid("reload");
	        	 }
	        }
	    });
    }
	
	function clearUserForm(){
		$('#userform').form('reset');
	}

</script>
<div class="easyui-layout">
	<div data-options="split:true" style="width:350px">
		<div style="padding: 10px;">
		    <form id="userform" method="post" action="${ctx}/manage/manage-add">
		        <input type="hidden" name="id" value="${manage.id}" />
		        <fieldset>
		        <legend>
		        	<span class="legend">普 惠 商 城 管 理 员 信 息</span>
		        </legend>
		        <table style="width:445px;height:100px;">
		            <tr>
		                <td width="50%" bgcolor="#ececec" align="right" ><span style="font-size: 13px; color: rgb(2, 48, 97);">管理员名称：</span></td>
		                <td>
		                	<c:if test="${manage.id!=null}">
								${manage.account}
								<input id="account" value="${manage.account}" name="account" type="hidden" />
							</c:if>
		                	<c:if test="${manage.id==null}">
							<input id="account" value="" required="required" validType="length[5,20]" invalidMessage="用户名必须在5到20之间，请重新输入" missingMessage="请输入用户名" placeholder="请输入用户名" name="account" class="easyui-validatebox" type="text" style="width:160px;"/>
							</c:if>
		                </td>
		            </tr>
		            <tr>
		                <td bgcolor="#ececec" align="right" ><span style="font-size: 13px; color: rgb(2, 48, 97);">管理员密码：</span></td>
		                <td>
		                	<input id="password" value="" required="required" validType="length[6,30]" invalidMessage="用户密码必须在6到30之间，请重新输入" missingMessage="请输入用户密码" placeholder="请输入用户密码" name="password" class="easyui-validatebox" type="password" style="width:160px;"/>
		                </td>
		            </tr>
		        </table>
		        </fieldset>
		    </form>
		</div>
	</div>
	<div data-options="border:false" style="text-align:center;padding:5px 0 5px;">
		 <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="submitUserForm()">确定</a>
	     <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="clearUserForm()">重置</a>
	</div>
</div>
