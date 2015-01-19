package org.ikar.qchart;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

public class EmbeddedWebServer {

	private final int port;

	public EmbeddedWebServer(int port) {
		this.port = port;
	}

	public void start() throws Exception {
		//		logger.debug("Starting server at port {}", port);
		Server server = new Server(port);
		server.setHandler(getServletContextHandler(getContext()));
		server.start();
//		logger.info("Server started at port {}", port);
//		server.join();
	}

	private static ServletContextHandler getServletContextHandler(WebApplicationContext context) throws IOException {
		ServletContextHandler contextHandler = new ServletContextHandler();
		contextHandler.setErrorHandler(null);
		contextHandler.setContextPath(CONTEXT_PATH);
		contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
		contextHandler.addEventListener(new ContextLoaderListener(context));
		contextHandler.setResourceBase(new ClassPathResource("webapp").getURI().toString());
		return contextHandler;
	}

	private static WebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(ApplicationConfig.class);
//		context.setConfigLocation(CONFIG_LOCATION);
//		context.getEnvironment().setDefaultProfiles(DEFAULT_PROFILE);
		return context;
	}

	private static final String CONTEXT_PATH = "/";
//	private static final String CONFIG_LOCATION = "org.ikar.qchart";
	private static final String MAPPING_URL = "/*";
//	private static final String DEFAULT_PROFILE = "dev";
}
