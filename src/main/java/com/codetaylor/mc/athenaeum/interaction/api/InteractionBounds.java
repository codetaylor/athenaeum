package com.codetaylor.mc.athenaeum.interaction.api;

import net.minecraft.util.math.AxisAlignedBB;

public final class InteractionBounds {

  public static final AxisAlignedBB BLOCK = new AxisAlignedBB(0, 0, 0, 1, 1, 1);
  public static final AxisAlignedBB SLAB = new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);

  private InteractionBounds() {
    //
  }

}
