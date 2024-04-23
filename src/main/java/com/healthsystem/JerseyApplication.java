package com.healthsystem;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApplication extends ResourceConfig {
    public JerseyApplication() {
        // Specify the package(s) that contain your JAX-RS resources
        packages("com.healthsystem.resource"); // Adjust this to your actual package(s)
    }
}
