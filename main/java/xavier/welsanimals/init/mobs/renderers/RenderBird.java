package xavier.welsanimals.init.mobs.renderers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xavier.welsanimals.init.mobs.entities.EntityBird;
import xavier.welsanimals.init.mobs.models.ModelBird;
import xavier.welsanimals.main.Reference;

@SideOnly(Side.CLIENT)
public class RenderBird extends RenderLiving<EntityBird>
{	
	public static final ResourceLocation BLUE_BIRD_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/bird/bluebird.png");
    public static final ResourceLocation RED_BIRD_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/bird/redbird.png");
    public static final ResourceLocation WHITE_BIRD_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/bird/whitebird.png");
    public static final ResourceLocation GREEN_BIRD_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/bird/greenbird.png");
    public static final ResourceLocation BLACK_BIRD_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/bird/blackbird.png");
    public static final ResourceLocation YELLOW_BIRD_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/bird/yellowbird.png");

    public RenderBird(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBird(), 0.25F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBird entity)
    {
        switch (entity.getBirdType())
        {
            case 0:
            default:
                return BLUE_BIRD_TEXTURE;
            case 1:
                return RED_BIRD_TEXTURE;
            case 2:
                return WHITE_BIRD_TEXTURE;
            case 3:
                return GREEN_BIRD_TEXTURE;
            case 4:
            	return BLACK_BIRD_TEXTURE;
            case 5:
            	return YELLOW_BIRD_TEXTURE;
        }
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityBird entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(0.35F, 0.35F, 0.35F);
    }
}