package com.gmtech.webase.common;

import java.util.ArrayList;
import java.util.List;

import lombok.Value;

public class ResponseEntity {

    private int status;

    private List<Message> msgs;

    private Object data;
    
    public ResponseEntity(int status, List<Message> msgs, Object data) {
        this.status = status;
        this.msgs = msgs;
        this.data = data;
    }
    
    public ResponseEntity addMessage(Message msg){
    	if(this.msgs == null){
    		this.msgs = new ArrayList<Message>();
    	}
    	
    	this.msgs.add(msg);
    	return this;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Message> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<Message> msgs) {
		this.msgs = msgs;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}