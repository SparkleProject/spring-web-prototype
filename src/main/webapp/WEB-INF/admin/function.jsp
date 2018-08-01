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
					<tr><td>Feature Name:</td><td><input name="name" class="textbox"></td></tr>
					<tr class="operate">
					<td>&nbsp;</td>
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="js.query()">Search</a>
			            <a href="javascript:void(0);" class="easyui-linkbutton" onclick="$('#fm-query').form('clear')">Reset</a>
					</td></tr>
				</tbody>
			</table>
	 </form>  
   </div>  
   <div data-options="region:'center',split:true,border:false,title:'User List'">
	 <table id="dg" class="easyui-datagrid" 
             data-options="
             url:'${ctx }/admin/function/init',
             toolbar:'#toolbar',
             singleSelect:true,border:false,collapsible:true,
            fit:true,pagination:true,fitColumns:true,rownumbers:true">
	    <thead>  
	        <tr>  
				<th field="id" width="30" hidden="true">ID</th>
				<th field="code" width="100">Code</th>
				<th field="name" width="100">Name</th>
				<th field="description" width="100">Description</th>
	        </tr>  
	    </thead>  
	</table>  
	<div id="toolbar">  
	    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="js.add()">New</a>  
	   <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="js.edit()">Edit</a>  
	   <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="js.remove()">Delete</a>  
	</div>  
	</div>  
</div>
<div id="dlg" class="easyui-dialog" style="width:400px;height:200px;padding:10px 20px"  
	        closed="true" buttons="#dlg-buttons">  
	  <form id="fm" method="post">  
	      <table class="dlg-table">
        	<tbody>
        		<tr><td class="ltd">Code:</td><td class="rtd"><input name="code" class="easyui-validatebox textbox" required="true"> </td></tr>
        		<tr><td class="ltd">Name:</td><td class="rtd"><input name="name" class="easyui-validatebox textbox" required="true"></td></tr>
        		<tr><td class="ltd">Description:</td><td class="rtd"><input name="description" class="easyui-validatebox textbox"> </td></tr> 
        	</tbody>
        </table>
        <input name="id" type="hidden">  
       </form>
	</div>  
	<div id="dlg-buttons">  
	    <a href="javascript:void(0);" class="easyui-linkbutton"  onclick="js.save()">Save</a>  
	    <a href="javascript:void(0);" class="easyui-linkbutton"  onclick="javascript:$('#dlg').dialog('close')">Cancel</a>  
	</div>  
	<script type="text/javascript">
	var url="${ctx}/admin/function";
	js.add=function (){  
	    $('#dlg').dialog('open').dialog('setTitle','New');  
	    $('#fm').form('clear');  
	};
	
	js.edit=function(){  
		var row = $('#dg').datagrid('getSelected');  
		if (row){  
		    $('#dlg').dialog('open').dialog('setTitle','Edit');  
		    $('#fm').form('load',row);  
		}  
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
	        $.messager.confirm('Message','Do you still want to Delete it?',function(r){  
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
	                },'json');  
	            }  
	        });  
	    }  
	};
	
	js.query=function(){
	   var params=$('#fm-query').serializeObject();
	   $('#dg').datagrid({url:url,queryParams:params,pageNumber:1});
	};
	</script>
</body>
</html>
