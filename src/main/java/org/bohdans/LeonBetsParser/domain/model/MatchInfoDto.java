package org.bohdans.LeonBetsParser.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.ZoneOffset.UTC;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchInfoDto implements ContainsPrintableInfo {

    long id;
    String matchName;
    LocalDateTime matchStart;
    List<MarketInfoDto> markets;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public MatchInfoDto(
            @JsonProperty("id") long id,
            @JsonProperty("name") String matchName,
            @JsonProperty("kickoff") Instant matchStart,
            @JsonProperty("markets") List<MarketInfoDto> markets){
        this.id = id;
        this.matchName = matchName;
        this.matchStart = LocalDateTime.from(matchStart.atZone(UTC));
        this.markets = markets;
    }

    @Override
    public String toString() {
        return "    "+matchName+", "+matchStart+", "+id+"\n";
    }

    @Override
    public List<? extends ContainsPrintableInfo> getNextLevel() {
        return markets;
    }
}
