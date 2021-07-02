package com.sda.RecipeWorldApp.model.recipeModel;

import com.sda.RecipeWorldApp.model.Account;
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
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    @OneToMany(mappedBy = "recipe")
    private Set<IngredientMeasure> ingredientList;
    private int portions;

    @ManyToOne
    private Account owner;

}
