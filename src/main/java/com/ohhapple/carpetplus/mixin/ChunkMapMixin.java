package com.ohhapple.carpetplus.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.ohhapple.carpetplus.settings.CarpetPlusSettings;
import com.ohhapple.carpetplus.settings.ohhappleinit;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChunkMap.class)
public class ChunkMapMixin {


    @ModifyReturnValue(
            method = "getPlayerViewDistance(Lnet/minecraft/server/level/ServerPlayer;)I",
            at = @At("RETURN")
    )
    private int onGetPlayerViewDistance(int original, ServerPlayer player) {
        // 如果启用了玩家独立区块加载功能
        if (CarpetPlusSettings.playerSpecificChunks) {
            // 获取自定义加载器
            var loader = ohhappleinit.getLoader();
            if (loader != null) {
                // 返回自定义的玩家视距
                int customDistance = loader.getPlayerViewDistance(player);
                if (customDistance != original) {
                    return customDistance;
                }
            }
        }
        // 否则返回原版值
        return original;
    }
}
