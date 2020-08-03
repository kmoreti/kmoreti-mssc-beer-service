package com.moreti.kmoretimsscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moreti.kmoretimsscbeerservice.web.model.BeerDto;
import com.moreti.kmoretimsscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class})
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "kmoreti.dev", uriPort = 80)
@WebMvcTest(BeerController.class)
class  BeerControllerTest {

    private static final String BEER_API_URL_V1 = "/api/v1/beer";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get(BEER_API_URL_V1 + "/{beerId}", UUID.randomUUID())
                .param("iscold", "yes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // Documentation
                .andDo(document("v1/beer-get",
                        pathParameters(
                            parameterWithName("beerId").description("UUID of desired beer to get.")
                        ),
                        requestParameters(
                                parameterWithName("iscold").description("Is beer cold query parameter.")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Id of Beer"),
                                fieldWithPath("version").description("Version number"),
                                fieldWithPath("createDate").description("Date created"),
                                fieldWithPath("lastModifiedDate").description("Date updated"),
                                fieldWithPath("beerName").description("Beer name"),
                                fieldWithPath("beerStyle").description("Beer style"),
                                fieldWithPath("upc").description("UPC of beer"),
                                fieldWithPath("price").description("Price"),
                                fieldWithPath("quantityOnHand").description("Quantity on hand")
                        )
                ));
    }

    @Test
    void saveNewBeer() throws Exception {
        String beerDtoJson = objectMapper.writeValueAsString(getValidBeerDto());

        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        mockMvc.perform(post(BEER_API_URL_V1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer-new",
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createDate").ignored(),
                                fields.withPath("lastModifiedDate").ignored(),
                                fields.withPath("beerName").description("Name of the beer"),
                                fields.withPath("beerStyle").description("Style of beer"),
                                fields.withPath("upc").description("UPC of beer"),
                                fields.withPath("price").description("Price"),
                                fields.withPath("quantityOnHand").ignored()
                        )
                ));
    }

    @Test
    void updateBeer() throws Exception {

        String beerDtoJson = objectMapper.writeValueAsString(getValidBeerDto());

        mockMvc.perform(put(BEER_API_URL_V1 + "/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .upc(123L)
                .price(new BigDecimal("3.90"))
                .build();
    }

    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}