package com.sda.RecipeWorldApp.model.recipeModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @Enumerated(value = EnumType.STRING)
    private IngredientType type;

    @OneToMany(mappedBy = "ingredient")
    private Set<IngredientMeasure> ingredientMeasures;
}
