package com.ohhapple.carpetplus.observes.recipe;

import carpet.api.settings.CarpetRule;
import com.ohhapple.carpetplus.helper.rule.recipeRule.RecipeRuleHelper;
import com.ohhapple.carpetplus.settings.RuleObserver;
import com.ohhapple.carpetplus.utils.MinecraftServerUtil;
import net.minecraft.commands.CommandSourceStack;

public class RecipeRuleObserver extends RuleObserver<Boolean> {
    @Override
    public void onValueChange(CommandSourceStack source, CarpetRule<Boolean> rule, Boolean oldValue, Boolean newValue) {
        RecipeRuleHelper.onValueChange(MinecraftServerUtil.getServer());
    }
}