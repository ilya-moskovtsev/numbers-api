package org.nameapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Create a thing that can be offered numbers.
 * <p>
 * At any given time it can tell 3 things:
 * 1) the smallest number it has encountered so far
 * 2) the largest number it has encountered so far
 * 3) the average of all numbers it has encountered so far
 * <p>
 * Prove that it is working correctly.
 * Make it so that a novice programmer cannot use it the wrong way,
 * nor that an evil programmer can break it.
 */
@SpringBootApplication
@EnableSwagger2
public class NumbersApplication {
    public static void main(String[] args) {
        SpringApplication.run(NumbersApplication.class, args);
    }
}
