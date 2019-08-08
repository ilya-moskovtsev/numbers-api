package org.nameapi.controller;

import io.swagger.annotations.ApiOperation;
import org.nameapi.models.NumberDTO;
import org.nameapi.models.Stats;
import org.nameapi.service.NumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NumbersRestController {
    @Autowired
    private NumbersService numbersService;

    @GetMapping("/stats")
    @ApiOperation(value = "Get stats that contains the smallest number, the average number and the largest number of all numbers encountered so far")
    public Stats stats() {
        return new Stats(
                numbersService.getSmallestNumberDTO(),
                numbersService.getAverageNumberDTO(),
                numbersService.getLargestNumberDTO()
        );
    }

    @GetMapping("/stats/smallest")
    @ApiOperation(value = "Get the smallest number of all numbers encountered so far")
    public NumberDTO getSmallestNumber() {
        return numbersService.getSmallestNumberDTO();
    }

    @GetMapping("/stats/average")
    @ApiOperation(value = "Get the average number of all numbers encountered so far")
    public NumberDTO getAverageNumber() {
        return numbersService.getAverageNumberDTO();
    }

    @GetMapping("/stats/largest")
    @ApiOperation(value = "Get the largest number of all numbers encountered so far")
    public NumberDTO getLargestNumber() {
        return numbersService.getLargestNumberDTO();
    }

    @PostMapping("/offer/number")
    @ApiOperation(value = "Offer a number")
    public ResponseEntity<NumberDTO> offer(@Valid @RequestBody NumberDTO numberDTO) {
        return new ResponseEntity<>(
                numbersService.offerNumber(numberDTO),
                HttpStatus.CREATED);
    }

    @PostMapping("/offer/numbers")
    @ApiOperation(value = "Offer numbers")
    public ResponseEntity<List<NumberDTO>> offerNumbers(@Valid @RequestBody List<NumberDTO> numbers) {
        return new ResponseEntity<>(
                numbersService.offerNumbers(numbers),
                HttpStatus.CREATED);
    }
}
