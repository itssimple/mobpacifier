package se.itssimple.mobpacifier;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import se.itssimple.mobpacifier.data.Constants;
import se.itssimple.mobpacifier.util.Reference;

import java.util.Map;

public class ModFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		ModCommon.init();

		ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
			@Override
			public ResourceLocation getFabricId() {
				return new ResourceLocation(Reference.MOD_ID, "pacifier_config");
			}

			@Override
			public void onResourceManagerReload(ResourceManager resourceManager) {
				// Implement some kind of registry for all configs here
				Map<ResourceLocation, Resource> resources = resourceManager.listResources("pacifier_config", path -> path.getPath().endsWith(".json"));

				Constants.LOG.info("Found files: {}", resources);

				resources.forEach((location, resource) -> {

				});
			}
		});
	}
}