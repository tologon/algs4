import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
  private Node root;
  private int count;

  private static class Node {
    // the point
    private Point2D p;
    // the left/bottom subtree
    private Node lb;
    // the right/top subtree
    private Node rt;

    public Node(Point2D p, Node lb, Node rt) {
      this.p = p;
      this.lb = lb;
      this.rt = rt;
    }
  }

  // construct an empty set of points
  public KdTree() {
    root = null;
    count = 0;
  }

  // is the set empty?
  public boolean isEmpty() {
    return count == 0;
  }

  // number of points in the set
  public int size() {
    return count;
  }

  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null)  throw new NullPointerException();

    // if 2d-tree contains given p, skip insertion
    if (contains(p))  return;

    // for current node, if it is null, insert point there
    // if not null, compare point with current node and move appropriately
    Node currentNode = root;
    boolean xCoordinate = true;
    while (currentNode != null) {
      Point2D q = currentNode.p;
      if (xCoordinate) {
        if (p.x() < q.x())
        { currentNode = currentNode.lb; }
        else
        { currentNode = currentNode.rt; }
        xCoordinate = false;
      } else {
        if (p.y() < q.y())
        { currentNode = currentNode.lb; }
        else
        { currentNode = currentNode.rt; }
        xCoordinate = true;
      }
    }
    Node newNode = new Node(p, null, null);
  }

  // does the set contain point p?
  public boolean contains(Point2D p) {
    return true;
  }

  // draw all points to standard draw
  // public void draw()

  // all points that are inside the rectangle
  // public Iterable<Point2D> range(RectHV rect)

  // a nearest neighbor in the set to point p; null if the set is empty
  // public Point2D nearest(Point2D p)

  // unit testing of the methods
  public static void main(String[] args) {
    KdTree tree = new KdTree();
    StdOut.println("tree.isEmpty(): " + tree.isEmpty());
    StdOut.println("tree.size(): " + tree.size());
  }
}
