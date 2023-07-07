package org.bohdans.LeonBetsParser.domain.model.rawdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Value;
import org.bohdans.LeonBetsParser.domain.model.MatchInfoDto;

import java.util.List;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class EventsPage {

    List<MatchInfoDto> matches;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public EventsPage(
            @JsonProperty("events") List<MatchInfoDto> matches){
        this.matches = matches;
    }
}
