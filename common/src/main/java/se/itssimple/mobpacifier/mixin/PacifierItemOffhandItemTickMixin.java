package se.itssimple.mobpacifier.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.itssimple.mobpacifier.data.Constants;

@Mixin(Player.class)
public class PacifierItemOffhandItemTickMixin {
    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void onPlayerTick(CallbackInfo ci) {
        Player player = (Player) (Object) this;
        ItemStack offhandItem = player.getOffhandItem();

        if (!offhandItem.isEmpty() && player.tickCount % 20 == 0) {
            mob_Pacifier_1_20_1$decreaseDurabilityIfPacifierItem(player, offhandItem);
        }
    }

    @Unique
    private void mob_Pacifier_1_20_1$decreaseDurabilityIfPacifierItem(Player player, ItemStack offhandItem) {
        String itemName = offhandItem.getItem().getDescriptionId();

        switch(itemName)
        {
            case "item.minecraft.rotten_flesh":
                mob_Pacifier_1_20_1$applyCustomDurability(player, offhandItem);
                break;
            default:
                break;
        }
    }

    @Unique
    private void mob_Pacifier_1_20_1$applyCustomDurability(Player player, ItemStack offhandItem) {
        CompoundTag itemTag = offhandItem.getOrCreateTag();

        if(!itemTag.contains("MobPacifierDurability", Tag.TAG_INT)) {
            itemTag.putInt("MobPacifierDurability", Constants.DEFAULT_DURABILITY);
            offhandItem.setTag(itemTag);
        }

        int durability = itemTag.getInt("MobPacifierDurability");

        if(durability > 0) {
            durability--;
            itemTag.putInt("MobPacifierDurability", durability);
            offhandItem.setTag(itemTag);
        } else {
            offhandItem.shrink(1);
            itemTag.putInt("MobPacifierDurability", Constants.DEFAULT_DURABILITY);
            offhandItem.setTag(itemTag);
            player.broadcastBreakEvent(EquipmentSlot.OFFHAND);
        }
    }
}
