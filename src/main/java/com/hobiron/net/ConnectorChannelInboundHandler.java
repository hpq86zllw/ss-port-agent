package com.hobiron.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hobiron.http.Server;
import com.hobiron.utils.Global;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

@Component
public class ConnectorChannelInboundHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Autowired
    private Server server;

    private static final Logger logger = LoggerFactory.getLogger(ConnectorChannelInboundHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        String command = packet.content().toString(CharsetUtil.UTF_8);
        logger.info("Receive command {}", command);
        server.receiveCommand(Global.token, command);
    }

}
