package com.hobiron.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hobiron.http.HttpFactory;
import com.hobiron.http.Server;

@Configuration
public class HttpConfig {

    @Autowired
    private HttpFactory httpFactory;

    @Bean
    public Server server() {
        return httpFactory.createServer();
    }

}
