import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import java.awt.Color;

public class SeamCarver {
  private Picture picture;

  // create a seam carver object based on the given picture
  public SeamCarver(Picture picture) {
    if (picture == null)  throw new NullPointerException();

    this.picture = new Picture(picture);
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
    double energy = Math.round(Math.sqrt(xGradient + yGradient));
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
    return null;
  }

  // sequence of indices for vertical seam
  public int[] findVerticalSeam() {
    double[][] energies = new double[width()][height()];
    for (int x = 0; x < energies.length; x++) {
      for (int y = 0; y < energies[0].length; y++)
      { energies[x][y] = energy(x, y); }
    }

    int[] seam = new int[height()];
    int x = findStartX(energies);
    seam[0] = x;
    seam[1] = x;

    int count = 2;
    while (count < seam.length) {
      seam[count] = findLowEnergy(x, count);
      x = seam[count];
      count++;
    }
    return seam;
  }

  private int findStartX(double[][] energies) {
    // finding vertex w/ lowest energy to start search from
    int startX = 0;
    double lowest = 1001.00;
    for (int i = 0; i < energies.length; i++) {
      if (energies[i][1] < lowest) {
        lowest = energies[i][1];
        startX = i;
      }
    }
    return startX;
  }

  private int findLowEnergy(int x, int y) {
    int lowIndex = -1;
    double lowNum = 1001.00;
    if (valid(x - 1, y) && energy(x - 1, y) < lowNum) {
      lowIndex = x - 1;
      lowNum = energy(x - 1, y);
    }
    if (valid(x, y) && energy(x, y) < lowNum) {
      lowIndex = x;
      lowNum = energy(x, y);
    }
    if (valid(x + 1, y) && energy(x + 1, y) < lowNum) {
      lowIndex = x + 1;
    }
    return lowIndex;
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
