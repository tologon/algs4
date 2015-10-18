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
    // the axis-aligned rectangle corresponding to this node
    private RectHV r;
    // the left/bottom subtree
    private Node lb;
    // the right/top subtree
    private Node rt;

    public Node(Point2D p, RectHV r, Node lb, Node rt) {
      this.p = p;
      this.r = r;
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

    if (root == null) {
      RectHV mainRect = new RectHV(0, 0, 1, 1);
      Node newNode = new Node(p, mainRect, null, null);
      root = newNode;
    }
    else {
      Node currentNode = root;
      insert(currentNode, p);
    }

    count++;
  }

  // does the set contain point p?
  public boolean contains(Point2D p) {
    if (p == null)  throw new NullPointerException();
    return isPresent(p);
  }

  // draw all points to standard draw
  public void draw() {
    if (root == null)   return;

    Node currentNode = root;
    // draw a point with corresponding line at root
    // drawTree() method
    // drawTree(true, currentNode);
  }

  // all points that are inside the rectangle
  // public Iterable<Point2D> range(RectHV rect)

  // a nearest neighbor in the set to point p; null if the set is empty
  // public Point2D nearest(Point2D p)

  // draw current's node point & line
  // and then recursively draws subtrees
  // private void drawTree(double min, double max, boolean vertical, Node node) {
  //   StdDraw.setPenColor(StdDraw.BLACK);
  //   StdDraw.setPenRadius(0.01);
  //   node.p.draw();
  //   if (vertical) {
  //     StdDraw.setPenColor(StdDraw.RED);
  //     StdDraw.setPenRadius();
  //     StdDraw.line(node.p.x(), min, node.p.x(), max);
  //     // if left-bottom child is not null,
  //     // proceed wit drawTree() with xmin and xmax
  //     // if right-top child is not null,
  //     // proceed wit drawTree() with xmin and xmax
  //   } else {
  //     StdDraw.setPenColor(StdDraw.BLUE);
  //     StdDraw.setPenRadius();
  //     StdDraw.line(min, node.p.y(), max, node.p.y());
  //     // if left-bottom child is not null,
  //     // proceed wit drawTree() with ymin and ymax
  //     // if right-top child is not null,
  //     // proceed wit drawTree() with ymin and ymax
  //   }
  // }

  private void insert(Node node, Point2D p) {
    boolean vertical = true;
    while (true) {
      Point2D q = node.p;
      // if left node is null, insert a new node there
      if (vertical && p.x() < q.x() && node.lb == null) {
        RectHV newRect = new RectHV(node.r.xmin(),  node.r.ymin(),
                                    q.x(),          node.r.ymax());
        Node newNode = new Node(p, newRect, null, null);
        node.lb = newNode;
        break;
      }
      // if bottom node is null, insert a new node there
      else if (!vertical && p.y() < q.y() && node.lb == null) {
        RectHV newRect = new RectHV(node.r.xmin(), node.r.ymin(),
                                    node.r.xmax(), q.y());
        Node newNode = new Node(p, newRect, null, null);
        node.lb = newNode;
        break;
      }
      // if right node is null, insert a new node there
      else if (vertical && p.x() >= q.x() && node.rt == null) {
        RectHV newRect = new RectHV(q.x(),        node.r.ymin(),
                                    node.r.xmax(),  node.r.ymax());
        Node newNode = new Node(p, newRect, null, null);
        node.rt = newNode;
        break;
      }
      // if top node is null, insert a new node there
      else if (!vertical && p.y() >= q.y() && node.rt == null) {
        RectHV newRect = new RectHV(node.r.xmin(), q.y(),
                                    node.r.xmax(), node.r.ymax());
        Node newNode = new Node(p, newRect, null, null);
        node.rt = newNode;
        break;
      }
      // if left-bottom node is another node, explore it
      else if ((vertical && p.x() < q.x() && node.lb != null)
           || (!vertical && p.y() < q.y() && node.lb != null))
      { node = node.lb; }
      else
      // if right-top node is another node, explore it
      { node = node.rt; }

      if (vertical)
      { vertical = false; }
      else
      { vertical = true; }
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
    StdOut.print("[before insertion] ");
    StdOut.println("tree.contains(p2): " + tree.contains(p2));
    tree.insert(p2);
    StdOut.print("[after insertion] ");
    StdOut.println("tree.contains(p2): " + tree.contains(p2));
    StdOut.println("tree.size(): " + tree.size());
  }
}
