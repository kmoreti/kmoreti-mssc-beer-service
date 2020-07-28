package com.moreti.kmoretimsscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moreti.kmoretimsscbeerservice.web.model.BeerDto;
import com.moreti.kmoretimsscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class  BeerControllerTest {

    private static final String BEER_API_URL_V1 = "/api/v1/beer";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get(BEER_API_URL_V1 + "/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = BeerDto.builder()
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .upc(123L)
                .price(new BigDecimal("3.50"))
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post(BEER_API_URL_V1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        BeerDto beerDto = BeerDto.builder()
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .upc(123L)
                .price(new BigDecimal("3.90"))
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put(BEER_API_URL_V1 + "/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }
}