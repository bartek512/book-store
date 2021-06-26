package book.book.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration - view resolvers, static resources etc
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("/webjars/");
		registry.addResourceHandler("/css/**")
				.addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/favicon.ico")
				.addResourceLocations("classpath:/static/favicon.ico");
	}

}
