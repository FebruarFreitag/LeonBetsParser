package org.bohdans.LeonBetsParser.service.domain;

import org.bohdans.LeonBetsParser.domain.model.rawdata.EventsPage;
import org.bohdans.LeonBetsParser.domain.model.rawdata.SportRegionsDto;

import java.util.List;

public interface LeonBetsClient {

    List<SportRegionsDto> getAllLeaguesInfo();
    EventsPage getMatchesListByLeague(long leagueId);
}
