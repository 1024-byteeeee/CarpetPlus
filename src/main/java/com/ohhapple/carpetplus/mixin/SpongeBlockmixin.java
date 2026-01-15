package com.ohhapple.carpetplus.mixin;

import com.ohhapple.carpetplus.settings.CarpetPlusSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SpongeBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(SpongeBlock.class)
public class SpongeBlockmixin extends Block {
    public SpongeBlockmixin(Properties properties) {
        super(properties);
    }

    @ModifyArgs(method = "removeWaterBreadthFirstSearch", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;breadthFirstTraversal(Lnet/minecraft/core/BlockPos;IILjava/util/function/BiConsumer;Ljava/util/function/Function;)I"))
    private void absorbWater(Args args){
        if (CarpetPlusSettings.SuperSponge){
            args.set(1, CarpetPlusSettings.SuperSpongeRadius);
            args.set(2, CarpetPlusSettings.SuperSpongeRadius* CarpetPlusSettings.SuperSpongeRadius* CarpetPlusSettings.SuperSpongeRadius);
        }
    }
}
