package com.ohhapple.carpetplus;

import com.ohhapple.carpetplus.mycommand.ChunkStatsCommand;
import com.ohhapple.carpetplus.mycommand.PlayerChunkCommand;
import com.ohhapple.carpetplus.settings.ohhappleinit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;


public class CarpetPlus implements ModInitializer
{
    public static final String MOD_ID = "carpetplus";

    @Override
    public void onInitialize()
    {
        ohhappleinit.open();

        // 注册命令
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            PlayerChunkCommand.register(dispatcher);
            ChunkStatsCommand.register(dispatcher);
        });
    }

    public static String getModId(){
        return MOD_ID;
    }

}