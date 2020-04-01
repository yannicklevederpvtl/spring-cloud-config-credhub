package com.yl.microservices.configdemoservice.bean;

public class ConfigDemoConfiguration {
	private String stringsampleproperty;
	private int	intsampleproperty;
	
	protected ConfigDemoConfiguration() {
		
	}
	
	public ConfigDemoConfiguration(String stringsampleproperty, int intsampleproperty) {
		super();
		this.stringsampleproperty = stringsampleproperty;
		this.intsampleproperty = intsampleproperty;
	}
	
	public String getStringsampleproperty() {
		return stringsampleproperty;
	}
	
	public int getIntsampleproperty() {
		return intsampleproperty;
	}
	
}
