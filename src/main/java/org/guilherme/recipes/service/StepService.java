package org.guilherme.recipes.service;

import lombok.RequiredArgsConstructor;
import org.guilherme.recipes.dto.CreateStepDto;
import org.guilherme.recipes.dto.StepDto;
import org.guilherme.recipes.model.Recipe;
import org.guilherme.recipes.model.Step;
import org.guilherme.recipes.repository.RecipeRepository;
import org.guilherme.recipes.repository.StepRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StepService {

    private final RecipeRepository recipeRepository;
    private final StepRepository stepRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<?> createStep(CreateStepDto dto) {
        Optional<Recipe> recipe = recipeRepository.findById(dto.getRecipeId());
        if (recipe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Step step = new Step();
        step.setStepNumber(dto.getStepNumber());
        step.setDescription(dto.getDescription());
        step.setRecipe(recipe.get());
        stepRepository.save(step);
        URI location = URI.create("/step/" + step.getId());
        return ResponseEntity.created(location).body(modelMapper.map(step, StepDto.class));

    }
}