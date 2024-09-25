package edu.grinnell.csc207.blocks;

import java.util.Arrays;
import edu.grinnell.csc207.util.Subdivision;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Andrew N. Fargo
 */
public class VComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param topBlock
   *   The block on the top.
   * @param bottomBlock
   *   The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock,
      AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBLOCK[])

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    String ret = "";
    if (i < 0) {
      throw new Exception("Invalid row: " + i);
    } // if

    // Find the block at row i
    AsciiBlock current = this.blocks[0];
    int heightThusFar = 0;
    for (int j = 0; j < this.blocks.length; j++) {
      current = this.blocks[j];
      int possibleHeight = heightThusFar + current.height();
      if (possibleHeight > i) {
	break;
      } else {
	heightThusFar += current.height();
      } // if/else
    } // for j

    // Calculate the row of that block to return
    int realRow = i - heightThusFar;

    // Find positional alignment
    Subdivision div = new Subdivision(this.align, this.width(),
				      current.width());
    int[] widths = div.getWidths();
    ret += " ".repeat(widths[0]);
    ret += current.row(realRow);
    ret += " ".repeat(widths[2]);
    return ret;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int sum = 0;
    for (AsciiBlock block : this.blocks) {
      sum += block.height();
    }
    return sum;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int max = 0;
    for (AsciiBlock block : this.blocks) {
      int width = block.width();
      if (width > max) {
	max = width;
      } // if
    } // for block
    return max;
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return (other instanceof VComp) && this.eqv((VComp) other);
  } // eqv(AsciiBlock)

    /**
   * Determine if another VComp block is structurally equivalent to this one.
   * @param other
   *   The other VComp block.
   * @return true if the two blocks are structurally equivalent and
   *   false otherwise.
   */
  public boolean eqv(VComp other) {
    boolean alignEq = this.align == other.align;
    boolean lenEq = this.blocks.length == other.blocks.length;
    if (!alignEq || !lenEq) {
      return false;
    } // if

    for (int i = 0; i < this.blocks.length; i++) {
      if (!this.blocks[i].eqv(other.blocks[i])) {
	return false;
      } // if
    } // for i
    return true;
  } // eqv(VComp)
} // class VComp
