package com.ohhapple.carpetplus.mixin.ohhapple;

import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ChunkMap.class)
public interface ChunkMapInvoker {
    @Invoker("updateChunkTracking")
    void invokeUpdateChunkTracking(final ServerPlayer player);
}
