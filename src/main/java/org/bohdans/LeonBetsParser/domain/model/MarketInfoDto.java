package org.bohdans.LeonBetsParser.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketInfoDto implements ContainsPrintableInfo {

    String marketName;
    List<OutcomeEstimateDto> outcomeEstimates;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public MarketInfoDto(
            @JsonProperty("name") String marketName,
            @JsonProperty("runners") List<OutcomeEstimateDto> outcomeEstimates) {
        this.marketName = marketName;
        this.outcomeEstimates = outcomeEstimates;
    }

    @Override
    public String toString() {
        return "        "+marketName+"\n ";
    }

    @Override
    public List<OutcomeEstimateDto> getNextLevel() {
        return outcomeEstimates;
    }
}
