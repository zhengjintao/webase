package com.gmtech.webase.controller.example;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmtech.webase.common.Message;
import com.gmtech.webase.common.ResponseEntity;
import com.gmtech.webase.service.SchedulerJobService;
import com.gmtech.webase.service.bean.ScheduleJob;
import com.gmtech.webase.util.ResponseEntityUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SchedulerJobService schedulerJobService;

    @RequestMapping("/index")
    public ResponseEntity index(HttpServletRequest request){
        logger.info("[JobController] the url path:------------/index----------------");
        logger.info("[JobController] the method index is start......");
        List<ScheduleJob> jobList = schedulerJobService.getAllScheduleJob();
        logger.info("[JobController] the method index is end......");
        return ResponseEntityUtil.success(jobList);
    }
    
    /**
     *获取所有的任务
     * @return
     */
    @RequestMapping("/getAllJobs")
    public ResponseEntity getAllJobs(){
        logger.info("[JobController] the method:getAllJobs! the url path:------------/getAllJobs----------------");
        List<ScheduleJob> jobList = schedulerJobService.getAllScheduleJob();
        logger.info("[JobController] the method:getAllJobs is execution over ");
        return ResponseEntityUtil.success(jobList);
    }

    /**
     * 获取正在执行的任务列表
     * @return
     * @throws SchedulerException
     */
    @RequestMapping("/getRunJob")
    public ResponseEntity getAllRunningJob() throws SchedulerException{
        logger.info("[JobController] the method:getAllRunningJob! the url path:------------/getRunJob----------------");
        List<ScheduleJob> jobList = schedulerJobService.getAllRunningJob();
        logger.info("[JobController] the method:getAllRunningJob is execution over ");
        return ResponseEntityUtil.success(jobList);
    }

    /**
     *更新或者添加一个任务
     * @param scheduleJob
     */
    @RequestMapping("/saveOrUpdate")
    public ResponseEntity addOrUpdateJob(){
    	ScheduleJob scheduleJob = new ScheduleJob();
    	scheduleJob.setId(1);
    	scheduleJob.setJobId("1");
    	scheduleJob.setJobName("hello");
    	scheduleJob.setJobStatus("NORMAL");
    	scheduleJob.setBeanName("testCronJob");
    	scheduleJob.setJobGroup("hello");
    	scheduleJob.setMethodName("execute");
    	scheduleJob.setCronExpression("0/55 * * * * ?");
        logger.info("[JobController] the method addOrUpdateJob is start URL path:/addJob, the param:{}", scheduleJob);
        Message message = Message.failure();
        try {
            schedulerJobService.saveOrUpdate(scheduleJob);
            message = Message.success();
        } catch (Exception e) {
            message.setMsg(e.getMessage());
            logger.error("[JobController] addOrUpdateJob is failure in method:addOrUpdateJob！");
        }
        return ResponseEntityUtil.success(message);
    }

    /**
     *运行一个任务
     * @param jobName
     * @param jobGroup
     */
    @RequestMapping("/runOneJob")
    public ResponseEntity runJob(String jobName, String jobGroup){
        logger.info("[JobController] the url path:------------/runOneJob----------------");
        Message message  = Message.failure();
        try {
            schedulerJobService.runOneJob(jobName,jobGroup);
            message = Message.success();
        } catch (SchedulerException e) {
            message.setMsg(e.getMessage());
            logger.error("[JobController] runOnejob is failure in method:runJob");
        }
        return ResponseEntityUtil.success(message);
    }

    /**
     *停止一个定时任务
     * @param jobName
     * @param jobGroup
     */
    @RequestMapping("/pauseJob")
    public ResponseEntity pauseJob(String jobName, String jobGroup){
        logger.info("[JobController] the url path:------------/runOneJob----------------");
        Message message = Message.failure();
        try {
            schedulerJobService.pauseJob(jobName,jobGroup);
            message = Message.success();
        } catch (SchedulerException e) {
            message.setMsg(e.getMessage());
            logger.error("[JobController] pauseJob is failure in method:pauseJob");
        }
        return ResponseEntityUtil.success(message);
    }

    /**
     * 删除一个定时任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping("/deleteJob")
    public ResponseEntity deleteJob(String jobName,String jobGroup){
        logger.info("[JobController] the url path:------------/runOneJob----------------");
        Message message = Message.failure();
        try {
            schedulerJobService.deleteJob(jobName,jobGroup);
            message = Message.success();
        } catch (SchedulerException e) {
            message.setMsg(e.getMessage());
            logger.error("[JobController] deleteJob is failre in method: deleteJob!");
        }
        return ResponseEntityUtil.success(message);
    }

    /**
     * 重启一个定时任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping("/resumeJob")
    public ResponseEntity resumeJob(String jobName, String jobGroup){
        logger.info("[JobController] the url path:------------/resumeJob----------------");
        Message message = Message.failure();
        try {
            schedulerJobService.resumeJob(jobName,jobGroup);
            message = Message.success();
        } catch (SchedulerException e) {
            message.setMsg(e.getMessage());
            logger.error("[JobController] resumeJob is failre in method: resumeJob!");
        }
        return ResponseEntityUtil.success(message);
    }

}
