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
    if (x < 0 || x >= width() || y < 0 || y >= height())
    { throw new IndexOutOfBoundsException("x and/or y are outside of range"); }
    // account for border pixels
    if (x == 0 || y == 0 || x == width() || y == height())
    { return 1000.0; }

    double xGradient = calculateGradient(x + 1, y, x - 1, y);
    double yGradient = calculateGradient(x, y + 1, x, y - 1);
    double energy = Math.sqrt(xGradient + yGradient);
    return energy;
  }

  private double calculateGradient(int x1, int y1, int x2, int y2) {
    Color color1  = picture.get(x1, y1);
    int red1      = color1.getRed();
    int green1    = color1.getGreen();
    int blue1     = color1.getBlue();

    Color color2  = picture.get(x2, y2);
    int red2      = color2.getRed();
    int green2    = color2.getGreen();
    int blue2     = color2.getBlue();

    double redDelta   = (double)red1 - red2;
    double greenDelta = (double)green1 - green2;
    double blueDelta  = (double)blue1 - blue2;
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
    return null;
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
