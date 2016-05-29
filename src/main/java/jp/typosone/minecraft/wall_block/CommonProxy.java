package jp.typosone.minecraft.wall_block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * クライアント/サーバー共通のmod登録処理を行うクラス
 */
public class CommonProxy {
    private static final String[][] recipies =
            {
                    {"SSS", "LDI", "REG"},
                    {"SSS", "LDI", "GER"},
                    {"SSS", "LDR", "IEG"},
                    {"SSS", "LDR", "GEI"},
                    {"SSS", "LDG", "IER"},
                    {"SSS", "LDG", "REI"},

                    {"SSS", "IDL", "REG"},
                    {"SSS", "IDL", "GER"},
                    {"SSS", "IDR", "LEG"},
                    {"SSS", "IDR", "GEL"},
                    {"SSS", "IDG", "LER"},
                    {"SSS", "IDG", "REL"},

                    {"SSS", "RDL", "IEG"},
                    {"SSS", "RDL", "GEI"},
                    {"SSS", "RDI", "LEG"},
                    {"SSS", "RDI", "GEL"},
                    {"SSS", "RDG", "LEI"},
                    {"SSS", "RDG", "IEL"},

                    {"SSS", "GDL", "IER"},
                    {"SSS", "GDL", "REI"},
                    {"SSS", "GDI", "LER"},
                    {"SSS", "GDI", "REL"},
                    {"SSS", "GDR", "LEI"},
                    {"SSS", "GDR", "IEL"},
            };

    public World getClientWorld() {
        return null;
    }

    public void registerBlock() {
        GameRegistry.registerBlock(WallBlockCore.wall = new WallBlock(), WallBlock.NAME);
        GameRegistry.registerBlock(WallBlockCore.generator = new WallGenerator(), WallGenerator.NAME);
    }

    public void registerTileEntity() {
        GameRegistry.registerTileEntity(WallTileEntity.class, WallTileEntity.ID);
    }

    public void registerRecipe() {
        for (String[] set : recipies) {
            GameRegistry.addRecipe(
                    new ItemStack(Item.getItemFromBlock(WallBlockCore.generator)),
                    set[0],
                    set[1],
                    set[2],
                    'S', Blocks.stone,
                    'L', Blocks.lapis_block,
                    'D', Blocks.dispenser,
                    'I', Blocks.iron_block,
                    'R', Blocks.redstone_block,
                    'E', Blocks.enchanting_table,
                    'G', Blocks.glowstone
            );
        }
    }
}
