package fr.aldev.itemduplicator.common.blocks;

import fr.aldev.itemduplicator.common.tile.TileBuildingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBuildingBlock extends Block{

	public static final int DEFAULT_CAPACITY = 10000000;
	public static final int DEFAULT_INPUT_RATE = Integer.MAX_VALUE;
	public static final int DEFAULT_OUTPUT_RATE = 0;
    public static final String REGISTRY_NAME = "building_block";
    
    public BlockBuildingBlock() {
        super(Material.ROCK);

        setHardness(1.0f);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setTranslationKey(REGISTRY_NAME);
        setRegistryName(REGISTRY_NAME);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
    	return true;
    }
    
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
    	return new TileBuildingBlock(0, DEFAULT_CAPACITY, DEFAULT_INPUT_RATE, DEFAULT_OUTPUT_RATE);
    }
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    	if(!worldIn.isRemote){
            TileEntity tile = worldIn.getTileEntity(pos);
            if(tile instanceof TileBuildingBlock) {
                TileBuildingBlock tilebuildingblock = (TileBuildingBlock)tile;
                if(facing == EnumFacing.EAST) {
                    tilebuildingblock.addEnergy(500);
                }
                else if(facing == EnumFacing.WEST) {
                    tilebuildingblock.removeEnergy(500);
                }
                playerIn.sendMessage(new TextComponentString("energy: "+tilebuildingblock.getEnergyStored()));
                return true;
            }
        }
        return false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
    	IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();
        
        return getIdFromBlock(block) != getIdFromBlock(this);
    }
    
    @Override
    public BlockRenderLayer getRenderLayer() {
    	return BlockRenderLayer.TRANSLUCENT;
    }
    
    @Override
    public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return 0;
    }
    
    @Override
    public float getAmbientOcclusionLightValue(IBlockState state) {
    	return 1f;
    }

}