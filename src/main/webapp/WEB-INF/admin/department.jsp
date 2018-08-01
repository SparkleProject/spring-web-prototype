<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>  
<html>  
<head>
</head>
<body style="border: 0;margin: 0;">
<div class="easyui-layout" data-options="fit:true">  
   <div data-options="region:'west',split:true,border:false,title:'Department'" style="width:200px">
	<ul id="tt" class="easyui-tree" url="${ctx}/admin/department/init" checkbox="false"></ul>
   </div>  
   <div data-options="region:'center',split:true,border:false,title:'Department Info'">
    			<div id="toolbar" class="datagrid-toolbar ">  
			    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="js.add()">New</a>  
			    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="js.remove()">Delete</a>  
			</div>  
			<form id="fm" method="post" style="width:400px;height:280px;padding:10px 20px" > 
				<table class="dlg-table">
		        	<tbody>
		        		<tr><td class="ltd">Department Name:</td><td class="rtd"><input id="name" name="name" class="easyui-validatebox textbox" required="true"></td></tr>
		        		<tr><td class="ltd">Sibling Order:</td><td class="rtd"><input id="seq" name="seq" class="easyui-numberbox" min="0" max="1000"></td></tr>
		        		<tr><td class="ltd">Parental node:</td><td class="rtd"><input id="pid" name="pid" class="easyui-combotree" url="${ctx }/admin/department/init" value="" style="width:204px;"></td></tr>
		        		<tr><td class="ltd">Description:</td><td class="rtd"><textarea id="description" name="description" class="textbox" style="height: 50px;"></textarea></td></tr>
		        		<tr><td class="ltd"></td><td class="rtd"><a href="javascript:void(0);" class="easyui-linkbutton" onclick="js.save()">Save</a><input id="id" name="id" type="hidden"> </td></tr>
		        	</tbody>
		        </table>
	    	</form> 
	</div>  
</div>
<script type="text/javascript">
	var url="${ctx}/admin/department";
	$(function(){
		$('#tt').tree({
			onClick:function(node){
				js.edit();
			}
		});
	});
	
	js.add=function (){  
	    $('#fm').form('clear');  
	    var node = $('#tt').tree('getSelected');
	    if(node){
			$('#pid').combotree('setValue',node.id);
	    }
	};
	
	js.edit=function(){  
		var node = $('#tt').tree('getSelected');
		if(node){
			if(node.id==0)
				return;
			$("#id").val(node.id);
			$("#name").val(node.text);
			$('#seq').numberbox('setValue', node.attributes.seq);
			$("#description").val(node.attributes.description);	
			$('#pid').combotree('setValue',node.attributes.pid); 
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
	            	 $('#tt').tree('reload');
	                 $('#fm').form('clear');  
	                 $('#pid').combotree('reload');  
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
	};
	
	js.remove=function(){  
		var node = $('#tt').tree('getSelected');
	    if (node){  
	        $.messager.confirm('Message','Please confirm you want to delete this',function(r){  
	            if (r){  
	                $.post(url+'/delete',{id:node.id},function(result){  
	                	result=js.json(result);
	                    if (result.success){  
	                    	$('#tt').tree('reload');  
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
	                },'json');  
	            }  
	        });  
	    }  
	};
	</script>
</body>
</html>
