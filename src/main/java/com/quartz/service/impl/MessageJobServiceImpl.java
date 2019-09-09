package com.quartz.service.impl;

import com.quartz.MessageJob;
import com.quartz.dto.Schedule;
import com.quartz.service.MessageJobService;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;


@Component
public class MessageJobServiceImpl implements MessageJobService {

    @Override
    public JobDetail buildJobDetail(Schedule schedule){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobTitle",schedule.getJobName());
        jobDataMap.put("schedule",schedule.getScheduledDate());

        return JobBuilder.newJob(MessageJob.class)
                .withIdentity(UUID.randomUUID().toString(),"message-jobs")
                .withDescription("Sending message Job - "+schedule.getJobName())
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    @Override
    public Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName().toString(),"message-triggers")
                .withDescription("Sending Message Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}
