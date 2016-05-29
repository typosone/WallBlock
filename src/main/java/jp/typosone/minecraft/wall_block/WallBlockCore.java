package jp.typosone.minecraft.wall_block;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = "wall_block",
        name = "Wall Block",
        version = "0.2"
)
public class WallBlockCore {
    @Mod.Instance("wall_block")
    public static WallBlockCore instance;

    @SidedProxy(
            clientSide = "jp.typosone.minecraft.wall_block.ClientProxy",
            serverSide = "jp.typosone.minecraft.wall_block.CommonProxy"
    )
    public static CommonProxy proxy;

    public static Block wall;
    public static Block generator;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.registerBlock();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerTileEntity();
        proxy.registerRecipe();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    public static class Log {
        public static Logger logger = LogManager.getLogger("WallBlock");

        @SuppressWarnings("unused")
        public static void trace(String msg) {
            logger.trace(msg);
        }

        @SuppressWarnings("unused")
        public static void i(String msg) {
            logger.info(msg);
        }

        @SuppressWarnings("unused")
        public static void w(String msg) {
            logger.warn(msg);
        }
    }
}
