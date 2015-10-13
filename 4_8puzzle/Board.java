import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class Board {
  private int N;
  private int[][] tiles;
  private boolean goal;
  private int hamming;
  private int zeroRow, zeroColumn;

  // construct a board from an N-by-N array of blocks
  // (where blocks[i][j] = block in row i, column j)
  public Board(int[][] blocks) {
    N = blocks.length;
    tiles = new int[N][N];
    goal = true;
    hamming = 0;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        tiles[i][j] = blocks[i][j];
        int goalBlock = coordinatesToIndex(i, j);

        if (tiles[i][j] != 0 && tiles[i][j] != goalBlock) {
          goal = false;
          hamming++;
        }
        else if (tiles[i][j] == 0) {
          zeroRow = i;
          zeroColumn = j;
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
        if (tiles[i][j] != 0 && tiles[i][j] != coordinatesToIndex(i, j)) {
          manhattan += (calculateDistance(i, j));
        }
      }
    }
    return manhattan;
  }

  // is this board the goal board?
  public boolean isGoal() {
    return goal;
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
    Board that = (Board) y;
    return java.util.Arrays.deepEquals(this.tiles, that.tiles);
  }

  // all neighboring boards
  public Iterable<Board> neighbors() {
    Stack<Board> s = new Stack<>();
    Board north = getNeighbor(zeroRow - 1, zeroColumn);
    if (north != null)  s.push(north);
    Board south = getNeighbor(zeroRow + 1, zeroColumn);
    if (south != null)  s.push(south);
    Board east = getNeighbor(zeroRow, zeroColumn + 1);
    if (east != null)  s.push(east);
    Board west = getNeighbor(zeroRow, zeroColumn - 1);
    if (west != null)  s.push(west);
    return s;
  }

  // string representation of this board (in the output format specified below)
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(N + "\n");
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
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
        if (coordinatesToIndex(i, j) == tiles[row][column]) {
          return Math.abs(j - column) + Math.abs(row - i);
        }
      }
    }
    // failure safe measure:
    // needed for compiler purposes only
    return distance;
  }

  private Board getNeighbor(int i, int j) {
    // if out of bonds, don't make a neighbor
    if (i >= N || i < 0 || j >= N || j < 0)   return null;

    int swap = tiles[zeroRow][zeroColumn];
    tiles[zeroRow][zeroColumn] = tiles[i][j];
    tiles[i][j] = swap;
    Board board = new Board(tiles);
    swap = tiles[zeroRow][zeroColumn];
    tiles[zeroRow][zeroColumn] = tiles[i][j];
    tiles[i][j] = swap;
    return board;
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
    // StdOut.println(initial.equals(initial));

    Iterable<Board> boards = initial.neighbors();
    for (Board board : boards)
    { StdOut.println(board); }
  }
}
