package se.itssimple.mobpacifier;
import se.itssimple.mobpacifier.util.Reference;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MOD_ID)
public class ModForge {

	public ModForge(FMLJavaModLoadingContext modLoadingContext) {
		ModCommon.init();
		IEventBus modEventBus = modLoadingContext.getModEventBus();
		modEventBus.addListener(this::loadComplete);
	}

	private void loadComplete(final FMLLoadCompleteEvent event) {
		
	}
}