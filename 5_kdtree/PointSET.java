import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

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

  // TODO update this method for correct functionality
  // draw all points to standard draw
  public void draw() {
    Iterator<Point2D> points = bst.iterator();


    boolean vertical = true;
    while (points.hasNext()) {
      Point2D point = points.next();
      if (vertical) {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius();
        StdDraw.line(point.x(), 0, point.x(), 1);
        vertical = false;
      } else {
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius();
        StdDraw.line(0, point.y(), 1, point.y());
        vertical = true;
      }
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(.01);
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
      }
    }
    return neighbor;
  }

  // unit testing of the methods
  public static void main(String[] args) {

  }
}
