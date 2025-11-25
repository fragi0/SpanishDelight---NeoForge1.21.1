package com.devjulen.spanishdelight.client.event;

import com.devjulen.spanishdelight.SpanishDelight;
import com.devjulen.spanishdelight.common.registry.ModItemsRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

@EventBusSubscriber(modid = SpanishDelight.MOD_ID, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        Item item = event.getItemStack().getItem();
        List<Component> tooltip = event.getToolTip();

        // 1. Casos especiales (Obtención)
        if (item == ModItemsRegistry.SQUID_RING.get()) {
            tooltip.add(Component.translatable("tooltip.spanishdelight.squid_ring").withStyle(ChatFormatting.GRAY));
        }
        if (item == ModItemsRegistry.GREEN_BEAN.get()) {
            tooltip.add(Component.translatable("tooltip.spanishdelight.green_bean").withStyle(ChatFormatting.GRAY));
        }

        // 2. Descripción general con SHIFT (Paella, Tortilla, etc.)
        // Busca si existe una traducción "tooltip.spanishdelight.item_name.desc"
        String descKey = "tooltip.spanishdelight." + getItemName(item) + ".desc";
        
        if (hasTranslation(descKey)) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Component.translatable(descKey).withStyle(ChatFormatting.GOLD));
            } else {
                tooltip.add(Component.translatable("tooltip.spanishdelight.press_shift").withStyle(ChatFormatting.YELLOW));
            }
        }
    }

    private static String getItemName(Item item) {
        // Obtiene el ID del item (ej: "spanishdelight:paella") y se queda con "paella"
        String key = item.getDescriptionId(); // item.spanishdelight.paella
        return key.substring(key.lastIndexOf('.') + 1);
    }

    private static boolean hasTranslation(String key) {
        return net.minecraft.client.resources.language.I18n.exists(key);
    }
}
