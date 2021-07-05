package com.sda.RecipeWorldApp.model.recipeModel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @ToString.Exclude
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Recipe recipe;

    @ManyToOne()
    @ToString.Exclude
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Ingredient ingredient;

    private double ammount;

    @Enumerated(EnumType.STRING)
    private MeasureUnit units;

}
