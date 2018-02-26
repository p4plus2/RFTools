package mcjty.rftools.blocks.logic.analog;

import mcjty.lib.container.EmptyContainer;
import mcjty.rftools.RFTools;
import mcjty.rftools.blocks.logic.generic.LogicSlabBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class AnalogBlock extends LogicSlabBlock<AnalogTileEntity, EmptyContainer> {

    public AnalogBlock() {
        super(Material.IRON, "analog_block", AnalogTileEntity.class, EmptyContainer.class);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, World player, List<String> list, ITooltipFlag advanced) {
        super.addInformation(itemStack, player, list, advanced);
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            list.add(TextFormatting.WHITE + "This logic block can do");
            list.add(TextFormatting.WHITE + "calculations on analog redstone");
        } else {
            list.add(TextFormatting.WHITE + RFTools.SHIFT_MESSAGE);
        }
    }

    @Override
    public int getGuiID() {
        return -1;
    }

    @Override
    protected void checkRedstone(World world, BlockPos pos) {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof AnalogTileEntity) {
            AnalogTileEntity logicTileEntity = (AnalogTileEntity)te;
            EnumFacing inputSide = logicTileEntity.getFacing(world.getBlockState(pos)).getInputSide();
            int power = getInputStrength(world, pos, inputSide);
            int oldPower = logicTileEntity.getPowerLevel();
            logicTileEntity.setPowerInput(power);
            if (oldPower != power) {
                world.notifyNeighborsOfStateChange(pos, this, false);
            }
        }
    }

    @Override
    protected int getRedstoneOutput(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        TileEntity te = world.getTileEntity(pos);
        if (state.getBlock() instanceof AnalogBlock && te instanceof AnalogTileEntity) {
            AnalogTileEntity logicTileEntity = (AnalogTileEntity) te;
            if (side == logicTileEntity.getFacing(state).getInputSide()) {
                return logicTileEntity.getPowerLevel();
            } else {
                return 0;
            }
        }
        return 0;
    }

}