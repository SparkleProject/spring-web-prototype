package com.lintech.entity;

import java.util.HashMap;
import java.util.Map;

import com.lintech.core.easyui.TreeNode;
import com.lintech.core.easyui.TreeNodeSupport;


public class Role implements TreeNodeSupport {
    
    private Integer id;

    private String code;

    private String name;

    private String description;
    
    //extend
    public Integer pid;
    
    private boolean checked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getPid() {
        return TreeNode.TREE_ROOT;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
    

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public TreeNode getEasyUITreeNode() {
        TreeNode node=new TreeNode();
        node.setId(this.getId());
        node.setPid(this.getPid());
        node.setText(this.getName());
        node.setChecked(this.isChecked());
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("pid",this.getPid());
        attributes.put("description",this.getDescription());
        node.setAttributes(attributes);
        return node;
    }
}