package net.tardis.mod.common.entities;


import net.minecraft.client.audio.Sound;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.renderers.entities.RenderRaider;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.Helper;

import java.util.UUID;

public class EntityRaider extends EntityMob {

    public static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(EntityRaider.class, DataSerializers.BOOLEAN);
    public static final DataParameter<String> TYPE = EntityDataManager.createKey(EntityRaider.class, DataSerializers.STRING);

    protected static final IAttribute SPAWN_REINFORCEMENTS_CHANCE = (new RangedAttribute((IAttribute)null, "zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setDescription("Spawn Reinforcements Chance");
    UUID player;
    private NonNullList<ItemStack> inv = NonNullList.withSize(27, ItemStack.EMPTY);

    public EntityRaider(World worldIn) {
        super(worldIn);
        this.stepHeight = 1;
    }


    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));

        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityTNTPrimed.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityDalek.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityArrow.class, 6.0F, 1.0D, 1.2D));


        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }


    protected void applyEntityAI()
    {
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityPigZombie.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCompanion.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityZombie.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));
    }


    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getAttributeMap().registerAttribute(SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(this.rand.nextDouble() * net.minecraftforge.common.ForgeModContainer.zombieSummonBaseChance);

    }


    @Override
    protected void entityInit() {
        super.entityInit();
        this.getDataManager().register(SITTING, false);
        this.getDataManager().register(TYPE, Helper.randomEnum(EntityRaider.EnumRaiderType.class, world.rand).name());
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!world.isRemote && this.dimension == TDimensions.TARDIS_ID && world.getWorldTime() % 20 == 0) {
            TileEntityTardis tardis = null;
            for (TileEntity te : world.loadedTileEntityList) {
                if (te instanceof TileEntityTardis && te.getPos().distanceSq(this.getPosition()) < Math.pow(16, 2)) {
                    tardis = (TileEntityTardis) te;
                    break;
                }
            }
        }
        if (this.isWrongSize()) {
            this.setSize(this.getType().size[0], this.getType().size[1]);
        }
    }

    @Override
    protected boolean canDespawn() {
        return true;
    }

    public EntityRaider.EnumRaiderType getType() {
        return Enum.valueOf(EntityRaider.EnumRaiderType.class, this.getDataManager().get(TYPE));
    }

    public void setType(EntityRaider.EnumRaiderType type) {
        this.getDataManager().set(TYPE, type.name());
    }


    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(this.getType().getName());
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagList list = new NBTTagList();
        for (ItemStack stack : inv) {
            list.appendTag(stack.writeToNBT(new NBTTagCompound()));
        }
        compound.setTag("inv", list);
        compound.setString("type", this.getType().name());
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        int id = 0;
        NBTTagList list = compound.getTagList("inv", Constants.NBT.TAG_COMPOUND);
        for (NBTBase base : list) {
            inv.set(id, new ItemStack((NBTTagCompound) base));
            ++id;
        }
        if (!compound.getString("owner").isEmpty()) player = UUID.fromString(compound.getString("owner"));
        if (compound.getString("type") != null && !compound.getString("type").isEmpty())
            this.setType(Enum.valueOf(EntityRaider.EnumRaiderType.class, compound.getString("type")));

    }


    @Override
    public boolean getAlwaysRenderNameTag() {
        return false;
    }

    public boolean isWrongSize() {
        float[] size = this.getType().size;
        return this.width != size[0] || this.height != size[1];
    }

    public boolean getSit() {
        return this.getDataManager().get(SITTING);
    }

    public void setSit(boolean sit) {
        this.getDataManager().set(SITTING, sit);
    }


    @Override
    public boolean isChild() {
        return false;
    }

    public enum EnumRaiderType {
        RICHARD("richard", "a Raider", RenderRaider.ALEX),
        MATT("matt", "a Raider", RenderRaider.STEVE),
        STEVEN("steven", "a Raider", RenderRaider.STEVE),
        JESSIE("jessie", "a Raider", RenderRaider.ALEX);

        @SideOnly(Side.CLIENT)
        public ModelBase model;
        ResourceLocation skin;
        String formattedName = "";
        float[] size = {0.95F, 1.75F};


        EnumRaiderType(String name, String formatName, ModelPlayer characterBody) {
            skin = new ResourceLocation(Tardis.MODID, "textures/entity/raider/" + name + ".png");
            formattedName = formatName;
            model = characterBody;
        }

        EnumRaiderType(String name, String formattedName, ModelPlayer characterBody, float[] size) {
            this(name, formattedName,characterBody);
            this.size = size;
        }

        public ResourceLocation getTexture() {
            return this.skin;
        }

        public String getName() {
            return this.formattedName;
        }

        @SideOnly(Side.CLIENT)
        public ModelBase getModel() {
            model.isChild = false;
            return model;
        }

        @SideOnly(Side.CLIENT)
        public void setModel(ModelBase model) {
            this.model = model;
        }
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.VINDICATION_ILLAGER_AMBIENT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.VINDICATION_ILLAGER_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_VINDICATION_ILLAGER_HURT;
    }


}
