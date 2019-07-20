package fr.aldev.itemduplicator.common.energy;

public interface IMatterEnergy {

	public void remove(float energy);
	public void add(float energy);
	public void set(float energy);
	
	public float getMatterEnergy();
}
