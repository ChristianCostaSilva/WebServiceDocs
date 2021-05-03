package br.com.documentonorma.soap.webservices.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(org.springframework.context.ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
		
	}
	
	@Bean
	public XsdSchema documentSchema() {
		return new SimpleXsdSchema(new ClassPathResource("documento-information.xsd"));
	}
	
	@Bean (  value =  "documents")
	public DefaultWsdl11Definition defaultWsdl11Definition (XsdSchema documentSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("DocumentPort");
		definition.setTargetNamespace("http://documentonorma.com.br");
		definition.setLocationUri("/ws");
		definition.setSchema(documentSchema);
		return definition;
	}
	
}
