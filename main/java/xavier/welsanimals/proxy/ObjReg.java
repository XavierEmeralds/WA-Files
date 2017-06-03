package xavier.welsanimals.proxy;

import xavier.welsanimals.init.ModSoundEvents;
import xavier.welsanimals.init.mobs.MobRegistry;

public class ObjReg 
{
	public static void Client()
	{
		MobRegistry.register();
		ModSoundEvents.registerSounds();
	}
	
	public static void Common()
	{
		
	}
	
}
