package xavier.welsanimals.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import xavier.welsanimals.main.Reference;

public class ModSoundEvents 
{
	private static int size = 0;
	
	public static SoundEvent ENTITY_GRIZZLY_BEAR_AMBIENT;
	public static SoundEvent ENTITY_GRIZZLY_BEAR_BABY_AMBIENT;
	public static SoundEvent ENTITY_GRIZZLY_BEAR_HURT;
	public static SoundEvent ENTITY_GRIZZLY_BEAR_DEATH;
	public static SoundEvent ENTITY_GRIZZLY_BEAR_STEP;
	public static SoundEvent ENTITY_GRIZZLY_BEAR_WARNING;
	
	public static void registerSounds()
	{
		ENTITY_GRIZZLY_BEAR_AMBIENT = registerSound("entity.grizzlybear.ambient");
		ENTITY_GRIZZLY_BEAR_BABY_AMBIENT = registerSound("entity.grizzlybearbaby.ambient");
		ENTITY_GRIZZLY_BEAR_HURT = registerSound("entity.grizzlybear.hurt");
		ENTITY_GRIZZLY_BEAR_DEATH = registerSound("entity.grizzlybear.death");
		ENTITY_GRIZZLY_BEAR_STEP = registerSound("entity.grizzlybear.step");
		ENTITY_GRIZZLY_BEAR_WARNING = registerSound("entity.grizzlybear.warning");
		size = SoundEvent.REGISTRY.getKeys().size();
	}
	
	
	
	private static SoundEvent registerSound(String soundName)
	{
		//final ResourceLocation soundID = new ResourceLocation(Reference.MODID, soundName);
		ResourceLocation location = new ResourceLocation(Reference.MODID, soundName);
		SoundEvent e = new SoundEvent(location);
		SoundEvent.REGISTRY.register(size, location, e);
		size++;
		return e;
		//return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
						
	}
}
