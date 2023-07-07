package org.bohdans.LeonBetsParser.service;

import org.bohdans.LeonBetsParser.domain.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
public class StatsPrinterImplTest {

    private final StatsPrinterImpl statsPrinter = new StatsPrinterImpl();

    @Test
    public void shouldPrintBetStats(CapturedOutput output){
        String sportName = "Ice Hockey";
        String finalCup = "Final Cup";
        String league = "NHL";
        String marketName = "Winner";
        var input = BetStatsDto.builder()
                .sportName(sportName)
                .league(league)
                .match(new MatchInfoDto(
                        111L,
                        finalCup,
                        Instant.now(),
                        List.of(new MarketInfoDto(marketName,
                                List.of(new OutcomeEstimateDto(222L, "Draw", 1.6F))))))
                .build();

        statsPrinter.printInfo(List.of(input));

        assertThat(output.getOut()).contains(List.of(sportName, finalCup, league, marketName));
    }

    @Test
    public void shouldSkipEmptyList(CapturedOutput output){
        List<ContainsPrintableInfo> input = Collections.emptyList();
        statsPrinter.printInfo(input);

        assertThat(output.getOut()).containsOnlyWhitespaces();
    }
}