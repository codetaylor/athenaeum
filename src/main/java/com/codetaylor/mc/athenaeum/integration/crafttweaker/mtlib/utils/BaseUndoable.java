/*
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 Jared
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */
package com.codetaylor.mc.athenaeum.integration.crafttweaker.mtlib.utils;

import crafttweaker.IAction;

/**
 * https://github.com/jaredlll08/MTLib
 */
public abstract class BaseUndoable
    implements IAction {

  // Holds the name of the mod / machine this action manipulates
  protected final String name;

  // Basic indicator, if the action was successful and can be reverted
  protected boolean success = false;

  protected BaseUndoable(String name) {

    this.name = name;
  }

  protected String getRecipeInfo() {

    return "Unknown item";
  }

  @Override
  public String describe() {

    return String.format("Altering %s Recipe(s) for %s", this.name, this.getRecipeInfo());
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == null) {
      return false;
    }

    if (!(obj instanceof BaseUndoable)) {
      return false;
    }

    BaseUndoable u = (BaseUndoable) obj;

    return name.equals(u.name);
  }

  @Override
  public int hashCode() {

    return name.hashCode();
  }

}
