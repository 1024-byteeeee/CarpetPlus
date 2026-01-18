package com.ohhapple.carpetplus.utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;

public class RegexTools {
    //state.getBlock.toString(); | Block{minecraft:bedrock} -> minecraft:bedrock
    public static String getBlockRegisterName(String sourceName) {
        return sourceName.replaceAll(".*\\{(.*?)}.*", "$1");
    }

    public static String getBlockRegisterName(BlockState blockState) {
        return blockState.getBlock().toString().replaceAll(".*\\{(.*?)}.*", "$1");
    }

    // itemStack.getItem.toString() | 5 minecraft:bedrock -> minecraft:bedrock
    public static String getItemRegisterName(String sourceName) {
        return sourceName.replaceAll(".*?(minecraft:[a-z_]+).*", "$1");
    }

    public static String getItemRegisterName(ItemStack itemStack) {
        return BuiltInRegistries.ITEM.getKey(itemStack.getItem()).toString().replaceAll(".*?(minecraft:[a-z_]+).*", "$1");
    }
}