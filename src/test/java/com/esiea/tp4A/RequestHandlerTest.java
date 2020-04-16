package com.esiea.tp4A;

import com.esiea.tp4A.server.GameApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = GameApplication.class)
@AutoConfigureMockMvc
public class RequestHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void indexReturn() throws Exception{
        this.mockMvc.perform(get("/")).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Welcome to Mars Rover : let's start your game !\n" +
                "Commands :\n" +
                "Create Game : post > /api/session/{gameName}\n" +
                "Create Player : post > /api/session/{gameName}/player/{playerName}\n" +
                "Get Player : get > /api/session/{gameName}/player/{playerName}\n" +
                "Command Player : patch > /api/session/{gameName}/player/{playerName}/{command}")));
    }

    @Test
    public void createGame() throws Exception {
        this.mockMvc.perform(post("/api/session/one"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("name", Matchers.is("one")));;
    }

    @Test
    public void createExistingGame() throws Exception {
        this.mockMvc.perform(post("/api/session/second"));
        this.mockMvc.perform(post("/api/session/second"))
            .andExpect(status().isConflict());
    }

    @Test
    public void createPlayer() throws Exception {
        this.mockMvc.perform(post("/api/session/third"));
        this.mockMvc.perform(post("/api/session/third/player/rover"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("player.name", Matchers.is("rover")));
    }

    @Test
    public void createExistingPlayer() throws Exception {
        this.mockMvc.perform(post("/api/session/fourth"));
        this.mockMvc.perform(post("/api/session/fourth/player/rover"));
        this.mockMvc.perform(post("/api/session/fourth/player/rover"))
            .andExpect(status().isConflict());
    }

    @Test
    public void createPlayerOnUnknownGame() throws Exception {
        this.mockMvc.perform(post("/api/session/unknown/player/rover"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void GetExistingPlayer() throws Exception{
        this.mockMvc.perform(post("/api/session/fifth"));
        this.mockMvc.perform(post("/api/session/fifth/player/rover"));
        this.mockMvc.perform(get("/api/session/fifth/player/rover"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("player.name", Matchers.is("rover")));;
    }

    @Test
    public void GetUnknownPlayer() throws Exception{
        this.mockMvc.perform(post("/api/session/sixth"));
        this.mockMvc.perform(get("/api/session/sixth/player/rover"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void movePlayer() throws Exception{
        this.mockMvc.perform(post("/api/session/seventh"));
        this.mockMvc.perform(post("/api/session/seventh/player/rover"));
        this.mockMvc.perform(patch("/api/session/seventh/player/rover/l"))
            .andExpect(status().isOk());
    }

    @Test
    public void moveUnknownPlayer() throws Exception{
        this.mockMvc.perform(post("/api/session/eighth"));
        this.mockMvc.perform(patch("/api/session/eighth/player/rover/r"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void movePlayerWithUnknownCommand() throws Exception{
        this.mockMvc.perform(post("/api/session/ninth"));
        this.mockMvc.perform(post("/api/session/ninth/player/rover"));
        this.mockMvc.perform(patch("/api/session/ninth/player/rover/a"))
            .andExpect(status().isBadRequest());
    }


}
