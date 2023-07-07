package org.bohdans.LeonBetsParser.controller;

import org.bohdans.LeonBetsParser.service.BetStatsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BetsStatsController {

    private final BetStatsServiceImpl betStatsService;

    @GetMapping("/betStats")
    public void displayBetsStats(){
        betStatsService.fetchMatchesBetStats();
    }
}
