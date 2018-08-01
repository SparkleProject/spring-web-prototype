<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>  
<html>  
<head>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
   <div data-options="region:'west',split:true,border:false,title:'Menu Tree'" style="width:200px">
	<ul id="tt" class="easyui-tree"
					url="${ctx}/admin/menu/init"
					animate="true"
					checkbox="false">
	</ul>
	</div>  
   <div data-options="region:'center',split:true,border:false,title:'Menu Info'">
        <div id="toolbar" class="datagrid-toolbar ">  
			    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="js.add()">+New</a>  
			    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="js.remove()">-Delete</a>  
			</div>  
			<form id="fm" method="post" style="width:400px;height:280px;padding:10px 20px" > 
			 <table class="dlg-table">
	        	<tbody>
	        		<tr><td class="ltd">Menu Name:</td><td class="rtd"><input id="name" name="name" class="easyui-validatebox textbox" required="true"></td></tr>
	        		<tr><td class="ltd">URL:</td><td class="rtd"><input id="url" name="url" class="easyui-validatebox textbox"></td></tr>
	        		<tr><td class="ltd">Link to:</td>
	        			<td class="rtd">
	        			  <select id="target" name="target" class="easyui-combobox"  style="width:204px;">
							<option value=""></option>
							<option value="_blank">_blank</option>
							<option value="_self">_self</option>
							<option value="_parent">_parent</option>
							<option value="_top">_top</option>
						 </select>
	        			</td>
	        		</tr> 
	        		<tr><td class="ltd">Siblings Order:</td><td class="rtd"><input id="seq" name="seq" class="easyui-numberbox" min="0" max="1000"></td></tr> 
	        		<tr><td class="ltd">Enable:</td>
		        		<td class="rtd">
		        		 <select id="enable" class="easyui-combobox" name="enable" style="width:204px;" required="true">
							<option value="1">YES</option>
							<option value="0">No</option>
						</select>
		        		</td>
	        		</tr> 
	        		<tr><td class="ltd">ICON:</td><td class="rtd"><input id="icon" name="icon" class="easyui-validatebox textbox"></td></tr> 
	        		<tr><td class="ltd">Parent Menu:</td><td class="rtd"><input id="pid" name="pid" class="easyui-combotree" url="${ctx}/admin/menu/init" value=""  style="width:204px;"></td></tr>
	        		<tr><td class="ltd"></td><td class="rtd"><a href="javascript:void(0);" class="easyui-linkbutton" onclick="js.save()">Save</a><input id="id" name="id" type="hidden"></td></tr> 
	        	</tbody>
	        </table>
	    	</form> 
        
	</div>  
</div>
<script type="text/javascript">
	var url="${ctx}/admin/menu";
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
			$('#enable').combobox('setValue','1');
	    }
	};
	
	js.edit=function (){  
		var node = $('#tt').tree('getSelected');
		if(node){
			if(node.id==0)
				return;
			$("#id").val(node.id);
			$("#name").val(node.text);
			$("#url").val(node.attributes.url);		
			$("#title").val(node.attributes.title);
			$('#target').combobox('setValue',node.attributes.target==null?'':node.attributes.target);
			$('#icon').val(node.attributes.icon); 
			$('#seq').numberbox('setValue',node.attributes.seq); 
			$('#enable').combobox('setValue',node.attributes.enable==null?1:node.attributes.enable);
			$('#pid').combotree('setValue',node.attributes.pid); 
		}
	};
	
	js.save=function (){  
	    $('#fm').form('submit',{  
	        url: url+"/save",  
	        onSubmit: function(){  
	        	if(!js.ckeckParentIsChild()){
	        		return false;
	        	};
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
	js.remove=function (){  
		var node = $('#tt').tree('getSelected');
		var nodes = $('#tt').tree('getChildren',node.target);
	    if (node){  
	    	if(nodes.length>0){
	    		 $.messager.alert('Message','Existing children menu, cannot delete now！',"warning");
	    		 return false;
	    	}
	        $.messager.confirm('Message','Please confirm you want to delete this?',function(r){  
	            if (r){  
	                $.post(url+'/delete',{id:node.id},function(result){  
	                	result=js.json(result);
	                    if (result.success){  
	                    	$('#tt').tree('reload');    // reload the user data  
	                    	 $.messager.show({
	                             title: 'Message',  
	                             msg: 'Success!'  
	                         });
	                    } else {  
	                        $.messager.show({   // show error message  
	                            title: 'Message',  
	                            msg: result.msg  
	                        });  
	                    }  
	                },'json');  
	            }  
	        });  
	    }  
	};
	
	//死循环检查
	js.ckeckParentIsChild=function (){
		var cnode_id = $('#id').val();
		if(cnode_id){
			var tree=$('#pid').combotree('tree');
			var pnode=$(tree).tree('getSelected');
			if(pnode){
				try{
					var path=pnode.attributes.path;
					if(path.indexOf('/'+cnode_id+'/')>-1){
						 $.messager.show({  
			                    title: 'Message',  
			                    msg: "[Parent Menu]Cannot set as itself or its sub-type"
			               });  
						 return false;
					}
				}catch(e){
					
				}
				return true;
			}
		}
		//新增，不检查
		return true;
	};
	</script>
</body>
</html>
