import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.In;

public class PercolationStats {
  private static Percolation p;
  private static int numOfSites;
  private static int initialT;
  private static double[] thresholds;

  // perform T independent experiments on an N-by-N grid
  public PercolationStats(int N, int T) {
    numOfSites = N * N;
    initialT = T;
    int openSites;
    thresholds = new double[initialT];
    for (int i = 0; i < initialT; i++) {
      p = new Percolation(N);
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
  public static double mean()
  { return StdStats.mean(thresholds); }

  // sample standard deviation of percolation threshold
  public static double stddev()
  { return StdStats.stddev(thresholds); }

  // low endpoint of 95% confidence interval
  public static double confidenceLo()
  { return mean() - (1.96 * stddev() / Math.sqrt(initialT)); }

  // high endpoint of 95% confidence interval
  public static double confidenceHi()
  { return mean() + (1.96 * stddev() / Math.sqrt(initialT)); }

  // test client
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);
    PercolationStats ps = new PercolationStats(N, T);
    StdOut.println("mean\t\t\t= " + mean());
    StdOut.println("stddev\t\t\t= " + stddev());
    StdOut.println("95% confidence interval = "
                +  confidenceLo() + ", " + confidenceHi());
  }
}
