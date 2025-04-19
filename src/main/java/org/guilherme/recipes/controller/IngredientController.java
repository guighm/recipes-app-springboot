package org.guilherme.recipes.controller;

import lombok.RequiredArgsConstructor;
import org.guilherme.recipes.dto.CreateIngredientDto;
import org.guilherme.recipes.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<?> createIngredient(@RequestBody CreateIngredientDto dto) {
        return ingredientService.createIngredient(dto);
    }
}
