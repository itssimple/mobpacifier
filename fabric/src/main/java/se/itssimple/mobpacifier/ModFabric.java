package se.itssimple.mobpacifier;
import net.fabricmc.api.ModInitializer;

public class ModFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		ModCommon.init();

		loadEvents();
	}

	private void loadEvents() {
		
	}
}