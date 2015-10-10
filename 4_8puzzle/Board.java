public class Board {
  // construct a board from an N-by-N array of blocks
  // (where blocks[i][j] = block in row i, column j)
  public Board(int[][] blocks)

  // board dimension N
  public int dimension()

  // number of blocks out of place
  public int hamming()

  // sum of Manhattan distances between blocks and goal
  public int manhattan()

  // is this board the goal board?
  public boolean isGoal()

  // a board that is obtained by exchanging any pair of blocks
  public Board twin()

  // does this board equal y?
  public boolean equals(Object y)

  // all neighboring boards
  public Iterable<Board> neighbors()

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

  // unit testing (not graded)
  public static void main(String[] args)
}
