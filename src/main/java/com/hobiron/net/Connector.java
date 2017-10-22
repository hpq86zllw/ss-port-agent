package com.hobiron.net;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hobiron.bean.Result;
import com.hobiron.conf.AgentConfig;
import com.hobiron.http.Server;
import com.hobiron.utils.Global;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

@Component
public class Connector {

    @Autowired
    private AgentConfig agentConfig;
    private Bootstrap bootstrap = new Bootstrap();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    @Autowired
    private ConnectorChannelInboundHandler inboundHandler;
    private Channel channel;
    private InetSocketAddress ssManagerSocketAddress;
    @Autowired
    private Server server;

    private static final Logger logger = LoggerFactory.getLogger(Connector.class);

    @PostConstruct
    public void open() throws InterruptedException {
        logger.info("Open connector");
        initConnector();
        authorize();
    }

    private void initConnector() throws InterruptedException {
        this.bootstrap.group(this.workerGroup);
        this.bootstrap.channel(NioDatagramChannel.class);
        this.bootstrap.handler(inboundHandler);
        this.channel = this.bootstrap.bind(0).sync().channel();
        this.ssManagerSocketAddress = new InetSocketAddress(agentConfig.getSsManagerHost(),
                agentConfig.getSsManagerPort());
    }

    @SuppressWarnings("unchecked")
    private void authorize() {

        Result<Map<String, Object>> result = server.authorize(agentConfig.getSsHost(), agentConfig.getSsMinPort(),
                agentConfig.getSsMaXPort(), agentConfig.getSsEncryptMethod(), agentConfig.getBaseUrl(),
                agentConfig.getMaxFlowBytes());

        logger.info("Result {}", result);
        Global.token = (String) result.getData().get("token");
        if (StringUtils.isEmpty(Global.token)) {
            throw new IllegalArgumentException("Token is empty");
        }

        List<String> commands = (List<String>) result.getData().get("commands");
        for (String command : commands) {
            send(command);
        }

    }

    @PreDestroy
    public void close() {
        logger.info("Close connector");
        this.workerGroup.shutdownGracefully();
    }

    public void send(String msg) {
        logger.info("Send msg {}", msg);
        this.channel
                .writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(msg.getBytes()), this.ssManagerSocketAddress));
    }

}
