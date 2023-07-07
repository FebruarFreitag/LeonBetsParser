package org.bohdans.LeonBetsParser.domain.model.rawdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Value
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SportRegionDto {

    List<LeagueDto> leagues;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SportRegionDto(
            @JsonProperty("leagues") List<LeagueDto> leagues){
        this.leagues = leagues;
    }
}
