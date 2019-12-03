package com.moreti.kmoretimsscbeerservice.repositories;

import com.moreti.kmoretimsscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
