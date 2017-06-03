package xavier.welsanimals.init.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xavier.welsanimals.init.mobs.entities.EntityBird;

/**
 * Bird by XavierEmeralds
 */
@SideOnly(Side.CLIENT)
public class ModelBird extends ModelBase 
{
    public ModelRenderer Body;
    public ModelRenderer Body1;
    public ModelRenderer Tail;
    public ModelRenderer Tail1;
    public ModelRenderer Tail2;
    public ModelRenderer Head;
    public ModelRenderer Neck;
    public ModelRenderer Beak1;
    public ModelRenderer Beak2;
    public ModelRenderer Wing;
    public ModelRenderer Wing11;
    public ModelRenderer Wing12;
    public ModelRenderer Wing13;
    public ModelRenderer Wing2;
    public ModelRenderer Wing21;
    public ModelRenderer Wing22;
    public ModelRenderer Wing23;
    public ModelRenderer Leg1;
    public ModelRenderer Foot1;
    public ModelRenderer Leg2;
    public ModelRenderer Foot2;

    public ModelBird() 
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.Body = new ModelRenderer(this, 22, 0);
        this.Body.setRotationPoint(0.0F, 20.5F, 0.0F);
        this.Body.addBox(-3.5F, -3.5F, -1.5F, 6, 7, 4);
        this.setRotation(this.Body, 1.5707963267948966F, 0.0F, 0.0F);
        this.Body1 = new ModelRenderer(this, 42, 4);
        this.Body1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body1.addBox(-3.0F, -3.0F, -2.0F, 5, 6, 1);
        this.Body.addChild(this.Body1);
        this.Tail = new ModelRenderer(this, 1, 8);
        this.Tail.setRotationPoint(-0.5F, 18.9F, 3.0F);
        this.Tail.addBox(-0.5F, -0.5F, 0.0F, 1, 6, 1);
        this.setRotation(this.Tail, 1.5707963267948966F, 0.0F, 0.0F);
        this.Tail1 = new ModelRenderer(this, 8, 4);
        this.Tail1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Tail1.addBox(0.5F, -0.5F, 0.0F, 1, 5, 1);
        this.Tail.addChild(this.Tail1);
        this.Tail2 = new ModelRenderer(this, 41, 15);
        this.Tail2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Tail2.addBox(-1.5F, -0.5F, 0.0F, 1, 5, 1);
        this.Tail.addChild(this.Tail2);
        this.Head = new ModelRenderer(this, 4, 13);
        this.Head.setRotationPoint(-0.5F, 18.0F, -3.5F);
        this.Head.addBox(-2.0F, -4.0F, -1.9F, 4, 4, 4);
        this.Neck = new ModelRenderer(this, 22, 12);
        this.Neck.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Neck.addBox(-2.0F, -1.6F, -1.0F, 4, 5, 4);
        this.setRotation(this.Neck, 0.7853981633974483F, 0.0F, 0.0F);
        this.Head.addChild(this.Neck);
        this.Beak1 = new ModelRenderer(this, 46, 23);
        this.Beak1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Beak1.addBox(-1.0F, -1.4F, -3.0F, 2, 1, 1);
        this.Head.addChild(this.Beak1);
        this.Beak2 = new ModelRenderer(this, 24, 25);
        this.Beak2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Beak2.addBox(-0.5F, -1.4F, -3.5F, 1, 1, 1);
        this.Beak1.addChild(this.Beak2);
        this.Wing = new ModelRenderer(this, 11, 12);
        this.Wing.setRotationPoint(-3.0F, 18.9F, -2.5F);
        this.Wing.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 9);
        this.Wing11 = new ModelRenderer(this, 12, 22);
        this.Wing11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Wing11.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 8);
        this.Wing.addChild(this.Wing11);
        this.Wing12 = new ModelRenderer(this, 48, 24);
        this.Wing12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Wing12.addBox(-1.0F, 1.0F, 0.5F, 1, 1, 6);
        this.Wing.addChild(this.Wing12);
        this.Wing13 = new ModelRenderer(this, 29, 23);
        this.Wing13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Wing13.addBox(-1.0F, 1.5F, 1.5F, 1, 1, 3);
        this.Wing.addChild(this.Wing13);
        this.Wing2 = new ModelRenderer(this, 11, 2);
        this.Wing2.setRotationPoint(2.0F, 18.9F, -2.5F);
        this.Wing2.addBox(0.0F, -1.0F, 0.0F, 1, 1, 9);
        this.Wing21 = new ModelRenderer(this, 30, 22);
        this.Wing21.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Wing21.addBox(0.0F, 0.0F, 0.0F, 1, 1, 8);
        this.Wing2.addChild(this.Wing21);
        this.Wing22 = new ModelRenderer(this, 0, 23);
        this.Wing22.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Wing22.addBox(0.0F, 1.0F, 0.5F, 1, 1, 6);
        this.Wing2.addChild(this.Wing22);
        this.Wing23 = new ModelRenderer(this, 46, 13);
        this.Wing23.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Wing23.addBox(0.0F, 1.5F, 1.5F, 1, 1, 3);
        this.Wing2.addChild(this.Wing23);
        this.Leg1 = new ModelRenderer(this, 53, 19);
        this.Leg1.setRotationPoint(0.6F, 22.0F, -0.4F);
        this.Leg1.addBox(-0.4F, 0.0F, -0.4F, 1, 2, 1);
        this.Foot1 = new ModelRenderer(this, 0, 0);
        this.Foot1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Foot1.addBox(-0.4F, 1.0F, -1.4F, 1, 1, 2);
        this.Leg1.addChild(this.Foot1);
        this.Leg2 = new ModelRenderer(this, 53, 19);
        this.Leg2.setRotationPoint(-1.3F, 22.0F, -0.4F);
        this.Leg2.addBox(-0.8F, 0.0F, -0.4F, 1, 2, 1);
        this.Foot2 = new ModelRenderer(this, 0, 0);
        this.Foot2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Foot2.addBox(-0.8F, 1.0F, -1.4F, 1, 1, 2);
        this.Leg2.addChild(this.Foot2);
    }
        
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    	setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
    	GlStateManager.pushMatrix();
    	GlStateManager.translate(0.0F, 1.0F * scale, 0.0F);
    	this.Head.render(scale);
    	this.Body.render(scale);
    	this.Wing.render(scale);
    	this.Wing2.render(scale);
    	this.Leg1.render(scale);
    	this.Leg2.render(scale);
    	this.Tail.render(scale);
    	GlStateManager.popMatrix();
    }

    public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) 
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) 
    {
    	
    	if (((EntityBird)entityIn).getIsBirdSitting())
        {
    		this.Head.rotateAngleX = headPitch * 0.0017453292F;
            this.Head.rotateAngleY = netHeadYaw * 0.0017453292F;
            this.Wing.rotateAngleX = (float)Math.PI / 0.25F;
            this.Wing2.rotateAngleX = -this.Wing.rotateAngleX;
            this.Wing.rotateAngleZ = (float)Math.PI / 0.25F;
            this.Wing2.rotateAngleZ = -this.Wing.rotateAngleZ;
            this.Wing.rotateAngleY = (float)Math.PI / 0.25F;
            this.Wing2.rotateAngleY = -this.Wing.rotateAngleY;
            this.Tail.rotateAngleY = MathHelper.cos(ageInTicks * 0.1F) * 0.15F;
        }
        else
        {
        	this.Wing.rotateAngleZ = (float)MathHelper.cos(ageInTicks * 1.5F) - 1.5F;
        	this.Wing2.rotateAngleZ = -this.Wing.rotateAngleZ;
         
        	this.Wing.rotateAngleX = 1.5F;
        	this.Wing2.rotateAngleX = 1.5F;
        }
    }
}
