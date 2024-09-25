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
   * Represents shadow at direct angle.
   */
  private static final String fullShadowChar = "#";
  
  /**
   * Represents shadow at indirect angle.
   */
  private static final String halfShadowChar = "*";
  
  /**
   * Vertical angle of the drop shadow.
   */
  VAlignment vAngle;
  
  /**
   * Horizontal angle of the drop shadow.
   */
  HAlignment hAngle;

  /**
   * Get the row $i$ of the drop shadow assuming that
   * it is not a shadow-only row.
   *
   * @param i The row index; may not be 0 if top shadow or height - 1 if bottom.
   * @return The row of text.
   * @throws Exception If the row of the content block is invalid.
   */
  private String rowNoSpecialCases(int i) throws Exception {
    // Get the row of the contents block
    int realRow = i;
    if (this.vAngle == VAlignment.TOP || this.vAngle == VAlignment.CENTER) {
      realRow--;
    } // if

    String leftShadow;
    String rightShadow;

    switch(this.hAngle) {
      case LEFT:
	leftShadow = DropShadow.fullShadowChar;
	rightShadow = "";
      case RIGHT:
	leftShadow = "";
	rightShadow = DropShadow.fullShadowChar;
      default: // CENTER
	leftShadow = DropShadow.halfShadowChar;
	rightShadow = DropShadow.halfShadowChar;
    } // switch hAngle

    return leftShadow + this.contents.row(realRow) + rightShadow;
  } // rowNoSpecialCases(int)

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
    } // if
    
    if (this.vAngle == VAlignment.CENTER
	&& (i == 0 || i == height - 1)) {
      // We're printing a weak vertical shadow
      return DropShadow.halfShadowChar.repeat(width);
    } // if
    
    if (this.vAngle == VAlignment.TOP && i == 0
	|| this.vAngle == VAlignment.BOTTOM && i == height - 1) {
      // We're printing a strong vertical shadow
      return DropShadow.fullShadowChar.repeat(width);
    } // if

    return this.rowNoSpecialCases(i);
  } // row(int)

  public int width() {
    switch (this.hAngle) {
      case CENTER:
	return this.contents.width() + 2;
      default:
	return this.contents.width() + 1;
    } // switch hAngle
  } // width

  public int height() {
    switch (this.vAngle) {
      case CENTER:
	return this.contents.height() + 2;
      default:
	return this.contents.height() + 1;
    } // switch vAngle
  } // height
  
  public boolean eqv(AsciiBlock other) {
    return (other instanceof DropShadow) && this.eqv((DropShadow) other);
  } // eqv(AsciiBlock)

  public boolean eqv(DropShadow other) {
    return this.vAngle == other.vAngle &&
      this.hAngle == other.hAngle &&
      this.contents.eqv(other.contents);
  } // eqv (DropShadow)
} // class DropShadow

