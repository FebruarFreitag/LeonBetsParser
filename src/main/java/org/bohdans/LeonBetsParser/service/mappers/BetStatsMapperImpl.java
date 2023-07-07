package org.bohdans.LeonBetsParser.service.mappers;

import org.bohdans.LeonBetsParser.domain.model.BetStatsDto;
import org.bohdans.LeonBetsParser.domain.model.MatchInfoDto;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BetStatsMapperImpl implements BetStatsMapper {

    @Override
    public List<BetStatsDto> apply(Map<String, Map<String, Optional<MatchInfoDto>>> rawBettingData) {
        var builder = BetStatsDto.builder();
        return rawBettingData.entrySet().stream()
                .peek(rawData -> builder.sportName(rawData.getKey()))
                .map(Map.Entry::getValue)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .map(this::unWrapMatchInfo)
                .filter(Objects::nonNull)
                .map(rawData -> builder.league(rawData.getKey()).match(rawData.getValue()).build())
                .toList();
    }

    private Map.Entry<String, MatchInfoDto> unWrapMatchInfo(Map.Entry<String, Optional<MatchInfoDto>> rawMatchesData) {
        return rawMatchesData.getValue().isPresent()
                ? Map.entry(rawMatchesData.getKey(), rawMatchesData.getValue().get())
                : null;
    }
}
