package xavier.welsanimals.init.mobs.entities;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import xavier.welsanimals.init.ai.PenguinAISwimming;
import xavier.welsanimals.init.ai.PenguinAIWander;

public class EntityPenguin extends EntityAnimal
{
    	
	private static final DataParameter<Integer> field_191520_bx = EntityDataManager.<Integer>createKey(EntityPenguin.class, DataSerializers.VARINT);
	private static final DataParameter<Byte> SWIMMING = EntityDataManager.<Byte>createKey(EntityPenguin.class, DataSerializers.BYTE);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new Item[] {Items.FISH});
    private boolean boosting;
    private int boostTime;
    private int totalBoostTime;
	
    
    public EntityPenguin(World worldIn)
    {
        super(worldIn);
        this.setSize(0.5F, 1.4F);
    }
	
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(SWIMMING, Byte.valueOf((byte)0));
    }  
   
    public boolean getIsPenguinSwimming()
    {
        return (((Byte)this.dataManager.get(SWIMMING)).byteValue() & 1) != 0;
    }
    
    public void setIsPenguinSwimming(boolean isSwimming)
    {
        byte b0 = ((Byte)this.dataManager.get(SWIMMING)).byteValue();

        if (isSwimming)
        {
            this.dataManager.set(SWIMMING, Byte.valueOf((byte)(b0 | 1)));
        }
        else
        {
            this.dataManager.set(SWIMMING, Byte.valueOf((byte)(b0 & -2)));
        }
    }
    
    
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setByte("PenguinFlags", ((Byte)this.dataManager.get(SWIMMING)).byteValue());
    }
    
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.dataManager.set(SWIMMING, Byte.valueOf(compound.getByte("PenguinFlags")));
    }
    
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new PenguinAISwimming(this));
        this.tasks.addTask(1, new PenguinAIWander(this));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.2D, Items.FISH, false));
        this.tasks.addTask(4, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        
        this.moveHelper = new EntityPenguin.PenguinMoveHelper();
    }
	
	public boolean isBreedingItem(ItemStack itemstack)
	{
		return itemstack == null ? false :itemstack.getItem() == Items.FISH;
	}
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
    }
	
	public void notifyDataManagerChange(DataParameter<?> key)
    {
        if (field_191520_bx.equals(key) && this.world.isRemote)
        {
            this.boosting = true;
            this.boostTime = 0;
            this.totalBoostTime = ((Integer)this.dataManager.get(field_191520_bx)).intValue();
        }

        super.notifyDataManagerChange(key);
    }
	
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		
		if(this.isInWater())
		{
			this.setAir(300);
			this.limbSwingAmount = 0;
			this.limbSwing = 0;
			this.prevLimbSwingAmount = this.limbSwingAmount;
			this.setIsPenguinSwimming(true);
		}
		else
		{
			this.setIsPenguinSwimming(false);
		}
	}
	
	public static void registerFixesPenguin(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityPenguin.class);
    }
	
	public EntityAgeable createChild(EntityAgeable ageable)
    {
        return new EntityPenguin(this.world);
    }

    public float getEyeHeight()
    {
        return this.isChild() ? this.height : 1.3F;
    }
    
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_POLAR_BEAR;
    }
    
    public boolean canBreatheUnderwater()
    {
        return true;
    }
    
    public boolean isPushedByWater()
    {
    	return false;
    }
    
    public void moveEntityWithHeading(float x, float z)
    {
    	if(this.isInWater())
    	{
    		this.moveRelative(x, z, 0.1F);
    		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
    		
    		this.motionX *= 0.8D;
    		this.motionY *= 0.8D;
    		this.motionZ *= 0.8D;
    	}
    	else
    	{
    		super.moveEntityWithHeading(x, z);
    	}
    }
    
    public class PenguinMoveHelper extends EntityMoveHelper
    {
    	private EntityPenguin entity = EntityPenguin.this;
    	private int randcounter;
    	
    	public PenguinMoveHelper()
    	{
    		super(EntityPenguin.this);
    	}
    	
    	public void onUpdateMoveHelper()
    	{
    		if(entity.isInWater())
    		{
    			if(this.isUpdating())
    			{
    				if(this.randcounter-- <=0)
    				{
    					this.randcounter += this.entity.getRNG().nextInt(5) + 10;
    					
    					double dirX = this.posX - this.entity.posX;
    					double dirY = this.posY - this.entity.posY;
    					double dirZ = this.posZ - this.entity.posZ;
    					
    					double destDistance = dirX * dirX + dirY * dirY + dirZ * dirZ;
    					
    					destDistance = (double)MathHelper.sqrt(destDistance);
    					
    					if(!this.checkCollision(this.posX, this.posY, this.posZ, destDistance))
    					{
    						this.entity.motionX += dirX / destDistance * 0.1D;
    						this.entity.motionY += dirY / destDistance * 0.1D;
    						this.entity.motionZ += dirZ / destDistance * 0.1D;
    					}
    					else
    					{
    						super.onUpdateMoveHelper();
    						double aboveBlockLocY = MathHelper.floor(this.posY) + 1;
    						Block blockAboveSelf = world.getBlockState(new BlockPos(MathHelper.floor(this.entity.posX),MathHelper.floor(aboveBlockLocY), MathHelper.floor(this.entity.posZ))).getBlock();
    						Block destBlock = this.entity.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY), MathHelper.floor(this.posZ))).getBlock();
    						
    						double Ydiff = MathHelper.floor(this.posY) - MathHelper.floor(this.entity.posY);
    						
    						if(Ydiff == 1.0 && blockAboveSelf == Blocks.AIR && destBlock !=Blocks.WATER && destDistance <= 3)
    						{
    							this.entity.motionX = dirX / destDistance * 0.3D;
    							this.entity.motionY = 0.4D;
    							this.entity.motionZ = dirZ / destDistance * 0.3D;
    							this.entity.getJumpHelper().setJumping();
    						}
    					}
    					this.UpdateYaw(motionX, motionZ);
    				}
    			}
    		}
    		else
    		{
    			super.onUpdateMoveHelper();
    		}
    	}
    	
    	public void UpdateYaw(double dirX, double dirZ)
    	{
    		renderYawOffset = rotationYaw = ((float)Math.atan2(dirX, dirZ)) * 180.0F / (float)Math.PI;
    	}
    	
    	private boolean checkCollision(double posX, double posY, double posZ, double distance)
    	{
    		double dirX = (posX - this.entity.posX) / distance;
    		double dirY = (posY - this.entity.posY) / distance;
    		double dirZ = (posZ - this.entity.posZ) / distance;
    		AxisAlignedBB collisionBox = this.entity.getEntityBoundingBox();
    		
    		for(int i = 1; (double)i < distance; ++i)
    		{
    			collisionBox = collisionBox.offset(dirX, dirY, dirZ);
    			
    			if(!this.entity.world.getCollisionBoxes(this.entity, collisionBox).isEmpty())
    			{
    				return true;
    			}
    		}
    		return false;
    	}
    	
    	public double getPosX()
    	{
    		return this.posX;
    	}
    	
    	public double getPosY()
    	{
    		return this.posY;
    	}
    	
    	public double getPosZ()
    	{
    		return this.posZ;
    	}
    	
    }
}
