public class FastCollinearPoints {
  private LineSegment[] segments;
  private int count;

  // finds all line segments containing 4 or more points
  public FastCollinearPoints(Point[] points) {
    checkPoints(points);
    segments = new LineSegment[1];
    count = 0;

    for (int i = 0; i < points.length; i++) {
      // sort remaining array of points
      // according to slopes they make with the current point
      Point currentPoint = points[i];
      java.util.Arrays.sort(points, i + 1, points.length,
                            currentPoint.slopeOrder());
      // iterate over the rest of points & find line segments
      int nextPoint = i + 1;
      if (nextPoint < points.length) {
        double currentSlope = currentPoint.slopeTo(points[nextPoint]);
        while (nextPoint < points.length)
        { findLineSegments(nextPoint, currentSlope); }
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
}
