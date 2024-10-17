package com.pocket.batch.job;

import com.pocket.batch.step.AddPlacesProcessor;
import com.pocket.batch.step.AddPlacesReader;
import com.pocket.batch.step.AddPlacesWriter;
import com.pocket.domain.dto.photobooth.PhotoBoothFindResponseDto;
import com.pocket.outbound.entity.photobooth.JpaPhotoBooth;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class AddPlacesJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final AddPlacesReader addPlacesReader;
    private final AddPlacesProcessor addPlacesProcessor;
    private final AddPlacesWriter addPlacesWriter;

    @Value("${batch.kakao.chunk-size}")
    private int chunkSize;

    @Bean
    public Job addPlacesJob() {
        return new JobBuilder("addPlacesJob", jobRepository)
                .start(addPlacesStep())
                .build();
    }

    @Bean
    public Step addPlacesStep() {
        return new StepBuilder("addPlacesStep", jobRepository)
                // 제네릭 타입을 List 대신 PhotoBoothFindResponseDto와 JpaPhotoBooth로 변경
                .<PhotoBoothFindResponseDto, JpaPhotoBooth>chunk(chunkSize, transactionManager)
                .reader(addPlacesReader)
                .processor(addPlacesProcessor)
                .writer(addPlacesWriter)
                .allowStartIfComplete(true)
                .build();
    }

}
