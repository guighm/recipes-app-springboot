package org.guilherme.recipes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String description;

    @Column(name = "preparation_time")
    private Integer preparationTime;

    private Integer servings;

    private String difficulty;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "recipe")
    private List<Step> steps = new ArrayList<>();

    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredients = new ArrayList<>();
}
