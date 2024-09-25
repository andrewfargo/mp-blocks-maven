package edu.grinnell.csc207;

import edu.grinnell.csc207.blocks.DropShadow;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.Boxed;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests of the new block.
 */
public class TestNewBlock {
  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * Test lines with shadows in every direction.
   */
  @Test
  public void testShadowLine() {
    Line hello = new Line("Hello, world!");
    assertEquals(""
		 + "Hello, world!#\n"
		 + "##############\n",
		 TestUtils.toString(new DropShadow(hello, VAlignment.BOTTOM, HAlignment.RIGHT)));
  } // testShadowLine

  /**
   * Test changed lines with shadows.
   */
  @Test
  public void testShadowChangedLine() {

  } // testShadowChangedLine

  /**
   * Test lines with boxes in every direction.
   */
  @Test
  public void testShadowBoxes() {

  } // testShadowBoxes

  /**
   * Test shadows with changed rectangles.
   */
  @Test
  public void testShadowChangedRect() {

  }
  
  /**
   * Test shadows with composition.
   */
  @Test
  public void testShadowComposites() {

  } // testShadowComposites
  
  /**
   * Test nested shadows.
   */
  @Test
  public void testNestedShadows() {

  } // testNestedShadows()

  /**
   * Test shadows of empties.
   */
  @Test
  public void testShadowEmpty() {

  } // testShadowEmpty()
  

} // class TestNewBlock
