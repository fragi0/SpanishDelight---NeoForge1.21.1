package com.devjulen.spanishdelight.common.event;

import com.devjulen.spanishdelight.common.registry.ModItemsRegistry;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

import java.util.List;

public class ModEvents {
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FARMER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            trades.get(1).add((e, r) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    ModItemsRegistry.PAPRIKA.get().getDefaultInstance(),
                    12, 5, 0.02f));
        }
    }

    public static void addCustomWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> generic = event.getGenericTrades();
        generic.add((e, r) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 5),
                ModItemsRegistry.PAPRIKA.get().getDefaultInstance(),
                6, 10, 0.15f));
    }
}
