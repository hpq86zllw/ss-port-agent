package com.hobiron.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.hobiron.conf.ServerConfig;

import feign.Feign;
import feign.gson.GsonDecoder;

@Component
public class HttpFactory {

    @Autowired
    private ServerConfig serverConfig;

    @Cacheable(cacheNames = "http", key = "#root.methodName")
    public Server createServer() {
        return Feign.builder().decoder(new GsonDecoder()).target(Server.class, serverConfig.getBaseUrl());
    }

}
