package com.codetaylor.mc.athenaeum.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class StackHelper {

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

  private StackHelper() {
    //
  }

}
