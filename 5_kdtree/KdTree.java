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
    drawTree(true, currentNode);
  }

  // all points that are inside the rectangle
  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null)   throw new NullPointerException();

    Stack<Point2D> stack = new Stack<>();
    range(stack, root, rect);
    return stack;
  }

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    if (p == null)    throw new NullPointerException();
    if (root == null) return null;
    return nearest(root, root.p, p);
  }

  private Point2D nearest(Node node, Point2D closest, Point2D p) {
    StdOut.println("call from recursive nearest() method.");
    StdOut.println("node.r: " + node.r);

    if (node.r.distanceSquaredTo(p) < closest.distanceSquaredTo(p)) {
      if (node.p.distanceSquaredTo(p) < closest.distanceSquaredTo(p)) {
        closest = node.p;
        StdOut.println("[1st IF] current closest: " + closest);
      }

      if (node.lb != null && node.rt != null
      && node.lb.r.distanceSquaredTo(p) < node.rt.r.distanceSquaredTo(p)
      && node.lb.r.distanceSquaredTo(p) < closest.distanceSquaredTo(p)) {
        closest = nearest(node.lb, closest, p);
        StdOut.println("[2nd IF] current closest: " + closest);
      }

      if (node.lb != null && node.rt != null
      && node.rt.r.distanceSquaredTo(p) < node.lb.r.distanceSquaredTo(p)
      && node.rt.r.distanceSquaredTo(p) < closest.distanceSquaredTo(p)) {
        closest = nearest(node.rt, closest, p);
        StdOut.println("[3rd IF] current closest: " + closest);
      }
    }

    StdOut.println("###### end of the recursive call ######");
    return closest;
  }

  private void range(Stack<Point2D> s, Node node, RectHV rect) {
    if (node != null && rect.intersects(node.r)) {
      if (rect.contains(node.p))
      { s.push(node.p); }
      range(s, node.lb, rect);
      range(s, node.rt, rect);
    }
  }

  // draw current's node point & line
  // and then recursively draws subtrees
  private void drawTree(boolean vertical, Node node) {
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setPenRadius(0.01);
    node.p.draw();
    if (vertical) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius();
      StdDraw.line(node.p.x(), node.r.ymin(), node.p.x(), node.r.ymax());
      if (node.lb != null)
      { drawTree(false, node.lb); }
      if (node.rt != null)
      { drawTree(false, node.rt); }
    } else {
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.setPenRadius();
      StdDraw.line(node.r.xmin(), node.p.y(), node.r.xmax(), node.p.y());
      if (node.lb != null)
      { drawTree(true, node.lb); }
      if (node.rt != null)
      { drawTree(true, node.rt); }
    }
  }

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
    boolean vertical = true;

    while (currentNode != null) {
      Point2D q = currentNode.p;
      if (q.equals(p)) {
        return true;
      }
      else if (vertical) {
        if (p.x() < q.x())
        { currentNode = currentNode.lb; }
        else
        { currentNode = currentNode.rt; }
        vertical = false;
      } else {
        if (p.y() < q.y())
        { currentNode = currentNode.lb; }
        else
        { currentNode = currentNode.rt; }
        vertical = true;
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
    Point2D p1 = new Point2D(0.50, 0.50);
    Point2D p2 = new Point2D(0.25, 0.60);
    Point2D p3 = new Point2D(0.75, 0.40);
    Point2D p4 = new Point2D(0.15, 0.30);
    Point2D p5 = new Point2D(0.85, 0.70);
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
    tree.insert(p3);
    tree.insert(p4);
    tree.insert(p5);
    tree.draw();
    RectHV rect = new RectHV(0, 0, 0.05, 0.05);
    Iterable<Point2D> points = tree.range(rect);
    StdOut.println("points inside of the rectangle: " + rect);
    for (Point2D point : points)
    { StdOut.println(point); }
    StdDraw.setPenColor(StdDraw.MAGENTA);
    rect.draw();
    StdOut.println("tree.nearest(p1): " + tree.nearest(p1) + "\n==============");
    StdOut.println("tree.nearest(p2): " + tree.nearest(p2) + "\n==============");
    StdOut.println("tree.nearest(p3): " + tree.nearest(p3) + "\n==============");
    StdOut.println("tree.nearest(p4): " + tree.nearest(p4) + "\n==============");
    StdOut.println("tree.nearest(p5): " + tree.nearest(p5) + "\n==============");
  }
}
