import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class Board {
  private int N;
  private int[][] tiles;
  private int[][] goal;
  private int hamming;

  // construct a board from an N-by-N array of blocks
  // (where blocks[i][j] = block in row i, column j)
  public Board(int[][] blocks) {
    N = blocks.length;
    tiles = new int[N][N];
    goal = new int[N][N];
    hamming = 0;

    for (int i = 0; i < N; i++) {
      for (int j= 0; j < N; j++) {
        tiles[i][j] = blocks[i][j];
        int goalBlock = coordinatesToIndex(i, j);
        goal[i][j] = goalBlock;

        if (tiles[i][j] != 0 && tiles[i][j] != goalBlock) {
          hamming++;
        }
      }
    }
  }

  // board dimension N
  public int dimension() {
    return N;
  }

  // number of blocks out of place
  public int hamming() {
    return hamming;
  }

  // sum of Manhattan distances between blocks and goal
  public int manhattan() {
    int manhattan = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (tiles[i][j] != goal[i][j]) {
          manhattan += (calculateDistance(i, j));
        }
      }
    }
    return manhattan;
  }

  // is this board the goal board?
  public boolean isGoal() {
    return tiles.equals(goal);
  }

  // a board that is obtained by exchanging any pair of blocks
  public Board twin() {
    Board twin;
    if (tiles[0][0] != 0 && tiles[0][1] != 0) {
      swap(0, 0);
      twin = new Board(tiles);
      swap(0, 0);
    } else {
      swap(1, 0);
      twin = new Board(tiles);
      swap(1, 0);
    }
    return twin;
  }

  // does this board equal y?
  public boolean equals(Object y) {
    // optimize for true object equality
    if (y == this) return true;

    // check for null
    if (y == null) return false;

    // objects must be in the same class
    if (y.getClass() != this.getClass()) {
      return false;
    }

    // check if all significant fields are the same
    Board that = (Board) y;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (this.tiles[i][j] != that.tiles[i][j]) {
          return false;
        }
      }
    }

    return true;
  }

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

  private void swap(int i, int j) {
    int swap = tiles[i][j];
    tiles[i][j] = tiles[i][j + 1];
    tiles[i][j + 1] = swap;
  }

  private int calculateDistance(int row, int column) {
    int distance = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (goal[i][j] == tiles[row][column]) {
          return Math.abs(j - column) + Math.abs(row - i);
        }
      }
    }
    // failure safe measure:
    // needed for compiler purposes only
    return distance;
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
    StdOut.println("Initial board:\n" + initial);
    Board copy = initial.twin();
    StdOut.println("Twin board:\n" + copy);
    StdOut.println("Is initial board is goal? " + initial.isGoal());
    StdOut.println("Is twin board is goal? " + copy.isGoal());
    StdOut.println("Initial board again (extra check):\n" + initial);
    StdOut.print("# of blocks out of place (hamming f(x)): ");
    StdOut.println(initial.hamming());
    StdOut.print("# of blocks out of place (manhattan f(x)): ");
    StdOut.println(initial.manhattan());
    StdOut.print("check if initial & twin boards are equal: ");
    StdOut.println(initial.equals(copy));
    StdOut.print("check if initial board is equal to itself: ");
    StdOut.println(initial.equals(initial));
  }
}
