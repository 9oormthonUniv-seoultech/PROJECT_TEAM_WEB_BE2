package com.pocket.batch.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import java.util.Properties;

@Getter
@Setter
@ToString
public class JobLauncherRequest {
    private String name;
    private Properties jobParameters;

    public JobParameters getJobParameters() {
        JobParametersBuilder builder = new JobParametersBuilder();
        for (String key : jobParameters.stringPropertyNames()) {
            String value = jobParameters.getProperty(key);
            builder.addString(key, value);
        }
        return builder.toJobParameters();
    }
}
