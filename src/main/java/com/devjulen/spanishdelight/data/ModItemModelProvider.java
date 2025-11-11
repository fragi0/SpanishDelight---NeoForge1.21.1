package com.devjulen.spanishdelight.data;

import com.devjulen.spanishdelight.SpanishDelight;
import com.devjulen.spanishdelight.common.registry.ModItemsRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SpanishDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Items simples (item/generated)
        simple(ModItemsRegistry.PAPRIKA.get());
        simple(ModItemsRegistry.SLICED_POTATO.get());
        simple(ModItemsRegistry.SLICED_ONION.get());
        simple(ModItemsRegistry.GARLIC.get());
        simple(ModItemsRegistry.GREEN_BEAN.get());
        simple(ModItemsRegistry.RED_PEPPER.get());
        simple(ModItemsRegistry.GREEN_PEPPER.get());
        simple(ModItemsRegistry.SQUID_RING.get());
        simple(ModItemsRegistry.FRIED_SQUID_RING.get());
        simple(ModItemsRegistry.SPANISH_TORTILLA.get());
        simple(ModItemsRegistry.PAELLA.get());
        simple(ModItemsRegistry.PIL_PIL_COD.get());
        simple(ModItemsRegistry.CROQUETTES.get());
        simple(ModItemsRegistry.BRAVA_POTATOES.get());
        simple(ModItemsRegistry.CHURRO.get());
        simple(ModItemsRegistry.GAZPACHO.get());
        simple(ModItemsRegistry.PANTUMACA.get());

        // BlockItems de plantas silvestres: icono con textura del bloque
        blockItemTexture("wild_garlic");
        blockItemTexture("wild_red_pepper");
        blockItemTexture("wild_green_pepper");
    }

    private void simple(Item item) {
        basicItem(item); // parent: minecraft:item/generated, crop: assets/<mod>/textures/item/<name>.png
    }

    private void blockItemTexture(String name) {
        getBuilder(name)
                .parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", modLoc("block/" + name));
    }
}
