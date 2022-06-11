package com.wangji92.arthas.plugin.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 观察用 logger 命令修改 logger 的等级效果
 *
 * @author 汪小哥
 * @date 18-04-2020
 */
@Slf4j
@Component
public class LoggerDemo {

    /**
     *
     * logger --name com.wangji92.arthas.plugin.demo.controller.LoggerDemo --level trace -c 18b4aac2
     * 第一次延迟1秒后执行，之后按fixedRate的规则每 60 秒执行一次
     */
    @Scheduled(initialDelay=1000, fixedRate=60000)
    public void sample() {
        log.trace("....trace");
        log.debug("......debug");
        log.warn("........info");
        log.info("..........warn ");
        log.error("............error");
    }
}
