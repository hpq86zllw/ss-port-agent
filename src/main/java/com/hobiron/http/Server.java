package com.hobiron.http;

import java.util.Map;

import com.hobiron.bean.Result;

import feign.Body;
import feign.Param;
import feign.RequestLine;

public interface Server {

    @RequestLine("POST /command/reception")
    @Body("token={token}&command={command}")
    void receiveCommand(@Param("token") String token, @Param("command") String command);

    @RequestLine("POST /authorization")
    @Body("ss_host={ss_host}&ss_min_port={ss_min_port}&ss_max_port={ss_max_port}&ss_encrypt_method={ss_encrypt_method}&base_url={base_url}&max_flow_bytes={max_flow_bytes}")
    Result<Map<String, Object>> authorize(@Param("ss_host") String ssHost, @Param("ss_min_port") int ssMinPort,
            @Param("ss_max_port") int ssMaxPort, @Param("ss_encrypt_method") String ssEncryptMethod,
            @Param("base_url") String baseUrl, @Param("max_flow_bytes") long maxFlowBytes);

}
