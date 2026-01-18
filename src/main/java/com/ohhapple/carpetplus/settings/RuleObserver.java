package com.ohhapple.carpetplus.settings;

import carpet.api.settings.CarpetRule;
import carpet.api.settings.Validator;
import net.minecraft.commands.CommandSourceStack;

public abstract class RuleObserver<T> extends Validator<T> {
    @Override
    public T validate(CommandSourceStack source, CarpetRule<T> rule, T newValue, String userInput) {
        if (rule.value() != newValue) {
            onValueChange(source, rule, rule.value(), newValue);
        }

        return newValue;
    }

    public abstract void onValueChange(CommandSourceStack source, CarpetRule<T> rule, T oldValue, T newValue);
}
