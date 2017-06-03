package xavier.welsanimals.init.mobs.entities;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityBird extends EntityAmbientCreature
{
	private static final DataParameter<Integer> BIRD_TYPE = EntityDataManager.<Integer>createKey(EntityBird.class, DataSerializers.VARINT);
	private EntityAIAvoidEntity<EntityPlayer> avoidEntity;
	
	
	private static final DataParameter<Byte> SITTING = EntityDataManager.<Byte>createKey(EntityBird.class, DataSerializers.BYTE);
    /** Coordinates of where the bird spawned. */
    private BlockPos spawnPosition;

    public EntityBird(World worldIn)
    {
        super(worldIn);
        this.setSize(0.25F, 0.25F);
        this.setIsBirdSitting(true);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(SITTING, Byte.valueOf((byte)0));
        this.dataManager.register(BIRD_TYPE, Integer.valueOf(0));
    }
    
    
    
    
    
    /** Returns the volume for the sounds this mob makes.
     **/
    protected float getSoundVolume()
    {
        return 0.1F;
    }

    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getSoundPitch()
    {
        return super.getSoundPitch() * 0.95F;
    }

    @Nullable
    protected SoundEvent getAmbientSound()
    {
        return this.getIsBirdSitting() && this.rand.nextInt(4) != 0 ? null : SoundEvents.ENTITY_BAT_AMBIENT;
    }

    protected SoundEvent getHurtSound()
    {
        return SoundEvents.ENTITY_BAT_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_BAT_DEATH;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return false;
    }

    protected void collideWithEntity(Entity entityIn)
    {
    }

    protected void collideWithNearbyEntities()
    {
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
    }

    public boolean getIsBirdSitting()
    {
        return (((Byte)this.dataManager.get(SITTING)).byteValue() & 1) != 0;
    }

    public void setIsBirdSitting(boolean isSitting)
    {
        byte b0 = ((Byte)this.dataManager.get(SITTING)).byteValue();

        if (isSitting)
        {
            this.dataManager.set(SITTING, Byte.valueOf((byte)(b0 | 1)));
        }
        else
        {
            this.dataManager.set(SITTING, Byte.valueOf((byte)(b0 & -2)));
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.getIsBirdSitting())
        {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
            this.posY = (double)MathHelper.floor(this.posY) + 0.03D;
        }
        else
        {
            this.motionY *= 0.6000000238418579D;
        }
    }

    protected void updateAITasks()
    {
        super.updateAITasks();
        BlockPos blockpos = new BlockPos(this);
        BlockPos blockpos1 = blockpos.down();

        if (this.getIsBirdSitting())
        {
            if (this.world.getBlockState(blockpos1).isNormalCube())
            {
                if (this.rand.nextInt(200) == 0)
                {
                    this.rotationYawHead = (float)this.rand.nextInt(360);
                }

                if (this.world.getNearestPlayerNotCreative(this, 5.0D) != null)
                {
                    this.setIsBirdSitting(false);
                    this.world.playEvent((EntityPlayer)null, 1025, blockpos, 0);
                }
            }
            else
            {
                this.setIsBirdSitting(false);
                this.world.playEvent((EntityPlayer)null, 1025, blockpos, 0);
            }
        }
        else
        {
            if (this.spawnPosition != null && (!this.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1))
            {
                this.spawnPosition = null;
            }

            if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.distanceSq((double)((int)this.posX), (double)((int)this.posY), (double)((int)this.posZ)) < 4.0D)
            {
                this.spawnPosition = new BlockPos((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
            }

            double d0 = (double)this.spawnPosition.getX() + 0.5D - this.posX;
            double d1 = (double)this.spawnPosition.getY() + 0.1D - this.posY;
            double d2 = (double)this.spawnPosition.getZ() + 0.5D - this.posZ;
            this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
            this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
            this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
            float f = (float)(MathHelper.atan2(this.motionZ, this.motionX) * (180D / Math.PI)) - 90.0F;
            float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
            this.moveForward = 0.5F;
            this.rotationYaw += f1;

            if (this.rand.nextInt(100) == 0 && this.world.getBlockState(blockpos1).isNormalCube())
            {
                this.setIsBirdSitting(true);
            }
        }
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    public void fall(float distance, float damageMultiplier)
    {
    }

    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    {
    }
    
    /**
     * Return whether this entity should NOT trigger a pressure plate or a tripwire.
     */
    public boolean doesEntityNotTriggerPressurePlate()
    {
        return true;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            if (!this.world.isRemote && this.getIsBirdSitting())
            {
                this.setIsBirdSitting(false);
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    public static void registerFixesBird(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityBird.class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setByte("BirdFlags", ((Byte)this.dataManager.get(SITTING)).byteValue());
        compound.setInteger("BirdType", this.getBirdType());
        
    }
    
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.dataManager.set(SITTING, Byte.valueOf(compound.getByte("BirdFlags")));
        this.setBirdType(compound.getInteger("BirdType"));       
    }
    
    public void setBirdType(int birdTypeId)
    {
        this.dataManager.set(BIRD_TYPE, Integer.valueOf(birdTypeId));
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        int i = this.getRandomBirdType();
        boolean flag = false;

        if (livingdata instanceof EntityBird.BirdTypeData)
        {
            i = ((EntityBird.BirdTypeData)livingdata).typeData;
            flag = true;
        }
        else
        {
            livingdata = new EntityBird.BirdTypeData(i);
        }

        this.setBirdType(i);
      
        
        return livingdata;
    }

    private int getRandomBirdType()
    {
        Biome biome = this.world.getBiome(new BlockPos(this));
        int i = this.rand.nextInt(6);
        return (i);
    }
    
    
    public float getEyeHeight()
    {
        return this.height / 2.0F;
    }
    
    public int getBirdType()
    {
        return ((Integer)this.dataManager.get(BIRD_TYPE)).intValue();
    }
    
    public static class BirdTypeData implements IEntityLivingData
    {
        public int typeData;

        public BirdTypeData(int type)
        {
            this.typeData = type;
        }
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_BAT;
    }
}