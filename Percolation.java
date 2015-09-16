import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
  private WeightedQuickUnionUF uf; // union-find data structure
  private boolean[] sites; // temporal use to track closed/open/full sites
  private int initialN; // useful for various calculations & initializations
  private int top, bottom; // virtual top & bottom sites (index goes 0 to N-1)

  // create N-by-N grid, with all sites blocked
  public Percolation(int N) {
    if (N <= 0) throw new java.lang.IllegalArgumentException();
    initialN = N;
    uf = new WeightedQuickUnionUF(initialN * initialN + 2);
    sites = new boolean[initialN * initialN + 2];
    top = initialN * initialN;
    bottom = top + 1;
    // set virtual top & bottom to be open sites
    sites[top] = true;
    sites[bottom] = true;
  }

  // public void open(int i, int j) // open site (row i, column j) if it is not open already
  // public boolean isOpen(int i, int j) // is site (row i, column j) open?
  // public boolean isFull(int i, int j) // is site (row i, column j) full?

  // does the system percolate?
  public boolean percolates()
  { return uf.connected(top, bottom); }

  private int coordinatesToIndex(int i, int j)
  { return initialN * (i - 1) + j - 1; }

  // test client (optional)
  public static void main(String[] args) {
    StdOut.println("Testing the constructor of Percolation class.");
    StdOut.print("Creating a Percolation object with 9 sites... ");
    Percolation p = new Percolation(3);
    StdOut.println("The object is successfully created.");

    StdOut.println("Does the system percolate?: " + p.percolates());
  }
}
