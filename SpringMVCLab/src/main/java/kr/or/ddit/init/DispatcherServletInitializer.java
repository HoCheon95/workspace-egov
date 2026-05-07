package kr.or.ddit.init;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration.Dynamic;

public class DispatcherServletInitializer extends AbstractDispatcherServletInitializer {
	@Override
	protected void customizeRegistration(Dynamic registration) {
		String location = "/home/ho/00.medias/temp";
		long maxFileSize = -1;
		long maxRequestSize = -1;
		int fileThreshold = 10 * 1024;
		MultipartConfigElement config = new MultipartConfigElement(
			location,
			maxFileSize,
			maxRequestSize,
			fileThreshold
		);
		registration.setMultipartConfig(config);
	}

	@Override
	protected WebApplicationContext createServletApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.scan("kr.or.ddit");
		return context;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		return null;
	}
}
