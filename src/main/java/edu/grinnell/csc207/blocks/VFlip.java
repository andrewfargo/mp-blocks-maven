package edu.grinnell.csc207.blocks;

/**
 * A vertically flipped ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Bonsen Yusuf
 */
public class VFlip implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original
   *   The original block.
   */
  public VFlip(AsciiBlock original) {
    this.block = original;
  } // VFlip(AsciiBlock)

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
   *   If the row is invalid.
   */
  public String row(int i) throws Exception {
    if (i < 0 || i >= height()) {
      throw new Exception("Invalid row index: " + i);
    }
    return block.row(height() - 1 - i); // Flip the row vertically, height() - 1 gives the index of the last row in the block and subtracting i from this value effectively maps the row index i to its corresponding row in the original block when viewed upside down.
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return block.height(); // Same height as the original block
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return block.width(); // Same width as the original block
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
    if (height() != other.height() || width() != other.width()) {
      return false; // Blocks must have the same dimensions
    }

    // Compare each row
    try {
      for (int i = 0; i < height(); i++) {
        if (!row(i).equals(other.row(i))) {
          return false;
        }
      }
    } catch (Exception e) {
      return false; // If there's an exception, they're not equivalent
    }

    return true;
  } // eqv(AsciiBlock)
} // class VFlip
