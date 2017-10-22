package com.hobiron.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    @Value("${port.server.base-url}")
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

}
