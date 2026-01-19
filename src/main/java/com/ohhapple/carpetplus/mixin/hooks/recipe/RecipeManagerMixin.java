package com.ohhapple.carpetplus.mixin.hooks.recipe;

import com.llamalad7.mixinextras.sugar.Local;
import com.ohhapple.carpetplus.settings.ohhappleinit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeMap;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.SortedMap;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {
//    @Inject(method = "prepare(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)Lnet/minecraft/world/item/crafting/RecipeMap;", at = @At("RETURN"), cancellable = true)
//    private void addCustomRecipes(ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfoReturnable<RecipeMap> cir) {
//        SortedMap<ResourceLocation, Recipe<?>> sortedMap = new TreeMap<>();
//        SortedMap<ResourceLocation, Recipe<?>> originalMap = cir.getReturnValue().values().stream().collect(Collectors.toMap(recipeEntry -> recipeEntry.id().location(), RecipeHolder::value, (a, b) -> a, TreeMap::new));
//        sortedMap.putAll(originalMap);
//        ohhappleinit.getInstance().registerCustomRecipes(sortedMap, ((RecipeManagerAccessor) this).getRegistries());
//        List<RecipeHolder<?>> list = new ArrayList<>(sortedMap.size());
//
//        sortedMap.forEach((id, recipe) -> {
//            ResourceKey<@NotNull Recipe<?>> registryKey = ResourceKey.create(Registries.RECIPE, id);
//            RecipeHolder<?> recipeEntry = new RecipeHolder<>(registryKey, recipe);
//            list.add(recipeEntry);
//        });
//
//        cir.setReturnValue(RecipeMap.create(list));
//    }
    @Inject(method = "prepare(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)Lnet/minecraft/world/item/crafting/RecipeMap;", at = @At(value = "INVOKE", target = "Ljava/util/ArrayList;<init>(I)V",shift = At.Shift.BEFORE))
    protected void prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller, CallbackInfoReturnable<RecipeMap> cir, @Local SortedMap<ResourceLocation, Recipe<?>> sortedMap) {
        ohhappleinit.getInstance().registerCustomRecipes(sortedMap, ((RecipeManagerAccessor) this).getRegistries());
    }
}