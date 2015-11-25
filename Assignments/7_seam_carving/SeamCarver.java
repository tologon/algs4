import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import java.awt.Color;

public class SeamCarver {
  private Picture picture;
  private boolean verticalSeam;
  private double energies[][];

  // create a seam carver object based on the given picture
  public SeamCarver(Picture picture) {
    if (picture == null)  throw new NullPointerException();

    this.picture = new Picture(picture);
    verticalSeam = true;
  }

  // current picture
  public Picture picture()
  { return picture; }

  // width of current picture
  public int width()
  { return picture.width(); }

  // height of current picture
  public int height()
  { return picture.height(); }

  // energy of pixel at column x and row y
  public double energy(int x, int y) {
    if (!valid(x, y))
    { throw new IndexOutOfBoundsException("x and/or y are outside of range"); }
    // account for border pixels

    if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1)
    { return 1000.00; }

    double xGradient = calculateGradient(x + 1, y, x - 1, y);
    double yGradient = calculateGradient(x, y + 1, x, y - 1);
    double energy = Math.sqrt(xGradient + yGradient);
    return energy;
  }

  private boolean valid(int x, int y)
  { return !(x < 0 || x >= width() || y < 0 || y >= height()); }

  private double calculateGradient(int x1, int y1, int x2, int y2) {
    Color color1  = picture.get(x1, y1);
    int red1      = color1.getRed();
    int green1    = color1.getGreen();
    int blue1     = color1.getBlue();

    Color color2  = picture.get(x2, y2);
    int red2      = color2.getRed();
    int green2    = color2.getGreen();
    int blue2     = color2.getBlue();

    double redDelta   = (red2 - red1) * 1.00;
    double greenDelta = (green2 - green1) * 1.00;
    double blueDelta  = (blue2 - blue1) * 1.00;
    return (squareOf(redDelta) + squareOf(greenDelta) + squareOf(blueDelta));
  }

  private double squareOf(double num) {
    return Math.pow(num, 2);
  }

  // sequence of indices for horizontal seam
  public int[] findHorizontalSeam() {
    verticalSeam = false;
    int[] seam = findVerticalSeam();
    verticalSeam = true;
    return seam;
  }

  // sequence of indices for vertical seam
  public int[] findVerticalSeam() {
    int width, height;
    if (verticalSeam) {
      width = width();
      height = height();
    } else {
      width = height();
      height = width();
    }
    energies = new double[width][height];

    if (verticalSeam) {
      for (int x = 0; x < energies.length; x++) {
        for (int y = 0; y < energies[0].length; y++)
        { energies[x][y] = energy(x, y); }
      }
    } else {
      for (int x = 0; x < energies[0].length; x++) {
        for (int y = 0; y < energies.length; y++)
        { energies[y][x] = energy(x, y); }
      }
    }

    // printEnergies(energies);

    int[] bestSeam = new int[height];
    int[] seam;
    double lowestTotalEnergy = 1001.00 * height;
    for (int i = 0; i < width; i++) {
      // get a complete path of a seam
      seam = new int[height];
      seam[0] = i;
      int x = i;
      for (int y = 1; y < seam.length; y++) {
        // StdOut.println("PRE-STATE; x: " + x);
        seam[y] = findLowEnergy(x, y);
        x = seam[y];
        // StdOut.println("POST-STATE; x: " + x);
      }

      // get total energy of a seam
      double totalEnergy = 0.00;
      if (verticalSeam) {
        for (int j = 0; j < seam.length; j++)
        { totalEnergy += energy(energies, seam[j], j); }
      } else {
        for (int j = 0; j < seam.length; j++)
        { //StdOut.println("x: " + seam[j] + ", y: " + j);
          totalEnergy += energy(energies, j, seam[j]); }
        //StdOut.println("=====================");
      }
      if (totalEnergy < lowestTotalEnergy) {
        lowestTotalEnergy = totalEnergy;
        bestSeam = seam;
      }
    }

    return bestSeam;
  }

  private void printEnergies() {
    StdOut.println("\n============== Printing newly created 2d array ================");
    StdOut.println("energies.length: " + energies.length);
    StdOut.println("energies[0].length: " + energies[0].length);
    for (int i = 0; i < energies[0].length; i++) {
      for (int j = 0; j < energies.length; j++)
      { StdOut.print(energies[j][i] + " "); }
      StdOut.println();
    }
    StdOut.println("============== End of newly created 2d array ================");
  }

  private int findLowEnergy(int x, int y) {
    int width = energies.length;
    int height = energies[0].length;
    int lowIndex = -1;
    double lowNum = 1001.00;
    if (valid(width, height, x - 1, y)
     && energy(energies, x - 1, y) < lowNum) {
      lowIndex = x - 1;
      lowNum = energy(x - 1, y);
    }
    if (valid(width, height, x, y)
     && energy(energies, x, y) < lowNum) {
      lowIndex = x;
      lowNum = energy(x, y);
    }
    if (valid(width, height, x + 1, y)
     && energy(energies, x + 1, y) < lowNum) {
      lowIndex = x + 1;
    }
    // the code below is for debugging purposes only
    // if (lowIndex == -1) {
    //   StdOut.println("\nALERT!!!!!! WRONG X OR Y");
    //   StdOut.println("x: " + x + ", y: " + y);
    //   StdOut.println("width: " + width + ", height: " + height);
    //   StdOut.println("energies[x][y]: " + energies[x][y]);
    //   printEnergies(energies);
    //   // return x;
    // }
    if (lowIndex != -1)   return lowIndex;
    else                  return x;
  }

  private boolean valid(int width, int height, int x, int y) {
    if (verticalSeam)
    { return x >= 0 && x < width && y >= 0 && y < height; }
    else
    { //StdOut.println("VALID(); x: " + x + ", y: " + y + ", width: " + width + ", height: " + height);
      return x >= 0 && x < height && y >= 0 && y < width; }
  }

  private double energy(double[][] energies, int x, int y) {
    if (verticalSeam)
    { return energies[x][y]; }
    else
    { return energies[y][x]; }
  }

  // remove horizontal seam from current picture
  public void removeHorizontalSeam(int[] seam) {
    if (seam == null)   throw new NullPointerException("seam cannot be null.");
    if (height() <= 1)          throw new IllegalArgumentException();
    if (seam.length != width()) throw new IllegalArgumentException();
  }

  // remove vertical seam from current picture
  public void removeVerticalSeam(int[] seam) {
    if (seam == null)   throw new NullPointerException("seam cannot be null.");
    if (width() <= 1)             throw new IllegalArgumentException();
    if (seam.length != height())  throw new IllegalArgumentException();
  }
}
