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
  }

  // sequence of indices for horizontal seam
  public int[] findHorizontalSeam() {}

  // sequence of indices for vertical seam
  public int[] findVerticalSeam() {}

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
