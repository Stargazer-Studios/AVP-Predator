package com.predator.fabric.data.gene_bonus_data;

import com.blib.api.common.entity.v1.EntityTypePredicate;
import com.human.common.gameplay.gene.Gene;
import com.human.common.gameplay.gene.GeneBonusData;
import com.human.common.gameplay.gene.GeneBonusDataEntry;
import com.human.common.gameplay.gene.GeneModifier;
import com.human.common.gameplay.gene.GeneOperationType;
import com.human.common.gameplay.gene.Genes;
import com.just.core.functional.tuple.Tuple2;
import com.predator.common.registry.init.PredatorEntityTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import java.util.List;

public class GeneBonusDataSubProvider extends GeneBonusDataProvider {

    public GeneBonusDataSubProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    protected void generate() {
        add(
            PredatorEntityTypes.YAUTJA.get(),
            List.of(
                // Positives
                new Tuple2<>(Genes.ATTACK_DAMAGE, new GeneModifier(GeneOperationType.ADDITIVE, 7.5)),
                new Tuple2<>(Genes.COLD_RESISTANCE, new GeneModifier(GeneOperationType.MULTIPLICATIVE, 0.05)),
                new Tuple2<>(Genes.FIRE_RESISTANCE, new GeneModifier(GeneOperationType.MULTIPLICATIVE, 0.2)),
                new Tuple2<>(Genes.INTELLIGENCE, new GeneModifier(GeneOperationType.ADDITIVE, 30.0)),
                new Tuple2<>(Genes.KNOCKBACK_RESISTANCE, new GeneModifier(GeneOperationType.ADDITIVE, 0.15)),
                new Tuple2<>(Genes.MAX_HEALTH, new GeneModifier(GeneOperationType.MULTIPLICATIVE, 0.5)),
                new Tuple2<>(Genes.MOVE_SPEED, new GeneModifier(GeneOperationType.ADDITIVE, 0.005))
            )
        );
    }

    private void add(TagKey<EntityType<?>> entityTypeTagKey, List<Tuple2<Gene, GeneModifier>> geneBonusList) {
        add(
            entityTypeTagKey.location().getPath() + "_gene_bonuses",
            new GeneBonusData(
                new EntityTypePredicate.Tag(entityTypeTagKey),
                geneBonusList.stream()
                    .map(
                        tuple -> new GeneBonusDataEntry(
                            tuple.v1().id(),
                            tuple.v2().operation(),
                            tuple.v2().value()
                        )
                    )
                    .toList()
            )
        );
    }

    private void add(EntityType<?> entityType, List<Tuple2<Gene, GeneModifier>> geneBonusList) {
        add(
            BuiltInRegistries.ENTITY_TYPE.getKey(entityType).getPath() + "_gene_bonuses",
            new GeneBonusData(
                new EntityTypePredicate.Single(entityType),
                geneBonusList.stream()
                    .map(
                        tuple -> new GeneBonusDataEntry(
                            tuple.v1().id(),
                            tuple.v2().operation(),
                            tuple.v2().value()
                        )
                    )
                    .toList()
            )
        );
    }
}
