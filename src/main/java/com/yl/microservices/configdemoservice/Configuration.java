package com.yl.microservices.configdemoservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("config-demo-service")
public class Configuration {
	
	private String stringsampleproperty;
	private int intsampleproperty;
	
	public void setStringsampleproperty(String stringsampleproperty) {
		this.stringsampleproperty = stringsampleproperty;
	}
	public void setIntsampleproperty(int intsampleproperty) {
		this.intsampleproperty = intsampleproperty;
	}
	public String getStringsampleproperty() {
		return stringsampleproperty;
	}
	public int getIntsampleproperty() {
		return intsampleproperty;
	}
	
}
