package org.bohdans.LeonBetsParser.service;

import org.bohdans.LeonBetsParser.service.domain.LeonBetsClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties
public class ITLeonBetsClientTest {

    @Autowired
    private LeonBetsClient leonBetsClient;

    @Test
    public void shouldReturnLeaguesList(){
        var response = leonBetsClient.getAllLeaguesInfo();
        assertThat(response).isNotEmpty();
        System.out.println(response.get(0));
    }

    @Test
    public void shouldReturnMatchesList(){
        var leaguesResponse = leonBetsClient.getAllLeaguesInfo();
        assertThat(leaguesResponse).isNotEmpty();
        var leagueId = leaguesResponse.get(0).getRegions().get(0).getLeagues().get(0).getId();
        var response = leonBetsClient.getMatchesListByLeague(leagueId);
        assertThat(response.getMatches()).isNotEmpty();
        System.out.println(response.getMatches().get(0));
    }
}