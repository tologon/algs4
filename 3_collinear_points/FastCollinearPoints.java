import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;

public class FastCollinearPoints {
  private LineSegment[] segments;
  private int count;

  // finds all line segments containing 4 or more points
  public FastCollinearPoints(Point[] points) {
    // check for null entries & copy the array
    check(points);
    Point[] origins = copy(points);

    segments = new LineSegment[10];
    count = 0;

    for (int i = 0; i < origins.length; i++) {
      // 1. think of every point p as the origin
      Point point = origins[i];

      // 2. sort all other points q according to the slopes with p
      Arrays.sort(origins, i + 1, origins.length, point.slopeOrder());

      // 3. check if any (3 or more) adjacent points make a line
      findLineSegments(origins, i);
    }
    downSize();
  }

  // the number of line segments
  public int numberOfSegments()
  { return count; }

  // the line segments
  public LineSegment[] segments()
  { return copy(segments); }

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

  private void check(Point[] points) {
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

  private void findLineSegments(Point[] origins, int index) {
    int originalIndex = index;
    int loopK = index;
    Point currentPoint = origins[index];
    if (index + 1 < origins.length) {
      // check if any (4 or more) adjacent points make a line
      double currentSlope = currentPoint.slopeTo(origins[index + 1]);
      int numOfPoints = 2;
      for (int i = index + 1, k = index + 2; k < origins.length; i++, k++) {
        Point q1 = origins[i];
        Point q2 = origins[k];
        double otherSlope = q1.slopeTo(q2);
        if (currentSlope == otherSlope)
        { numOfPoints++; }
        else {
          if (numOfPoints >= 3) {
            makeSegment(origins, index, k);
            break;
          }
          currentSlope = otherSlope;
          numOfPoints = 2;
        }
        loopK = k;
      }
      if (numOfPoints >= 3)
      { makeSegment(origins, index, loopK); }
    }
  }

  private void makeSegment(Point[] origins, int start, int end) {
    upSize();
    Point[] collinearPoints;
    collinearPoints = Arrays.copyOfRange(origins, start, end);
    Arrays.sort(collinearPoints);
    Point first = collinearPoints[0];
    Point last = collinearPoints[collinearPoints.length - 1];
    LineSegment ls = new LineSegment(first, last);
    segments[count++] = ls;
  }

  private void upSize() {
    if (count == segments.length) {
      LineSegment[] tmp = new LineSegment[segments.length + segments.length];
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
