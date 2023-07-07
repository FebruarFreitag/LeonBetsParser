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
public class SportRegionsDto {

    String sportName;
    List<SportRegionDto> regions;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SportRegionsDto(
            @JsonProperty("name") String sportName,
            @JsonProperty("regions") List<SportRegionDto> regions){
        this.sportName = sportName;
        this.regions = regions;
    }
}
