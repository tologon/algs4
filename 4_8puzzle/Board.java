import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class Board {
  private int[][] tiles;
  private int N;
  private boolean goal;

  // construct a board from an N-by-N array of blocks
  // (where blocks[i][j] = block in row i, column j)
  public Board(int[][] blocks) {
    N = blocks.length;
    tiles = new int[N][N];
    goal = true;
    for (int i = 0; i < N; i++) {
      for (int j= 0; j < N; j++) {
        tiles[i][j] = blocks[i][j];

        int goalBlock = coordinatesToIndex(i, j);
        if (goalBlock != N * N && tiles[i][j] != goalBlock) {
          goal = false;
        }
      }
    }
  }

  // board dimension N
  public int dimension() {
    return N;
  }

  // number of blocks out of place
  // public int hamming()

  // sum of Manhattan distances between blocks and goal
  // public int manhattan()

  // is this board the goal board?
  public boolean isGoal() {
    return goal;
  }

  // a board that is obtained by exchanging any pair of blocks
  public Board twin() {
    Board twin;
    if (tiles[0][0] != 0 && tiles[0][1] != 0) {
      int swap = tiles[0][0];
      tiles[0][0] = tiles[0][1];
      tiles[0][1] = swap;
      twin = new Board(tiles);
      swap = tiles[0][0];
      tiles[0][0] = tiles[0][1];
      tiles[0][1] = swap;
    } else {
      int swap = tiles[1][0];
      tiles[1][0] = tiles[1][1];
      tiles[1][1] = swap;
      twin = new Board(tiles);
      swap = tiles[1][0];
      tiles[1][0] = tiles[1][1];
      tiles[1][1] = swap;
    }
    return twin;
  }

  // does this board equal y?
  // public boolean equals(Object y)

  // all neighboring boards
  // public Iterable<Board> neighbors()

  // string representation of this board (in the output format specified below)
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(N + "\n");
    for (int i = 0; i < N; i++) {
      for (int j = 0 ; j < N; j++) {
        s.append(String.format("%2d ", tiles[i][j]));
      }
      s.append("\n");
    }
    return s.toString();
  }

  private int coordinatesToIndex(int i, int j) {
    return N * i + j + 1;
  }

  // unit testing (not graded)
  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);
    StdOut.println("initial board:\n" + initial);
    Board copy = initial.twin();
    StdOut.println("twin board:\n" + copy);
  }
}
