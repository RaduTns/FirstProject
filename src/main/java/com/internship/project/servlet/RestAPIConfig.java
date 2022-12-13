package com.internship.project.servlet;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.internship.project.api.InventoryAPI;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("api")
public class RestAPIConfig extends Application {

	public RestAPIConfig() {

		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.2");
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setHost("localhost:8082");
		beanConfig.setBasePath("/inventory/api");
		beanConfig.setResourcePackage("com.internship.project.api");
		beanConfig.setScan(true);
	}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> clazzes = new HashSet<>();
		clazzes.add(InventoryAPI.class);
		return clazzes;
	}

}
