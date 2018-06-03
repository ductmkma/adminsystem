package com.zent.dto;

public class MenuDTO {
	private String id;
	private String text;
	private String url;
	private String parent;
	private StateDTO state;
	public StateDTO getState() {
		return state;
	}
	public void setState(StateDTO state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}

	
	
	
	
	
	
}
