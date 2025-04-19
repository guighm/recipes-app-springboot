package org.guilherme.recipes.service;

import lombok.RequiredArgsConstructor;
import org.guilherme.recipes.dto.CreateIngredientDto;
import org.guilherme.recipes.dto.IngredientDto;
import org.guilherme.recipes.model.Ingredient;
import org.guilherme.recipes.model.Recipe;
import org.guilherme.recipes.repository.IngredientRepository;
import org.guilherme.recipes.repository.RecipeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<?> createIngredient(CreateIngredientDto dto) {
        Optional<Recipe> recipe = recipeRepository.findById(dto.getRecipeId());
        if (recipe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Ingredient ingredient = new Ingredient();
        ingredient.setName(dto.getName());
        ingredient.setQuantity(dto.getQuantity());
        ingredient.setRecipe(recipe.get());
        ingredientRepository.save(ingredient);
        URI location = URI.create("/ingredient/" + ingredient.getId());
        return ResponseEntity.created(location).body(modelMapper.map(ingredient, IngredientDto.class));
    }
}
