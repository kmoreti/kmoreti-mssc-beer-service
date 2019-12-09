package com.moreti.kmoretimsscbeerservice.web.controller;

import com.moreti.kmoretimsscbeerservice.web.model.BeerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {
        // todo impl
        return new ResponseEntity<>(BeerDto.builder().id(UUID.randomUUID()).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
        // todo impl
        BeerDto savedBeer = BeerDto.builder().id(UUID.randomUUID()).build();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable UUID beerId, @Valid @RequestBody BeerDto beerDto) {
        // todo impl
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
