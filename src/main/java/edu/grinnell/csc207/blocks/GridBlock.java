package edu.grinnell.csc207.blocks;

/**
 * A grid of multiple AsciiBlocks, allowing for horizontal and vertical arrangements.
 */
public class GridBlock implements AsciiBlock {
    private AsciiBlock[] elements; // Array of elements in the grid
    private int totalHeight;        // Total height of the grid
    private int totalWidth;         // Total width of the grid

    /**
     * Constructs a Grid from an array of AsciiBlocks.
     *
     * @param elements The array of AsciiBlocks to include in the grid.
     */
    public GridBlock(AsciiBlock[] elements) {
        this.elements = elements;
        this.totalHeight = computeTotalHeight(elements);
        this.totalWidth = computeTotalWidth(elements);
    }

    @Override
    public String row(int i) throws Exception {
        if (i < 0 || i >= totalHeight) {
            throw new Exception("Invalid row: " + i);
        }

        StringBuilder rowBuilder = new StringBuilder();
        for (AsciiBlock element : elements) {
            int elementHeight = element.height();
            // Use modulo to get the correct row from the element
            rowBuilder.append(element.row(i % elementHeight));
        }
        return rowBuilder.toString();
    }

    @Override
    public int height() {
        return totalHeight;
    }

    @Override
    public int width() {
        return totalWidth;
    }

    @Override
    public boolean eqv(AsciiBlock other) {
        if (other instanceof GridBlock) { // Changed from Grid to GridBlock
            GridBlock otherGrid = (GridBlock) other; // Changed from Grid to GridBlock
            // Compare heights, widths, and elements of the grids
            if (this.totalHeight != otherGrid.totalHeight || this.totalWidth != otherGrid.totalWidth) {
                return false;
            }
            // Compare each element
            for (int i = 0; i < elements.length; i++) {
                if (!this.elements[i].eqv(otherGrid.elements[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private int computeTotalHeight(AsciiBlock[] elements) {
        int maxHeight = 0;
        for (AsciiBlock element : elements) {
            maxHeight = Math.max(maxHeight, element.height());
        }
        return maxHeight; // Use the maximum height of the elements for the grid height
    }

    private int computeTotalWidth(AsciiBlock[] elements) {
        int width = 0;
        for (AsciiBlock element : elements) {
            width += element.width();
        }
        return width; // Total width is the sum of all widths
    }
}