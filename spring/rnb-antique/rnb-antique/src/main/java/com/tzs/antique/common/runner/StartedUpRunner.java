package com.tzs.antique.common.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Order
@Slf4j
@Component
public class StartedUpRunner implements ApplicationRunner {

    /****
     *  启动结束打印
     */
    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void run(ApplicationArguments args) {
        if (context.isActive()) {
            log.info(" __    ___   _      ___   _     ____ _____  ____ ");
            log.info("/ /`  / / \\ | |\\/| | |_) | |   | |_   | |  | |_  ");
            log.info("\\_\\_, \\_\\_/ |_|  | |_|   |_|__ |_|__  |_|  |_|__ ");
            log.info("                                                      ");
            log.info("系统启动完毕，时间：" + LocalDateTime.now());
        }
    }
}
