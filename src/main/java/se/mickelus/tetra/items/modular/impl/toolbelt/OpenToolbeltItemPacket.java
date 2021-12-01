package se.mickelus.tetra.items.modular.impl.toolbelt;

import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkHooks;
import se.mickelus.tetra.items.modular.impl.toolbelt.inventory.ToolbeltSlotType;
import se.mickelus.tetra.network.AbstractPacket;

public class OpenToolbeltItemPacket extends AbstractPacket {

    public OpenToolbeltItemPacket() { }

    @Override
    public void toBytes(FriendlyByteBuf buffer) {
    }

    @Override
    public void fromBytes(FriendlyByteBuf buffer) {
    }

    @Override
    public void handle(Player player) {
        ItemStack itemStack = ToolbeltHelper.findToolbelt(player);
        if (!itemStack.isEmpty()) {
            NetworkHooks.openGui((ServerPlayer) player, (MenuProvider) itemStack.getItem());
        }
    }
}
