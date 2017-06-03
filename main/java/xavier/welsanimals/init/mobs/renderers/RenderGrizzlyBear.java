package xavier.welsanimals.init.mobs.renderers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xavier.welsanimals.init.mobs.entities.EntityGrizzlyBear;
import xavier.welsanimals.init.mobs.models.ModelGrizzlyBear;
import xavier.welsanimals.main.Reference;

@SideOnly(Side.CLIENT)
public class RenderGrizzlyBear extends RenderLiving<EntityGrizzlyBear>
{
    public static final ResourceLocation GRIZZLY_BEAR_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/grizzlybear/grizzlybear.png");

    public RenderGrizzlyBear(RenderManager p_i47197_1_)
    {
        super(p_i47197_1_, new ModelGrizzlyBear(), 0.7F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityGrizzlyBear entity)
    {
        return GRIZZLY_BEAR_TEXTURE;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityGrizzlyBear entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityGrizzlyBear entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(1.2F, 1.2F, 1.2F);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }
}