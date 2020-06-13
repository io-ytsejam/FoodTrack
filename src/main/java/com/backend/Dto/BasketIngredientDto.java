package com.backend.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class BasketIngredientDto {
    @NotBlank
    String name;
    @NotNull
    boolean completed=false;

    public BasketIngredientDto() {
    }

    public BasketIngredientDto(@NotBlank String name) {
        this.name = name;
        completed=false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketIngredientDto that = (BasketIngredientDto) o;
        return completed == that.completed &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, completed);
    }
}
