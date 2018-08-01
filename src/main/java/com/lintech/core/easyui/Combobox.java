package com.lintech.core.easyui;

public class Combobox {
	
	String textField;
	
	String valueField;
	
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
