<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>  
<html>  
<head>
<script type="text/javascript" src="${ctx}/admin/assets/javascript/ckeditor/4.9/ckeditor.js"></script>
</head>
<body>
<div  class="easyui-layout" data-options="fit:true">  
   <div data-options="region:'west',split:true,border:false,title:'Search'" style="width:225px">
		<form id="fm-query" method="post">  
		<table class="query-table">
			<tbody>
				<tr><td class="ltd">Name:</td><td><input name="name" class="textbox"></td></tr>
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
   <div data-options="region:'center',split:true,border:false,title:'List'">
    <table id="dg" class="easyui-datagrid" 
            data-options="url:'${ctx}/admin/staff/init',singleSelect:true,border:false,collapsible:true,
            fit:true,pagination:true,fitColumns:true,toolbar:'#toolbar',rownumbers:true">  
			    <thead>  
			        <tr>  
						<th field="id" hidden="true" width="60">ID</th>
						<th field="name" width="50">Full Name</th>
						<th field="loginName" width="50">User Name</th>
						<th field="yearEntry" width="50">Employ Year</th>
						<th field="deptid" hidden="true">Department</th>
						<th field="positionId" width="50">Role</th>
						<th field="sex" width="50" formatter="sexFormat">Gender</th>
						<th field="birthday" width="50">DOB</th>
						<th field="mobile" width="50">Mobile</th>
						<th field="education" width="50">Education</th>
						<th field="nation" hidden="true" width="50">Nation</th>
						<th field="marital" hidden="true" width="50">Marital Status</th>
						<th field="household"  hidden="true" width="50"> City</th>
						<th field="profession" hidden="true" width="50">Major</th>
						<th field="address" hidden="true" width="50">Address</th>
						<th field="email" hidden="true" width="50">Email</th>
						<th field="idCard" hidden="true" width="50">ID Number</th>
						<th field="status" width="50"  formatter="jobStatusFormat">Employ State</th>
						<th field="enabled" width="50" formatter="enabledFormat">Enable</th>
						<th field="resume" hidden="true" width="50">Resume</th>
						<th field="evaluation" hidden="true" width="50">Evaluation</th>
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
			<div id="dlg" class="easyui-dialog" style="width:800px;height:500px;padding: 10px;" closed="true" buttons="#dlg-buttons">
			<form id="fm" method="post">    
		        <table class="dlg-table" style="width:746px;">
		        	<tbody>
		        		<tr>
		        			<td>
		        				<table>
					        		<tbody>
					        		<tr><td class="ltd">User name:</td><td class="rtd"><input name="loginName" class="easyui-validatebox textbox" required="true"><input name="id" type="hidden"></td></tr>
					        		<tr><td class="ltd">Full Name:</td><td class="rtd"><input name="name" class="easyui-validatebox textbox" required="true">  </td></tr>
					        		<tr><td class="ltd">Gender:</td><td class="rtd"><select name="sex" class="easyui-combobox"  style="width:202px;"><option value="">- Select -</option><option value="1">Male</option><option value="0">Female</option></select></td></tr>
					        		<tr><td class="ltd">DOB</td><td class="rtd"><input name="birthday" class="easyui-datebox" style="width:202px;"></td></tr>
					        		<tr><td class="ltd">ID Number:</td><td class="rtd"><input name="idCard"  class="textbox"></td></tr>
					        		<tr><td class="ltd">City:</td><td  class="rtd"><input name="household"  class="textbox"></td></tr>
					        		<tr><td class="ltd">Nation:</td>
					        			<td class="rtd">
					        			<select id="nation" name="nation"  class="easyui-combobox"  style="width:202px;">
											<option value="">- Select -</option>
											<option value="New Zealand">New Zealand</option>
											<option value="Australia">Australia</option>
											<option value="China">China</option>
											<option value="Other">Other</option>
										</select>
					        			</td>
					        		</tr>
					        		<tr><td class="ltd">Merital State:</td>
					        			<td class="rtd">
						        			<select name="marital" class="easyui-combobox"  style="width:202px;">
									        	<option value="">- Select -</option>
												<option value="0">Single</option>
												<option value="1">Merried</option>
											</select>
					        			</td>
					        		</tr>
						        	</tbody>
						        </table>
		        			</td>
		        			<td>
		        				<table>
					        		<tbody>
						        		<tr><td class="ltd">Mobile:</td><td class="rtd"><input name="mobile" class="textbox"></td></tr>
						        		<tr><td class="ltd">Email:</td><td class="rtd"> <input name="email"  class="textbox"></td></tr>
						        		<tr><td class="ltd">Employ Year:</td><td class="rtd"><input name="yearEntry"maxlength="4"  class="textbox"></td></tr>
						        		<tr><td class="ltd">Company:</td>
						        		<td class="rtd">
						        		<select name="companyId" id="companyId" class="easyui-combobox" style="width:202px;">
										<option value="">- Select -</option>
										</select>
						        		</td></tr>
						        		<tr><td class="ltd">Department:</td><td class="rtd"><input id="deptId" name="deptId" class="easyui-combotree" url="${ctx }/admin/department/init" value=""  style="width:202px;"></td></tr>
						        		<!-- <tr><td class="ltd">岗位:</td><td class="rtd"><input name="positionId"  class="textbox"></td></tr> -->
						        		<tr><td class="ltd">Education:</td>
						        			<td class="rtd"><select name="education" class="easyui-combobox"  style="width:202px;">
										        	<option value="">- Select -</option>
													<option value="Level4">Level 4</option>
													<option value="evel5">level 5</option>
													<option value="evel6">level 6</option>
													<option value="evel7">level 7</option>
													<option value="evel8">level 8</option>
												</select>
											</td>
										</tr>
						        		<tr><td class="ltd">Major:</td>
						        			<td class="rtd">
						        				<select name="profession" id="profession" class="easyui-combobox"  style="width:202px;">
													
													<option value="Other">Other</option>	
												</select>
						        			</td>
						        		</tr>
						        		<tr><td class="ltd">Employ State:</td>
						        			<td class="rtd">
							        			 <select name="status" class="easyui-combobox"  style="width:202px;"  >  
										        	<option value="">- Select -</option>
													<option value="1">Employed</option>
													<option value="0">Dismiss</option>
												</select>
											</td>
										</tr>
						        	</tbody>
						        </table>
						       </td>
						      </tr>
		        	</tbody>
		        </table>
		        <table class="dlg-table" style="width:746px;">
		        	<tbody>
		        		<tr><td class="ltd">Adress:</td><td class="rtd"><input name="address" style="width: 575px;"  class="textbox"></td></tr>
		        		<tr><td class="ltd">Evaluation:</td><td class="rtd"><textarea id="evaluation" name="evaluation"   class="textbox" style="width:575px;height: 80px;"></textarea></td></tr>
		        		<tr><td class="ltd">Resume:</td><td class="rtd"></td></tr> 
		        		<tr>
		        			<td>&nbsp;</td>
		        			<td colspan="1">
		        				<textarea id="resume" name="resume" style="width:300px;height: 100px;"></textarea>
		        			</td>
					   	</tr>
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
	var editor = CKEDITOR.replace("resume");
	var url="${ctx}/admin/staff";
	
	js.add=function(){  
	    $('#dlg').dialog('open').dialog('setTitle','New');  
	    $('#fm').form('clear');  
	    $('#resume').ckeditor("clear");
	};
	
	js.edit=function(){  
		var row = $('#dg').datagrid('getSelected');  
		$('#fm').form('clear');  
		if (row){  
		    $.post("${ctx}/admin/staff/load?id="+row.id,function(staff){
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
		var msg="user is ";
		var enabled=1;
		if(row.loginName=='admin'){
			 $.messager.show({  
                 title: 'Message',  
                 msg: 'Addministrator  '
             });
			 return;
		}
		if(row.enabled==1){
			enabled=0;
			msg="Users are disable";
		}
		$.post("${ctx}/admin/staff/enabled/"+row.id+"/"+enabled,function(result){
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
                    msg: "Error code" 
                });
            }
		});
	};  
	
	js.save_password=function(){  
	    $('#fm-pwd').form('submit',{  
	        url: "${ctx}/admin/staff/password",  
	        onSubmit: function(){  
	            return $(this).form('validate');
	        },  
	        success: function(result){  
	        	result=js.json(result);
	            if (result.success){  
	                $('#dlg-pwd').dialog('close');  
	                $.messager.show({  
	                    title: 'Message',  
	                    msg: "Change Password success！" 
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
		$('#resume').ckeditor("sync");
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
	
	$(function(){
		$('#companyId').combobox({
			url:'${ctx}/admin/company/combobox',
		    valueField:'id',
		    textField:'name'
		});
		
	});
	</script>
</body>
</html>
