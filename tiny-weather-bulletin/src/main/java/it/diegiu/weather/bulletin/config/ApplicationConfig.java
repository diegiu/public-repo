package it.diegiu.weather.bulletin.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.Api;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration class of the "tiny-weather-bulletin" project.
 * 
 * @author giudicidiego
 */
@Configuration
@EnableSwagger2
public class ApplicationConfig {
	
	@Value("${swagger.title}")
	private String swaggerTitle;
	
	@Value("${swagger.description}")
	private String swaggerDescription;
	
	@Value("${swagger.version}")
	private String swaggerVersion;
	
	@Value("${swagger.termsOfServiceUrl}")
	private String swaggerTermsOfServiceUrl;
	
	@Value("${swagger.contact.name}")
	private String swaggerContactName;
	
	@Value("${swagger.contact.url}")
	private String swaggerContactUrl;
	
	@Value("${swagger.license}")
	private String swaggerLicense;
	
	@Value("${swagger.licenseUrl}")
	private String swaggerLicenseUrl;

	/**
	 * The method that initializes a docket component to document the API on display.
	 * 
	 * @return the configured docket component; as {@link Docket}
	 * @author giudicidiego
	 */
	@Bean
	@SuppressWarnings("rawtypes")
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
			.build()
			.apiInfo(new ApiInfo (
				swaggerTitle,
				swaggerDescription,
				swaggerVersion,
				swaggerTermsOfServiceUrl,
				new Contact(swaggerContactName, swaggerContactUrl, null),
				swaggerLicense,
				swaggerLicenseUrl, new ArrayList<VendorExtension>()));
	}
	
	// the default value is 3000 ms
	@Value("${rest.client.connection.timeout:3000}")
	private int restClientConnectionTimeout;
	
	// the default value is 3000 ms
	@Value("${rest.client.read.timeout:3000}")
	private int restClientReadTimeout;
	
	/**
	 * The method that initializes a template for the rest calls.
	 * 
	 * @return the configured template for the rest calls; as {@link RestTemplate}
	 * @author giudicidiego
	 */
	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
	    factory.setConnectTimeout(restClientConnectionTimeout);
	    factory.setReadTimeout(restClientReadTimeout);
	    return new RestTemplate(factory);
	}
}