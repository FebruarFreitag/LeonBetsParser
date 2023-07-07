package org.bohdans.LeonBetsParser.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableConfigurationProperties
@PropertySource("classpath:application.properties")
@ExtendWith({OutputCaptureExtension.class})
public class ITBetStatsServiceImplTest {

    @Autowired
    private BetStatsServiceImpl betStatsService;

    @Value("#{'${testTask.leonBetsParser.supportedSportTypes}'.split(',')}")
    private List<String> supportedSportTypes;

    @Test
    public void shouldFetchAndPrintBetStats(CapturedOutput output){
        betStatsService.fetchMatchesBetStats();
        assertThat(output.getOut())
                .isNotBlank()
                .contains(supportedSportTypes);
    }
}