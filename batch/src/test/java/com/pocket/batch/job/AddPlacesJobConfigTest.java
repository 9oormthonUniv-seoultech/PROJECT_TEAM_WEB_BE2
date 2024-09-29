package com.pocket.batch.job;

import com.pocket.batch.step.AddPlacesProcessor;
import com.pocket.batch.step.AddPlacesReader;
import com.pocket.batch.step.AddPlacesWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBatchTest
@SpringBootTest
public class AddPlacesJobConfigTest {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job addPlacesJob;

    @Autowired
    private AddPlacesReader addPlacesReader;

    @Autowired
    private AddPlacesProcessor addPlacesProcessor;

    @Autowired
    private AddPlacesWriter addPlacesWriter;

    @BeforeEach
    void setup() {
        // 테스트용 Job/Step 실행 전 초기화 코드 추가 가능
    }

    @Test
    public void testAddPlacesJob() throws Exception {
        // Job 파라미터 설정
        JobParameters jobParameters = new JobParameters();

        // Job 실행
        JobExecution jobExecution = jobLauncher.run(addPlacesJob, jobParameters);

        // JobExecution 결과 검증
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

        // StepExecution 결과 검증
        for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
            assertThat(stepExecution.getReadCount()).isGreaterThan(0);  // Reader가 데이터를 읽었는지 확인
            assertThat(stepExecution.getWriteCount()).isGreaterThan(0); // Writer가 데이터를 썼는지 확인
        }
    }
}
