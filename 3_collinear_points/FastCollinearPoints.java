import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
  private LineSegment[] segments;
  private Point[] pointsCopy;
  private int count;

  // finds all line segments containing 4 or more points
  public FastCollinearPoints(Point[] points) {
    pointsCopy = copy(points);

    checkPoints(pointsCopy);
    segments = new LineSegment[10];
    count = 0;

    testSort(pointsCopy);

    for (int i = 0; i < pointsCopy.length; i++) {
      // sort remaining array of points
      // according to slopes they make with the current point
      Point currentPoint = pointsCopy[i];
      java.util.Arrays.sort(pointsCopy, i + 1, pointsCopy.length,
                            currentPoint.slopeOrder());
      testSort(pointsCopy);
      // iterate over the rest of points & find line segments
      findLineSegments(i);
    }
    StdOut.println("=================================");
    downSize();
  }

  private Point[] copy(Point[] points) {
    Point[] tmp = new Point[points.length];
    for (int i = 0; i < tmp.length; i++) {
      Point p = points[i];
      tmp[i] = p;
    }
    return tmp;
  }

  private LineSegment[] copy(LineSegment[] lineSegments) {
    LineSegment[] tmp = new LineSegment[lineSegments.length];
    for (int i = 0; i < tmp.length; i++) {
      LineSegment p = lineSegments[i];
      tmp[i] = p;
    }
    return tmp;
  }

  private void testSort(Point[] points) {
    StdOut.println("TESTING SORT USING SPECIFIC SLOPE ORDER.");
    for (int i = 0; i < points.length; i++) {
      StdOut.println(points[i].toString());
    }
  }

  private void findLineSegments(int index) {
    Point currentPoint = pointsCopy[index];
    if (index + 1 < pointsCopy.length) {
      StdOut.print("IN FINDLINESEGMENTS; ");
      double currentSlope = currentPoint.slopeTo(pointsCopy[index + 1]);
      StdOut.println("CURRENT SLOPE: " + currentSlope);
      StdOut.print("NEW SLOPE(S):");
      int numOfPoints = 2;
      for (int k = index + 1; k < pointsCopy.length; k++) {
        Point newPoint = pointsCopy[k - 1];
        double newSlope = newPoint.slopeTo(pointsCopy[k]);
        StdOut.print(" " + newSlope + ", ");
        // continue until a truly "new" slope is found
        if (currentSlope == newSlope)
        { numOfPoints++; }
        else {
          // if 4 or more points, they are collinear
          if (numOfPoints >= 4) {
            createSegment(index, k + 1);
          }
          currentSlope = newSlope;
          numOfPoints = 2;
        }
        StdOut.print("POINTS #: " + numOfPoints + "; ");
      }
      StdOut.println();
    }
  }

  private void createSegment(int start, int end) {
    upSize();
    Point[] collinearPoints;
    collinearPoints = java.util.Arrays.copyOfRange(pointsCopy, start, end);
    java.util.Arrays.sort(collinearPoints);
    Point first = collinearPoints[0];
    Point last = collinearPoints[collinearPoints.length - 1];
    LineSegment s = new LineSegment(first, last);
    segments[count++] = s;
    StdOut.print(" NEW LINE SEGMENT ADDED: " + s.toString() + ", ");
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

  private void upSize() {
    if (count == segments.length) {
      LineSegment[] tmp = new LineSegment[segments.length * 2];
      for (int i = 0; i < count; i++)
      { tmp[i] = segments[i]; }
      segments = tmp;
    }
  }

  private void downSize() {
    if (count < segments.length) {
      LineSegment[] tmp = new LineSegment[count];
      for (int i = 0; i < count; i++)
      { tmp[i] = segments[i]; }
      segments = tmp;
    }
  }

  // the number of line segments
  public int numberOfSegments()
  { return count; }

  // the line segments
  public LineSegment[] segments() {
    LineSegment[] segmentsCopy = copy(segments);
    return segmentsCopy;
  }

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
      FastCollinearPoints collinear = new FastCollinearPoints(points);
      for (LineSegment segment : collinear.segments()) {
          StdOut.println(segment);
          segment.draw();
      }
  }
}
