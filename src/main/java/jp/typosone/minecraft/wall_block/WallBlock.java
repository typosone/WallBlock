package jp.typosone.minecraft.wall_block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * 壊れることのない壁ブロック
 */
public class WallBlock extends BlockContainer {
    public static final String NAME = "unbreakable_wall_block";

    public WallBlock() {
        super(Material.rock);
        setBlockName(NAME);
//        setBlockTextureName("stone");
        setStepSound(soundTypeStone);
        setBlockUnbreakable();
        setResistance(6000000.f);
        setLightLevel(1.f);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getMobilityFlag() {
        return 2; // immobility
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {

    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new WallTileEntity();
    }

    @Override
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        WallTileEntity tileEntity = (WallTileEntity) blockAccess.getTileEntity(x, y, z);
        if (tileEntity == null) {
            return Blocks.stone.getIcon(side, 0);
        }
        Block baseBlock = blockAccess.getBlock(tileEntity.gx, tileEntity.gy - 1, tileEntity.gz);
        if (baseBlock instanceof BlockAir) {
            return Blocks.stone.getIcon(side, 0);
        }
        return baseBlock.getIcon(blockAccess, tileEntity.gx, tileEntity.gy - 1, tileEntity.gz, side);
    }


}
