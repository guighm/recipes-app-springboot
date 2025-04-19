package org.guilherme.recipes.dto;

import lombok.Data;

@Data
public class RecipeDto {

    private Long id;

    private String title;

    private String description;

    private Integer preparationTime;

    private Integer servings;

    private String difficulty;

    private String imageUrl;

}
