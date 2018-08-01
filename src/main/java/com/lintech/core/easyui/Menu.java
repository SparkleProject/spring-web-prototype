package com.lintech.core.easyui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台菜单数据结构
 */
public class Menu implements TreeNodeSupport,Serializable{

	private static final long serialVersionUID = 4636917498077376589L;

	private Integer id;

    private Integer pid;

    private String name;

    private String url;

    private String target;

    private Boolean relative;

    private String icon;

    private Integer seq;

    private Boolean enable;

    private Boolean visible;
    
    //extend
    private boolean checked;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public Boolean getRelative() {
        return relative;
    }

    public void setRelative(Boolean relative) {
        this.relative = relative;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
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
        node.setSeq(this.getSeq());
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("pid",this.getPid());
        attributes.put("url",this.getUrl());
        attributes.put("target",this.getTarget());
        attributes.put("relative",this.getRelative());
        attributes.put("icon",this.getIcon());
        attributes.put("seq",this.getSeq());
        attributes.put("enable",this.getEnable());
        attributes.put("visible",this.getVisible());
        node.setAttributes(attributes);
        return node;
    }
}