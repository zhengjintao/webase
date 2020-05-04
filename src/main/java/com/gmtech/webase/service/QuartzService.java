package com.gmtech.webase.service;

public interface QuartzService {
    void executeTask(String beanName,String methodName);

    void executeTask(String beanName);
}
