package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.DropShadow;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.VComp;

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
       Rect eightyWide = new Rect(' ', 80, 1);
       VComp image = new VComp(HAlignment.CENTER, clock, eightyWide);
       PrintWriter pen = new PrintWriter(System.out, true);
       AsciiBlock.print(pen, image);
       pen.close();
   } // main(String[])

   /**
    * Create a clock composed of drop-shadowed boxes for numbers 1 through 12,
    * arranged in a grid layout.
    *
    * @return An AsciiBlock representing the clock artwork.
    */
  private static AsciiBlock createClock() throws Exception {
    AsciiBlock[] numbers = new AsciiBlock[12];
    for (int i = 1; i <= 12; i++) {
      String numStr = String.format("%2d", i);
      AsciiBlock box = createDropShadowedBox(numStr);
      numbers[i % 12] = box;
    }
    
    AsciiBlock[] cols = new AsciiBlock[11];
    
    // Col 1: 9
    cols[0] = numbers[9];
    
    // Col 2: 10 & 8
    cols[1] = createColWithHeight(numbers[10], 13, numbers[8]);

    // Col 3: Spacer
    cols[2] = new Rect(' ', 1, 1);
    
    // Col 4: 11 & 7
    cols[3] = createColWithHeight(numbers[11], 20, numbers[7]);

    // Col 5: Spacer
    cols[4] = new Rect(' ', 2, 1);
    
    // Col 6: 12 & 6
    cols[5] = createColWithHeight(numbers[0], 23, numbers[6]);

    // Col 7: Spacer
    cols[6] = new Rect(' ', 2, 1);
    
    // Col 8: 1 & 5
    cols[7] = createColWithHeight(numbers[1], 20, numbers[5]);

    // Col 9: Spacer
    cols[8] = new Rect(' ', 1, 1);

    // Col 10: 2 & 4
    cols[9] = createColWithHeight(numbers[2], 13, numbers[5]);

    // Col 11: 3
    cols[10] = numbers[3];
     
    // Create a grid from the rows
    return new HComp(VAlignment.CENTER, cols);
  } // createClock()

   /**
    * Create a col with specified elements and spaces between them.
    *
    * @param firstBlock The first AsciiBlock element.
    * @param gap Number of spaces between firstBlock and secondBlock.
    * @param secondBlock The second AsciiBlock element.
    *
    * @return An AsciiBlock representing the constructed row.
    * @throws Exception When gap is less than or equal to zero.
    */
  private static AsciiBlock createColWithHeight(AsciiBlock firstBlock, int gap,
						AsciiBlock secondBlock)
    throws Exception {
    int realGap = gap - firstBlock.height() - secondBlock.height();
    Rect gapSpaces = new Rect(' ', 1, realGap);

    AsciiBlock[] arr = new AsciiBlock[] {firstBlock, gapSpaces, secondBlock};
    return new VComp(HAlignment.CENTER, arr);
   } // createRowWithSpaces

   /**
    * Create a drop-shadowed box containing the specified text.
    *
    * @param text The text to be displayed in the box.
    * @return An AsciiBlock representing the drop-shadowed box.
    */
   private static AsciiBlock createDropShadowedBox(String text) {
     // Create the inner box
     Line lineText = new Line(text);
     Boxed boxedText = new Boxed(lineText); 
     // Create the drop shadow with centered alignment
     return new DropShadow(boxedText, VAlignment.BOTTOM, HAlignment.RIGHT);
   } // createDropShadowedBox
} // class Art80x24
