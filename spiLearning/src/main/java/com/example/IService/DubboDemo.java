package com.example.IService;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * @Author shizq18
 * @Date 2023/5/29
 * @Description
 */
public class DubboDemo {
    public static void main(String[] args) {
        ExtensionLoader<DubboSPI> extensionLoader = ExtensionLoader.getExtensionLoader(DubboSPI.class);
        DubboSPI imageDubbo = extensionLoader.getExtension("imageDubbo");
        imageDubbo.sayHello();
        DubboSPI textDubbo = extensionLoader.getExtension("textDubbo");
        textDubbo.sayHello();
    }
}
