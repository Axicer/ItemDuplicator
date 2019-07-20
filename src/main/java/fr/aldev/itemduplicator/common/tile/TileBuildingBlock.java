package fr.aldev.itemduplicator.common.tile;

import cjminecraft.core.energy.compat.TileEntityEnergyConsumer;
import ic2.api.energy.tile.IEnergyEmitter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileBuildingBlock extends TileEntityEnergyConsumer implements ITickable{
	
	public TileBuildingBlock() {
		super(1000000,Integer.MAX_VALUE,0,0);
	}
	
	@Override
	public boolean canConnectEnergy(EnumFacing from) {
		return from != EnumFacing.UP && from != EnumFacing.DOWN;
	}
	
	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return side != EnumFacing.UP && side!= EnumFacing.DOWN;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.storage.readFromNBT(compound.getCompoundTag("energy"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		this.storage.writeToNBT(compound);
		return super.writeToNBT(compound);
	}

	@Override
	public int getSinkTier() {
		return 5;
	}
	
	@Override
	public void update() {
		if(this.storage.getEnergyStored() == this.storage.getMaxEnergyStored()) {
			
		}
	}
}
