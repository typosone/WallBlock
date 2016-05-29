package jp.typosone.minecraft.wall_block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * 壁を生成するジェネレータブロックです
 */
public class WallGenerator extends Block {
    public static final String NAME = "wall_generator";
    public static final String FRONT_TEXTURE_NAME = "wall_block:generator_front";
    public static final String OTHER_TEXTURE_NAME = "stone";

    @SideOnly(Side.CLIENT)
    private IIcon frontIcon;

    @SideOnly(Side.CLIENT)
    private IIcon otherIcon;

    protected WallGenerator() {
        super(Material.iron);
        setBlockName(NAME);
        setBlockTextureName(FRONT_TEXTURE_NAME);
        setStepSound(soundTypeMetal);
        setCreativeTab(CreativeTabs.tabRedstone);
        setHardness(1.0f);
        setResistance(2000.f);
        setLightLevel(1.f);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public int getMobilityFlag() {
        return 2; // immobility
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
                                EntityLivingBase entityLivingBase, ItemStack itemStack) {
        int l = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.f / 360.f) + 2.5d) & 3;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
        generateWall(x, y, z, world.getBlockMetadata(x, y, z));
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!world.isRemote) {
            world.scheduleBlockUpdate(x, y, z, this, 1);
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            if (!world.isBlockIndirectlyGettingPowered(x, y, z)) {
                generateWall(x, y, z, world.getBlockMetadata(x, y, z));
            } else {
                clearWall(x, y, z, world.getBlockMetadata(x, y, z));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        frontIcon = register.registerIcon(FRONT_TEXTURE_NAME);
        otherIcon = register.registerIcon(OTHER_TEXTURE_NAME);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? otherIcon :
                (side == 0 ? otherIcon :
                        (meta == 2 && side == 2 ? frontIcon :
                                (meta == 3 && side == 5 ? frontIcon :
                                        (meta == 0 && side == 3 ? frontIcon :
                                                (meta == 1 && side == 4 ? frontIcon : otherIcon)))));
    }

    @Override
    public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
        clearWall(x, y, z, meta);
    }

    private void generateWall(int x, int y, int z, int meta) {
        World world = Minecraft.getMinecraft().theWorld;
        if (meta == 1 || meta == 3) {
            for (int dz = -1; dz < 2; dz++) {
                for (int dy = 2; dy < 5; dy++) {
                    world.setBlock(x, y + dy, z + dz, WallBlockCore.wall);
                    ((WallTileEntity) world.getTileEntity(x, y + dy, z + dz))
                            .setGeneratorCoords(x, y, z);
                }
            }
            return;
        }
        for (int dx = -1; dx < 2; dx++) {
            for (int dy = 2; dy < 5; dy++) {
                world.setBlock(x + dx, y + dy, z, WallBlockCore.wall);
                ((WallTileEntity) world.getTileEntity(x + dx, y + dy, z))
                        .setGeneratorCoords(x, y, z);
            }
        }
    }

    private void clearWall(int x, int y, int z, int meta) {
        World world = Minecraft.getMinecraft().theWorld;
        if (meta == 1 || meta == 3) {
            for (int dz = -1; dz < 2; dz++) {
                for (int dy = 2; dy < 5; dy++) {
                    world.setBlockToAir(x, y + dy, z + dz);
                }
            }
        } else if (meta == 0 || meta == 2) {
            for (int dx = -1; dx < 2; dx++) {
                for (int dy = 2; dy < 5; dy++) {
                    world.setBlockToAir(x + dx, y + dy, z);
                }
            }
        }
    }
}
