# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

AVP (Predator) is a Minecraft 1.21.1 mod that adds Predator (Yautja) content from the Aliens vs Predator franchise. It's one module in the split AVP universe (alongside AVP-Alien and AVP-Human modules).

- **Mod ID**: `avp_predator`
- **Java Version**: 21
- **Loaders**: Fabric and NeoForge (multi-loader architecture)

## Build Commands

```bash
./gradlew build                    # Build both Fabric and NeoForge jars
./gradlew runAllDatagen            # Run data generation for both loaders (NeoForge first, then Fabric)
./gradlew :fabric:runClient        # Run Fabric client
./gradlew :neoforge:runClient      # Run NeoForge client
./gradlew :fabric:runDatagen       # Fabric data generation only
./gradlew :neoforge:runData        # NeoForge data generation only
./gradlew spotlessApply            # Format code (required before PRs)
```

## Architecture

### Multi-Loader Structure

```
common/     # Shared loader-agnostic code (~66% of codebase)
fabric/     # Fabric-specific: entry point, data providers
neoforge/   # NeoForge-specific: entry point, minimal code
buildSrc/   # Custom Gradle plugins (multiloader-common, multiloader-loader)
```

**Key principle**: The `common` project has no access to loader-specific APIs. Loader-specific code must go in `fabric/` or `neoforge/`. Both loader projects can access all code in `common`.

### Entry Points

- **Common**: `com.predator.Predator` - main initialization, called by both loaders
- **Fabric**: `com.predator.fabric.PredatorFabric` implements `ModInitializer`
- **NeoForge**: `com.predator.neoforge.PredatorNeoForge` uses `@Mod` annotation

### Common Module Organization (`common/src/main/java/com/predator/`)

- `client/` - Client-side rendering, animation (armor, block, entity, item renderers)
- `common/data/` - Data fixers/migrations for world compatibility
- `common/gameplay/` - Core gameplay: blocks, entities, items, AI goals
- `common/property/` - Custom property system for entities
- `common/registry/` - Registry initialization (armor, blocks, entities, items, sounds, creative tabs)
- `compatibility/` - Integration with AVP-Alien module
- `mixin/` - Bytecode modifications
- `util/` - Utility classes

### Data Generation

Data generation runs on both loaders but Fabric is the primary generator. Generated files are automatically copied from `fabric/src/main/generated/` to `common/src/main/generated/` after Fabric datagen runs.

Data providers are in `fabric/src/main/java/com/predator/fabric/data/`:
- `lang/` - Language translations
- `loot/` - Loot tables (block, entity)
- `model/` - Block and item models
- `recipe/` - Crafting recipes
- `tag/` - Block, entity type, and item tags

## Key Dependencies

- **BLib**: Base library (`com.blib`) providing `BLibAPI`, `BLibMod`, event systems
- **Just Libraries**: `just-codec`, `just-core`, `just-goap` (Goal-Oriented Action Planning AI)
- **Architectury**: Multi-loader abstraction layer
- **Cloth Config**: Configuration UI

## Code Formatting

Uses Spotless with Eclipse formatter (`eclipse-formatter.xml`). Run `./gradlew spotlessApply` before submitting PRs. Import order: default, java, com.avp.predator group, then static imports.

## Publishing

Requires `key.properties` file in project root with `curseKey`, `modrinthKey`, and Discord webhook URLs. Set `dry_run=false` in `gradle.properties` for actual publishing.

Maven publishing uses `AVP_MAVEN_USERNAME` and `AVP_MAVEN_PASSWORD` environment variables.
