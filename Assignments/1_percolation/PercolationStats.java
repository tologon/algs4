import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private int initialT;
  private double[] thresholds;

  // perform T independent experiments on an N-by-N grid
  public PercolationStats(int N, int T) {
    if (N <= 0 || T <= 0) throw new IllegalArgumentException();
    int numOfSites = N * N;
    initialT = T;
    int openSites;
    thresholds = new double[initialT];
    for (int i = 0; i < initialT; i++) {
      Percolation p = new Percolation(N);
      openSites = 0;
      while (!p.percolates()) {
        int randomRow = StdRandom.uniform(1, N + 1);
        int randomColumn = StdRandom.uniform(1, N + 1);
        if (!p.isOpen(randomRow, randomColumn)) {
          openSites++;
          p.open(randomRow, randomColumn);
        }
      }
      thresholds[i] = (double) openSites / numOfSites;
    }
  }

  // sample mean of percolation threshold
  public double mean()
  { return StdStats.mean(thresholds); }

  // sample standard deviation of percolation threshold
  public double stddev()
  { return StdStats.stddev(thresholds); }

  // low endpoint of 95% confidence interval
  public double confidenceLo()
  { return mean() - (1.96 * stddev() / Math.sqrt(initialT)); }

  // high endpoint of 95% confidence interval
  public double confidenceHi()
  { return mean() + (1.96 * stddev() / Math.sqrt(initialT)); }

  // test client
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);
    PercolationStats ps = new PercolationStats(N, T);
    StdOut.println("mean\t\t\t= " + ps.mean());
    StdOut.println("stddev\t\t\t= " + ps.stddev());
    StdOut.println("95% confidence interval = "
                +  ps.confidenceLo() + ", " + ps.confidenceHi());
  }
}
