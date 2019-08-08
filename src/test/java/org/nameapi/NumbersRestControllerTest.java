package org.nameapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.nameapi.controller.NumbersRestController;
import org.nameapi.models.NumberDTO;
import org.nameapi.service.NumbersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NumbersRestController.class)
public class NumbersRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private NumbersServiceImpl numbersService;

    @BeforeEach
    public void setUp() {
        reset(numbersService);
    }

    @Value(value = "${numbers.http.auth-token-header-name}")
    private String principalRequestHeader;

    @Value(value = "${numbers.http.auth-token}")
    private String principalRequestValue;

    @Test
    public void givenNumbers_whenGetStats_thenReturnJsonWithSmallestAverageAndLargestNumbers() throws Exception {
        given(numbersService.getSmallestNumberDTO()).willReturn(new NumberDTO(1d));
        given(numbersService.getAverageNumberDTO()).willReturn(new NumberDTO(2d));
        given(numbersService.getLargestNumberDTO()).willReturn(new NumberDTO(3d));

        mvc.perform(get("/api/stats")
                .contentType(MediaType.APPLICATION_JSON)
                .header(principalRequestHeader, principalRequestValue))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.smallest.number", is(1d)))
                .andExpect(jsonPath("$.average.number", is(2d)))
                .andExpect(jsonPath("$.largest.number", is(3d)));
    }

    @Test
    public void givenNumbers_whenGetStatsSmallest_thenReturnJsonWithSmallestNumber() throws Exception {
        given(numbersService.getSmallestNumberDTO()).willReturn(new NumberDTO(1d));
        given(numbersService.getAverageNumberDTO()).willReturn(new NumberDTO(2d));
        given(numbersService.getLargestNumberDTO()).willReturn(new NumberDTO(3d));

        mvc.perform(get("/api/stats/smallest")
                .contentType(MediaType.APPLICATION_JSON)
                .header(principalRequestHeader, principalRequestValue))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(1d)));
    }

    @Test
    public void givenNumbers_whenGetStatsAverage_thenReturnJsonWithAverageNumber() throws Exception {
        given(numbersService.getSmallestNumberDTO()).willReturn(new NumberDTO(1d));
        given(numbersService.getAverageNumberDTO()).willReturn(new NumberDTO(2d));
        given(numbersService.getLargestNumberDTO()).willReturn(new NumberDTO(3d));

        mvc.perform(get("/api/stats/average")
                .contentType(MediaType.APPLICATION_JSON)
                .header(principalRequestHeader, principalRequestValue))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(2d)));
    }

    @Test
    public void givenNumbers_whenGetStatsLargest_thenReturnJsonWithLargestNumber() throws Exception {
        given(numbersService.getSmallestNumberDTO()).willReturn(new NumberDTO(1d));
        given(numbersService.getAverageNumberDTO()).willReturn(new NumberDTO(2d));
        given(numbersService.getLargestNumberDTO()).willReturn(new NumberDTO(3d));

        mvc.perform(get("/api/stats/largest")
                .contentType(MediaType.APPLICATION_JSON)
                .header(principalRequestHeader, principalRequestValue))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(3d)));
    }

    @Test
    public void whenPostOfferNumber_thenOfferNumber() throws Exception {
        NumberDTO numberDTO = new NumberDTO(4d);
        given(numbersService.offerNumber(Mockito.any())).willReturn(numberDTO);
        String numberJson = new ObjectMapper().writeValueAsString(numberDTO);

        mvc.perform(post("/api/offer/number")
                .contentType(MediaType.APPLICATION_JSON)
                .header(principalRequestHeader, principalRequestValue)
                .content(numberJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.number", is(numberDTO.getNumber())));
        verify(numbersService, VerificationModeFactory.times(1)).offerNumber(Mockito.any());
    }

    @Test
    public void whenPostOfferNumbers_thenOfferNumbers() throws Exception {
        List<NumberDTO> numberDTOs = List.of(new NumberDTO(5d), new NumberDTO(6d), new NumberDTO(7d));
        given(numbersService.offerNumbers(Mockito.any())).willReturn(numberDTOs);
        String numbersJson = new ObjectMapper().writeValueAsString(numberDTOs);

        mvc.perform(post("/api/offer/numbers")
                .contentType(MediaType.APPLICATION_JSON)
                .header(principalRequestHeader, principalRequestValue)
                .content(numbersJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].number", is(5d)))
                .andExpect(jsonPath("$[1].number", is(6d)))
                .andExpect(jsonPath("$[2].number", is(7d)));
        verify(numbersService, VerificationModeFactory.times(1)).offerNumbers(Mockito.any());
    }
}