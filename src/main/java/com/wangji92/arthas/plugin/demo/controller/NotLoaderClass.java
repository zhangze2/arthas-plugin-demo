package com.wangji92.arthas.plugin.demo.controller;

import lombok.extern.slf4j.Slf4j;

/**
 * 一个没有被加载的类
 * 1. # search all the classes loaded by jvm
 *    sc -d com.wangji92.arthas.plugin.demo.controller.NotLoaderClass
 * 2. classloader -l  find classloader hash value
 * 3. classloader --load com.wangji92.arthas.plugin.demo.controller.NotLoaderClass -c 1d55258e
 * @author 汪小哥
 * @date 15-08-2020
 */
@Slf4j
public class NotLoaderClass {
    /**
     * 4. 获取静态的值 getstatic com.wangji92.arthas.plugin.demo.controller.NotLoaderClass NO_LOAD -x 3
     * */
    public static String NO_LOAD = "not load";
}
