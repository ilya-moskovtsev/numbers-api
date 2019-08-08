package org.nameapi.service;

import org.nameapi.models.NumberDTO;
import org.nameapi.repository.NumbersJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NumbersServiceImpl implements NumbersService {
    @Autowired
    private NumbersJPARepository numbersRepository;

    public NumberDTO offerNumber(NumberDTO numberDTO) {
        return numbersRepository.save(numberDTO);
    }

    public List<NumberDTO> offerNumbers(List<NumberDTO> numbers) {
        ArrayList<NumberDTO> saved = new ArrayList<>();
        numbersRepository.saveAll(numbers).forEach(saved::add);
        return saved;
    }

    public NumberDTO getSmallestNumberDTO() {
        return numbersRepository.findFirstByOrderByNumberAsc();
    }

    public NumberDTO getAverageNumberDTO() {
        return numbersRepository.getAverageNumber();
    }

    public NumberDTO getLargestNumberDTO() {
        return numbersRepository.findFirstByOrderByNumberDesc();
    }
}
