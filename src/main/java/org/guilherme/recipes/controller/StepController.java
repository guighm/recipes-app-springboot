package org.guilherme.recipes.controller;

import lombok.RequiredArgsConstructor;
import org.guilherme.recipes.dto.CreateStepDto;
import org.guilherme.recipes.service.StepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/step")
@RequiredArgsConstructor
public class StepController {

    private final StepService stepService;

    @PostMapping
    public ResponseEntity<?> createStep(@RequestBody CreateStepDto dto) {
        return stepService.createStep(dto);
    }
}
