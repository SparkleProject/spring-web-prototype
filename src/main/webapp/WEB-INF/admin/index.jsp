<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>  
<html>  
<head>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
<div data-options="region:'north'" style="height:80px;border: 0;">
			<table width="100%">
				<tbody>
					<tr height="40">
						<td class="logo">Management</td>
						<td align="right">
						       <a href="javascript:void(0);" class="easyui-menubutton" menu="#user-center-menu" iconCls="icon-user">${staff.name}</a>
						       <!-- <a href="javascript:void(0);" class="easyui-menubutton" menu="#theme-menu" iconCls="icon-theme">风格</a> -->
						       <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-logout'" onclick="js.logout('${ctx}');">Logout</a>  
						</td>
					</tr>
					<tr>
						<td class="panel-header"  colspan="2" style="padding: 1px;">
						    ${menu}
						</td>
					</tr>
				</tbody>
			</table>
		</div>
        <div data-options="region:'center'" style="padding: 0 2px 0px 2px;border: 0;">
	 	   <div id="tt" class="easyui-tabs main-tabs" data-options="fit:true,border:false" > 
	 	   		 <div title="Home" data-options="href:'${ctx}/admin/dashboard'"></div>
		   </div>  
        </div>
        <div id="user-center-menu" class="easyui-menu" style="width: 100px;">
<!-- 			<div onclick="js.profile();">profile</div> -->
		    <div onclick="js.change_password();">Change Password</div>
<%-- 		    <div  onclick="js.logout('${ctx}');">logout</div> --%>
		</div>
        <div id="theme-menu" class="easyui-menu" style="width: 100px;">
		    <div onclick="js.change_theme('${ctx}','default');">default</div>
			<div onclick="js.change_theme('${ctx}','black');">black</div>
			<div onclick="js.change_theme('${ctx}','bootstrap');">bootstrap</div>
			<div onclick="js.change_theme('${ctx}','gray');">gray</div>
			<div onclick="js.change_theme('${ctx}','metro');">metro</div>
		</div>
        <div id="mm" class="easyui-menu" style="width:150px;">
		    <div id="mm-tabclose">Close This Tab</div>
		    <div id="mm-tabcloseother">Close Other Tab</div>
		    <div class="menu-sep"></div>
		    <div id="mm-tabcloseright">Close Right Tab</div>
		    <div id="mm-tabcloseleft">Close Left Tab</div>
		</div>
		<div id="dlg" class="easyui-dialog" style="width:400px;height:200px;padding:10px 20px"  closed="true" buttons="#dlg-buttons">  
		    <form id="fm" method="post">  
			    <table class="dlg-table">
			    	<tbody>
			    		<tr><td class="ltd">current password</td><td class="rtd"><input name="password_old"  id="password_old"  type="password" class="easyui-validatebox textbox" required="true"></td></tr>
			    		<tr><td class="ltd">new password</td><td class="rtd"><input name="password_new"  id="password_new"  type="password" class="easyui-validatebox textbox" required="true"></td></tr>
			    		<tr><td class="ltd">confirm new password</td><td class="rtd"><input name="password_confirm" id="password_confirm"   type="password" class="easyui-validatebox textbox" required="true"></td></tr>
			    	</tbody>
			    </table>
			</form>
		</div>
		<div id="dlg-buttons">  
		    <a href="javascript:void(0);" class="easyui-linkbutton" onclick="js.save()">Save</a>  
		    <a href="javascript:void(0);" class="easyui-linkbutton" onclick="$('#dlg').dialog('close')">Cancel</a>  
		</div>
</div>
		<script type="text/javascript">
		var url="${ctx}/admin/profile/password";
		js.change_password=function(){  
			$('#dlg').dialog('open').dialog('setTitle','Change password'); 
			$('#fm').form('clear');
		};
		js.save=function(){
			var p_o=$('#password_old').val();
			var p=$('#password_new').val();
			var p_c=$('#password_confirm').val();
			if(p!=p_c){
				 $.messager.show({  
	                 title: 'Message',  
	                 msg:"Please check your New Password" 
	             }); 
				 return false;
			}
		    $('#fm').form('submit',{  
		        url: url,  
		        onSubmit: function(){  
		            return $(this).form('validate');
		        },  
		        success: function(result){  
		            var result = eval('('+result+')');  
		            if (result.success){  
		                $('#dlg').dialog('close');  
		                $.messager.show({  
		                    title: 'Message',  
		                    msg: "Success! Your password has been changed." 
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
		</script>
</body>  
</html>  