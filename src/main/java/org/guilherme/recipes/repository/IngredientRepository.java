package org.guilherme.recipes.repository;

import org.guilherme.recipes.model.Ingredient;
import org.guilherme.recipes.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByRecipe(Recipe recipe);
}
