import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class Percolation {
  private WeightedQuickUnionUF uf; // union-find data structure
  private boolean[] sites; // temporal use to track closed/open/full sites
  private int initialN; // useful for various calculations & initializations
  private int topSite, bottomSite; // virtual top & bottom sites (index goes 0 to N-1)

  // create N-by-N grid, with all sites blocked
  public Percolation(int N) {
    if (N <= 0) throw new IllegalArgumentException();
    initialN = N;
    uf = new WeightedQuickUnionUF(initialN * initialN + 2);
    sites = new boolean[initialN * initialN + 2];
    topSite = initialN * initialN;
    bottomSite = topSite + 1;
  }

  // open site (row i, column j) if it is not open already
  public void open(int i, int j) {
    if (isOpen(i, j)) return;
    int aSite = coordinatesToIndex(i, j);
    sites[aSite] = true;
    // assuming i is a valid index,
    // if it's in a top row, connect it to virtual top site
    if (i == 1)
    { uf.union(topSite, aSite); }
    // if i is in a bottom row, connect it to virtual bottom site
    if (i == initialN)
    { uf.union(bottomSite, aSite); }

    // connect with all valid & open neighbors

    // connect w/ north neighbor
    connectNeighbors(i - 1, j, aSite);
    // connect w/ south neighbor
    connectNeighbors(i + 1, j, aSite);
    // connect w/ east neighbor
    connectNeighbors(i, j + 1, aSite);
    // connect w/ west neighbor
    connectNeighbors(i, j - 1, aSite);
  }

  // assuming that a site at row i & column j is open
  private void connectNeighbors(int i, int j, int aSite) {
    // connect with a given neighbor
    if (isValid(i, j) && isOpen(i, j)) {
      int neighborSite = coordinatesToIndex(i, j);
      uf.union(aSite, neighborSite);
    }
  }

  // checks whether row i and column j are valid
  private boolean isValid(int i, int j) {
    return (i > 0 && i <= initialN)
        && (j > 0 && j <= initialN);
  }

  // is site (row i, column j) open?
  public boolean isOpen(int i, int j) {
    if (!isValid(i, j)) throw new IndexOutOfBoundsException();
    int aSite = coordinatesToIndex(i, j);
    return sites[aSite];
  }

  // is site (row i, column j) full?
  public boolean isFull(int i, int j) {
    if (!isValid(i, j)) throw new IndexOutOfBoundsException();
    int aSite = coordinatesToIndex(i, j);
    return uf.connected(topSite, aSite);
  }

  // does the system percolate?
  public boolean percolates()
  { return uf.connected(topSite, bottomSite); }

  private int coordinatesToIndex(int i, int j)
  { return initialN * (i - 1) + j - 1; }

  // test client (optional)
  public static void main(String[] args) {
    In in = new In(args[0]);  // input file
    int N = in.readInt();     // N-by-N grid
    Percolation p = new Percolation(N);
    while (!in.isEmpty()) {
      int i = in.readInt();
      int j = in.readInt();
      p.open(i, j);
    }
    StdOut.println("System percolates: " + p.percolates());
  }
}
