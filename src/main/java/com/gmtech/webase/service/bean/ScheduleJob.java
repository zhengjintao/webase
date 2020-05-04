package com.gmtech.webase.service.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleJob implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String jobId;

    private String jobName;

    private String jobStatus;

    private String jobGroup;

    private String cronExpression;

    private String description;

    private String beanName;

    private String methodName;
}