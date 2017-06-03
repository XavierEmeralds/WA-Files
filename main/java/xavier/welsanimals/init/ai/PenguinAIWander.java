package xavier.welsanimals.init.ai;

import javax.annotation.Nullable;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;
import xavier.welsanimals.init.mobs.entities.EntityPenguin;

public class PenguinAIWander extends EntityAIBase
{
	private EntityPenguin theEntity;
	private double xPosition;
	private double yPosition;
	private double zPosition;
	private boolean proceed;
	protected boolean mustUpdate;
	
	public PenguinAIWander(EntityPenguin entity)
	{
		this.theEntity = entity;
		this.setMutexBits(1);
	}
	
	public boolean shouldExecute()
	{
		if(!this.theEntity.onGround || this.theEntity.isInWater())
		{
			return false;
		}
		if(!this.proceed)
		{
			if(this.theEntity.getRNG().nextInt(15) != 0)
			{
				return false;
			}
		}
		
		Vec3d vec3d = this.getPosition();

        if (vec3d == null)
        {
            return false;
        }
        else
        {
            this.xPosition = vec3d.xCoord;
            this.yPosition = vec3d.yCoord;
            this.zPosition = vec3d.zCoord;
            this.mustUpdate = false;
            return true;
        }
	}
	
	@Nullable
    protected Vec3d getPosition()
    {
        return RandomPositionGenerator.findRandomTarget(this.theEntity, 10, 7);
    }
	
	public boolean continueExecuting()
	{
		return false;
	}
	
	public void startExecuting()
	{
		this.theEntity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, 0.9D);
	}
	
	public void makeUpdate()
	{
		this.proceed = true;
	}
}




















