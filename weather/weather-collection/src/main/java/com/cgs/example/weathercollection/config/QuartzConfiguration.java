package com.cgs.example.weathercollection.config;


import com.cgs.example.weathercollection.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {
    //JobDetail
    @Bean
    public JobDetail weatherDataSyncJobDetail() {

        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("WeatherDataSyncJob")
                .storeDurably().build();
    }

    //Trigger
    @Bean
    public Trigger weatherDataSyncTrigger() {

        //每个1800秒执行job
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1800).repeatForever();

        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail()).withIdentity("weatherDataSyncTrigger").startNow().withSchedule(scheduleBuilder).build();
    }
}
