package org.bohdans.LeonBetsParser.controller;

import org.bohdans.LeonBetsParser.service.BetStatsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BetsStatsController.class)
public class ITBetsStatsControllerTestWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BetStatsServiceImpl betStatsService;

    @Test
    public void requestShouldBeOk() throws Exception {
        mockMvc.perform(get("/betStats"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}