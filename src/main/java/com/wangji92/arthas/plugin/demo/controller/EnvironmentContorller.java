package com.wangji92.arthas.plugin.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zz
 * 环境变量优先级问题排查
 */
@Api(value = "环境变量优先级问题排查", description = "获取静态static spring context 获取所有的环境变量", position = 100, protocols = "http")
@RestController
public class EnvironmentContorller {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Environment environment;



    /**
     * 功能：环境变量优先级问题排查
     * 目前只支持获取静态static spring context 获取所有的环境变量 按照优先级排序
     * ognl -x 3
     * '#springContext=@com.wangji92.arthas.plugin.demo.common.ApplicationContextProvider@context,#allProperties={},#standardServletEnvironment=#propertySourceIterator=#springContext.getEnvironment(),#propertySourceIterator=#standardServletEnvironment.getPropertySources().iterator(),#propertySourceIterator.{#key=#this.getName(),#allProperties.add("
     * "),#allProperties.add("------------------------- name:"+#key),#this.getSource() instanceof java.util.Map
     * ?#this.getSource().entrySet().iterator.{#key=#this.key,#allProperties.add(#key+"="+#standardServletEnvironment.getProperty(#key))}:#{}},#allProperties'
     * -c e374b99
     * <p>
     * 选中 custom.name 获取当前的变量的信息 ognl -x 3
     * '#springContext=@com.wangji92.arthas.plugin.demo.common.ApplicationContextProvider@context,#springContext.getEnvironment().getProperty("custom.name")'
     * -c e374b99
     *
     * @return
     */
    @ApiOperation(
            value = "environmentPriority",
            notes = "按属性名获取配置信息",
            response = Object.class)
    @GetMapping("environmentPriority")
    @ResponseBody
    public String environmentPriority() {
        return applicationContext.getEnvironment().getProperty("custom.name");
    }


    /**
     * 获取spring应用中所有的配置信息
     */
    @ApiOperation(
            value = "obtain.environment",
            notes = "获取spring应用中所有的配置信息：",
            response = Object.class)
    @GetMapping("obtain.environment")
    public Map<String, Map<String, String>> obtainEnvironment() {
        StandardServletEnvironment standardServletEnvironment = (StandardServletEnvironment) environment;
        Map<String, Map<String, String>> map = new HashMap<>(8);
        Iterator<PropertySource<?>> iterator = standardServletEnvironment.getPropertySources().iterator();
        while (iterator.hasNext()) {
            PropertySource<?> source = iterator.next();
            Map<String, String> m = new HashMap<>(128);
            String name = source.getName();
            Object o = source.getSource();
            if (o instanceof Map) {
                for (Map.Entry<String, Object> entry : ((Map<String, Object>) o).entrySet()) {
                    String key = entry.getKey();
                    m.put(key, standardServletEnvironment.getProperty(key));
                }
            }
            map.put(name, m);
        }
        return map;
    }


}
