package com.quartz.api;


import com.quartz.dto.Schedule;
import com.quartz.service.MessageJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@Slf4j
public class ResourceController {

    @Autowired
    private MessageJobService messageJobService;

    @Autowired
    private Scheduler scheduler;

    @GetMapping("/schedule")
    public ResponseEntity<?> sendMessage(@RequestParam("job") String jobTitle,
                                         @RequestParam("jobDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime jobTime) {
        try {
            ZonedDateTime zonedDateTime = ZonedDateTime.of(jobTime, ZoneId.systemDefault());
            JobDetail jobDetail = messageJobService.buildJobDetail(new Schedule(jobTitle, jobTime));
            Trigger trigger = messageJobService.buildJobTrigger(jobDetail, zonedDateTime);
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("Scheduler", "Unable to schedule {}");
        }

        return ResponseEntity.accepted().build();
    }

}
