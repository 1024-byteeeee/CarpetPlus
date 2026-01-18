package com.ohhapple.carpetplus.utils;

import net.minecraft.resources.ResourceLocation;
public class IdentifierUtil {
    public static ResourceLocation of(String namespace, String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }

    public static ResourceLocation ofId(String id) {
        return ResourceLocation.parse(id);
    }
}
