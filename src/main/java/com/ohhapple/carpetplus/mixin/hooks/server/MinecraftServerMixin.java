package com.ohhapple.carpetplus.mixin.hooks.server;

import com.ohhapple.carpetplus.settings.ohhappleinit;
import com.ohhapple.carpetplus.utils.MinecraftServerUtil;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Inject(method = "loadLevel", at = @At("TAIL"))
    private void afterServerLoadWorld(CallbackInfo ci) {
        if (MinecraftServerUtil.serverIsRunning()) {
            ohhappleinit.getInstance().afterServerLoadWorlds(MinecraftServerUtil.getServer());
        }
    }
}
