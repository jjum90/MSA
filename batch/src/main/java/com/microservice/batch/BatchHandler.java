package com.microservice.batch;

import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

public class BatchHandler {
    private static final String JOB_NAME = "job";
    private static final String JOB_PARAMETERS_INSTANCE_ID = "InstanceId";
    private static final String JOB_PARAMETERS_TIMESTAMP = "timestamp";

    public static String getJobName(JobExecutionContext jobExecutionContext) {
        return (String) jobExecutionContext.getMergedJobDataMap().get(JOB_NAME);
    }

    public static JobParameters getParameters(JobExecutionContext jobExecutionContext) throws SchedulerException {
        return new JobParametersBuilder()
                .addString(JOB_PARAMETERS_INSTANCE_ID, jobExecutionContext.getScheduler().getSchedulerInstanceId())
                .addLong(JOB_PARAMETERS_TIMESTAMP, System.currentTimeMillis())
                .toJobParameters();
    }
}
