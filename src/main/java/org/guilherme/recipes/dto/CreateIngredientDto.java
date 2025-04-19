package org.guilherme.recipes.dto;

import lombok.Data;

@Data
public class CreateIngredientDto {

    private Long recipeId;
    private String name;

    private String quantity;
}
