package com.ohhapple.carpetplus.mixin;

import com.ohhapple.carpetplus.settings.CarpetPlusSettings;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.windcharge.AbstractWindCharge;
import net.minecraft.world.entity.projectile.windcharge.WindCharge;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.function.Function;

@Mixin(WindCharge.class)
public abstract class WindChargemixin extends AbstractWindCharge {
    public WindChargemixin(EntityType<? extends AbstractWindCharge> entityType, Level level) {
        super(entityType, level);
    }


    @Shadow
    @Final
    @Mutable
    private static ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR;




    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void modifyExplosionBehavior(CallbackInfo ci) {
        // 创建新的爆炸行为实例
        if (CarpetPlusSettings.SuperWindCharge){
            EXPLOSION_DAMAGE_CALCULATOR = new SimpleExplosionDamageCalculator(
                    true, // 修改参数
                    true,  // 修改参数
                    Optional.of(10F), // 增大爆炸威力
                    BuiltInRegistries.BLOCK.get(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())
            );
        }
    }

    @ModifyArg(method = "explode", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/util/random/WeightedList;Lnet/minecraft/core/Holder;)V"),index = 6)
    protected float createExplosion(float power) {
        if (CarpetPlusSettings.SuperWindCharge){
            return 10.0F;
        }
        return power;
    }
}
