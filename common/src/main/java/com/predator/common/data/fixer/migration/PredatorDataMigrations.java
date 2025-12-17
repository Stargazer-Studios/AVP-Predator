package com.predator.common.data.fixer.migration;

import com.blib.common.data.fixer.migration.BLibDataMigration;
import com.predator.Predator;
import com.predator.common.data.fixer.migration.impl.AVP_0_3_0_To_Predator_0_1_0;

import java.util.List;

public class PredatorDataMigrations {

    private static final List<BLibDataMigration> MIGRATIONS = List.of(
        new AVP_0_3_0_To_Predator_0_1_0()
    );

    public static void initialize() {
        var version = Predator.MOD.version();

        if (version != null) {
            MIGRATIONS.forEach(BLibDataMigration::apply);
        }
    }
}
