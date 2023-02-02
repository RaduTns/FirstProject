package com.internship.project.servlet;

import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;

//@ApplicationPath("api/")
public class RestAPIConfig extends Application {

	private static final String X_HEADER_API_KEY = null;
	private static final String SECURITY_DEF_FOR_API_KEY = null;

	public RestAPIConfig() {

		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.2");
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setHost("localhost:8082");
		beanConfig.setBasePath("/inventory/api");
		beanConfig.setResourcePackage("com.internship.project.api");
		beanConfig.setScan(true);

//		Swagger swagger = beanConfig.getSwagger();
//		swagger.securityDefinition("basicAuth", new BasicAuthDefinition());
//		new SwaggerContextService().updateSwagger(swagger);
	}

//	@Override
//	public Set<Class<?>> getClasses() {
//		Set<Class<?>> clazzes = new HashSet<>();
//		clazzes.add(InventoryAPI.class);
//		return clazzes;
//	}

}
