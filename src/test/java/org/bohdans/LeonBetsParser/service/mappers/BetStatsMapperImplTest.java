package org.bohdans.LeonBetsParser.service.mappers;

import org.bohdans.LeonBetsParser.domain.model.BetStatsDto;
import org.bohdans.LeonBetsParser.domain.model.MarketInfoDto;
import org.bohdans.LeonBetsParser.domain.model.MatchInfoDto;
import org.bohdans.LeonBetsParser.domain.model.OutcomeEstimateDto;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class BetStatsMapperImplTest {

    private final BetStatsMapperImpl betStatsMapper = new BetStatsMapperImpl();

    @Test
    public void shouldMapRawData(){
        String expectedSportName = "Football";
        String expectedLeagueName = "Community Shield";
        long expectedId = 111L;
        String expectedMatchName = "Manchester City - Arsenal";
        String expectedMarketName = "Double Chance";
        String expectedOutcome = "win";
        float expectedCoefficient = 1.6F;
        Instant expectedMatchStartTime = Instant.now();
        var rawDataMap = Map.of(expectedSportName,
                Map.of(expectedLeagueName, Optional.of(new MatchInfoDto(expectedId, expectedMatchName, expectedMatchStartTime,
                        List.of(new MarketInfoDto(expectedMarketName,
                                List.of(new OutcomeEstimateDto(expectedId, expectedOutcome, expectedCoefficient))))))));
        var expected = List.of(BetStatsDto.builder()
                .sportName(expectedSportName)
                .league(expectedLeagueName)
                .match(new MatchInfoDto(expectedId, expectedMatchName, expectedMatchStartTime,
                        List.of(new MarketInfoDto(expectedMarketName,
                                List.of(new OutcomeEstimateDto(expectedId, expectedOutcome, expectedCoefficient))))))
                .build());

        var actual = betStatsMapper.apply(rawDataMap);
        assertThat(actual).isEqualTo(expected);
    }
}