package org.nameapi.service;

import org.nameapi.models.NumberDTO;

import java.util.List;

public interface NumbersService {
    NumberDTO offerNumber(NumberDTO numberDTO);

    List<NumberDTO> offerNumbers(List<NumberDTO> numbers);

    NumberDTO getSmallestNumberDTO();

    NumberDTO getAverageNumberDTO();

    NumberDTO getLargestNumberDTO();
}
