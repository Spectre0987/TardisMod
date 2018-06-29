package net.tardis.mod.common.items;

public class ItemManual extends ItemBase {
	
	public ItemManual() {
		this.setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation("manual.help"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
}
