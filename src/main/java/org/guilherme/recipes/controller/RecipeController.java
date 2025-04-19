package org.guilherme.recipes.controller;

import lombok.RequiredArgsConstructor;
import org.guilherme.recipes.dto.CreateRecipeDto;
import org.guilherme.recipes.dto.RecipeDto;
import org.guilherme.recipes.service.RecipeService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    public ResponseEntity<List<RecipeDto>> getRecipes() {
        List<RecipeDto> recipeDtos = recipeService.getRecipes();
        if (!recipeDtos.isEmpty()) {
            return ResponseEntity.ok().body(recipeDtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable("id") Long id) {
        return recipeService.getRecipe(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") Long id) {
        return recipeService.deleteRecipe(id);
    }

    @PostMapping
    public ResponseEntity<?> createRecipe(@RequestBody CreateRecipeDto dto) {
        return recipeService.createRecipe(dto);
    }

    @GetMapping("/{id}/ingredients")
    public ResponseEntity<?> getIngredients(@PathVariable("id") Long id) {
        return recipeService.getIngredients(id);
    }

    @GetMapping("/{id}/steps")
    public ResponseEntity<?> getSteps(@PathVariable("id") Long id) {
        return recipeService.getSteps(id);
    }
}
