<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>  
<html>  
<head>
<script type="text/javascript" src="admin/assets/easyui/jquery.edatagrid.js"></script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">  
   <div data-options="region:'west',split:true,border:false,title:'>SysCode Management<'" style="width:350px">
 			<form id="fm-type-query" method="post">  
			<table>
				<tbody>
					<tr><td>Type Code</td><td><input name="code" class="easyui-validatebox textbox"></td><td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="query_type()">Search</a></td></tr>
					<tr><td>Type Name</td><td><input name="name" class="easyui-validatebox textbox"></td><td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="$('#fm-type-query').form('clear')">Reset</a></td></tr>
				</tbody>
			</table>
	    	</form>  
	    	<table id="dg-type" title="" class="easyui-datagrid frame-datagrid"
			        url="${ctx}/admin/code/type/init"  
			        toolbar="#toolbar-type"  
			        data-options="border:false"
			        rownumbers="true" fitColumns="true" singleSelect="true"  pagination="true">  
			    <thead>  
			        <tr>  
						<th field="id" width="100"  editor="text" hidden="true">ID</th>
						<th field="code" width="100" editor="{type:'validatebox',options:{required:true}}">Type Code</th>
						<th field="name" width="100" editor="{type:'validatebox',options:{required:true}}">Type Name</th>
						<th field="seq" width="100" editor="{type:'text'}"  hidden="true" >Order</th>
						<th field="description" width="100" editor="{type:'text'}"  hidden="true" >Description</th>
			        </tr>  
			    </thead>  
			</table>  
			<div id="toolbar-type">  
			    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="javascript:$('#dg-type').edatagrid('addRow')">New</a>  
			    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="javascript:dg_type_remove()">Delete</a>  
			    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="javascript:$('#dg-type').edatagrid('saveRow')">Save</a>
			    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="javascript:$('#dg-type').edatagrid('cancelRow')">Calcel</a>
			</div>  
	</div>  
   <div data-options="region:'center',split:true,border:false">
			<table id="dg" class="easyui-datagrid" 
	            data-options="singleSelect:true,border:false,collapsible:true,
	            pagination:true,fitColumns:true,toolbar:'#toolbar',pageSize:20,rownumbers:true"> 
			    <thead>  
			        <tr>  
						<th field="id" width="100" hidden="true">ID</th>
						<th field="type" width="100" editor="{type:'codeType'}" hidden="true">Type</th>
						<th field="code" width="100" editor="{type:'validatebox',options:{required:true}}">Code</th>
						<th field="name" width="100" editor="{type:'validatebox',options:{required:true}}">Name</th>
						<th field="value" width="100" editor="{type:'validatebox',options:{required:true}}">Value</th>
						<th field="enable" width="100" formatter="booleanFormat" editor="{type:'combobox',options:{required:true,textField:'text',valueField:'id',editable:false,panelHeight:'auto',data:[{id:1,text:'是'},{id:0,text:'否'}]}}">是否可用</th>
						<th field="seq" width="100"  editor="{type:'numberbox'}">Order</th>
						<th field="description" width="100" editor="{type:'validatebox'}"  formatter="titleFormat">Description</th>
			        </tr>  
			    </thead>  
			</table>  
			<div id="toolbar">  
			    <a href="javascript:void(0);" class="easyui-linkbutton"  plain="true" onclick="dg_addRow()">New</a>  
			    <a href="javascript:void(0);" class="easyui-linkbutton"  plain="true" onclick="javascript:dg_remove();">Delete</a>  
			    <a href="javascript:void(0);" class="easyui-linkbutton"  plain="true" onclick="javascript:$('#dg').edatagrid('saveRow')">Save</a>
			    <a href="javascript:void(0);" class="easyui-linkbutton"  plain="true" onclick="javascript:$('#dg').edatagrid('cancelRow')">Calcel</a>
			</div>  
	</div>  
</div>
<script type="text/javascript">
  	var url="${ctx}/admin/code";
  	var url_type="${ctx}/admin/code/type";
  	function dg_type_remove(){  
	    var row = $('#dg-type').datagrid('getSelected');  
	    if (row){  
	        $.messager.confirm('Message','Please confirm you want to delete this?',function(r){  
	            if (r){  
	                $.post(url_type+'/delete',{id:row.id},function(result){  
	                	result=js.json(result);
	                    if (result.success){  
	                        $('#dg-type').datagrid('load');  
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
	
	function query_type(){ 
		try{
		   var params=$('#fm-type-query').serializeObject();
		   $('#dg-type').datagrid({url:url_type+"/init",queryParams:params});

			//重定义#dg-type pagination
			var pager=$('#dg-type').datagrid('getPager');
			$(pager).pagination({
				displayMsg:""
			});
		}catch(err){
			$.messager.show({ title: 'Message',msg:'Faild to seach a result.'});
		}
	}
	
	$(document).ready(function(){
		$('#dg-type').edatagrid({
			url:url_type+"/init",  
		    saveUrl:url_type+'/save',  
		    updateUrl:url_type+'/save',  
		    destroyUrl:url_type+'/delete',		    		
			onClickRow:function(rowIndex,rowData){
				$('#dg').datagrid('load',{
					type:rowData.id
				});
			},
			onSave:function(index,row){
				$('#dg-type').datagrid('load');
			}
		});
		
		//重定义#dg-type pagination
		var pager=$('#dg-type').datagrid('getPager');
		$(pager).pagination({
			displayMsg:""
		});
		
		$('#dg').edatagrid({
			url:url+"/init",  
		    saveUrl:url+'/save',  
		    updateUrl:url+'/save',  
		    destroyUrl:url+'/delete',
		    onError: function(index,row){
		    	$.messager.alert('Faild to save',row.msg,"error");
			}
		});
		
	});
	
	function codeTypeFormat(val,row){
		var valueField='id';
		var textField='name';
		var result="";
		var data=document.codeTypeData;
		if(!data){
			$.ajax({
				url:'/admin/code/type/loadAll',
				success:function(res){
					data=js.json(res);
					document.codeTypeData=data;
				},
				async:false
			});
		}
		for(var i=0;i<data.length;i++){
			var  obj=data[i];
			if(obj[valueField]==val){
				result=obj[textField];
				break;
			}
		}
		return result;
	}
	
	function dg_addRow(){
		var row = $('#dg-type').datagrid('getSelected');  
		if (row){  
			$('#dg').edatagrid('addRow');
		}else{
       		$.messager.show({
                title: 'Message',  
                msg:"Please select one type on left, then click New."
            }); 
		}
	}
	
	
	function dg_remove(){  
	    var row = $('#dg').datagrid('getSelected');  
	    if (row){  
	        $.messager.confirm('Message','Please confirm you want to delete this?',function(r){  
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
	}
	
	$.extend($.fn.datagrid.defaults.editors, {
		codeType: {
			init : function(container, options) {
				var row=$('#dg-type').datagrid('getSelected');
				var val=row.id;
				var text = $("<input type='text' value='"+val+"'>").appendTo(container);	
				return text;
			},
			getValue : function(target) {
				return $(target).val();
			},
			setValue : function(target, value) {
				if(value){
					$(target).val(value);
				}else{
					$(target).text('setValue',value);
				}
			},
			resize : function(target, width) {
				$(target)._outerWidth(width);
			}
		}
	});
	</script>
</body>
</html>
