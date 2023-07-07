package org.bohdans.LeonBetsParser.domain.model.rawdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueDto {
    long id;
    String name;
    boolean isTop;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public LeagueDto(
            @JsonProperty("id") long id,
            @JsonProperty("name") String name,
            @JsonProperty("top") boolean isTop){
        this.id = id;
        this.name = name;
        this.isTop = isTop;
    }
}
