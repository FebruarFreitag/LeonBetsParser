package org.bohdans.LeonBetsParser.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutcomeEstimateDto implements ContainsPrintableInfo {

    long id;
    String outcomeName;
    float coefficient;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public OutcomeEstimateDto(
            @JsonProperty("id") long id,
            @JsonProperty("name") String outcomeName,
            @JsonProperty("price") float coefficient) {
        this.id = id;
        this.outcomeName = outcomeName;
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "            "+outcomeName+", "+coefficient+", "+id+"\n";
    }

    @Override
    public List<ContainsPrintableInfo> getNextLevel() {
        return List.of(this);
    }
}
