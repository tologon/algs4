import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
  private LineSegment[] segments;
  private Point[] points;
  private int count;

  // finds all line segments containing 4 or more points
  public FastCollinearPoints(Point[] points) {
    this.points = copy(points);

    /**
     * 1. think of every point p as the origin
     * 2. sort all other points q according to the slopes they make with p
     * 3. check if any (3 or more) adjacent points make a line
     * 4. if so, add them as line segment
     */
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
