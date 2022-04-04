package com.microservice.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import java.time.LocalDateTime;

/**
 * Quartz 스케줄링 및 batch 실행
 */
@RequiredArgsConstructor
@Slf4j
public class BatchExecutor implements org.quartz.Job {
    private final JobLauncher jobLauncher;
    private final JobLocator jobLocator;
//    private static final String JOB_NAME_KEY = "job";
//    private static final String JOB_PARAMETERS_INSTANCE_ID_KEY = "InstanceId";
//    private static final String JOB_PARAMETERS_TIMESTAMP_KEY = "timestamp";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Start quartz scheduler in {}", LocalDateTime.now());
        try {
//            JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
//            String jobName = (String) jobDataMap.get(JOB_NAME_KEY);
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addString(JOB_PARAMETERS_INSTANCE_ID_KEY, jobExecutionContext.getScheduler().getSchedulerInstanceId())
//                    .addLong(JOB_PARAMETERS_TIMESTAMP_KEY, System.currentTimeMillis())
//                    .toJobParameters();
            String jobName = BatchHandler.getJobName(jobExecutionContext);
            log.info("Job name info : {}", jobName);
            Job job = jobLocator.getJob(jobName);
            JobParameters jobParameters = BatchHandler.getParameters(jobExecutionContext);
            log.info("Job parameters info : {}", jobParameters);
            jobLauncher.run(job, jobParameters);
        } catch (NoSuchJobException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException | SchedulerException e) {
            e.printStackTrace();
        }
        log.info("End quartz scheduler {}", LocalDateTime.now());
    }
}
