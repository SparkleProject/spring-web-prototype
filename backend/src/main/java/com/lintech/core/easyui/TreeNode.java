package com.lintech.core.easyui;

import java.util.List;
import java.util.Map;

/**
 * EasyUI树节点辅助类
 */
public class TreeNode implements Comparable<TreeNode>{
    
    /**
     * 根节点ID
     */
    public final static int TREE_ROOT=0;
    
    /**
     * 根节点TEXT
     */
    public final static String TREE_ROOT_TEXT = "Root";
    
    public int id;
   
    public String text;
    
    public Integer seq;
    
    public String state;
    
    public boolean checked;
    
    public Map<String,Object> attributes;
    
    public List<TreeNode> children;
    
    //extend
    public int pid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	

    public int compareTo(TreeNode node) {
        if(this.getSeq()==null||node.getSeq()==null){
            return -1;
        }else if (this.getSeq() > node.getSeq())
            return 1;
        else if (this.getSeq() == node.getSeq())
            return 0;
        else
            return -1;
    }
}
