package edu.grinnell.csc207.blocks;

import java.util.Arrays;
import edu.grinnell.csc207.util.Subdivision;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Andrew N. Fargo
 */
public class HComp implements AsciiBlock {
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
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param leftBlock
   *   The block on the left.
   * @param rightBlock
   *   The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock,
      AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(Alignment, AsciiBLOCK[])

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
    int height = this.height();

    if (i < 0 || i > height - 1) {
      throw new Exception("Invalid row: " + i);
    } // if

    for (AsciiBlock block : this.blocks) {
      Subdivision div = new Subdivision(this.align, height, block.height());
      Subdivision.Alignment condition = div.getAlignment(i);
      switch (condition) {
	case ANTE:
	case POST:
	  ret += " ".repeat(block.width());
	  break;
	case CENTER:
	  ret += block.row(i);
	default:
	  throw new Exception("Invalid alignment condition: " + condition);
      }
    } // for block
    return ret;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int max = 0;
    for (AsciiBlock block : this.blocks) {
      if (block.height() > max) {
	max = block.height();
      } // if
    } // for block
    return max;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int sum = 0;
    for (AsciiBlock block : this.blocks) {
      sum += block.width();
    } // for
    return sum;
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
    return (other instanceof HComp) && this.eqv((HComp) other);
  } // eqv(AsciiBlock)

  /**
   * Determine if another HComp block is structurally equivalent to this one.
   * @param other
   *   The other HComp block.
   * @return true if the two blocks are structurally equivalend and
   *   false otherwise.
   */
  public boolean eqv(HComp other) {
    boolean alignEq = this.align == other.align;
    boolean lenEq = this.blocks.length == other.blocks.length;
    if (!alignEq || !lenEq) {
      return false;
    }

    for (int i = 0; i < this.blocks.length; i++) {
      if (!this.blocks[i].eqv(other.blocks[i])) {
	return false;
      } // if
    } // for i
    return true;
  } // eqv(HComp)
} // class HComp
