package com.lintech.core.easyui;

import jakarta.persistence.Transient;

public class Combobox {

	@Transient
	String textField;

	@Transient
	String valueField;

	@Transient
	boolean selected;

	public String getTextField() {
		if(textField==null) {
			return valueField;
		}
		return textField;
	}

	public void setTextField(String textField) {
		this.textField = textField;
	}

	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	

}
