package br.com.macedo.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title="",
                version = "1.0-SNAPSHOT",
                contact = @Contact(
                        name = "Suporte",
                        url = "http://exampleurl.com/contact",
                        email = "techsupport@example.com"))
)
public class AppConfig extends Application {}