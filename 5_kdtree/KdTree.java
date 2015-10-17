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

    Node currentNode = root;
    Node newNode = new Node(p, null, null);

    if (root == null)
    { root = newNode; }
    else
    { insert(currentNode, newNode); }

    count++;
  }

  // does the set contain point p?
  public boolean contains(Point2D p) {
    if (p == null)  throw new NullPointerException();
    return isPresent(p);
  }

  // draw all points to standard draw
  // public void draw()

  // all points that are inside the rectangle
  // public Iterable<Point2D> range(RectHV rect)

  // a nearest neighbor in the set to point p; null if the set is empty
  // public Point2D nearest(Point2D p)

  private void insert(Node currentNode, Node newNode) {
    boolean xCoordinate = true;
    Point2D p = newNode.p;
    while (true) {
      Point2D q = currentNode.p;
      // if left-bottom node is null, insert a new node there
      if ((xCoordinate && p.x() < q.x() && currentNode.lb == null)
      || (!xCoordinate && p.y() < q.y() && currentNode.lb == null)) {
        currentNode.lb = newNode;
        break;
      }
      // if right-top node is null, insert a new node there
      else if ((xCoordinate && p.x() >= q.x() && currentNode.rt == null)
           || (!xCoordinate && p.y() >= q.y() && currentNode.rt == null)) {
        currentNode.rt = newNode;
        break;
      }
      // if left-bottom node is another node, explore it
      else if ((xCoordinate && p.x() < q.x() && currentNode.lb != null)
           || (!xCoordinate && p.y() < q.y() && currentNode.lb != null))
      { currentNode = currentNode.lb; }
      else
      // if right-top node is another node, explore it
      { currentNode = currentNode.rt; }

      if (xCoordinate)
      { xCoordinate = false; }
      else
      { xCoordinate = true; }
    }
  }

  private boolean isPresent(Point2D p) {
    // for current node, if it is null, return null
    // if not null, compare point with current node and move appropriately
    Node currentNode = root;
    boolean xCoordinate = true;

    while (currentNode != null) {
      Point2D q = currentNode.p;
      if (q.equals(p)) {
        return true;
      }
      else if (xCoordinate) {
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
    return false;
  }

  // unit testing of the methods
  public static void main(String[] args) {
    KdTree tree = new KdTree();
    StdOut.print("[before insertion] ");
    StdOut.println("tree.isEmpty(): " + tree.isEmpty());
    StdOut.println("tree.size(): " + tree.size());
    Point2D p1 = new Point2D(0.5, 0.5);
    Point2D p2 = new Point2D(0.2, 0.7);
    tree.insert(p1);
    StdOut.print("[after insertion] ");
    StdOut.println("tree.isEmpty(): " + tree.isEmpty());
    StdOut.println("tree.contains(p1): " + tree.contains(p1));
    StdOut.println("[not inserted] tree.contains(p2): " + tree.contains(p2));
  }
}
