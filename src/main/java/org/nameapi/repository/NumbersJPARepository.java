package org.nameapi.repository;

import org.nameapi.models.NumberDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NumbersJPARepository extends CrudRepository<NumberDTO, Long> {
    NumberDTO findFirstByOrderByNumberAsc();

    NumberDTO findFirstByOrderByNumberDesc();

    @Query(value = "SELECT new NumberDTO(avg(number)) from NumberDTO")
    NumberDTO getAverageNumber();
}
