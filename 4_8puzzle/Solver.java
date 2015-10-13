import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
// import edu.princeton.cs.algs4.Stopwatch;

public class Solver {
  private Node goalNode;
  private int moves;
  private Stack<Board> solution;

  private class Node implements Comparable<Node> {
    private Board board;
    private int moves;
    private Node previous;
    private int priority;

    private Node(Board board, int moves, Node previous) {
      this.board = board;
      this.moves = moves;
      this.previous = previous;
      this.priority = this.moves + board.manhattan();
    }

    public int compareTo(Solver.Node that) {
      if (this.priority == that.priority)
      { return 0; }
      else if (this.priority < that.priority)
      { return -1; }
      else
      { return 1; }
    }
  }

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null) throw new NullPointerException();

    MinPQ<Node> mainPQ = new MinPQ<Node>();
    Node start = new Node(initial, 0, null);
    mainPQ.insert(start);

    // MinPQ<Node> twinPQ = new MinPQ<Node>();
    Node twin = new Node(initial.twin(), 0, null);
    mainPQ.insert(twin);

    Node minNode;
    // Node twinNode;
    do {
      minNode = mainPQ.delMin();
      Iterable<Node> nodes = neighbors(minNode);
      for (Node node : nodes)
      { mainPQ.insert(node); }

      // twinNode = twinPQ.delMin();
      // Iterable<Node> twinNodes = neighbors(twinNode);
      // for (Node node : twinNodes)
      // { twinPQ.insert(node); }
    } while (!isGoal(minNode));

    moves = minNode.moves;
    solution = new Stack<>();
    Board board = minNode.board;
    solution.push(board);
    while (minNode.previous != null) {
      minNode = minNode.previous;
      board = minNode.board;
      solution.push(board);
    }

    if (minNode.board.equals(initial))
    { goalNode = minNode; }
    else
    { moves = -1; }
  }

  // is the initial board solvable?
  public boolean isSolvable() {
    return goalNode != null;
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    return moves;
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    return solution;
  }

  // checks whether the board for a node is goal board
  private boolean isGoal(Node node) {
    return node.board.isGoal();
  }

  // gets all the neighboring nodes;
  // implements critical optimization to avoid same boards
  private Iterable<Node> neighbors(Node node) {
    Iterable<Board> boards = node.board.neighbors();
    Stack<Node> s = new Stack<>();
    for (Board board : boards) {
      Node newNode = new Node(board, node.moves + 1, node);
      if (node.previous == null)
      { s.push(newNode); }
      else if (!node.previous.board.equals(newNode.board))
      { s.push(newNode); }
    }
    return s;
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

    // Stopwatch sw = new Stopwatch();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);
    // StdOut.println("elapsed time (in seconds): " + sw.elapsedTime());

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
