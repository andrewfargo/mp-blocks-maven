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
		 + "Hello, world! \n"
		 + " #############\n",
		 TestUtils.toString(new DropShadow(hello, VAlignment.BOTTOM, HAlignment.RIGHT)));
    assertEquals(""
		 + " Hello, world! \n"
		 + "###############\n",
		 TestUtils.toString(new DropShadow(hello, VAlignment.BOTTOM, HAlignment.CENTER)));
    assertEquals(""
		 + " Hello, world!\n"
		 + "############# \n",
		 TestUtils.toString(new DropShadow(hello, VAlignment.BOTTOM, HAlignment.LEFT)));
    assertEquals(""
		 + "############# \n"
		 + "#Hello, world!\n"
		 + "############# \n",
		 TestUtils.toString(new DropShadow(hello, VAlignment.CENTER, HAlignment.LEFT)));
    assertEquals(""
		 + "############# \n"
		 + " Hello, world!\n",
		 TestUtils.toString(new DropShadow(hello, VAlignment.TOP, HAlignment.LEFT)));
    assertEquals(""
		 + "###############\n"
		 + " Hello, world! \n",
		 TestUtils.toString(new DropShadow(hello, VAlignment.TOP, HAlignment.CENTER)));
    assertEquals(""
		 + " #############\n"
		 + "Hello, world! \n",
		 TestUtils.toString(new DropShadow(hello, VAlignment.TOP, HAlignment.RIGHT)));
    assertEquals(""
		 + " #############\n"
		 + "Hello, world!#\n"
		 + " #############\n",
		 TestUtils.toString(new DropShadow(hello, VAlignment.CENTER, HAlignment.RIGHT)));
    assertEquals(""
		 + "###############\n"
		 + "#Hello, world!#\n"
		 + "###############\n",
		 TestUtils.toString(new DropShadow(hello, VAlignment.CENTER, HAlignment.CENTER)));
  } // testShadowLine

  /**
   * Test changed lines with shadows.
   */
  @Test
  public void testShadowChangedLine() {

  } // testShadowChangedLine

  /**
   * Test shadows with rectangles in every direction.
   */
  @Test
  public void testShadowRect() throws Exception {
    Rect test = new Rect('a', 3,3);
    DropShadow shadowRect = new DropShadow(test, VAlignment.BOTTOM, HAlignment.RIGHT);
    assertEquals(""
		 + "aaa \n"
		 + "aaa#\n"
		 + "aaa#\n"
		 + " ###\n",
		 TestUtils.toString(shadowRect));
    shadowRect = new DropShadow(test, VAlignment.BOTTOM, HAlignment.CENTER);
    assertEquals(""
		 + " aaa \n"
		 + "#aaa#\n"
		 + "#aaa#\n"
		 + "#####\n",
		 TestUtils.toString(shadowRect));
    shadowRect = new DropShadow(test, VAlignment.BOTTOM, HAlignment.LEFT);
    assertEquals(""
		 + " aaa\n"
		 + "#aaa\n"
		 + "#aaa\n"
		 + "### \n",
		 TestUtils.toString(shadowRect));
    shadowRect = new DropShadow(test, VAlignment.CENTER, HAlignment.LEFT);
    assertEquals(""
		 + "### \n"
		 + "#aaa\n"
		 + "#aaa\n"
		 + "#aaa\n"
		 + "### \n",
		 TestUtils.toString(shadowRect));
    shadowRect = new DropShadow(test, VAlignment.TOP, HAlignment.LEFT);
    assertEquals(""
		 + "### \n"
		 + "#aaa\n"
		 + "#aaa\n"
		 + " aaa\n",
		 TestUtils.toString(shadowRect));
    shadowRect = new DropShadow(test, VAlignment.TOP, HAlignment.CENTER);
    assertEquals(""
		 + "#####\n"
		 + "#aaa#\n"
		 + "#aaa#\n"
		 + " aaa \n",
		 TestUtils.toString(shadowRect));
    shadowRect = new DropShadow(test, VAlignment.TOP, HAlignment.RIGHT);
    assertEquals(""
		 + " ###\n"
		 + "aaa#\n"
		 + "aaa#\n"
		 + "aaa \n",
		 TestUtils.toString(shadowRect));
    shadowRect = new DropShadow(test, VAlignment.CENTER, HAlignment.RIGHT);
    assertEquals(""
		 + " ###\n"
		 + "aaa#\n"
		 + "aaa#\n"
		 + "aaa#\n"
		 + " ###\n",
		 TestUtils.toString(shadowRect));
    shadowRect = new DropShadow(test, VAlignment.CENTER, HAlignment.CENTER);
    assertEquals(""
		 + "#####\n"
		 + "#aaa#\n"
		 + "#aaa#\n"
		 + "#aaa#\n"
		 + "#####\n",
		 TestUtils.toString(shadowRect));
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
