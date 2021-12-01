package se.mickelus.tetra.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.IForgeRegistry;
import se.mickelus.tetra.items.TetraItemGroup;
import se.mickelus.tetra.network.PacketHandler;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface ITetraBlock {

    @OnlyIn(Dist.CLIENT)
    default void clientInit() { }

    default void init(PacketHandler packetHandler) {}

    boolean hasItem();

    default void registerItem(IForgeRegistry<Item> registry) {
        if (this instanceof Block) {
            registerItem(registry, (Block) this);
        }
    }

    default void registerItem(IForgeRegistry<Item> registry, Block block) {
        Item item = new BlockItem(block, new Item.Properties().tab(TetraItemGroup.instance))
                .setRegistryName(block.getRegistryName());

        registry.register(item);
    }

    default boolean canProvideTools(Level world, BlockPos pos, BlockPos targetPos) {
        return false;
    }

    default Collection<ToolType> getTools(Level world, BlockPos pos, BlockState blockState) {
        return Collections.emptyList();
    }

    default int getToolLevel(Level world, BlockPos pos, BlockState blockState, ToolType toolType) {
        return -1;
    }

    default Map<ToolType, Integer> getToolLevels(Level world, BlockPos pos, BlockState blockState) {
        return getTools(world, pos, blockState).stream()
                .collect(Collectors.toMap(Function.identity(), toolType -> getToolLevel(world, pos, blockState, toolType)));
    }

    default ItemStack onCraftConsumeTool(Level world, BlockPos pos, BlockState blockState, ItemStack targetStack, String slot, boolean isReplacing,
            Player player, ToolType requiredTool, int requiredLevel, boolean consumeResources) {
        return null;
    }

    default ItemStack onActionConsumeTool(Level world, BlockPos pos, BlockState blockState, ItemStack targetStack, Player player,
            ToolType requiredTool, int requiredLevel, boolean consumeResources) {
        return null;
    }

    default boolean canUnlockSchematics(Level world, BlockPos pos, BlockPos targetPos) {
        return false;
    }

    default ResourceLocation[] getSchematics(Level world, BlockPos pos, BlockState blockState) {
        return new ResourceLocation[0];
    }

    default boolean canUnlockCraftingEffects(Level world, BlockPos pos, BlockPos targetPos) {
        return false;
    }

    default ResourceLocation[] getCraftingEffects(Level world, BlockPos pos, BlockState blockState) {
        return new ResourceLocation[0];
    }
}
