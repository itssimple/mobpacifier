package se.itssimple.mobpacifier;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import se.itssimple.mobpacifier.data.Constants;
import se.itssimple.mobpacifier.util.Reference;

public class ModCommon {

	public static final TagKey<EntityType<?>> PACIFIED_MOBS = entitytypeCreator("mobpacifier");

	private static TagKey<EntityType<?>> entitytypeCreator(String path) {
		return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(Reference.MOD_ID, path));
	}

	public static void init() {
		Constants.LOG.info("Loading {} (ID: {}), version {}", Reference.NAME, Reference.MOD_ID, Reference.VERSION);
		load();
	}

	private static void load() {

	}
}