package com.ohhapple.carpetplus.CustomRecipes;

import com.ohhapple.carpetplus.CarpetPlus;
import com.ohhapple.carpetplus.CustomRecipes.template.ShapedRecipeTemplate;
import com.ohhapple.carpetplus.CustomRecipes.template.ShapelessRecipeTemplate;
import com.ohhapple.carpetplus.CustomRecipes.template.SmeltingRecipeTemplate;
import com.ohhapple.carpetplus.utils.ChainableHashMap;
import com.ohhapple.carpetplus.utils.ChainableList;
import com.ohhapple.carpetplus.utils.IdentifierUtil;

import java.util.ArrayList;
import java.util.List;

public class PlusRecipeBuilder {
    private static final String MOD_ID = CarpetPlus.MOD_ID;
    private static final PlusRecipeBuilder INSTANCE = new PlusRecipeBuilder();
    private static final List<ShapedRecipeTemplate> shapedRecipeList = new ArrayList<>();
    private static final List<ShapelessRecipeTemplate> shapelessRecipeList = new ArrayList<>();
    private static final List<SmeltingRecipeTemplate> smeltingRecipeList = new ArrayList<>();

    private PlusRecipeBuilder() {}

    public static PlusRecipeBuilder getInstance() {
        return INSTANCE;
    }

    public List<ShapedRecipeTemplate> getShapedRecipeList() {
        return shapedRecipeList;
    }

    public List<ShapelessRecipeTemplate> getShapelessRecipeList() {
        return shapelessRecipeList;
    }

    public List<SmeltingRecipeTemplate> getSmeltingRecipeList() {
        return smeltingRecipeList;
    }

    public void addShapedRecipe(String id, String[][] pattern, ChainableHashMap<Character, String> ingredients, String result, int count) {
        shapedRecipeList.add(new ShapedRecipeTemplate(IdentifierUtil.of(MOD_ID, id), pattern, ingredients, result, count));
    }

    public void addShapelessRecipe(String id, ChainableList<String> ingredients, String result, int count) {
        shapelessRecipeList.add(new ShapelessRecipeTemplate(IdentifierUtil.of(MOD_ID, id), ingredients, result, count));
    }

    public void addSmeltingRecipe(String id, String input, String output, float experience, int cookingTime) {
        smeltingRecipeList.add(new SmeltingRecipeTemplate(IdentifierUtil.of(MOD_ID, id), input, output, experience, cookingTime));
    }
}