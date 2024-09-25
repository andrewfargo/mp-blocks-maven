package edu.grinnell.csc207;

import edu.grinnell.csc207.blocks.DropShadow;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.Empty;
import edu.grinnell.csc207.blocks.AsciiBlock;
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
    Line mittens = new Line("Schrodinger's Cat: Alive!");
    DropShadow quantumPhysics = new DropShadow(mittens, VAlignment.BOTTOM, HAlignment.RIGHT);
    assertEquals(""
		 + "Schrodinger's Cat: Alive! \n"
		 + " #########################\n",
		 TestUtils.toString(quantumPhysics));
    mittens.update("Schrodinger's Cat: Dead :^(");
    assertEquals(""
		 + "Schrodinger's Cat: Dead :^( \n"
		 + " ###########################\n",
		 TestUtils.toString(quantumPhysics));
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
  public void testShadowChangedRect() throws Exception {
    Rect test = new Rect('a', 3, 3);
    DropShadow shadowRect = new DropShadow(test, VAlignment.BOTTOM, HAlignment.RIGHT);
    assertEquals(""
		 + "aaa \n"
		 + "aaa#\n"
		 + "aaa#\n"
		 + " ###\n",
		 TestUtils.toString(shadowRect));
    test.wider();
    assertEquals(""
		 + "aaaa \n"
		 + "aaaa#\n"
		 + "aaaa#\n"
		 + " ####\n",
		 TestUtils.toString(shadowRect));
    test.shorter();
    assertEquals(""
		 + "aaaa \n"
		 + "aaaa#\n"
		 + " ####\n",
		 TestUtils.toString(shadowRect));
    test.narrower();
    assertEquals(""
		 + "aaa \n"
		 + "aaa#\n"
		 + " ###\n",
		 TestUtils.toString(shadowRect));
    test.taller();
    assertEquals(""
		 + "aaa \n"
		 + "aaa#\n"
		 + "aaa#\n"
		 + " ###\n",
		 TestUtils.toString(shadowRect));
  } // testShadowChangedRect
  
  /**
   * Test shadows with composition.
   */
  @Test
  public void testShadowComposites() throws Exception {
    Rect blockOne = new Rect('1', 3,3);
    Rect blockTwo = new Rect('2', 5,5);
    Rect blockThree = new Rect('3', 1, 1);
    HComp skyline = new HComp(VAlignment.BOTTOM,
			      new AsciiBlock[] {blockOne,
						blockTwo,
						blockThree});
    DropShadow skylineShadow = new DropShadow(skyline, VAlignment.BOTTOM, HAlignment.RIGHT);
    VComp tower = new VComp(HAlignment.CENTER,
			    new AsciiBlock[] {blockOne,
					      blockTwo,
					      blockThree});
    DropShadow towerShadow = new DropShadow(tower, VAlignment.BOTTOM, HAlignment.RIGHT);
    assertEquals(""
		 + "   22222  \n"
		 + "   22222 #\n"
		 + "11122222 #\n"
		 + "11122222 #\n"
		 + "111222223#\n"
		 + " #########\n",
		 TestUtils.toString(skylineShadow));
    assertEquals(""
		 + " 111  \n"
		 + " 111 #\n"
		 + " 111 #\n"
		 + "22222#\n"
		 + "22222#\n"
		 + "22222#\n"
		 + "22222#\n"
		 + "22222#\n"
		 + "  3  #\n"
		 + " #####\n",
		 TestUtils.toString(towerShadow));
  } // testShadowComposites
  
  /**
   * Test nested shadows.
   */
  @Test
  public void testNestedShadows() throws Exception {
    Rect test = new Rect('a', 2, 2);
    DropShadow testShadow = new DropShadow(test, VAlignment.BOTTOM, HAlignment.RIGHT);
    DropShadow doublyShadow = new DropShadow(testShadow, VAlignment.BOTTOM, HAlignment.RIGHT);
    DropShadow oppositeShadow = new DropShadow(testShadow, VAlignment.TOP, HAlignment.LEFT);
    assertEquals(""
		 + "aa  \n"
		 + "aa##\n"
		 + " ###\n"
		 + " ###\n",
		 TestUtils.toString(doublyShadow));
    assertEquals(""
		 + "### \n"
		 + "#aa \n"
		 + "#aa#\n"
		 + "  ##\n",
		 TestUtils.toString(oppositeShadow));
  } // testNestedShadows

  /**
   * Test shadows of empties and small objects.
   */
  @Test
  public void testShadowEmpty() throws Exception {
    Empty test = new Empty();
    DropShadow emptyShadow = new DropShadow(test, VAlignment.BOTTOM, HAlignment.RIGHT);
    assertEquals("", TestUtils.toString(emptyShadow));

    Rect small = new Rect('a', 1, 1);
    DropShadow smallShadow = new DropShadow(small, VAlignment.BOTTOM, HAlignment.RIGHT);
    assertEquals(""
		 + "a \n"
		 + " #\n",
		 TestUtils.toString(smallShadow));
  } // testShadowEmpty
  

} // class TestNewBlock
