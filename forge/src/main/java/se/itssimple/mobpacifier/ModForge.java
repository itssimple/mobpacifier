package se.itssimple.mobpacifier;
import se.itssimple.mobpacifier.util.Reference;
import net.minecraftforge.fml.common.Mod;

@Mod(Reference.MOD_ID)
public class ModForge {

	public ModForge() {
		ModCommon.init();
	}

}