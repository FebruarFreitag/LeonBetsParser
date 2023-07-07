package org.bohdans.LeonBetsParser.service.domain;

import lombok.RequiredArgsConstructor;
import org.bohdans.LeonBetsParser.domain.model.rawdata.EventsPage;
import org.bohdans.LeonBetsParser.domain.model.rawdata.SportRegionsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeonBetsClientImpl implements LeonBetsClient {

    private final RestTemplate restTemplate;

    @Value("${testTask.leonBetsParser.baseUrl}")
    private String baseUrl;
    @Value("${testTask.leonBetsParser.leaguesListUrlSuffix}")
    private String leaguesListUrlSuffix;
    @Value("${testTask.leonBetsParser.gamesListUrlSuffixTemplate}")
    private String gamesListUrlSuffixTemplate;

    public List<SportRegionsDto> getAllLeaguesInfo() {
        try {
            var leaguesListUri = UriComponentsBuilder
                    .fromHttpUrl(baseUrl)
                    .pathSegment(leaguesListUrlSuffix)
                    .build().toUriString();
            var responseType = new ParameterizedTypeReference<List<SportRegionsDto>>() {
            };
            return restTemplate.exchange(leaguesListUri, HttpMethod.GET, null, responseType).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public EventsPage getMatchesListByLeague(long leagueId) {
        try {
            var matchesListUri = UriComponentsBuilder
                    .fromHttpUrl(baseUrl)
                    .pathSegment(String.format(gamesListUrlSuffixTemplate, leagueId))
                    .build().toUriString();
            return restTemplate.exchange(matchesListUri, HttpMethod.GET, null, EventsPage.class).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return new EventsPage(Collections.emptyList());
        }
    }
}
