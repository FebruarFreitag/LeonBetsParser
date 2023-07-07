package org.bohdans.LeonBetsParser.service.mappers;

import org.bohdans.LeonBetsParser.domain.model.BetStatsDto;
import org.bohdans.LeonBetsParser.domain.model.MatchInfoDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public interface BetStatsMapper extends Function<Map<String, Map<String, Optional<MatchInfoDto>>>, List<BetStatsDto>> {


    @Override
    List<BetStatsDto> apply(Map<String, Map<String, Optional<MatchInfoDto>>> rawBettingData);
}
