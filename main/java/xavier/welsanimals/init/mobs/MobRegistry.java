package xavier.welsanimals.init.mobs;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import xavier.welsanimals.init.mobs.entities.EntityBird;
import xavier.welsanimals.init.mobs.entities.EntityGrizzlyBear;
import xavier.welsanimals.init.mobs.entities.EntityPenguin;
import xavier.welsanimals.init.mobs.renderers.RenderBird;
import xavier.welsanimals.init.mobs.renderers.RenderGrizzlyBear;
import xavier.welsanimals.init.mobs.renderers.RenderPenguin;
import xavier.welsanimals.main.WelsAnimals;

public class MobRegistry 
{
	public static void register()
	{
		MobRegistry.registerRender();
		MobRegistry.registerEntity();
	}
	
	public static void registerRender()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityPenguin.class, new RenderPenguin(Minecraft.getMinecraft().getRenderManager()));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityGrizzlyBear.class, new RenderGrizzlyBear(Minecraft.getMinecraft().getRenderManager()));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBird.class, new RenderBird(Minecraft.getMinecraft().getRenderManager()));
	}
	
	public static void registerEntity()
	{
		EntityRegistry.registerModEntity(RenderPenguin.PENGUIN_TEXTURE, EntityPenguin.class, "penguin", 300, WelsAnimals.instance, 32, 3, true, 0xffffff, 0x000000);
		EntityRegistry.addSpawn(EntityPenguin.class, 15, 5, 10, EnumCreatureType.CREATURE, Biome.getBiome(12));
		
		EntityRegistry.registerModEntity(RenderGrizzlyBear.GRIZZLY_BEAR_TEXTURE, EntityGrizzlyBear.class, "grizzlybear", 301, WelsAnimals.instance, 32, 3, true, 0x754d2e, 0x604c3d);
		EntityRegistry.addSpawn(EntityGrizzlyBear.class, 10, 1, 3, EnumCreatureType.CREATURE, Biome.getBiome(29));
		
		EntityRegistry.registerModEntity(RenderBird.BLUE_BIRD_TEXTURE, EntityBird.class, "bird", 302, WelsAnimals.instance, 32, 1, true, 0x55593f, 0xe0ecff);
		EntityRegistry.addSpawn(EntityBird.class, 20, 5, 10, EnumCreatureType.AMBIENT, Biome.getBiome(4), Biome.getBiome(5), Biome.getBiome(9), 
				Biome.getBiome(18), Biome.getBiome(19), Biome.getBiome(21), Biome.getBiome(22), Biome.getBiome(23), Biome.getBiome(27), 
				Biome.getBiome(28), Biome.getBiome(29), Biome.getBiome(32), Biome.getBiome(33), Biome.getBiome(34), Biome.getBiome(132), 
				Biome.getBiome(149), Biome.getBiome(151), Biome.getBiome(155), Biome.getBiome(156), Biome.getBiome(157), Biome.getBiome(160), 
				Biome.getBiome(161), Biome.getBiome(162));
	}
}
