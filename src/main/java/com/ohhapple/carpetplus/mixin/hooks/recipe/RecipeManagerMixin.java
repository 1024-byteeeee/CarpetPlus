package com.ohhapple.carpetplus.mixin.hooks.recipe;

import com.ohhapple.carpetplus.settings.ohhappleinit;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeMap;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {
    @Inject(method = "prepare(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)Lnet/minecraft/world/item/crafting/RecipeMap;", at = @At("RETURN"), cancellable = true)
    private void addCustomRecipes(ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfoReturnable<RecipeMap> cir) {
        SortedMap<ResourceLocation, Recipe<?>> sortedMap = new TreeMap<>();
        SortedMap<ResourceLocation, Recipe<?>> originalMap = cir.getReturnValue().values().stream().collect(Collectors.toMap(recipeEntry -> recipeEntry.id().location(), RecipeHolder::value, (a, b) -> a, TreeMap::new));
        sortedMap.putAll(originalMap);
        ohhappleinit.getInstance().registerCustomRecipes(sortedMap, ((RecipeManagerAccessor) this).getRegistries());
        List<RecipeHolder<?>> list = new ArrayList<>(sortedMap.size());

        sortedMap.forEach((id, recipe) -> {
            ResourceKey<@NotNull Recipe<?>> registryKey = ResourceKey.create(Registries.RECIPE, id);
            RecipeHolder<?> recipeEntry = new RecipeHolder<>(registryKey, recipe);
            list.add(recipeEntry);
        });

        cir.setReturnValue(RecipeMap.create(list));
    }
}