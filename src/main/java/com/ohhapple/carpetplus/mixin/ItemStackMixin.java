package com.ohhapple.carpetplus.mixin;

import com.ohhapple.carpetplus.settings.CarpetPlusSettings;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(
            method = "getMaxStackSize",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onGetMaxStackSize(CallbackInfoReturnable<Integer> cir) {
        ItemStack self = (ItemStack)(Object)this;
        if (CarpetPlusSettings.StackablePotion!=1) {
            // 检查是否是药水物品
            if (self.is(Items.POTION) ||
                    self.is(Items.SPLASH_POTION) ||
                    self.is(Items.LINGERING_POTION)) {
                cir.setReturnValue(CarpetPlusSettings.StackablePotion);
            }
        }

        if (CarpetPlusSettings.StackableEnchantedBook!=1) {
            // 检查是否是附魔书物品
            if (self.is(Items.ENCHANTED_BOOK)) {
                cir.setReturnValue(CarpetPlusSettings.StackableEnchantedBook);
            }
        }

        if (CarpetPlusSettings.StackableTotemOfUndying!=1) {
            // 检查是否是不死图腾物品
            if (self.is(Items.TOTEM_OF_UNDYING)) {
                cir.setReturnValue(CarpetPlusSettings.StackableTotemOfUndying);
            }
        }
    }

    //耐久消耗
    @Inject(
            method = "hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void HurtAndBreak(int i, ServerLevel serverLevel, @Nullable ServerPlayer serverPlayer, Consumer<Item> consumer, CallbackInfo ci){
        if(CarpetPlusSettings.EquipmentUnbreak){ci.cancel();}
    }

}
