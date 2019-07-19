package fr.aldev.itemduplicator.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBuildingBlock extends Block{

    public static final String NAME = "building_block";
    private static final String UNLOCALIZED_NAME = "building_block_block";
    private static final String REGISTRY_NAME = "building_block";

    public BlockBuildingBlock() {
        super(Material.ROCK);

        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        // setUnlocalizedName(UNLOCALIZED_NAME);
        setRegistryName(REGISTRY_NAME);
    }

    // @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getLightOpacity(IBlockState state) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getAmbientOcclusionLightValue(IBlockState state) {
        return 16f;
    }
}