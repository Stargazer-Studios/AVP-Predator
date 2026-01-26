package com.predator.common.data.fixer.migration.impl;

import com.blib.api.common.data_fix.v1.BLibDataFixerRegistry;
import com.blib.api.common.data_fix.v1.BLibDataMigration;
import com.blib.api.common.mod.v1.model.Version;
import com.predator.Predator;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class AVP_0_3_0_To_Predator_0_1_0 implements BLibDataMigration {

    @Override
    public Version fromVersion() {
        return new Version(0, 3, 0);
    }

    @Override
    public Version toVersion() {
        return new Version(0, 1, 0);
    }

    @Override
    public void apply() {
        BuiltInRegistries.REGISTRY.forEach(AVP_0_3_0_To_Predator_0_1_0::registerMigrationsForRegistry);
    }

    private static void registerMigrationsForRegistry(Registry<?> registry) {
        Predator.MOD.registries()
            .getAllHolders(registry)
            .forEach(
                holder -> register(
                    new BLibDataFixerRegistry.Entry.Direct(
                        registry,
                        createAvpResourceLocation(holder.getPath()),
                        holder.getResourceLocation()
                    )
                )
            );
    }

    private static ResourceLocation createAvpResourceLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath("avp", path);
    }

    public static void register(BLibDataFixerRegistry.Entry entry) {
        BLibDataFixerRegistry.register(entry);
    }
}
