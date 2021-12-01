package se.mickelus.tetra.gui.stats.getter;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;

// todo: doesn't really work for bars since improvement & slot getters only reference the base item
public class StatGetterSum implements IStatGetter {
    private IStatGetter[] statGetters;
    private double offset = 0;

    public StatGetterSum(IStatGetter ... statGetters) {
        this.statGetters = statGetters;
    }

    public StatGetterSum(double offset, IStatGetter ... statGetters) {
        this(statGetters);
        this.offset = offset;
    }

    @Override
    public double getValue(Player player, ItemStack itemStack) {
        return Arrays.stream(statGetters)
                .mapToDouble(getter -> getter.getValue(player, itemStack))
                .sum() + offset;
    }

    @Override
    public double getValue(Player player, ItemStack itemStack, String slot) {
        return Arrays.stream(statGetters)
                .mapToDouble(getter -> getter.getValue(player, itemStack, slot))
                .sum() + offset;
    }

    @Override
    public double getValue(Player player, ItemStack itemStack, String slot, String improvement) {
        return Arrays.stream(statGetters)
                .mapToDouble(getter -> getter.getValue(player, itemStack, slot, improvement))
                .sum() + offset;
    }
}
