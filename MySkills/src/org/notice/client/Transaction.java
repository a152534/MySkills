package org.notice.client;

import java.io.Serializable;

public class Transaction implements Serializable{
	String Description ; 
	Object object ;
	
	
	
	
	public Transaction(String description, Object object) {
		super();
		Description = description;
		this.object = object;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	} 

}
