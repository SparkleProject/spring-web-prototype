<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>  
<html>  
<head>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">  
   <div data-options="region:'west',split:true,border:false,title:'Search'" style="width:225px">
	<form id="fm-query" method="post">  
	<table class="query-table">
	<tbody>
		<tr><td class="ltd">Staff</td><td><input name="staffId" class="easyui-combobox"  url="${ctx}/admin/staff/load"  textField='name' valueField='id'></td></tr>
		<tr><td class="ltd">From</td><td><input name="loginDateFrom" class="easyui-datebox"  editable="false"></td></tr>
		<tr><td class="ltd">To</td><td><input name="loginDateTo"   class="easyui-datebox"  editable="false"></td></tr>
		<tr class="operate">
			<td>&nbsp;</td>
			<td>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="query()">Search</a>  
            <a href="javascript:void(0);" class="easyui-linkbutton" onclick="$('#fm-query').form('clear')">Reset</a>
            </td>
        </tr>
		</tbody>
	</table>
   	</form>  
   </div>  
   <div data-options="region:'center',split:true,border:false,title:'Reatult'">
	 <table id="dg" class="easyui-datagrid" 
            data-options="url:'${ctx }/admin/staff-login/init',singleSelect:true,border:false,collapsible:true,
            fit:true,pagination:true,fitColumns:true,toolbar:'#toolbar',rownumbers:true">  
        <thead>  
	        <tr>  
				<th field="id" hidden="true">ID</th>
				<th field="staffName" width="50">Staff</th>
				<th field="staffId" width="50" hidden="true">StaffID</th>
				<th field="loginIp" width="50">Login IP</th>
				<th field="loginDate" width="50">Login Date</th>
	        </tr>  
	    </thead>  
    </table>  
	</div>  
</div>
<script type="text/javascript">
	var url="${ctx}/admin/staff-login";
	function query(){ 
		   var params=$('#fm-query').serializeObject();
		   $('#dg').datagrid({url:url,queryParams:params,pageNumber:1});
	}
	</script>
</body>
</html>
