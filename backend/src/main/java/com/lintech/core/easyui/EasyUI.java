package com.lintech.core.easyui;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * EasyUI辅助工具类
 */
public class EasyUI {
    
    String contextPath;
    
    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    /**
     * 返回EasyUI菜单
     * @param nodeList
     * @param contextPath
     * @return
     */
    public static String getEasyUIMenu(List<Menu> srcList,List<Menu> distList,String contextPath){
        wrapMenuParent(srcList, distList);
        StringBuffer rootMenu=new StringBuffer();
        StringBuffer subMenu=new StringBuffer();
        TreeNode node = buildEasyUITreeNode(distList);
        List<TreeNode> children = node.getChildren();
        for(TreeNode n:children){
            if(n.getChildren()==null||n.getChildren().size()==0){
                rootMenu.append("<a class=\"easyui-linkbutton\" data-options=\"plain:true\"");
                if(n.getAttributes().get("url")!=null){
                    rootMenu.append(" onclick=\"javascript:js.add_tab('")
                    .append(n.getText())
                    .append("','")
                    .append(contextPath)
                    .append(n.getAttributes().get("url"))
                    .append("');\"");
                }
                rootMenu.append(">");
                rootMenu.append(n.getText()).append("</a>\n");
            }else{
                rootMenu.append("<a class=\"easyui-menubutton\" data-options=\"menu:'#mm").append(n.getId()).append("'\">").append(n.getText()).append("</a>\n");
                subMenu.append("<div").append(" id=\"mm").append(n.getId()).append("\">\n");
                buildEasyUISubMenu(n,subMenu,contextPath);
                subMenu.append("</div>");
            }
        }
        rootMenu.append(subMenu);
        return rootMenu.toString();
    }
    
    /**
     * 辅助函数：补足父节点
     * @param srcList
     * @param distList
     */
    private static void wrapMenuParent(List<Menu> srcList,List<Menu> distList){
        Set<Integer> pidSet=new HashSet<Integer>();
        Set<Integer>  additionalPidSet=new HashSet<Integer>();
        Set<Integer> idSet=new HashSet<Integer>();
        for(Menu menu:distList){
            pidSet.add(menu.getPid());
            idSet.add(menu.getId());
        }
        for(int pid:pidSet){
            if(idSet.contains(pid))
                continue;
            additionalPidSet.add(pid);
        }
        
        for(int pid:additionalPidSet){
            for(Menu menu:srcList){
                if(pid==menu.getId()){
                    distList.add(menu);
                }
            }
        }
    }
    
    /**
     * 辅助函数：返回子菜单
     * @param node
     * @param subMenu
     * @return
     */
    public static StringBuffer buildEasyUISubMenu(TreeNode node,StringBuffer subMenu,String contextPath){
        List<TreeNode> children = node.getChildren();
        for(TreeNode n:children){
            if(n.getChildren()==null||n.getChildren().size()==0){
                subMenu.append("<div")
                       .append(" onclick=\"javascript:js.add_tab('")
                       .append(n.getText())
                       .append("','")
                       .append(contextPath)
                       .append(n.getAttributes().get("url"))
                       .append("');\"")
                       .append(">")
                       .append(n.getText()).append("</div>\n");
            }else{
                subMenu.append("<div>\n<span>").append(n.getText()).append("</span>\n<div>\n");
                buildEasyUISubMenu(n,subMenu,contextPath);
                subMenu.append("</div>\n</div>\n");
            }
        }
        return subMenu;
    }
    
	
    /**
     * 返回EasyUI树形结构数据
     * @param nodeList
     * @return
     */
    public static List<TreeNode> getEasyUITree(List<? extends TreeNodeSupport> nodeList){
        List<TreeNode> result=new ArrayList<TreeNode>();
        result.add(buildEasyUITreeNode(nodeList));
        return result;
    }
    
    /**
     * 辅助函数：处理树形结构数据
     * @param nodeList
     * @return
     */
    private static TreeNode buildEasyUITreeNode(List<? extends TreeNodeSupport> nodeList){
        TreeNode root=new TreeNode();
        root.setId(TreeNode.TREE_ROOT);
        root.setText(TreeNode.TREE_ROOT_TEXT);
        buildEasyUITree(root,nodeList);
        return root;
    }
    
    /**
     * 辅助函数：处理树形结构数据
     * @param node
     * @param nodeList
     */
    private static void buildEasyUITree(TreeNode node,List<? extends TreeNodeSupport> nodeList){
        List<TreeNode> children=new ArrayList<TreeNode>();
        for(TreeNodeSupport ns:nodeList){
            TreeNode e = ns.getEasyUITreeNode();
            if(node.getId()==e.getPid()){
                children.add(e);
                buildEasyUITree(e,nodeList);
            }
        }
        java.util.Collections.sort(children);
        node.setChildren(children);
    }
    
    
    /**
     * 
     * @param list
     * @param formatter
     * @return
     */
    public static List<Combobox> formatCombobox(List<Combobox> list, Formatter formatter) {
		return formatCombobox(list, formatter, true, null);
	}
    
    /**
     * 
     * @param list
     * @param textFieldFormatter
     * @param appendFirst
     * @param selectValue
     * @return
     */
    public static List<Combobox> formatCombobox(List<Combobox> list,Formatter textFieldFormatter,boolean appendFirst,String selectValue) {
		for(Combobox combobox:list ) {
			if(textFieldFormatter==null) {
				if(combobox==null) 
					continue;
				String textField = combobox.getTextField();
				if(StringUtils.isNotBlank(textField)) {
					combobox.setTextField(combobox.getTextField());
				}else {
					combobox.setTextField(combobox.getValueField());
				}
			}else {
				combobox.setTextField(textFieldFormatter.format(combobox.getValueField()));
			}
			if(StringUtils.isNotBlank(selectValue)) {
				if(combobox.getValueField().equals(selectValue)) {
					combobox.setSelected(true);
				}
			}
		}
		if(appendFirst) {
			Combobox combobox = new Combobox();
			combobox.setTextField("- 请选择 -");
			combobox.setValueField("");
			list.add(0,combobox);
		}
		return list;
	}

	
}
