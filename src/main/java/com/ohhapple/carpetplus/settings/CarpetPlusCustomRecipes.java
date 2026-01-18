package com.ohhapple.carpetplus.settings;

import static net.minecraft.world.item.Items.*;
import com.ohhapple.carpetplus.CustomRecipes.SmeltingRecipeBuilder;

public class CarpetPlusCustomRecipes {
    private static final CarpetPlusCustomRecipes INSTANCE = new CarpetPlusCustomRecipes();

    private CarpetPlusCustomRecipes() {}

    public static CarpetPlusCustomRecipes getInstance() {
        return INSTANCE;
    }



    public void buildRecipes() {
        /*
         * 熔炉烧炼配方
         */
//       混凝土玻璃16色 "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "white_stained_glass")
                .material(WHITE_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(WHITE_STAINED_GLASS, 1).build();
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "orange_stained_glass")
                .material(ORANGE_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(ORANGE_STAINED_GLASS, 1).build();
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "magenta_stained_glass")
                .material(MAGENTA_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(MAGENTA_STAINED_GLASS, 1).build();
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "light_blue_stained_glass")
                .material(LIGHT_BLUE_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(LIGHT_BLUE_STAINED_GLASS, 1).build();
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "yellow_stained_glass")
                .material(YELLOW_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(YELLOW_STAINED_GLASS, 1).build();
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "lime_stained_glass")
                .material(LIME_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(LIME_STAINED_GLASS, 1).build();
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "pink_stained_glass")
                .material(PINK_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(PINK_STAINED_GLASS, 1).build();
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "gray_stained_glass")
                .material(GRAY_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(GRAY_STAINED_GLASS, 1).build();
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "light_gray_stained_glass")
                .material(LIGHT_GRAY_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(LIGHT_GRAY_STAINED_GLASS, 1).build();
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "cyan_stained_glass")
                .material(CYAN_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(CYAN_STAINED_GLASS, 1).build();
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "purple_stained_glass")
                .material(PURPLE_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(PURPLE_STAINED_GLASS, 1).build();
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "blue_stained_glass")
                .material(BLUE_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(BLUE_STAINED_GLASS, 1).build();
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "brown_stained_glass")
                .material(BROWN_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(BROWN_STAINED_GLASS, 1).build();
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "green_stained_glass")
                .material(GREEN_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(GREEN_STAINED_GLASS, 1).build();
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "red_stained_glass")
                .material(RED_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(RED_STAINED_GLASS, 1).build();
        SmeltingRecipeBuilder.create(CarpetPlusSettings.concreteBurnedIntoglass, "black_stained_glass")
                .material(BLACK_CONCRETE_POWDER).experience(0.1F).cookTime(200).output(BLACK_STAINED_GLASS, 1).build();
    }
}
