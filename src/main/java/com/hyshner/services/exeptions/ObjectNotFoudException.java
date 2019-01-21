package com.hyshner.services.exeptions;

import javassist.tools.rmi.ObjectNotFoundException;

public class ObjectNotFoudException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoudException (String msg) {
		super (msg);
	}
	public ObjectNotFoudException (String msg, Throwable cause) {
		super (msg,cause);
	}

}
