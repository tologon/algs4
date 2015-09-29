/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertcal;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
      // the line segment is degenerate, i.e. the points are equal
      if (compareTo(that) == 0)
      { return Double.NEGATIVE_INFINITY; }
      // the line segment is horizontal
      else if (that.y - this.y == +0
            || that.y - this.y == -0)
      { return +0.0; }
      // the line segment is vertical
      else if (that.x - this.x == +0
            || that.x - this.x == -0)
      { return Double.POSITIVE_INFINITY; }
      // the line segment is "normal", i.e. it can be calculated
      else {
        double dY = (double) that.y - (double) this.y;
        double dX = (double) that.x - (double) this.x;
        return dY / dX;
      }
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
      // points are equal
      if (this.x == that.x && this.y == that.y)
      { return 0; }
      // the invoking point is less than the argument point
      else if (this.y < that.y || (this.y == that.y && this.x < that.x))
      { return -1; }
      // the invoking point is greater than the argument point
      else
      { return 1; }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    // public Comparator<Point> slopeOrder() {
    //     /* YOUR CODE HERE */
    // }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(0, 0);
        StdOut.println("Slope from " + p1.toString()
                     + " to " + p2.toString() + ": " + p1.slopeTo(p2));
        StdOut.println("Slope from " + p2.toString()
                     + " to " + p1.toString() + ": " + p2.slopeTo(p1));
        StdOut.println("Slope from " + p1.toString()
                     + " to " + p1.toString() + ": " + p1.slopeTo(p1));
        StdOut.println(p1.toString() + " is " + p1.compareTo(p2)
                    +  " than " + p2.toString());
        StdOut.println(p2.toString() + " is " + p2.compareTo(p1)
                    +  " than " + p1.toString());
        StdOut.println(p1.toString() + " is " + p1.compareTo(p1)
                    +  " than " + p1.toString());
    }
}
