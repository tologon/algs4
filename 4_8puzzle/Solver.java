import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
  private Node goalNode;

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
      { return - 1; }
      else
      { return 1; }
    }

    // TODO for debuggin purposes only; delete before submission
    public String toString() {
      StringBuilder s = new StringBuilder();
      s.append("moves: " + moves + "\n");
      s.append("priority: " + priority + "\n");
      s.append("board:\n" + board);
      return s.toString();
    }
  }

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null) throw new NullPointerException();

    MinPQ<Node> mainPQ = new MinPQ<Node>();
    Node start = new Node(initial, 0, null);
    mainPQ.insert(start);

    MinPQ<Node> twinPQ = new MinPQ<Node>();
    Node twin = new Node(initial.twin(), 0, null);
    twinPQ.insert(twin);

    Node minNode;
    Node twinNode;
    do {
      minNode = mainPQ.delMin();
      Iterable<Node> nodes = neighbors(minNode);
      for (Node node : nodes)
      { mainPQ.insert(node); }

      twinNode = twinPQ.delMin();
      Iterable<Node> twinNodes = neighbors(twinNode);
      for (Node node : twinNodes)
      { twinPQ.insert(node); }
    } while ((!isGoal(minNode)) && (!isGoal(twinNode)));

    if (isGoal(minNode)) {
      goalNode = minNode;
      Stack<Board> s = new Stack<>();
      Board board = goalNode.board;
      s.push(board);
      while (goalNode.previous != null) {
        goalNode = goalNode.previous;
        board = goalNode.board;
        s.push(board);
      }
    }
  }

  // is the initial board solvable?
  public boolean isSolvable() {
    return goalNode != null;
  }

  // TODO
  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    if (!isSolvable())  return -1;

    // TODO implement method
    return 0;
  }

  // TODO
  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    if (!isSolvable())  return null;

    // TODO implement method
    return null;
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
      if (!board.equals(node.previous.board)) {
        Node newNode = new Node(board, node.moves + 1, node);
        s.push(newNode);
      }
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
