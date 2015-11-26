import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import java.awt.Color;

public class SeamCarver {
  private Picture picture;
  private double[][] energies;

  // create a seam carver object based on the given picture
  public SeamCarver(Picture picture) {
    if (picture == null)  throw new NullPointerException("picture cannot be null");

    this.picture = new Picture(picture);
    energies = new double[width()][height()];
    calculateEnergies();
    printEnergies(energies);
  }

  private void calculateEnergies() {
    // for every row
    for (int y = 0; y < height(); y++) {
      // for every column
      for (int x = 0; x < width(); x++) {
        // if its a border pixel, give it energy of 1000.00
        if (isBorderPixel(x, y))
        { energies[x][y] = 1000.00; }
        // else calculate its energy using formula
        else
        { energies[x][y] = energy(x, y); }
      }
    }
  }

  private boolean isBorderPixel(int x, int y) {
    return ((x >= 0 && y == 0) || (x >= 0 && y == height() - 1)
         || (x == 0 && y >= 0) || (x == width() - 1 && y >= 0));
  }

  // use for debugging
  private void printEnergies(double[][] energies) {
    StdOut.println("\n##### Printing energies #####");
    for (int y = 0; y < energies[0].length; y++) {
      for (int x = 0; x < energies.length; x++)
      { StdOut.print(energies[x][y] + " "); }
      StdOut.println();
    }
    StdOut.println("\n##### End of energies #####");
  }

  // current picture
  public Picture picture() {
    return picture;
  }

  // width of current picture
  public int width() {
    return picture.width();
  }

  // height of current picture
  public int height() {
    return picture.height();
  }

  // energy of pixel at column x and row y
  public double energy(int x, int y) {
    if (x < 0 || x >= width() || y < 0 || y >= height())
    { throw new IndexOutOfBoundsException("x and/or y outside of the range."); }
    return 0.00;
  }

  // sequence of indices for horizontal seam
  public int[] findHorizontalSeam() {
    return null;
  }

  // sequence of indices for vertical seam
  public int[] findVerticalSeam() {
    return null;
  }

  // remove horizontal seam from current picture
  public void removeHorizontalSeam(int[] seam) {
    if (seam == null)   throw new NullPointerException("h-seam cannot be null");
    if (seam.length != width()) throw new IllegalArgumentException();
    if (height() <= 1)  throw new IllegalArgumentException("nothing to remove");
    // TODO add check for invalid seam, throw IllegalArgumentException

  }

  // remove vertical seam from current picture
  public void removeVerticalSeam(int[] seam) {
    if (seam == null)   throw new NullPointerException("v-seam cannot be null");
    if (seam.length != height()) throw new IllegalArgumentException();
    if (width() <= 1)  throw new IllegalArgumentException("nothing to remove");
    // TODO add check for invalid seam, throw IllegalArgumentException

  }
}
