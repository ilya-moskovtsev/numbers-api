package org.nameapi.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the statistics")
public class Stats {
    @ApiModelProperty(notes = "the smallest number encountered so far")
    private NumberDTO smallest;
    @ApiModelProperty(notes = "the average of all numbers encountered so far")
    private NumberDTO average;
    @ApiModelProperty(notes = "the largest number encountered so far")
    private NumberDTO largest;

    public Stats() {
    }

    public Stats(NumberDTO smallest, NumberDTO average, NumberDTO largest) {
        this.smallest = smallest;
        this.largest = largest;
        this.average = average;
    }

    public NumberDTO getSmallest() {
        return smallest;
    }

    public void setSmallest(NumberDTO smallest) {
        this.smallest = smallest;
    }

    public NumberDTO getAverage() {
        return average;
    }

    public void setAverage(NumberDTO average) {
        this.average = average;
    }

    public NumberDTO getLargest() {
        return largest;
    }

    public void setLargest(NumberDTO largest) {
        this.largest = largest;
    }
}
