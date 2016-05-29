package jp.typosone.minecraft.wall_block;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.world.World;

/**
 * クライアント固有の初期化処理を担当
 */
public class ClientProxy extends CommonProxy {
    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    @Override
    public void registerBlock() {
        super.registerBlock();
    }

    @Override
    public void registerTileEntity() {
        super.registerTileEntity();
    }
}
