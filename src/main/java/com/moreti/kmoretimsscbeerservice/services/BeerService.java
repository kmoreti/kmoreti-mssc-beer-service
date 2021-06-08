package com.moreti.kmoretimsscbeerservice.services;

import com.moreti.kmoretimsscbeerservice.web.model.BeerDto;
import com.moreti.kmoretimsscbeerservice.web.model.BeerPagedList;
import com.moreti.kmoretimsscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID beerId, Boolean showInvetoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of, Boolean showInventoryOnHand);

    BeerDto getByUpc(String upc, Boolean showInventoryOnHand);
}
