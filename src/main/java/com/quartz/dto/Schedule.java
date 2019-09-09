package com.quartz.dto;

import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;


public class Schedule {

    private String jobName;

    private LocalDateTime scheduledDate;

    public Schedule(){
        super();
    }


    public Schedule(String jobName,LocalDateTime scheduledDate){
        this.scheduledDate = scheduledDate;
        this.jobName = jobName;
    }


    @Override
    public String toString(){
        return "[Job Name: "+ jobName +"\n Job Scheduled Date: "+ scheduledDate +" \n  Job Complete Date: "+ LocalDateTime.now()+"]";
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
}
