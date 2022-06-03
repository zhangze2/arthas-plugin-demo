package com.wangji92.arthas.plugin.demo.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 通过实现ApplicationListener接口动态获取tomcat启动端口和访问路径，通过InetAddress类获取主机的ip地址，最后控制台打印项目访问地址
 * @author zz
 * @date
 */
@Component
@Slf4j
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {

    private static final String SWAGGER2_UI_URL = "/swagger-ui.html";

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            InetAddress inetAddress = Inet4Address.getLocalHost();
            // 主机ip地址
            String hostAddress = inetAddress.getHostAddress();
            // tomcat启动端口
            int serverPort = event.getWebServer().getPort();
            // 新增动态path
            String serverPath = event.getApplicationContext().getApplicationName();
            log.info("项目启动成功！访问地址: http://{}:{}{}", hostAddress, serverPort, serverPath);
            log.info("本机地址: http://localhost:{}{}", serverPort, serverPath);
            log.info("本机 Swagger ui 地址: http://localhost:{}{}", serverPort, SWAGGER2_UI_URL);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
