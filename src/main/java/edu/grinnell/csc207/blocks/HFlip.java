package edu.grinnell.csc207.blocks;

/**
 * A horizontally flipped ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Bonsen Yusuf
 */
public class HFlip implements AsciiBlock {
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
  public HFlip(AsciiBlock original) {
    this.block = original;
  } // HFlip(AsciiBlock)

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
    if (i < 0 || i >= height()) { //index cannot be negative or out of the height bounds
      throw new Exception("Invalid row index: " + i);
    }
    // Get the original row and reverse it
    String originalRow = block.row(i);
    return new StringBuilder(originalRow).reverse().toString(); // Reverse the string
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return block.height();   // Should be the same height as the original block
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return block.width();   // Should be the same width as original blovk
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
      return false; // ensure the blocks have the same dimensions
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
} // class HFlip
