package com.codetaylor.mc.athenaeum.util;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class StackHelper {

  public static final String BLOCK_ENTITY_TAG = "BlockEntityTag";

  /**
   * Returns an item stack's {@link NBTTagCompound}. If the stack is empty,
   * returns a new, empty tag.
   *
   * @param itemStack the item stack
   * @return the stack's tag or a new tag if the stack is empty
   */
  @Nonnull
  public static NBTTagCompound getTagSafe(ItemStack itemStack) {

    if (itemStack.isEmpty()) {
      return new NBTTagCompound();
    }

    NBTTagCompound tag = itemStack.getTagCompound();

    if (tag == null) {
      tag = new NBTTagCompound();
      itemStack.setTagCompound(tag);
    }

    return tag;
  }

  @ParametersAreNonnullByDefault
  public static List<ItemStack> copyInto(List<ItemStack> sourceList, List<ItemStack> targetList) {

    for (ItemStack itemStack : sourceList) {
      targetList.add(itemStack.copy());
    }

    return targetList;
  }

  /**
   * Container sensitive decrease stack.
   * <p>
   * ie. bucket
   *
   * @param itemStack      the {@link ItemStack}
   * @param amount         decrease amount
   * @param checkContainer check for container
   * @return the resulting {@link ItemStack}
   * @author codetaylor
   */
  public static ItemStack decrease(ItemStack itemStack, int amount, boolean checkContainer) {

    if (itemStack.isEmpty()) {
      return ItemStack.EMPTY;
    }

    itemStack.shrink(amount);

    if (itemStack.getCount() <= 0) {

      if (checkContainer && itemStack.getItem().hasContainerItem(itemStack)) {
        return itemStack.getItem().getContainerItem(itemStack);

      } else {
        return ItemStack.EMPTY;
      }
    }

    return itemStack;
  }

  /**
   * Spawns an {@link ItemStack} in the world, directly above the given position.
   * <p>
   * Server only.
   *
   * @param world     the world
   * @param itemStack the {@link ItemStack} to spawn
   * @param pos       the position to spawn
   * @author codetaylor
   */
  public static void spawnStackOnTop(World world, ItemStack itemStack, BlockPos pos) {

    StackHelper.spawnStackOnTop(world, itemStack, pos, 1.0);
  }

  /**
   * Spawns an {@link ItemStack} in the world, directly above the given position.
   * <p>
   * Server only.
   *
   * @param world     the world
   * @param itemStack the {@link ItemStack} to spawn
   * @param pos       the position to spawn
   * @author codetaylor
   */
  public static void spawnStackOnTop(World world, ItemStack itemStack, BlockPos pos, double offsetY) {

    EntityItem entityItem = new EntityItem(
        world,
        pos.getX() + 0.5,
        pos.getY() + 0.5 + offsetY,
        pos.getZ() + 0.5,
        itemStack
    );
    entityItem.motionX = 0;
    entityItem.motionY = 0.1;
    entityItem.motionZ = 0;

    world.spawnEntity(entityItem);
  }

  /**
   * Create and write a tile entity's NBT to the block entity tag of an item stack.
   *
   * @param block      the block
   * @param amount     the amount
   * @param meta       the item meta
   * @param tileEntity the TE
   * @return the IS
   */
  public static ItemStack createItemStackFromTileEntity(Block block, int amount, int meta, TileEntity tileEntity) {

    return StackHelper.createItemStackFromTileEntity(Item.getItemFromBlock(block), amount, meta, tileEntity);
  }

  /**
   * Create and write a tile entity's NBT to the block entity tag of an item stack.
   *
   * @param item       the item
   * @param amount     the amount
   * @param meta       the item meta
   * @param tileEntity the TE
   * @return the IS
   */
  public static ItemStack createItemStackFromTileEntity(Item item, int amount, int meta, TileEntity tileEntity) {

    ItemStack itemStack = new ItemStack(item, amount, meta);
    return StackHelper.writeTileEntityToItemStack(tileEntity, itemStack);
  }

  /**
   * Write a tile entity's NBT to the block entity tag of an item stack.
   *
   * @param tileEntity the TE
   * @param itemStack  the IS
   * @return the IS
   */
  public static ItemStack writeTileEntityToItemStack(TileEntity tileEntity, ItemStack itemStack) {

    NBTTagCompound compound = new NBTTagCompound();
    NBTTagCompound teCompound = new NBTTagCompound();
    tileEntity.writeToNBT(teCompound);
    compound.setTag(BLOCK_ENTITY_TAG, teCompound);
    itemStack.setTagCompound(compound);
    return itemStack;
  }

  private StackHelper() {
    //
  }

}
