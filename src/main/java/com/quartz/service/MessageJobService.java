package com.quartz.service;

import com.quartz.dto.Schedule;
import org.quartz.JobDetail;
import org.quartz.Trigger;

import java.time.ZonedDateTime;

public interface MessageJobService {
    JobDetail buildJobDetail(Schedule schedule);

    Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt);
}
