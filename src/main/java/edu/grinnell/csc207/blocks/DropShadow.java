package edu.grinnell.csc207.blocks;

/**
 * An ASCII block with a specified drop shadow.
 *
 * @author Andrew N. Fargo
 * @author Bonsen Yusuf
 */
public class DropShadow implements AsciiBlock {
  /**
   * The stuff casting the shadow.
   */
  AsciiBlock contents;

  /**
   * Character used to shade.
   */
  private static final String SHADOW_CHAR = "#";

  /**
   * Vertical angle of the drop shadow.
   */
  VAlignment vAngle;

  /**
   * Horizontal angle of the drop shadow.
   */
  HAlignment hAngle;

  /**
   * Create a new DropShadow.
   * @param block The contents of the dropshadow
   * @param vertical The vertical direction of the shadow
   * @param horizontal The horizontal direction of the shadow
   */
  public DropShadow(AsciiBlock block, VAlignment vertical, HAlignment horizontal) {
    this.contents = block;
    this.vAngle = vertical;
    this.hAngle = horizontal;
  } // DropShadow(AsciiBLock, VAlignment, HAlignment)

  /**
   * Get the row i of the drop shadow assuming that
   * it is not a shadow-only row.
   *
   * @param i The row index; may not be 0 if top shadow or height - 1 if bottom.
   * @return The row of text.
   * @throws Exception If the row of the content block is invalid.
   */
  private String contentRow(int i) throws Exception {
    // Get the row of the contents block
    int realRow = i;
    if (this.vAngle == VAlignment.TOP || this.vAngle == VAlignment.CENTER) {
      realRow--;
    } // if

    // Are we printing a space where a shadow should go?
    boolean hasDropChar = (realRow == 0
                           && this.vAngle == VAlignment.BOTTOM)
        || (realRow == this.contents.height() - 1
            && this.vAngle == VAlignment.TOP);

    String printChar = hasDropChar ? " " : DropShadow.SHADOW_CHAR;

    String leftChar = "";
    String rightChar = "";

    switch (this.hAngle) {
      case LEFT:
        leftChar = printChar;
        break;
      case CENTER:
        leftChar = printChar;
        rightChar = printChar;
        break;
      default: // Right
        rightChar = printChar;
        break;
    } // switch

    return leftChar + this.contents.row(realRow) + rightChar;
  } // contentRow(int)

  /**
   * Get the row i of the drop shadow assuming that
   * it is a shadow-only row.
   *
   * @param i The row index
   * @return The row of text
   */
  private String shadowRow(int i) {
    // We're certainly drawing some shadow.
    String shadowMiddle = DropShadow.SHADOW_CHAR.repeat(this.width() - 2);
    // We may or may not have shadows on the left and right, let's assume we don't.
    String shadowLeft = " ";
    String shadowRight = " ";

    switch (this.hAngle) {
      case LEFT:
        shadowLeft = DropShadow.SHADOW_CHAR;
        break;
      case CENTER:
        shadowLeft = DropShadow.SHADOW_CHAR;
        shadowRight = DropShadow.SHADOW_CHAR;
        break;
      default: // Right
        shadowRight = DropShadow.SHADOW_CHAR;
        break;
    } // switch
    return shadowLeft + shadowMiddle + shadowRight;
  } // shadowRow(int)

  /**
   * Get a row of text from the DropShadow block.
   *
   * @param i The row index.
   * @return The row string.
   * @throws Exception If the row is out of bounds.
   */
  public String row(int i) throws Exception {
    int height = this.height();
    int width = this.width();
    if (i < 0 || i >= height) {
      throw new Exception("Invalid row: " + i);
    } else if (height == 0 || width == 0) {
      return ""; // Empty objects don't cast shadows
    } // if/else

    boolean topShadow = this.vAngle == VAlignment.TOP;
    boolean bottomShadow = this.vAngle == VAlignment.BOTTOM;

    // But center shadows have both

    topShadow |= this.vAngle == VAlignment.CENTER;
    bottomShadow |= this.vAngle == VAlignment.CENTER;

    if ((topShadow && i == 0) || (bottomShadow && i == height - 1)) {
      return shadowRow(i);
    } else {
      return contentRow(i);
    } // else
  } // row(int)

  /**
   * Return the width of this block.
   * @return The number of columns.
   */
  public int width() {
    // An empty block doesn't cast a shadow
    if (this.contents.width() <= 0) {
      return 0;
    } // if
    switch (this.hAngle) {
      case CENTER:
        return this.contents.width() + 2;
      default:
        return this.contents.width() + 1;
    } // switch hAngle
  } // width

  /**
   * Return the height of this block.
   * @return The number of rows.
   */
  public int height() {
    // An empty block doesn't cast a shadow
    if (this.contents.height() <= 0) {
      return 0;
    } // if
    switch (this.vAngle) {
      case CENTER:
        return this.contents.height() + 2;
      default:
        return this.contents.height() + 1;
    } // switch vAngle
  } // height

  /**
   * Determine if `other` is structurally equivalent to this block.
   * @param other The block to compare.
   * @return true if the blocks are structurally equivalent, false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return (other instanceof DropShadow) && this.eqv((DropShadow) other);
  } // eqv(AsciiBlock)

  /**
   * Determine if the DropShadow block is structurally equivalent to this one.
   * @param other The DropShadow to compare.
   * @return true if the blocks are structurally equivalent, false otherwise.
   */
  public boolean eqv(DropShadow other) {
    return this.vAngle == other.vAngle
      && this.hAngle == other.hAngle
      && this.contents.eqv(other.contents);
  } // eqv (DropShadow)
} // class DropShadow

