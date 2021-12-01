package se.mickelus.tetra.module.improvement;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import se.mickelus.tetra.network.AbstractPacket;

public class HonePacket extends AbstractPacket {

    ItemStack itemStack;

    public HonePacket() {}

    public HonePacket(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeItem(itemStack);
    }

    @Override
    public void fromBytes(FriendlyByteBuf buffer) {
        itemStack = buffer.readItem();
    }

    @Override
    public void handle(Player player) {
        ProgressionHelper.showHoneToastClient(itemStack);
    }
}
