import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class kdTree {
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
  public kdTree()

  // is the set empty?
  public boolean isEmpty()

  // number of points in the set
  public int size()

  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p)

  // does the set contain point p?
  public boolean contains(Point2D p)

  // draw all points to standard draw
  public void draw()

  // all points that are inside the rectangle
  public Iterable<Point2D> range(RectHV rect)

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p)

  // unit testing of the methods
  public static void main(String[] args)
}
