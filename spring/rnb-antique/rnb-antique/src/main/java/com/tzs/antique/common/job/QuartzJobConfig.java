//package com.tzs.hbook.common.job;
//
//
//import org.quartz.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class QuartzJobConfig {
//    private String Cron = "0 0 1 ? * *";
//
//    @Bean
//    public JobDetail jobDetail(){
//        //return JobBuilder.newJob(EndCourseJob.class).withIdentity("trigger1").storeDurably().build();
//        return null;
//    }
//
//    @Bean
//    public Trigger trigger(){
//        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(Cron);
//        return TriggerBuilder.newTrigger().forJob(jobDetail())
//                .withIdentity("Trigger")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
//
//}
