package net.tardis.mod.common.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.tardis.mod.common.items.components.ItemComponent;
import net.tardis.mod.common.recipes.RepairRecipes;
import net.tardis.mod.common.tileentity.TileEntityComponentRepair;

public class ContainerCR extends Container
{
    private final IInventory tileFurnace;

    public ContainerCR(InventoryPlayer playerInventory, IInventory furnaceInventory)
    {
        this.tileFurnace = furnaceInventory;
        this.addSlotToContainer(new Slot(furnaceInventory, 0, 38, 35));
        this.addSlotToContainer(new Slot(furnaceInventory, 1, 61, 35));
        this.addSlotToContainer(new SlotOutput(furnaceInventory, 2, 121, 35));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		if(index > 2 && this.getSlot(0).getStack().isEmpty()) {
			if(this.getSlot(index).getStack().getItem() instanceof ItemComponent) {
				this.getSlot(0).putStack(this.getSlot(index).getStack().copy());
				this.getSlot(index).putStack(ItemStack.EMPTY);
				return this.getSlot(index).getStack();
			}
		}
		else if(index == 0 && !this.getSlot(0).getStack().isEmpty()) {
			playerIn.addItemStackToInventory(this.getSlot(0).getStack().copy());
			this.getSlot(0).putStack(ItemStack.EMPTY);
		}
		else if(index > 2 && this.getSlot(1).getStack().isEmpty() && RepairRecipes.recipes.containsValue(this.getSlot(index).getStack().getItem())) {
			this.getSlot(1).putStack(this.getSlot(index).getStack().copy());
			this.getSlot(index).putStack(ItemStack.EMPTY);
		}
		else if(index == 1 && !this.getSlot(1).getStack().isEmpty()) {
			playerIn.addItemStackToInventory(this.getSlot(1).getStack());
			this.getSlot(1).putStack(ItemStack.EMPTY);
		}
		else if(index == 2 && !this.getSlot(2).getStack().isEmpty()) {
			playerIn.addItemStackToInventory(this.getSlot(2).getStack().copy());
			this.getSlot(2).putStack(ItemStack.EMPTY);
		}
		return ItemStack.EMPTY;
	}
	
	public int getProgress() {
		return this.tileFurnace.getField(0);
	}

	public TileEntityComponentRepair getTile() {
		return (TileEntityComponentRepair)tileFurnace;
	}

	public static class SlotOutput extends Slot{

		public SlotOutput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			return false;
		}
		
	}
}