<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>  
<html>
<head>
<script type="text/javascript" src="admin/assets/easyui/jquery.edatagrid.js"></script>
</head>  
<body>
<div class="easyui-layout" data-options="fit:true">  
   <div data-options="region:'west',split:true,border:false,title:'Role'" style="width:350px">
 			<form id="fm" method="post" style="padding: 5px;">  
			<table class="query-table">
			<tbody>
				<tr><td>Role Code:</td><td> <input name="code" class="easyui-validatebox textbox" autocomplete="off"></td><td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="query()">Search</a></td></tr>
				<tr><td>Role Name:</td><td> <input name="name" class="easyui-validatebox textbox" autocomplete="off"></td><td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="$('#fm').form('clear')">Reset</a></td></tr>
				</tbody>
			</table>
   			</form>
		 	<table id="dg" class="easyui-datagrid" 
	            data-options="singleSelect:true,border:false,collapsible:true,
	            pagination:true,fitColumns:true,toolbar:'#toolbar',rownumbers:true"> 
	    	<thead>  
	        <tr>  
				<th field="id" width="100"  editor="text" hidden="true">Role ID</th>
				<th field="code" width="100"  editor="{type:'validatebox',options:{required:true}}">Role Code</th>
				<th field="name" width="100"  editor="{type:'validatebox',options:{required:true}}">Role Name</th>
	        </tr>  
		    </thead>  
			</table>  
			<div id="toolbar">  
			    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="javascript:$('#dg').edatagrid('addRow')">New</a>  
			    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="javascript:dg_remove()">Delete</a>  
			    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow')">Save</a>
			    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="javascript:$('#dg').edatagrid('cancelRow')">Calcel</a>
			</div>  
	</div>  
   <div data-options="region:'center',split:true,border:false">
   	<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
			    <div title="Menu Authrization" class="ti">
			    	<div id="buttoms-menu" class="datagrid-toolbar">  
					    <a href="javascript:void(0);" class="easyui-linkbutton"  plain="true" onclick="save_menu()" enabled="function.add">Save</a>  
					</div>  
			    	<div id="p" class="easyui-panel"  style="height: auto;border: 0px;">  
				        <form id="fm-tt" method="post">
					        <input type="hidden" name="role_id" id="roleId_menu">
							<ul id="tt" class="easyui-tree" checkbox="true" lines="true" cascadeCheck="true"></ul>
						</form>
					</div>
			    </div>
			    <div title="Feature Authrization" class="ti">
			    	<form id="fm-func" method="post">  
			    	<input type="hidden" name="role_id" id="roleId_func">
					<table id="dg-func"  class="easyui-datagrid"  
					toolbar="#toolbar-func"
					url="${ctx}/admin/function/all" rownumbers="true" fitColumns="false" 
					singleSelect="false"  pagination="false" data-options="border:false"  >  
					    <thead>  
					        <tr>  
								<th field="ck" checkbox="true"></th>
								<th field="id" width="50" hidden="true">Feature ID</th>
								<th field="code" width="150">Feature Code</th>
								<th field="name" width="150">Feature Name</th>
								<th field="description" width="250">Description</th>
					        </tr>  
					    </thead>  
					</table>  
					</form>
					<div id="toolbar-func">  
					    <a href="javascript:void(0);" class="easyui-linkbutton"  plain="true" onclick="save_func()">Save</a>  
					</div>  
			    </div>
		    </div> 
 
	</div>  
</div>
<script type="text/javascript">
var url="${ctx}/admin/role";
$(document).ready(function(){
	$('#dg').edatagrid({
		url:url+"/init",  
	    saveUrl:url+'/save',  
	    updateUrl:url+'/save',  
	    destroyUrl:url+'/delete',		    		
	 	onClickRow:function(rowIndex,row){
			$('#tt').tree({url:"${ctx}/admin/role-res/load-menu?role_id="+row.id});
			$('#roleId_func').val(row.id);
			$('#roleId_menu').val(row.id);
			var data_url="${ctx}/admin/role-res/load-func?role_id="+row.id;
			$('#dg-func').datagrid('unselectAll');
			$.post(data_url,function(data){
				var funcs=js.json(data);
				var rows=$('#dg-func').datagrid('getRows');
		 		for(var i=0;i<rows.length;i++){
		 			for(var j=0;j<funcs.length;j++){
		 				if(funcs[j].resId==rows[i].id){
		 					js.log("role.jsp:select row:"+i);
							$('#dg-func').datagrid('selectRow',i);
		 				}
		 			}
				}
			});
		},
		onSave:function(index,row){
			$('#dg').datagrid('load');
		}
	});
	
	//重定义#dg pagination
	var pager=$('#dg').datagrid('getPager');
	$(pager).pagination({
		displayMsg:""
	});
});

 function dg_remove(){  
    var row = $('#dg').datagrid('getSelected');  
    if (row){  
        $.messager.confirm('Message','Please confirm you want to delete this?',function(r){  
            if (r){  
                $.post(url+'/delete',{id:row.id},function(result){  
                	result=js.json(result);
                    if (result.success){  
                        $('#dg').datagrid('load');  
                        $('#roleId_func').val("");
                        $('#roleId_menu').val("");
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
}

function query(){ 
   var params=$('#fm').serializeObject();
   $('#dg').datagrid({url:url,queryParams:params,pageNumber:1});
}

function save_menu(){  
	var v=$('#roleId_menu').val();
	if(v==null||v==''||v==undefined){
		 $.messager.show({
             title: 'Message',  
             msg: 'Please select one Role on left'  
         });
		 return;
	}
	var url="${ctx}/admin/role-res";
    $('#fm-tt').form('submit',{  
        url: url+"/save_menu",  
        onSubmit: function(){  
    		var nodes = $('#tt').tree('getChecked');
    		for(var i=0;i<nodes.length;i++){
    		   $("<input type='hidden' class='menuId' name='menuId' value='"+nodes[i].id+"'>").appendTo("#fm-tt");
    		}
    		return;
        },  
        success: function(result){  
        	$(".menuId").remove();
        	result=js.json(result);
            if (result.success){  
            	 $.messager.show({
                     title: 'Message',  
                     msg: 'Success!'  
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

function save_func(){  
	var v=$('#roleId_func').val();
	if(v==null||v==''||v==undefined){
		 $.messager.show({
             title: 'Message',  
             msg: 'Please select one Role on left'  
         });
		 return;
	}
	var url="${ctx}/admin/role-res";
    $('#fm-func').form('submit',{  
        url: url+"/save_func",  
        onSubmit: function(){  
    		var rows=$('#dg-func').datagrid('getSelections');
    		for(var i=0;i<rows.length;i++){
    		   $("<input type='hidden' class='funcId' name='funcId' value='"+rows[i].id+"'>").appendTo("#fm-func");
    		}
    		return;
        },  
        success: function(result){  
        	$(".funcId").remove();
        	result=js.json(result);
            if (result.success){  
            	 $.messager.show({
                     title: 'Message',  
                     msg: 'Success!'  
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
</script>
</body>
</html>
