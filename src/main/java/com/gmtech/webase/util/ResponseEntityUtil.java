package com.gmtech.webase.util;

import java.util.List;

import com.gmtech.webase.common.Message;
import com.gmtech.webase.common.ResponseEntity;

public class ResponseEntityUtil {
	public static ResponseEntity success(Object data){
		return success(null, data);
	}
	
	public static ResponseEntity success(List<Message> msgs, Object data){
		return new ResponseEntity(200, msgs, data);
	}
}
