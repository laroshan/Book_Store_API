package com.bookStore;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApplication extends ResourceConfig {
    public JerseyApplication() {
        packages(
                "com.bookStore.resource",
                "com.bookStore.exception");
        register(JacksonFeature.class);
    }
}
