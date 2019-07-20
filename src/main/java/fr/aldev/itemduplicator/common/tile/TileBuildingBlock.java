package fr.aldev.itemduplicator.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.energy.IEnergyStorage;

public class TileBuildingBlock extends TileEntity implements IEnergyStorage{

	protected int power;
	protected int capacity;
	protected int outputRate;
	protected int inputRate;
	
	public TileBuildingBlock(int power, int capacity, int inputRate, int outputRate) {
		this.power = Math.max(0 , Math.min(capacity, power));
		this.capacity = capacity;
		this.inputRate = inputRate;
		this.outputRate = outputRate;
	}
	
	public void addEnergy(int energy) {
		this.power = Math.min(this.capacity, this.power+energy);
	}
	
	public void removeEnergy(int energy) {
		this.power = Math.max(0, this.power-energy);
	}
	
	public void setEnergy(int energy) {
		this.power = energy;
	}
	
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		if (!canReceive())
            return 0;

        int energyReceived = Math.min(capacity - maxReceive, Math.min(this.inputRate, maxReceive));
        if (!simulate)
        	maxReceive += energyReceived;
        return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		if (!canExtract())
            return 0;

        int energyExtracted = Math.min(power, Math.min(this.outputRate, maxExtract));
        if (!simulate)
            power -= energyExtracted;
        return energyExtracted;
	}

	@Override
	public int getEnergyStored() {
		return power;
	}

	@Override
	public int getMaxEnergyStored() {
		return capacity;
	}

	@Override
	public boolean canExtract() {
		return outputRate > 0;
	}

	@Override
	public boolean canReceive() {
		return inputRate > 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		 super.readFromNBT(compound);
	     this.power = compound.getInteger("Power");
	}
	
	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("Power", this.power);
        return compound;
    }
	
}
