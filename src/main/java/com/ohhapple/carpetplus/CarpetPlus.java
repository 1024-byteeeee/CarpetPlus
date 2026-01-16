package com.ohhapple.carpetplus;

import com.ohhapple.carpetplus.settings.ohhappleinit;
import net.fabricmc.api.ModInitializer;



public class CarpetPlus implements ModInitializer
{
    public static final String MOD_ID = "carpetplus";

    @Override
    public void onInitialize()
    {
        ohhappleinit.open();

        // 注册命令
//        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
//
//        });
    }

    public static String getModId(){
        return MOD_ID;
    }

}