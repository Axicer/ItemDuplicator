package fr.aldev.itemduplicator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import fr.aldev.itemduplicator.common.blocks.BlockBuildingBlock;
import fr.aldev.itemduplicator.common.tile.TileBuildingBlock;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(
        modid = ItemDuplicator.MOD_ID,
        name = ItemDuplicator.MOD_NAME,
        version = ItemDuplicator.VERSION
)
public class ItemDuplicator {

    public static final String MOD_ID = "itemduplicator";
    public static final String MOD_NAME = "ItemDuplicator";
    public static final String VERSION = "2019.1-1.2.23";

    public static Logger logger;

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static ItemDuplicator INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @SuppressWarnings("deprecation")
	@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();

        BlockBuildingBlock buildingBlock = new BlockBuildingBlock();// création d'un bloc
        ForgeRegistries.BLOCKS.register(buildingBlock);// enregistrement

        ItemBlock itemBuildingBlock = new ItemBlock(buildingBlock);// création du block item lié
        itemBuildingBlock.setRegistryName(buildingBlock.getRegistryName()); //définition du nom de registre du block
        ForgeRegistries.ITEMS.register(itemBuildingBlock);// enregistrement

        ModelResourceLocation buildingBlockModelResourceLocation = new ModelResourceLocation(MOD_ID + ":" + BlockBuildingBlock.REGISTRY_NAME, "normal"); //récupération du modele du block
        ModelLoader.setCustomModelResourceLocation(itemBuildingBlock, 0, buildingBlockModelResourceLocation); //assignation du model
        
        GameRegistry.registerTileEntity(TileBuildingBlock.class, MOD_ID+":"+BlockBuildingBlock.REGISTRY_NAME);
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    	logger.log(Level.INFO, "Item Duplicator mod loaded !");
    }
}
