package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.DropShadow;
import edu.grinnell.csc207.blocks.EmptyBlock; 
import edu.grinnell.csc207.blocks.GridBlock;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.VAlignment;

import java.io.PrintWriter;

/**
 * Create and print an amazing 80x24 ASCII artwork that resembles a clock.
 * The clock consists of drop-shadowed numbers from 1 to 12, arranged in a grid layout.
 *
 * @author Bonsen Yusuf
 * @author Drew Fargo
 */
public class Art80x24 {

   /**
    * Create the artwork.
    *
    * @param args Command-line arguments (currently ignored).
    *
    * @exception Exception If something goes wrong with one of the underlying classes.
    */
   public static void main(String[] args) throws Exception {
       AsciiBlock clock = createClock();
       PrintWriter pen = new PrintWriter(System.out, true);
       AsciiBlock.print(pen, clock);
       pen.close();
   } // main(String[])

   /**
    * Create a clock composed of drop-shadowed boxes for numbers 1 through 12,
    * arranged in a grid layout.
    *
    * @return An AsciiBlock representing the clock artwork.
    */
   private static AsciiBlock createClock() {
       AsciiBlock[] rows = new AsciiBlock[11];

       // Row 1: 5 spaces, 12, 5 spaces
       rows[0] = createRowWithSpaces(25, createDropShadowedBox("12"), 5, createEmptyBlock(0, 1), 0);

       // Row 2: 2 spaces, 11, 5 spaces, 1, 2 spaces
       rows[1] = createRowWithSpaces(2, createDropShadowedBox("11"), 5,
                                      createDropShadowedBox("1"), 2);

       // Row 3: 11 spaces
       rows[2] = createEmptyBlock(11, 1); // Assuming height of 1 for empty rows

       // Row 4: 1 space, 10, 7 spaces, 2, 1 space
       rows[3] = createRowWithSpaces(1, createDropShadowedBox("10"), 7,
                                      createDropShadowedBox("2"), 1);

       // Row 5: 11 spaces
       rows[4] = createEmptyBlock(11, 1);

       // Row 6: 0 spaces, 9, 9 spaces, 3
       rows[5] = createRowWithSpaces(0, createDropShadowedBox("9"), 9,
                                      createDropShadowedBox("3"), 0);

       // Row 7: 11 spaces
       rows[6] = createEmptyBlock(11, 1);

       // Row 8: 1 space, 8, 7 spaces, 4, 1 space
       rows[7] = createRowWithSpaces(1, createDropShadowedBox("8"), 7,
                                      createDropShadowedBox("4"), 1);

       // Row 9: 11 spaces
       rows[8] = createEmptyBlock(11, 1);

       // Row 10: 2 spaces, 7, 5 spaces, 5, 2 spaces
       rows[9] = createRowWithSpaces(2, createDropShadowedBox("7"), 5,
                                      createDropShadowedBox("5"), 2);

       // Row 11: 5 spaces, 6, 5 spaces
       rows[10] = createRowWithSpaces(5, createDropShadowedBox("6"), 5, createEmptyBlock(0, 1), 0);

       // Create a grid from the rows
       return new GridBlock(rows);
   } // createClock()

   /**
    * Create a row with specified elements and spaces between them.
    *
    * @param firstSpace Number of leading spaces.
    * @param firstBlock The first AsciiBlock element.
    * @param firstGap Number of spaces between firstBlock and secondBlock.
    * @param secondBlock The second AsciiBlock element.
    * @param secondSpace Number of trailing spaces.
    *
    * @return An AsciiBlock representing the constructed row.
    */
   private static AsciiBlock createRowWithSpaces(int firstSpace, AsciiBlock firstBlock,
                                                 int firstGap, AsciiBlock secondBlock,
                                                 int secondSpace) {
       AsciiBlock firstSpaces = createEmptyBlock(firstSpace, 1);
       AsciiBlock gapSpaces = createEmptyBlock(firstGap, 1);
       AsciiBlock trailingSpaces = createEmptyBlock(secondSpace, 1);

       return new GridBlock(new AsciiBlock[]{firstSpaces, firstBlock, gapSpaces, secondBlock, trailingSpaces});
   } // createRowWithSpaces

   /**
    * Create a drop-shadowed box containing the specified text.
    *
    * @param text The text to be displayed in the box.
    * @return An AsciiBlock representing the drop-shadowed box.
    */
   private static AsciiBlock createDropShadowedBox(String text) {
       // Create the inner box
       AsciiBlock boxedText = new Boxed(createEmptyBlock(text.length(), 1)); 
       // Create the drop shadow with centered alignment
       return new DropShadow(boxedText, VAlignment.CENTER, HAlignment.CENTER);
   } // createDropShadowedBox

   /**
    * Create an EmptyBlock with a specified width and height.
    *
    * @param width The width of the empty block.
    * @param height The height of the empty block.
    * @return An EmptyBlock representing the empty spaces.
    */
   private static AsciiBlock createEmptyBlock(int width, int height) {
       return new EmptyBlock(width, height); // Assuming EmptyBlock is defined to handle this
   } // createEmptyBlock
} // class Art80x24