package org.bohdans.LeonBetsParser.service;

import org.bohdans.LeonBetsParser.domain.model.BetStatsDto;
import org.bohdans.LeonBetsParser.domain.model.MarketInfoDto;
import org.bohdans.LeonBetsParser.domain.model.MatchInfoDto;
import org.bohdans.LeonBetsParser.domain.model.OutcomeEstimateDto;
import org.bohdans.LeonBetsParser.domain.model.rawdata.EventsPage;
import org.bohdans.LeonBetsParser.domain.model.rawdata.LeagueDto;
import org.bohdans.LeonBetsParser.domain.model.rawdata.SportRegionDto;
import org.bohdans.LeonBetsParser.domain.model.rawdata.SportRegionsDto;
import org.bohdans.LeonBetsParser.service.domain.LeonBetsClientImpl;
import org.bohdans.LeonBetsParser.service.mappers.BetStatsMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BetStatsServiceImpl.class)
@EnableConfigurationProperties
@TestPropertySource("classpath:application.properties")
public class BetStatsServiceImplTest {

    @MockBean
    private LeonBetsClientImpl leonBetsClient;
    @MockBean
    private BetStatsMapperImpl betStatsMapper;
    @MockBean
    private StatsPrinterImpl statsPrinter;
    @Autowired
    private BetStatsServiceImpl betStatsService;

    @Test
    public void shouldFetchAndProcessBetsData(){
        String expectedSportName = "Football";
        String expectedLeagueName = "Community Shield";
        long expectedId = 111L;
        var rawDtoList = List.of(
                new SportRegionsDto(expectedSportName, List.of(
                        new SportRegionDto(List.of(new LeagueDto(expectedId, expectedLeagueName, true)))
                )),
                new SportRegionsDto(expectedSportName, List.of(
                        new SportRegionDto(List.of(new LeagueDto(222L, "1st league", false)))
                )),
                new SportRegionsDto("Table Tennis", List.of(
                        new SportRegionDto(List.of(new LeagueDto(333L, "Setka Cup", true)))
                ))
        );
        String expectedMatchName = "Manchester City - Arsenal";
        String expectedMarketName = "Double Chance";
        String expectedOutcome = "win";
        float expectedCoefficient = 1.6F;
        Instant expectedMatchStartTime = Instant.now();
        var matchDtoList = new EventsPage(List.of(
                new MatchInfoDto(expectedId, expectedMatchName, expectedMatchStartTime,
                        List.of(new MarketInfoDto(expectedMarketName,
                                List.of(new OutcomeEstimateDto(expectedId, expectedOutcome, expectedCoefficient)))))));
        var rawDataMap = Map.of(expectedSportName,
                Map.of(expectedLeagueName, Optional.of(new MatchInfoDto(expectedId, expectedMatchName, expectedMatchStartTime,
                        List.of(new MarketInfoDto(expectedMarketName,
                                List.of(new OutcomeEstimateDto(expectedId, expectedOutcome, expectedCoefficient))))))));
        var printableDtoList = List.of(BetStatsDto.builder()
                .sportName(expectedSportName)
                .league(expectedLeagueName)
                .match(new MatchInfoDto(expectedId, expectedMatchName, expectedMatchStartTime,
                        List.of(new MarketInfoDto(expectedMarketName,
                                List.of(new OutcomeEstimateDto(expectedId, expectedOutcome, expectedCoefficient))))))
                .build());

        when(leonBetsClient.getAllLeaguesInfo()).thenReturn(rawDtoList);
        when(leonBetsClient.getMatchesListByLeague(expectedId)).thenReturn(matchDtoList);
        when(betStatsMapper.apply(rawDataMap)).thenReturn(printableDtoList);

        betStatsService.fetchMatchesBetStats();
        verify(statsPrinter).printInfo(printableDtoList);
    }
}