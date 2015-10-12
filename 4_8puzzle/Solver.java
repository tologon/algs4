import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
  private boolean solvable;
  private MinPQ<Node> pq;

  private class Node implements Comparable<Node> {
    private Board board;
    private int moves;
    private Board previous;
    private int priority;

    private Node(Board board, int moves, Board previous) {
      this.board = board;
      this.moves = moves;
      this.previous = previous;
      this.priority = this.moves + board.manhattan();
    }

    public int compareTo(Solver.Node that) {
      if (this.priority == that.priority)
      { return 0; }
      else if (this.priority < that.priority)
      { return - 1; }
      else
      { return 1; }
    }
  }

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null) throw new NullPointerException();

    pq = new MinPQ<Node>();
    // TODO implement constructor
    Node node = new Node(initial, 0, null);
    pq.insert(node);
  }

  // is the initial board solvable?
  public boolean isSolvable() {
    return solvable;
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    if (!isSolvable())  return -1;

    // TODO implement method
    return 0;
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    if (!isSolvable())  return null;

    // TODO implement method
    return null;
  }

  // solve a slider puzzle
  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
  }
}
