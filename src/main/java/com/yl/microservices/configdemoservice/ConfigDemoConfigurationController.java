package com.yl.microservices.configdemoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yl.microservices.configdemoservice.bean.ConfigDemoConfiguration;

@RestController
public class ConfigDemoConfigurationController {

	@Autowired
	private Configuration configuration;
	
	@GetMapping("/properties")
	public ConfigDemoConfiguration retrievePropertiesFromConfigurations () {
		return new ConfigDemoConfiguration(configuration.getStringsampleproperty(),configuration.getIntsampleproperty());
	}
	
}
