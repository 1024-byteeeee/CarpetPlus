package com.ohhapple.carpetplus.CustomRecipes.template;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public interface RecipeTemplateInterface {
    JsonObject toJson();

    void addToRecipeMap(Map<ResourceLocation, JsonElement> recipeMap);
}
