package com.gmtech.webase.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gmtech.webase.service.QuartzService;
import com.gmtech.webase.util.ApplicationContextUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service("quartzService")
public class QuartzServiceImpl implements QuartzService {

    private static final Logger logger = LoggerFactory.getLogger(QuartzServiceImpl.class);
    private static final String METHODNAME = "execute";

    @Override
    public void executeTask(String beanName, String methodName) {
        Object object = ApplicationContextUtil.getBean(beanName);
        try {
            logger.info("[QuartzServiceImpl] 反射调beanName:{},methodName:{}法开始.........",beanName,methodName);
            if (beanName.contains("\\.")){
                Class clazz = Class.forName(beanName);
                Object cronJob =  ApplicationContextUtil.getBean(clazz);
                Method method1 = clazz.getMethod(methodName);
                method1.invoke(cronJob);
            }else {
                Method method = object.getClass().getMethod(methodName);
                method.invoke(object);
            }

        } catch (Exception e) {
            logger.error("[QuartzServiceImpl] method invoke error,beanName:{},methodName:{}",beanName,methodName);
            return;
        }
        logger.info("[QuartzServiceImpl] 反射调beanName:{},methodName:{}法结束.........",beanName,methodName);

    }

    @Override
    public void executeTask(String beanName) {
        executeTask(beanName, METHODNAME);
    }
}
