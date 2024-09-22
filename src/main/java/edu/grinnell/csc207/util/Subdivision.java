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
  public enum Alignment {
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
   * Given an index into the subdivision, return which part
   * of the subdivision the index is in.
   * @param index
   *   An index of the total width (0 <= index < totalWidth)
   * @return An alignment representing the section `index` is in.
   * @throws Exception if index is less than zero or g.e. totalWidth
   */
  Alignment getAlignment(int index) throws Exception {
    int totalwidth = antecontent + content + postcontent;
    if (index < 0 || index >= totalwidth) {
      throw new Exception("Invalid index: " + index);
    } else if (index < antecontent) {
      return Alignment.ANTE;
    } else if (index < antecontent + content) {
      return Alignment.CENTER;
    } else {
      return Alignment.POST;
    } // else
  } // getAlign(int)
} // class Subdivision
