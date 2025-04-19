package org.guilherme.recipes.dto;

import lombok.Data;

@Data
public class IngredientDto {

    private Long id;

    private String name;

    private String quantity;
}
