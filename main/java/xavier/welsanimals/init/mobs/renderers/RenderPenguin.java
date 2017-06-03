package xavier.welsanimals.init.mobs.renderers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xavier.welsanimals.init.mobs.entities.EntityPenguin;
import xavier.welsanimals.init.mobs.models.ModelPenguin;
import xavier.welsanimals.main.Reference;

@SideOnly(Side.CLIENT)
public class RenderPenguin extends RenderLiving<EntityPenguin>
{
	public static final ResourceLocation PENGUIN_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/penguin/penguin.png");
	
	 public RenderPenguin(RenderManager p_i47197_1_)
	    {
	        super(p_i47197_1_, new ModelPenguin(), 0.5F);
	    }
	
	protected ResourceLocation getEntityTexture(EntityPenguin entity)
	{
		return PENGUIN_TEXTURE;
	}
	
	public void doRender(EntityPenguin entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityPenguin entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(1.2F, 1.2F, 1.2F);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }
	
}
