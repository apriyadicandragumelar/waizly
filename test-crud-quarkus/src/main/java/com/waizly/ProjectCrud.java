package com.waizly;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title="Project CRUD Waizly",
                version = "1.0.0",
                description = "Project Crud Waizly Quarkus",
                contact = @Contact(
                        name = "M Apriyadi Candra Gumelar",
                        email = "candragumelar879@email.com"),
                license = @License(
                        name = "Waizly",
                        url = "http://")))
public class ProjectCrud extends Application{}

