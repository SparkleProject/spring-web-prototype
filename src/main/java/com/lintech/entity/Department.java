package com.lintech.entity;

import java.util.HashMap;
import java.util.Map;

import com.lintech.core.easyui.TreeNode;
import com.lintech.core.easyui.TreeNodeSupport;


public class Department implements TreeNodeSupport {
    
    private Integer id;

    private String name;

    private Integer pid;

    private Integer seq;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public TreeNode getEasyUITreeNode() {
        TreeNode node=new TreeNode();
        node.setId(this.getId());
        node.setPid(this.getPid());
        node.setText(this.getName());
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("pid",this.getPid());
        attributes.put("seq",this.getSeq());
        attributes.put("description",this.getDescription());
        node.setAttributes(attributes);
        return node;
    }
}