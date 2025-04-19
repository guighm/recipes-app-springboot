package org.guilherme.recipes.service;

import lombok.RequiredArgsConstructor;
import org.guilherme.recipes.dto.CreateRecipeDto;
import org.guilherme.recipes.dto.IngredientDto;
import org.guilherme.recipes.dto.RecipeDto;
import org.guilherme.recipes.dto.StepDto;
import org.guilherme.recipes.model.Ingredient;
import org.guilherme.recipes.model.Recipe;
import org.guilherme.recipes.model.Step;
import org.guilherme.recipes.model.User;
import org.guilherme.recipes.repository.IngredientRepository;
import org.guilherme.recipes.repository.RecipeRepository;
import org.guilherme.recipes.repository.StepRepository;
import org.guilherme.recipes.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final StepRepository stepRepository;
    private final ModelMapper modelMapper;

    public List<RecipeDto> getRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes
                .stream()
                .map(recipe -> modelMapper.map(recipe, RecipeDto.class))
                .toList();
    }

    public ResponseEntity<?> deleteRecipe(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            recipeRepository.delete(recipe.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> createRecipe(CreateRecipeDto dto) {
        Recipe recipe = modelMapper.map(dto, Recipe.class);
        Optional<User> user = userRepository.findByName("admin");
        recipe.setCreatedAt(Instant.now());
        if (user.isPresent()) {
            recipe.setUser(user.get());
            Recipe newRecipe = recipeRepository.save(recipe);
            URI location = URI.create("/recipe/" + newRecipe.getId());
            return ResponseEntity.created(location).body(modelMapper.map(newRecipe, RecipeDto.class));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> getRecipe(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            RecipeDto recipeDto = modelMapper.map(recipe.get(), RecipeDto.class);
            return ResponseEntity.ok(recipeDto);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> getIngredients(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Ingredient> ingredients = ingredientRepository.findByRecipe(recipe.get());
        List<IngredientDto> ingredientDtos = ingredients
                .stream()
                .map(ingredient -> modelMapper.map(ingredient, IngredientDto.class))
                .toList();

        return ResponseEntity.ok(ingredientDtos);
    }

    public ResponseEntity<?> getSteps(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Step> steps = stepRepository.findByRecipe(recipe.get());
        List<StepDto> stepDtos = steps
                .stream()
                .map(step -> modelMapper.map(step, StepDto.class))
                .toList();

        return ResponseEntity.ok(stepDtos);
    }
}
