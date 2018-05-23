package com.truck.api.transit.controller;

import com.truck.api.transit.model.Transit;
import com.truck.api.transit.service.TransitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransitService transitService;

    @Test
    public void shouldParseJson_ToTransitPOST() throws Exception {
        doNothing().when(transitService).addTransit(isA(Transit.class));
        ClassLoader classLoader = getClass().getClassLoader();
        Path path = Paths.get(Objects.requireNonNull(classLoader.getResource("transit.txt")).toURI());
        String json = Files.lines(path).collect(Collectors.joining("\n"));

        this.mockMvc.perform(post("/transits")
                .content(json).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("2018-03-15")));
    }
}