package com.hobiron.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hobiron.bean.Result;
import com.hobiron.net.Connector;

@RestController
@RequestMapping("/command/execution")
public class CommandExecution {

    @Autowired
    private Connector connector;

    private static final Logger logger = LoggerFactory.getLogger(CommandExecution.class);

    @PostMapping
    public Result<Map<String, Object>> handlePost(@RequestParam("command") String command) {
        logger.info("command {}", command);
        connector.send(command);
        return Result.newSuccess("Success");
    }

}
