package com.hobiron.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {

    @Value("${port.agent.ss.manager.host}")
    private String ssManagerHost;
    @Value("${port.agent.ss.manager.port}")
    private int ssManagerPort;
    @Value("${port.agent.ss.min-port}")
    private int ssMinPort;
    @Value("${port.agent.ss.max-port}")
    private int ssMaXPort;
    @Value("${port.agent.ss.encrypt-method}")
    private String ssEncryptMethod;
    @Value("${port.agent.ss.host}")
    private String ssHost;
    @Value("${porr.agent.base-url}")
    private String baseUrl;
    @Value("${port.agent.max-flow-bytes}")
    private long maxFlowBytes;

    public String getSsManagerHost() {
        return ssManagerHost;
    }

    public int getSsManagerPort() {
        return ssManagerPort;
    }

    public int getSsMinPort() {
        return ssMinPort;
    }

    public int getSsMaXPort() {
        return ssMaXPort;
    }

    public String getSsEncryptMethod() {
        return ssEncryptMethod;
    }

    public String getSsHost() {
        return ssHost;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public long getMaxFlowBytes() {
        return maxFlowBytes;
    }

}
