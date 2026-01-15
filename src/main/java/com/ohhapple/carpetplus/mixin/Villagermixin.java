package com.ohhapple.carpetplus.mixin;

import com.ohhapple.carpetplus.settings.CarpetPlusSettings;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ReputationEventHandler;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Villager.class)
public abstract class Villagermixin extends AbstractVillager implements ReputationEventHandler, VillagerDataHolder {
    public Villagermixin(EntityType<? extends AbstractVillager> entityType, Level level) {
        super(entityType, level);
    }
    @Inject(method = "canBreed", at = @At("HEAD"), cancellable = true)
    public void isReadyToBreed(CallbackInfoReturnable<Boolean> cir){
        if (CarpetPlusSettings.VillageAlwaysBreed){
            // cir.setReturnValue(this.foodLevel + this.getAvailableFood() >= 12 && !this.isSleeping() && this.getBreedingAge() == 0);
            cir.setReturnValue(true);
        }
    }
}
