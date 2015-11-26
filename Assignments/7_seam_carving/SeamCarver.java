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
    // printEnergies(energies);
  }

  private void calculateEnergies() {
    // for every row/height/y
    for (int y = 0; y < height(); y++) {
      // for every column/width/x
      for (int x = 0; x < width(); x++) {
        // calculate its energy using formula
        energies[x][y] = energy(x, y);
      }
    }
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

    // if value is already calculated, return it
    if (energies[x][y] != 0.00)     return energies[x][y];
    // if value is NOT calculated and its borded pixel, return value for border
    else if (isBorderPixel(x, y))   return 1000.00;
    // if value is NOT calculated, calculate energy using the formula
    else                            return energyOf(x, y);
  }

  private boolean isBorderPixel(int x, int y) {
    return ((x >= 0 && y == 0) || (x >= 0 && y == height() - 1)
         || (x == 0 && y >= 0) || (x == width() - 1 && y >= 0));
  }

  private double energyOf(int x, int y) {
    double deltaX = calculateDeltaX(x, y);
    double deltaY = calculateDeltaY(x, y);
    return Math.sqrt(deltaX + deltaY);
  }

  private double calculateDeltaX(int x, int y) {
    Color color1        = picture.get(x - 1, y);
    int red1            = color1.getRed();
    int green1          = color1.getGreen();
    int blue1           = color1.getBlue();

    Color color2        = picture.get(x + 1, y);
    int red2            = color2.getRed();
    int green2          = color2.getGreen();
    int blue2           = color2.getBlue();

    double deltaRed     = red2 - red1 * 1.00;
    double deltaGreen   = green2 - green1 * 1.00;
    double deltaBlue    = blue2 - blue1 * 1.00;

    return deltaSquaredOf(deltaRed, deltaGreen, deltaBlue);
  }

  private double calculateDeltaY(int x, int y) {
    Color color1      = picture.get(x, y - 1);
    int red1          = color1.getRed();
    int green1        = color1.getGreen();
    int blue1         = color1.getBlue();

    Color color2      = picture.get(x, y + 1);
    int red2          = color2.getRed();
    int green2        = color2.getGreen();
    int blue2         = color2.getBlue();

    double deltaRed      = red2 - red1 * 1.00;
    double deltaGreen    = green2 - green1 * 1.00;
    double deltaBlue     = blue2 - blue1 * 1.00;

    return deltaSquaredOf(deltaRed, deltaGreen, deltaBlue);
  }

  private double deltaSquaredOf(double delta1, double delta2, double delta3) {
    double squaredDelta = 0.00;
    squaredDelta += Math.pow(delta1, 2);
    squaredDelta += Math.pow(delta2, 2);
    squaredDelta += Math.pow(delta3, 2);
    return squaredDelta;
  }

  // sequence of indices for horizontal seam
  public int[] findHorizontalSeam() {
    return null;
  }

  // sequence of indices for vertical seam
  public int[] findVerticalSeam() {
    double smallestTotalEnergy = 1001.00 * height();
    int[] bestSeam = new int[height()];

    // for every column in matrix
    for (int x = 0; x < width(); x++) {
      int[] seam = new int[height()];
      seam[0] = x;

      int customX = x;
      // TODO re-do to use algorithm w/ topological order
      // instead of the current lazy approach (which will cost points)
      // find shortest path from top to bottom
      for (int y = 1; y < height(); y++) {
        seam[y] = indexOfMinEnergy(customX, y);
        customX = seam[y];
      }
      double totalEnergyOfSeam = totalEnergy(seam);

      // printSeam(seam);

      // if its total energy is less than smallest seam's total energy
      if (totalEnergyOfSeam < smallestTotalEnergy) {
        // assign current seam as smallest one
        smallestTotalEnergy = totalEnergyOfSeam;
        bestSeam = seam;
      }
    }

    return bestSeam;
  }

  private int indexOfMinEnergy(int x, int y) {
    int bestIndex = -1;
    double lowestEnergy = 1001.00;
    if (isValid(x - 1, y) && energy(x - 1, y) < lowestEnergy) {
      bestIndex = x - 1;
      lowestEnergy = energy(x - 1, y);
    }
    if (isValid(x, y) && energy(x, y) < lowestEnergy) {
      bestIndex = x;
      lowestEnergy = energy(x, y);
    }
    if (isValid(x + 1, y) && energy(x + 1, y) < lowestEnergy) {
      bestIndex = x + 1;
      lowestEnergy = energy(x + 1, y);
    }

    if (bestIndex == -1)    return x;
    else                    return bestIndex;
  }

  private double totalEnergy(int[] seam) {
    double total = 0.00;
    for (int i = 0; i < seam.length; i++)
    { total += seam[i]; }
    return total;
  }

  private boolean isValid(int x, int y) {
    return (x >= 0 && x < width() && y >= 0 && y < height());
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

  // use for debugging energy matrix
  private void printEnergies(double[][] energies) {
    StdOut.println("\n##### Printing energies #####");
    for (int y = 0; y < energies[0].length; y++) {
      for (int x = 0; x < energies.length; x++)
      { StdOut.print(energies[x][y] + "\t"); }
      StdOut.println();
    }
    StdOut.println("\n##### End of energies #####");
  }

  // use for debugging seam(s)
  private void printSeam(int[] seam) {
    StdOut.print("\n##### Printing seam #####\n{ ");
    for (int i = 0; i < seam.length; i++)
    { StdOut.print(seam[i] + " "); }
    StdOut.println("}\n##### End of seam #####");
  }
}
