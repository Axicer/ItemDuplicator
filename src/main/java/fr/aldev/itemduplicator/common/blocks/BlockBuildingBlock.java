package fr.aldev.itemduplicator.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBuildingBlock extends Block{

    public static final String REGISTRY_NAME = "building_block";

    public BlockBuildingBlock() {
        super(Material.ROCK);

        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setTranslationKey(REGISTRY_NAME);
        setRegistryName(REGISTRY_NAME);
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
}