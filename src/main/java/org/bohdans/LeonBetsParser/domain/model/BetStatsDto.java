package org.bohdans.LeonBetsParser.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Value
@Builder
@Getter
public class BetStatsDto implements ContainsPrintableInfo {

    String sportName;
    String league;
    MatchInfoDto match;

    @Override
    public String toString() {
        return sportName + ", " + league + "\n";
    }

    @Override
    public List<ContainsPrintableInfo> getNextLevel() {
        return List.of(match);
    }
}
