package edu.grinnell.csc207.blocks;

/**
 * Represents an empty block of a specific size.
 */
public class EmptyBlock implements AsciiBlock {
    int width;
    int height;

    public EmptyBlock(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String row(int i) throws Exception {
        if (i < 0 || i >= height) {
            throw new Exception("Invalid row: " + i);
        }
        return " ".repeat(width);
    }

    @Override
    public int width() {
        return this.width;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public boolean eqv(AsciiBlock other) {
        return (other instanceof EmptyBlock)
                && this.width == ((EmptyBlock) other).width
                && this.height == ((EmptyBlock) other).height;
    }
}
