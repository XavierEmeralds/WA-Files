package xavier.welsanimals.init.ai;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import xavier.welsanimals.init.mobs.entities.EntityPenguin;
import xavier.welsanimals.init.mobs.entities.EntityPenguin.PenguinMoveHelper;

public class PenguinAISwimming extends EntityAIBase
{
	private EntityPenguin theEntity;
	
	public PenguinAISwimming(EntityPenguin entity)
	{
		this.theEntity = entity;
		this.setMutexBits(1);
	}
	
	public boolean shouldExecute()
	{
		if(!this.theEntity.isInWater())
		{
			return false;
		}
		
		EntityPenguin.PenguinMoveHelper mh = (PenguinMoveHelper)this.theEntity.getMoveHelper();
		
		if(!mh.isUpdating())
		{
			if(this.theEntity.getRNG().nextInt(15) != 0)
			{
				return false;
			}
			return true;
		}
		else
		{
			double dirX = mh.getPosX() - this.theEntity.posX;
			double dirY = mh.getPosY() - this.theEntity.posY;
			double dirZ = mh.getPosZ() - this.theEntity.posZ;
			
			double destDistance = dirX * dirX + dirY * dirY + dirZ * dirZ;
			destDistance = (double)MathHelper.sqrt(destDistance);
			
			if(destDistance < 0.5D)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	public boolean continueExecuting()
	{
		return false;
	}
	
	public void startExecuting()
	{
		Random random = this.theEntity.getRNG();
		double randX = this.theEntity.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 3.0F);
		double randY = this.theEntity.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 3.0F);
		double randZ = this.theEntity.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 3.0F);
		
		BlockPos DestBlockPos = new BlockPos(MathHelper.floor(randX), MathHelper.floor(randY), MathHelper.floor(randZ));
		Block destBlock = this.theEntity.world.getBlockState(DestBlockPos).getBlock();
		Block underDestBlock = this.theEntity.world.getBlockState(DestBlockPos.down()).getBlock();
		
		if(destBlock == Blocks.WATER || (destBlock == Blocks.AIR && underDestBlock != Blocks.WATER && underDestBlock != Blocks.AIR))
		{
			this.theEntity.getMoveHelper().setMoveTo(randX, randY, randZ, 10.0D);
		}
	}
	
	
}
