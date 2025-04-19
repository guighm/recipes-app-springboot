package org.guilherme.recipes.dto;

import lombok.Data;

@Data
public class StepDto {

    private Long id;

    private Integer stepNumber;

    private String description;
}
