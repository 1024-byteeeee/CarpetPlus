package com.ohhapple.carpetplus.CustomRecipes.template;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class SmeltingRecipeTemplate implements RecipeTemplateInterface {
    private final ResourceLocation recipeId;
    private final String ingredient;
    private final String resultItem;
    private final float experience;
    private final int cookingTime;

    public SmeltingRecipeTemplate(ResourceLocation recipeId, String ingredient, String resultItem, float experience, int cookingTime) {
        this.recipeId = recipeId;
        this.ingredient = ingredient;
        this.resultItem = resultItem;
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    @Override
    public JsonObject toJson() {
        JsonObject recipeJson = new JsonObject();
        recipeJson.addProperty("type", "minecraft:smelting");

        recipeJson.addProperty("ingredient", ingredient);

        JsonObject resultJson = new JsonObject();
        resultJson.addProperty("id", resultItem);
        recipeJson.add("result", resultJson);

        recipeJson.addProperty("experience", experience);
        recipeJson.addProperty("cookingtime", cookingTime);

        return recipeJson;
    }

    @Override
    public void addToRecipeMap(Map<ResourceLocation, JsonElement> recipeMap) {
        recipeMap.put(recipeId, toJson());
    }
}
