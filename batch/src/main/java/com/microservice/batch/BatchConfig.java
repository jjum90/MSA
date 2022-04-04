package com.microservice.batch;

import com.microservice.notification.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.jsr.configuration.xml.JobFactoryBean;
import org.springframework.batch.core.jsr.configuration.xml.StepFactoryBean;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * spring batch 및 Quartz 스케줄링에 필요한 bean 등록
 *
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private static final String JOB_KEY = "job";
    private static final String JOB_NAME = "readMailJob";
    private static final String STEP_KEY = "step";
    private static final int CHUNCK_SIZE = 10;
//    private static final String JOB_NAME= "mailJob";
//    private static final String READ_STEP_NAME = "readMailStep";
//    private static final String SEND_STEP_NAME = "sendMailStep";
    /**
     * Quartz 스케줄링 정보
     * @return
     */
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean () {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setCronExpression("0 0/1 * 1/1 * ? *");
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
        return cronTriggerFactoryBean;
    }

    /**
     * Quartz 스케줄링 Job에 대한 상세 정보
     * @return
     */
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean () {
        Map<String, Object> jobDataAsMap = new HashMap<>();
        jobDataAsMap.put(JOB_KEY, JOB_NAME);
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(BatchExecutor.class);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setJobDataAsMap(jobDataAsMap);
        return jobDetailFactoryBean;
    }

    /**
     * EnableBatchProcessing에서 제공하는 JobRegistry를 받아 세팅
     * @param jobRegistry
     * @return
     */
    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
        return jobRegistryBeanPostProcessor;
    }
    /**
     * jpa를 이용하여 Page만큼 db에서 data 읽어오기
     */
    @Bean
    public Job jpaPagingMailReaderJob() {
        return jobBuilderFactory.get(JOB_KEY)
                .start(jpaPagingMailReaderStep())
                .build();
    }

    @Bean
    public Step jpaPagingMailReaderStep() {
        return stepBuilderFactory.get(STEP_KEY)
                .<Notification, Notification>chunk(CHUNCK_SIZE) // 얼마나 일괄 처리 할 것인지?
                .reader(jpaPagingMailReader())
                .writer(jpaPagingMailWriter())
                .build();

    }

    @Bean
    public JpaPagingItemReader<Notification> jpaPagingMailReader() {
        return new JpaPagingItemReaderBuilder<Notification>()
                .name("jpaPagingMailReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(CHUNCK_SIZE) // 얼마나 읽어들일 것인지?
                .queryString("SELECT n FROM Notification n ORDER BY ASC")
                .build();
    }

    public ItemWriter<Notification> jpaPagingMailWriter() {
        return list -> {
            for (Notification notification : list) {
                log.info("Current Notification info {}", notification);
            }
        };
    }

    /**
     * Spring batch job 세팅
     * @return
     */
//    @Bean
//    public Job mailJob() {
//        return jobBuilderFactory.get(JOB_NAME)
//                .start(readMailJob())
//                .next(sendMailJob())
//                .build();
//    }

    /**
     * Spring batch read step 세팅
     * @return
     */
//    @Bean
//    public Step readMailJob() {
//        return stepBuilderFactory.get(READ_STEP_NAME)
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("readMail");
//                    return RepeatStatus.FINISHED;
//                }).build();
//    }

    /**
     * Spring batch send step 세팅
     * @return
     */
//    @Bean
//    public Step sendMailJob() {
//        return stepBuilderFactory.get(SEND_STEP_NAME)
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("sendMail");
//                    return RepeatStatus.FINISHED;
//                }).build();
//    }

}
