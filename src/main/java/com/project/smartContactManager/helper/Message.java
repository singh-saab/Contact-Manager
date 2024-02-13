package com.project.smartContactManager.helper;

public class Message {

	String content;
	String type;

	public Message() {

		// TODO Auto-generated constructor stub
	}

	public Message(String content, String type) {
		super();
		this.content = content;
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
