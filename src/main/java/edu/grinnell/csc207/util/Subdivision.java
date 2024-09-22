package edu.grinnell.csc207.util;

import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.VAlignment;

/**
 * Represents subdivision coordinates along one dimension.
 * The rationale for these methods is to unify code for composition,
 * padding, and trimming.
 *
 * A "subdivision" is a representation of where things should
 * go based on alignment.
 *
 * For instance, if we were to subdivide a total width of 9 by a content
 * width of two with each alignment it would look like:
 *
 * bbccccccc // left/top
 *
 * aaabbcccc // center
 *
 * cccccccbb // right/bottom
 *
 * Where "a" is the space before the content, "b" is the content,
 * and "c" is the space after the content.
 *
 * This class contains methods of retrieving both how long each section is,
 * and also the index from the start each section begins.
 * 
 * @author Andrew N. Fargo
 */
public class Subdivision {
  /** How long the antecontent section is. */
  private final int antecontent;
  /** How long the content section is. */
  private final int content;
  /** How long the postcontent section is. */
  private final int postcontent;
  
  /**
   * A generic representation of HAlign and VAlign,
   * defined by how they impact the calculations.
   */
  private enum Alignment {
    /** Either Top or Left. */
    ANTE,
    /** Center in both cases. */
    CENTER,
    /** Either Bottom or Right. */
    POST,
  } // Alignment

  private static Alignment convertHAlign(HAlignment align) {
    switch (align) {
      case LEFT:
	return Alignment.ANTE;
      case CENTER:
	return Alignment.CENTER;
      case RIGHT:
	return  Alignment.POST;
      default:
	throw new RuntimeException("Invalid HAlignment provided.");
    } // switch
  } // convertHAlign(HAlignment)

  private static Alignment convertVAlign(VAlignment align) {
    switch (align) {
      case TOP:
	return Alignment.ANTE;
      case CENTER:
	return Alignment.CENTER;
      case BOTTOM:
	return Alignment.POST;
      default:
	throw new RuntimeException("Invalid VAlignment provided.");
    } // switch
  } // convertVAlign(VAlignment)

  /**
   * Create a subdivision from a horizontal alignment, total width,
   * and the target width.
   * @param align
   *   Alignment left, center, or right.
   * @param totalWidth
   *   How wide of a space we're trying to subdivide.
   * @param targetWidth
   *   The width of the content.
   */
  public Subdivision(HAlignment align, int totalWidth, int targetWidth) {
    this(Subdivision.convertHAlign(align), totalWidth, targetWidth);
  } // Subdivision(HAlignment, int, int)

  /**
   * Create a subdivision from a vertical alignment, total width,
   * and the target width.
   * @param align
   *   Alignment top, center, or bottom.
   * @param totalWidth
   *   How wide of a space we're trying to subdivide.
   * @param targetWidth
   *   The width of the content.
   */
  public Subdivision(VAlignment align, int totalWidth, int targetWidth) {
    this(Subdivision.convertVAlign(align), totalWidth, targetWidth);
  } // Subdivision(VAlignment, int, int)

  /**
   * Create a subdivision from a generic alignment, total width,
   * and the target width. Only for internal use.
   * @param align
   *   Alignment ante, center, or post.
   * @param totalWidth
   *   How wide of a space we're trying to subdivide.
   * @param targetWidth
   *   The width of the content.
   */
  private Subdivision(Subdivision.Alignment align, int totalWidth, int targetWidth) {
    int remainder = totalWidth - targetWidth;
    this.content = targetWidth;
    switch (align) {
      case ANTE:
        this.antecontent = 0;
	this.postcontent = remainder;
	break;
      case CENTER:
        this.antecontent = remainder / 2;
	this.postcontent = totalWidth - this.content - this.antecontent;
	break;
      case POST:
        this.postcontent = 0;
	this.antecontent = remainder;
	break;
      default:
	throw new RuntimeException("Invalid alignment provided.");
    } // switch
  } // Subdivision(Alignment, int, int)

  /**
   * Retrieve the antecontent length.
   * In the example "aaabbcccc" this would return 3.
   * @return Antecontent length.
   */
  public int getAnteLength() {
    return this.antecontent;
  } // getAnteLength()

  /**
   * Retrieve the content length.
   * In the example "aaabbcccc" this would return 2.
   * @return Content length.
   */
  public int getContentLength() {
    return this.content;
  } // getContentLength()

  /**
   * Retrive the postcontent length.
   * In the example "aaabbcccc" this would return 4.
   * @return Postcontent length.
   */
  public int getPostLength() {
    return this.postcontent;
  } // getPostLength()

  /**
   * Retrieve the index of the start of the content.
   * In the example "aaabbcccc" this would return 3,
   * since an array index 3 represents the fourth element,
   * which is the first instance of "b".
   * @return Content offset
   */
  public int getContentOffset() {
    return this.getAnteLength();
  } // getContentOffset()

  /**
   * Retrieve the index of the start of the postcontent.
   * In the example "aaabbcccc" this would return 5,
   * since an array index 5 represents the sixth element,
   * which is the first instance of "c".
   *
   * Note: If the content is the same width as the total width,
   * or if the content is post-aligned, this index will be
   * out of bounds.
   * @return Postcontent offset
   */
  public int getPostOffset() {
    return this.getAnteLength() + this.getContentLength();
  } // getPostOffset()

  /**
   * Retrieve the subdivision lengths as an integer array.
   * @return [Ante, Content, Post]
   */
  public int[] getLengthArray() {
    return new int[] {this.getAnteLength(),
		      this.getContentLength(),
		      this.getPostLength()};
  }

  /**
   * Retrieve the subdivision offsets as an integer array.
   * @return [ContentOffset, PostcontentOffset]
   */
  public int[] getOffsetArray() {
    return new int[] {this.getContentOffset(),
		      this.getPostOffset()};
  }
  
} // class Subdivision
