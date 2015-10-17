import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class PointSET {
  private SET<Point2D> bst;

  // construct an empty set of points
  public PointSET() {
    bst = new SET<>();
  }

  // is the set empty?
  public boolean isEmpty() {
    return bst.isEmpty();
  }

  // number of points in the set
  public int size() {
    return bst.size();
  }

  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null)  throw new NullPointerException();
    bst.add(p);
  }

  // does the set contain point p?
  public boolean contains(Point2D p) {
    if (p == null)  throw new NullPointerException();
    return bst.contains(p);
  }

  // draw all points to standard draw
  public void draw() {
    Iterator<Point2D> points = bst.iterator();
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setPenRadius(.01);
    while (points.hasNext()) {
      Point2D point = points.next();
      point.draw();
    }
  }

  // all points that are inside the rectangle
  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) throw new NullPointerException();

    Iterator<Point2D> bstPoints = bst.iterator();
    Stack<Point2D> stack = new Stack<>();

    while(bstPoints.hasNext()) {
      Point2D point = bstPoints.next();
      if (rect.contains(point))
      { stack.push(point); }
    }
    return stack;
  }

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    if (p == null)  throw new NullPointerException();
    if (bst.isEmpty()) return null;

    double minDistance = -1.0;
    Point2D neighbor = null;
    Iterator<Point2D> points = bst.iterator();

    while (points.hasNext()) {
      Point2D point = points.next();
      if (minDistance == -1.0
       || point.distanceSquaredTo(p) < minDistance) {
         neighbor = point;
         minDistance = point.distanceSquaredTo(p);
      }
    }
    return neighbor;
  }

  // unit testing of the methods
  public static void main(String[] args) {
    PointSET pset = new PointSET();
    StdOut.println("pset.isEmpty(): " + pset.isEmpty());
    StdOut.println("pset.size(): " + pset.size());
    Point2D middle = new Point2D(0.5, 0.5);
    StdOut.print("[before insertion] ");
    StdOut.println("pset.contains(middle): " + pset.contains(middle));
    pset.insert(middle);
    StdOut.print("[after insertion] ");
    StdOut.println("pset.contains(middle): " + pset.contains(middle));
    StdOut.println("pset.size(): " + pset.size());
    Point2D middleCopy = new Point2D(0.5, 0.5);
    pset.insert(middleCopy);
    StdOut.println("[after inserting a copy] pset.size(): " + pset.size());
    Point2D p1 = new Point2D(0.25, 0.25);
    Point2D p2 = new Point2D(0.75, 0.25);
    Point2D p3 = new Point2D(0.25, 0.75);
    Point2D p4 = new Point2D(0.75, 0.75);
    Point2D p5 = new Point2D(0.10, 0.10);
    pset.insert(p1);
    pset.insert(p2);
    pset.insert(p3);
    pset.insert(p4);
    pset.insert(p5);
    StdOut.println("pset.size(): " + pset.size());
    pset.draw();
    RectHV mainRect = new RectHV(0, 0, 0.5, 1);
    Iterable<Point2D> allPoints = pset.range(mainRect);
    StdOut.println("Points in the main rectangle:");
    for (Point2D p : allPoints)
    { StdOut.println(p); }
    Point2D n1 = new Point2D(0.9, 0.9);
    StdOut.println("pset.nearest(): " + pset.nearest(n1));
    Point2D n2 = new Point2D(0.6, 0.1);
    StdOut.println("pset.nearest(): " + pset.nearest(n2));
    Point2D n3 = new Point2D(0.5, 0.6);
    StdOut.println("pset.nearest(): " + pset.nearest(n3));
  }
}
