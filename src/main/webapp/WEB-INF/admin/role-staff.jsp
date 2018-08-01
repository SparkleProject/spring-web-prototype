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
					<tr><td class="ltd">Name</td><td><input name="name" class="textbox"></td></tr>
					<tr class="operate">
					<td>&nbsp;</td>
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="query()"  enabled="function.query">Search</a>  
		            	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="$('#fm-query').form('clear')"  enabled="function.query">Reset</a>
		            </td>
		        </tr>
				</tbody>
			</table>
			</form>
   </div>  
   <div data-options="region:'center',split:true,border:false,title:'List'">
  		 <table id="dg" title="" class="easyui-datagrid frame-datagrid"
			        url="${ctx}/admin/staff/init"  
			        toolbar="#toolbar"  
			        rownumbers="true" fitColumns="true" singleSelect="true"  pagination="true" data-options="border:false,fit:true" pageSize="20">
			    <thead>  
			        <tr>  
						<th field="name" width="50">Name</th>
						<th field="id" width="50" formatter="grantFormat">Authority</th>
			        </tr>  
			    </thead>  
			</table>
			<div id="toolbar">
			    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()"  enabled="function.edit">Authorization</a>
			</div>  
   </div>
</div>

	<div id="dlg" class="easyui-dialog" style="width:350px;height:410px;padding:5px 5px"
	        closed="true" buttons="#dlg-buttons">  
	    <form id="fm" method="post">  
	        <div class="fitem" style="display: none;">  
	            <label>ID:</label>  
	            <input name="id"  style="width:200px;">  
	        </div>  
	        <div style="width: 250px; height: 300px;">
				<ul id="tt" class="easyui-tree"
						url=""
						animate="true"
						checkbox="true">
				</ul>
			</div>
	    </form>  
	</div>  
	<div id="dlg-buttons">  
	    <a href="javascript:void(0);" class="easyui-linkbutton" onclick="save()">Save</a>  
	    <a href="javascript:void(0);" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>  
	</div>  
	<input type="button" id="test" value="test-tab">
	<script type="text/javascript">
	var url="${ctx}/admin/role-staff";
	function edit(){  
		var row = $('#dg').datagrid('getSelected');  
		if (row){  
		    $('#tt').tree({  
			    url:url+"/load-role?id="+row.id, 
			});
		    $('#dlg').dialog('open').dialog('setTitle','Select Role');  
		    $('#fm').form('load',row);  
		}
	}
	function save(){  
    	var role = $('#tt').tree('getSelected');
	    $('#fm').form('submit',{  
	        url: url+"/save",  
	        onSubmit: function(){  
	    		var nodes = $('#tt').tree('getChecked');
	    		for(var i=0;i<nodes.length;i++){
	    		   $("<input type='hidden' class='roleId' name='roleId' value='"+nodes[i].id+"'>").appendTo("#fm");
	    		}
	    		return;
	        },  
	        success: function(result){  
	        	 $(".roleId").remove();
		       	 $('#tt').tree('reload');
	             $('#dlg').dialog('close');   
	             result=js.json(result);  
	            if (result.success){  
	            	 $.messager.show({
                         title: 'Message',  
                         msg: 'Success！'  
                     });
	            } else {  
	                $.messager.show({  
	                    title: 'Message',  
	                    msg: result.msg  
	                });  
	            }  
	        }  
	    });  
	}  
	
	function grantFormat(val,row){
		if(val=="")
			return "";
		 else
		 	return "<a href=\"javascript:edit()\">Select Role</span>";  
	}
	
	function query(){ 
	   var params=$('#fm-query').serializeObject();
	   $('#dg').datagrid({url:"${ctx}/admin/staff",queryParams:params,pageNumber:1});
	}
	
</script>
</body>
</html>
