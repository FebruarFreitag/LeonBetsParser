package org.bohdans.LeonBetsParser.service;

import lombok.RequiredArgsConstructor;
import org.bohdans.LeonBetsParser.domain.model.ContainsPrintableInfo;
import org.bohdans.LeonBetsParser.domain.model.MatchInfoDto;
import org.bohdans.LeonBetsParser.domain.model.rawdata.LeagueDto;
import org.bohdans.LeonBetsParser.domain.model.rawdata.SportRegionsDto;
import org.bohdans.LeonBetsParser.service.domain.LeonBetsClient;
import org.bohdans.LeonBetsParser.service.mappers.BetStatsMapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BetStatsServiceImpl implements BetStatsService {

    private final LeonBetsClient leonBetsClient;
    private final BetStatsMapperImpl betStatsMapperImpl;
    private final StatsPrinterImpl statsPrinterImpl;

    @Value("#{'${testTask.leonBetsParser.supportedSportTypes}'.split(',')}")
    private List<String> supportedSportTypes;

    public void fetchMatchesBetStats() {
        var unfilteredLeaguesData = leonBetsClient.getAllLeaguesInfo();
        var filteredLeagues = unfilteredLeaguesData.stream()
                .filter(this::sportIsInList)
                .collect(Collectors.toMap(SportRegionsDto::getSportName, this::extractTopLeaguesBySportType,
                        (a,b)-> Stream.of(a,b)
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList())));
        var combinedRawData = filteredLeagues.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, this::extractMatchInfo));
        List<? extends ContainsPrintableInfo> outputBettingStats = betStatsMapperImpl.apply(combinedRawData);
        statsPrinterImpl.printInfo(outputBettingStats);
    }

    private Map<String, Optional<MatchInfoDto>> extractMatchInfo(Map.Entry<String, List<LeagueDto>> regionMathesEntr) {
        return regionMathesEntr.getValue().stream()
                .collect(Collectors.toMap(LeagueDto::getName, this::extractFirstMatchFromList, (a, b) -> a));
    }

    private Optional<MatchInfoDto> extractFirstMatchFromList(LeagueDto league) {
        return leonBetsClient.getMatchesListByLeague(league.getId()).getMatches().stream()
                .max(Comparator.comparingLong(MatchInfoDto::getId));
    }

    private List<LeagueDto> extractTopLeaguesBySportType(SportRegionsDto sportRegionsDto) {
        return sportRegionsDto.getRegions().stream().flatMap(sportRegionDto -> sportRegionDto.getLeagues().stream()
                .filter(LeagueDto::isTop)).collect(Collectors.toList());
    }

    private boolean sportIsInList(SportRegionsDto sportRegionsDto) {
        return supportedSportTypes.stream()
                .anyMatch(sportType -> sportType.equals(sportRegionsDto.getSportName()));
    }
}
