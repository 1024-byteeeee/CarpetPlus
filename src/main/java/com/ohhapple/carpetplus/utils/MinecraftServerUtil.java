package com.ohhapple.carpetplus.utils;

import com.ohhapple.carpetplus.settings.ohhappleinit;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;

import java.util.Collection;
import java.util.Collections;

public class MinecraftServerUtil {
    public static MinecraftServer getServer() {
        return ohhappleinit.getInstance().getMinecraftServer();
    }

    public static boolean serverIsRunning() {
        return getServer() != null && getServer().isRunning();
    }

    public static boolean serverIsRunning(MinecraftServer server) {
        return server != null && server.isRunning();
    }

    public static Collection<ServerPlayer> getOnlinePlayers() {
        MinecraftServer server = getServer();

        if (serverIsRunning(server)) {
            return server.getPlayerList().getPlayers();
        }

        return Collections.emptyList();
    }

    public static PlayerList getPlayerList() {
        if (serverIsRunning()) {
            return getServer().getPlayerList();
        } else {
            return null;
        }
    }
}
