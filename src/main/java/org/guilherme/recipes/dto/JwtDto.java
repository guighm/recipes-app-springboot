package org.guilherme.recipes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtDto {
    private Long id;
    private String accessToken;
}
