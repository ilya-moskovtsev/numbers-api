package org.nameapi.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class NumberDTO {
    @Id
    @GeneratedValue
    @ApiModelProperty(hidden = true)
    private Long id;
    private Double number;

    public NumberDTO() {
    }

    public NumberDTO(Double number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }
}
