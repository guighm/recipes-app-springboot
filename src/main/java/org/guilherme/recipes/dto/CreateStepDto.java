package org.guilherme.recipes.dto;

import lombok.Data;

@Data
public class CreateStepDto {

    private Long recipeId;

    private Integer stepNumber;

    private String description;
}
