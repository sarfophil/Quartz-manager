package com.quartz;

import com.quartz.dto.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MessageJob extends QuartzJobBean {
    @Autowired
    private KafkaTemplate<Object,Object> template;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Executing Job with Key {}",jobExecutionContext.getJobDetail().getKey());

        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobTitle = jobDataMap.getString("jobTitle");

       // log.info("Job "+jobExecutionContext.getJobDetail().getKey()+" completed at "+ LocalDateTime.now());

        //Send Message to Kafka to update all microservices listening to this topic
        this.template.send("completedSchedules","Job "+jobExecutionContext.getJobDetail().getKey()+" completed at "+ LocalDateTime.now());

    }
}
