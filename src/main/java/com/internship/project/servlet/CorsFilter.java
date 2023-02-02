package com.internship.project.servlet;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsFilter implements ContainerResponseFilter {
	public void filter(ContainerRequestContext paramContainerRequestContext,
			ContainerResponseContext paramContainerResponseContext) throws IOException {
		paramContainerResponseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		paramContainerResponseContext.getHeaders().add("Access-Control-Allow-Headers",
				"origin, content-type, accept, authorization, accept-language");
		paramContainerResponseContext.getHeaders().add("Access-Control-Expose-Headers",
				"location, cache-control, content-type, content-length, expires");
		paramContainerResponseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		paramContainerResponseContext.getHeaders().add("Access-Control-Allow-Methods", "*");
		paramContainerResponseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
	}
}
