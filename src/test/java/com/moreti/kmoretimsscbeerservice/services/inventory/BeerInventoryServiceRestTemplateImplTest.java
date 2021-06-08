package com.moreti.kmoretimsscbeerservice.services.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Disabled
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");

    @BeforeEach
    void setUp() {

    }

    @Test
    void getOnHandInventory() {
        Integer qoh = beerInventoryService.getOnHandInventory(BEER_1_UUID);

        System.out.println(qoh);
    }
}