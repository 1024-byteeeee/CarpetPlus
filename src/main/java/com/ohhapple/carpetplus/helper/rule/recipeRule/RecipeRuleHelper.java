package com.ohhapple.carpetplus.helper.rule.recipeRule;

import com.ohhapple.carpetplus.CarpetPlus;
import com.ohhapple.carpetplus.CustomRecipes.PlusRecipeBuilder;
import com.ohhapple.carpetplus.CustomRecipes.PlusRecipeManager;
import com.ohhapple.carpetplus.settings.CarpetPlusCustomRecipes;
import com.ohhapple.carpetplus.settings.CarpetPlusSettings;
import com.ohhapple.carpetplus.settings.RecipeRule;
import com.ohhapple.carpetplus.settings.ohhappleinit;
import com.ohhapple.carpetplus.utils.MinecraftServerUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class RecipeRuleHelper {
    private static final String MOD_ID = CarpetPlus.MOD_ID;

    public static void onPlayerLoggedIn(MinecraftServer server, ServerPlayer player) {
        if (MinecraftServerUtil.serverIsRunning(server) && hasActiveRecipeRule()) {
            Collection<RecipeHolder<?>> allRecipes = getServerRecipeManager(server).getRecipes();
            for (RecipeHolder<?> recipe : allRecipes) {
                //location()在26.1后为identifier()
                if (recipe.id().location().getNamespace().equals(MOD_ID) && !player.getRecipeBook().contains(recipe.id())) {
                    player.awardRecipes(List.of(recipe));
                }
            }
        }
    }

    public static void onValueChange(MinecraftServer server) {
        if (MinecraftServerUtil.serverIsRunning(server)) {
            server.execute(() -> {
                PlusRecipeManager.clearRecipeListMemory(PlusRecipeBuilder.getInstance());
                CarpetPlusCustomRecipes.getInstance().buildRecipes();
                reloadServerResources(server);
                Collection<RecipeHolder<?>> allRecipes = getServerRecipeManager(server).getRecipes();
                for (RecipeHolder<?> recipe : allRecipes) {
                    //location()在26.1后为identifier()
                    if (recipe.id().location().getNamespace().equals(MOD_ID)) {
                        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                            if (!player.getRecipeBook().contains(recipe.id())) {
                                player.awardRecipes(List.of(recipe));
                            }
                        }
                    }
                }
            });
        }
    }

    private static boolean hasActiveRecipeRule() {
        Field[] fields = CarpetPlusSettings.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(RecipeRule.class)) {
                try {
                    field.setAccessible(true);
                    if (field.getBoolean(null)) {
                        return true;
                    }
                } catch (IllegalAccessException e) {
                    ohhappleinit.LOGGER.warn("Failed to access RecipeRule field: {}", field.getName(), e);
                }
            }
        }
        return false;
    }

    private static RecipeManager getServerRecipeManager(MinecraftServer server) {
        return server.getRecipeManager();
    }

    public static void reloadServerResources(MinecraftServer server) {
        server.reloadResources(server.getPackRepository().getSelectedIds());
    }
}