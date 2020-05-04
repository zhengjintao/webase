package com.gmtech.webase.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
	String type;
	String code;
	String lang;
	String content;
	
	public static Message failure() {
		return new Message();
	}
	
	public static Message success() {
		return new Message();
	}
	
	public void setMsg(String msg) {
		this.content = msg;
	}
}
