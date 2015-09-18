public class PercolationStats {
  private Percolation p;
  private int initialN;
  private int initialT;
  private double[] experiments;

  // perform T independent experiments on an N-by-N grid
  public PercolationStats(int N, int T) {
    initialN = N;
    initialT = T;
    int openSites;
    for (int i = 0; i < initialT; i++) {
      p = new Percolation(N);
      openSites = 0;
      while (!p.percolates()) {
        int randomRow = StdRandom.uniform(initialN);
        int randomColumn = StdRandom.uniform(initialN);
        if (!p.isOpen(randomRow, randomColumn)) openSites++;
        p.open(randomRow, randomColumn);
      }
      experiments[i] = openSites;
    }
  }

  // sample mean of percolation threshold
  public double mean()

  // sample standard deviation of percolation threshold
  public double stddev()

  // low endpoint of 95% confidence interval
  public double confidenceLo()

  // high endpoint of 95% confidence interval
  public double confidenceHi()

  // test client
  public static void main(String[] args) {

  }
}
