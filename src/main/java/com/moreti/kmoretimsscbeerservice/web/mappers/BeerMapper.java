package com.moreti.kmoretimsscbeerservice.web.mappers;

import com.moreti.kmoretimsscbeerservice.domain.Beer;
import com.moreti.kmoretimsscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
