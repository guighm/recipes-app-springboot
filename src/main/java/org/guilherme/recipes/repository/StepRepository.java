package org.guilherme.recipes.repository;

import org.guilherme.recipes.model.Recipe;
import org.guilherme.recipes.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepRepository extends JpaRepository<Step, Long> {
    List<Step> findByRecipe(Recipe recipe);
}
