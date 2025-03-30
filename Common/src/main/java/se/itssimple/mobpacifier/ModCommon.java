package se.itssimple.mobpacifier;

import se.itssimple.mobpacifier.data.Constants;
import se.itssimple.mobpacifier.util.Reference;

public class ModCommon {

	public static void init() {
		Constants.LOG.info("Loading {} (ID: {}), version {}", Reference.NAME, Reference.MOD_ID, Reference.VERSION);
		load();
	}

	private static void load() {

	}
}