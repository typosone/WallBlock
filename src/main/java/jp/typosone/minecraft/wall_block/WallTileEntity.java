package jp.typosone.minecraft.wall_block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * 壁ブロック用のTileEntity
 */
public class WallTileEntity extends TileEntity {
    public static final String ID = "wall_block:wall_tile_entity";
    public static final String KEY_GENERATOR_X = "generator x";
    public static final String KEY_GENERATOR_Y = "generator y";
    public static final String KEY_GENERATOR_Z = "generator z";
    public int gx;
    public int gy;
    public int gz;

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        gx = nbtTagCompound.getInteger(KEY_GENERATOR_X);
        gy = nbtTagCompound.getInteger(KEY_GENERATOR_Y);
        gz = nbtTagCompound.getInteger(KEY_GENERATOR_Z);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger(KEY_GENERATOR_X, gx);
        nbtTagCompound.setInteger(KEY_GENERATOR_Y, gy);
        nbtTagCompound.setInteger(KEY_GENERATOR_Z, gz);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
    }


    public void setGeneratorCoords(int x, int y, int z) {
        gx = x;
        gy = y;
        gz = z;
    }
}