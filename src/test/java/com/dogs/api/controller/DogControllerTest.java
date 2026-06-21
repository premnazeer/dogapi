package com.dogs.api.controller;

import com.dogs.api.dto.DogRequest;
import com.dogs.api.dto.DogResponse;
import com.dogs.api.service.DogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DogController.class)
class DogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private DogService service;

    @Test
    void shouldCreateDog() throws Exception {

        DogResponse response = new DogResponse();
        response.setId(1L);
        response.setName("Rex");

        when(service.create(any()))
                .thenReturn(response);

        DogRequest request = new DogRequest();
        request.setName("Rex");

        mockMvc.perform(
                        post("/api/dogs/dogs")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Rex"));
    }

    @Test
    void shouldGetDog() throws Exception {

        DogResponse response = new DogResponse();
        response.setId(1L);
        response.setName("Rex");

        when(service.getDog(1L))
                .thenReturn(response);

        mockMvc.perform(
                        get("/api/dogs/dogs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Rex"));
    }

    @Test
    void shouldDeleteDog() throws Exception {

        mockMvc.perform(
                        delete("/api/dogs/dogs/1"))
                .andExpect(status().isNoContent());
    }
}
