import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
  private LineSegment[] segments;
  private int count;

  // finds all line segments containing 4 or more points
  public FastCollinearPoints(Point[] points) {
    checkPoints(points);
    segments = new LineSegment[10];
    count = 0;

    for (int i = 0; i < points.length; i++) {
      // sort remaining array of points
      // according to slopes they make with the current point
      Point currentPoint = points[i];
      java.util.Arrays.sort(points, i + 1, points.length,
                            currentPoint.slopeOrder());

      double currentSlope;
      // continue as long as there are points after current point
      if (i + 1 <  points.length)
      { currentSlope = currentPoint.slopeTo(points[i + 1]); }
      // break when no points exist after current point
      else
      { break; }
      int numOfPoints = 1;

      // iterate over the rest of points & find line segments
      for (int k = i + 1; k < points.length; k++) {
        double newSlope = currentPoint.slopeTo(points[k]);
        // continue until a truly "new" slope is found
        if (currentSlope == newSlope)
        { numOfPoints++; }
        else {
          // if 4 or more points, they are collinear
          if (numOfPoints >= 4) {
            // TODO implement upSize from Brute Force algorithm
            Point[] collinearPoints = java.util.Arrays.copyOfRange(points, i, k + 1);
            java.util.Arrays.sort(collinearPoints);
            Point first = collinearPoints[0];
            Point last = collinearPoints[collinearPoints.length - 1];
            LineSegment s = new LineSegment(first, last);
            segments[count++] = s;
          }
          currentSlope = newSlope;
          numOfPoints = 1;
        }
      }
    }
  }

  private void checkPoints(Point[] points) {
    if (points == null) throw new NullPointerException();
    if (points[0] == null) throw new NullPointerException();
    for (int i = 0; i < points.length; i++) {
      Point p1 = points[i];
      for (int k = i + 1; k < points.length; k++) {
        if (points[k] == null) throw new NullPointerException();
        Point p2 = points[k];
        if (p1.compareTo(p2) == 0) throw new IllegalArgumentException();
      }
    }
  }

  // the number of line segments
  public int numberOfSegments()
  { return count; }

  // the line segments
  public LineSegment[] segments()
  { return segments; }

  // unit testing
  public static void main(String[] args) {

      // read the N points from a file
      In in = new In(args[0]);
      int N = in.readInt();
      Point[] points = new Point[N];
      for (int i = 0; i < N; i++) {
          int x = in.readInt();
          int y = in.readInt();
          points[i] = new Point(x, y);
      }

      // draw the points
      StdDraw.show(0);
      StdDraw.setXscale(0, 32768);
      StdDraw.setYscale(0, 32768);
      for (Point p : points) {
          p.draw();
      }
      StdDraw.show();

      // print and draw the line segments
      BruteCollinearPoints collinear = new BruteCollinearPoints(points);
      for (LineSegment segment : collinear.segments()) {
          StdOut.println(segment);
          segment.draw();
      }
  }
}
