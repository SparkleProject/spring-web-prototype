<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>  
<html>  
<head>
</head>
<body>
<div  class="easyui-layout" data-options="fit:true">  
   <div data-options="region:'west',split:true,border:false,title:'Search'" style="width:250px">
		<form id="fm-query" method="post">  
		<table >
			<tbody>
				<tr><td class="ltd">Name:</td><td><input name="name" class="textbox"></td></tr>
				<tr><td class="ltd">user Name:</td><td><input name="loginName" class="textbox"></td></tr>
				<tr class="operate">
				<td>&nbsp;</td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" plain="false" onclick="js.query()"  enabled="function.query">Search</a>
		            <a href="javascript:void(0);" class="easyui-linkbutton" plain="false" onclick="$('#fm-query').form('clear')"  enabled="function.query">Reset</a>
				</td>
				</tr>
			</tbody>
		</table>
	   	</form>  
	</div>  
   <div data-options="region:'center',split:true,border:false,title:'User List'">
    <table id="dg" class="easyui-datagrid" 
            data-options="url:'${ctx}/admin/user/init',singleSelect:true,border:false,collapsible:true,
            fit:true,pagination:true,pageSize:20,fitColumns:true,toolbar:'#toolbar',rownumbers:true">  
			    <thead>  
			        <tr>  
						<th field="id" hidden="true" width="60">ID</th>
						<th field="name" width="20">Full Name</th>
						<th field="loginName" width="20">User Name</th>
						<th field="mobile" width="50">Mobile</th>
						<th field="email" width="50">Email</th>
						<th field="locked" width="50" formatter="lockedFormat">Lock</th>
						<th field="enabled" width="50" formatter="enabledFormat">Enable</th>
			        </tr>  
			    </thead>  
			</table>
			<div id="toolbar">
			    <a href="javascript:void(0);" class="easyui-linkbutton"  plain="true" onclick="js.add()" enabled="function.add">New</a>
			    <a href="javascript:void(0);" class="easyui-linkbutton"  plain="true" onclick="js.edit()"  enabled="function.edit">Edit</a>
			    <a href="javascript:void(0);" class="easyui-linkbutton"  plain="true" onclick="js.remove()"  enabled="function.delete">Delete</a>
			    <a href="javascript:void(0);" class="easyui-linkbutton"  plain="true" onclick="js.edit_password()"  enabled="function.edit.password">Change Password</a>
			    <a href="javascript:void(0);" class="easyui-linkbutton"  plain="true" onclick="js.enabled()"  enabled="function.edit">Enable/Disable</a>
			</div>  
			<div id="dlg" class="easyui-dialog" style="width:500px;height:600px;padding: 10px;" closed="true" buttons="#dlg-buttons">
			<form id="fm" method="post">    
		        <table class="dlg-table" style="width:456px;">
		        	<tbody>
		        		<tr><td class="ltd">User Name:</td><td class="rtd"><input name="loginName" class="easyui-validatebox textbox" required="true" style="width:300px;"><input name="id" type="hidden"></td></tr>
		        		<tr><td class="ltd">Full Name:</td><td class="rtd"><input name="name" class="easyui-validatebox textbox" required="true" style="width:300px;">  </td></tr>
		        		<tr><td class="ltd">Gender:</td><td class="rtd"><select name="sex" class="easyui-combobox"  style="width:300px;"><option value="">- Select -</option><option value="1">Male</option><option value="0">Female</option></select></td></tr>
		        		<tr><td class="ltd">DOB:</td><td class="rtd"><input name="birthday" class="easyui-datebox" style="width:300px;"></td></tr>
		        		<tr><td class="ltd">ID Number:</td><td class="rtd"><input name="idCard"  class="textbox" style="width:300px;"></td></tr>
		        		<tr><td class="ltd">Mobile:</td><td class="rtd"><input name="mobile" class="textbox" style="width:300px;"></td></tr>
		        		<tr><td class="ltd">Email:</td><td class="rtd"> <input name="email"  class="textbox" style="width:300px;"></td></tr>
		        		<tr><td class="ltd">Adress:</td><td class="rtd"><input name="address" style="width: 300px;"  class="textbox"></td></tr>
		        	</tbody>
		        </table>
		    </form>  
			</div>  
			<div id="dlg-buttons">  
			    <a href="javascript:void(0);" class="easyui-linkbutton" onclick="js.save()">Save</a>  
			    <a href="javascript:void(0);" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>  
			</div>  
			<div id="dlg-pwd" class="easyui-dialog" style="width:400px;height:250px;padding:10px"  closed="true" buttons="#dlg-buttons-pwd">  
			    <form id="fm-pwd" method="post">  
				    <table class="dlg-table">
				    	<tbody>
				    		<tr><td class="ltd">ID:</td><td class="rtd"><input name="id"  class="textbox"></td></tr>
				    		<tr><td class="ltd">User Name:</td><td class="rtd"><input name="loginName" class="easyui-validatebox textbox"  readonly="readonly"></td></tr>
				    		<tr><td class="ltd">Full Name:</td><td class="rtd"><input name="name" class="easyui-validatebox textbox" readonly="readonly"></td></tr>
				    		<tr><td class="ltd">New Password:</td><td class="rtd"><input name="password_new"  id="password"  type="password" class="easyui-validatebox textbox" required="true"></td></tr>
				    	</tbody>
				    </table>
				</form>
			</div>
			<div id="dlg-buttons-pwd">  
			    <a href="javascript:void(0);" class="easyui-linkbutton" onclick="js.save_password()">Save</a>  
			    <a href="javascript:void(0);" class="easyui-linkbutton" onclick="javascript:$('#dlg-pwd').dialog('close')">Cancel</a>  
			</div>  
	</div>  
</div>
<script type="text/javascript">
	var url="${ctx}/admin/user";
	
	js.add=function(){  
	    $('#dlg').dialog('open').dialog('setTitle','New');  
	    $('#fm').form('clear');  
	};
	
	js.edit=function(){  
		var row = $('#dg').datagrid('getSelected');  
		$('#fm').form('clear');  
		if (row){  
		    $.post("${ctx}/admin/user/load?id="+row.id,function(staff){
		    	$('#fm').form('load',staff);  	
		    	editor.setData(staff.resume);
			    $('#companyId').combobox("setValue",staff.companyId);
		    });
		    $('#dlg').dialog('open').dialog('setTitle','Edit');  
		}  
	};
	
	js.edit_password=function(){  
		var row = $('#dg').datagrid('getSelected');  
		$('#fm-pwd').form('clear');  
		if (row){  
		    $('#dlg-pwd').dialog('open').dialog('setTitle','Change Password');  
		    $('#fm-pwd').form('load',row);  
		}  
	};  
	js.enabled=function(){  
		var row = $('#dg').datagrid('getSelected');  
		var msg="User is enable";
		var enabled=1;
		if(row.loginName=='admin'){
			 $.messager.show({  
                 title: 'Message',  
                 msg: 'Administrator cannot be disabled'
             });
			 return;
		}
		if(row.enabled==1){
			enabled=0;
			msg="This user is diabled";
		}
		$.post("${ctx}/admin/user/enabled/"+row.id+"/"+enabled,function(result){
			result=js.json(result);
            if (result.success){  
                $.messager.show({  
                    title: 'Message',  
                    msg:msg
                });
                $('#dg').datagrid('load');
            }else{
            	$.messager.show({  
                    title: 'Message',  
                    msg: "System error" 
                });
            }
		});
	};  
	
	js.save_password=function(){  
	    $('#fm-pwd').form('submit',{  
	        url: "${ctx}/admin/user/password",  
	        onSubmit: function(){  
	            return $(this).form('validate');
	        },  
	        success: function(result){  
	        	result=js.json(result);
	            if (result.success){  
	                $('#dlg-pwd').dialog('close');  
	                $.messager.show({  
	                    title: 'Message',  
	                    msg: "Success！" 
	                });
	            } else {  
	                $.messager.show({  
	                    title: 'Message',  
	                    msg: result.msg  
	                });  
	            }  
	        }  
	    });  
	};
	
	js.save=function(){  
	    $('#fm').form('submit',{  
	        url: url+"/save",  
	        onSubmit: function(){  
	            return $(this).form('validate');  
	        },  
	        success: function(result){  
	        	result=js.json(result);
	            if (result.success){  
	                $('#dlg').dialog('close');  
	                $('#dg').datagrid('load');  
	            } else {  
	                $.messager.show({  
	                    title: 'Message',  
	                    msg: result.msg  
	                });  
	            }  
	        }  
	    });  
	};
	
	js.remove=function(){ 
	    var row = $('#dg').datagrid('getSelected');  
	    if (row){  
	        $.messager.confirm('Message','Confirm Delete?',function(r){  
	            if (r){  
	                $.post(url+'/delete',{id:row.id},function(result){ 
	                	result=js.json(result);
	                    if (result.success){  
	                        $('#dg').datagrid('load');
	                    } else {  
	                        $.messager.show({  
	                            title: 'Message',  
	                            msg: result.msg  
	                        });  
	                    }
	                });  
	            }  
	        });  
	    }  
	};
	
	js.query=function(){ 
	   var params=$('#fm-query').serializeObject();
	   $('#dg').datagrid({url:url+"/init",queryParams:params,pageNumber:1});
	};
	</script>
</body>
</html>
