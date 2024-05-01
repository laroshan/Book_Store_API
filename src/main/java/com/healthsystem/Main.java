package com.healthsystem;

import com.healthsystem.JerseyApplication;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.servlet.ServletContainer;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        String contextPath = "";
        String appBase = ".";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8087);
        tomcat.setHostname("localhost");
        tomcat.getHost().setAppBase(appBase);
        tomcat.setBaseDir("tomcat-base");

        // Create a context for the Tomcat application
        Context context = tomcat.addWebapp(contextPath, new File("src/main/webapp/").getAbsolutePath());

        // Configure the Jersey servlet with the JAX-RS application
        Tomcat.addServlet(context, "JerseyServlet1", new ServletContainer(new JerseyApplication()));
        context.addServletMappingDecoded("/*", "JerseyServlet1");

        System.out.println("Starting Tomcat on port: " + tomcat.getConnector().getPort());
        tomcat.start();
        tomcat.getServer().await();
    }
}
