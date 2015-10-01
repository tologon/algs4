public class BruteCollinearPoints {
  private LineSegment[] segments;
  private int count;

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {
     segments = new LineSegment[1];
     count = 0;
     for (int i = 0; i < points.length; i++) {
       Point p1 = points[i];
       for (int n = i + 1; n < points.length; n++) {
         Point p2 = points[j];
         for (int k = n + 1; k < points.length; k++) {
           Point p3 = points[k];
           double slope1to2 = p1.slopeTo(p2);
           double slope1to3 = p1.slopeTo(p3);
           double slope2to3 = p2.slopeTo(p3);
           // 3 points are not degenerate & their slopes match
           if (threeMatch(slope1to2, slope1to3, slope2to3)) {
             for (int m = k + 1; m < points.length; m++) {
               Point p4 = points[m];
               double lastSlope = p4.slopeTo(p1);
               if (fourMatch(slope1to2, lastSlope)) {
                 checkSize();
                 Point[] fourPoints = {p1, p2, p3, p4};
                 java.util.Arrays.sort(fourPoints);
                 Point first = fourPoints[0];
                 Point last = fourPoints[3];
                 LineSegment segment = new LineSegment(first, last);
                 segments[count++] = segment;
               }
             }
           }
         }
       }
     }
  }

  private boolean threeMatch(double slope1, double slope2, double slope3) {
    return (slope1 != Double.NEGATIVE_INFINITY
         && slope2 != Double.NEGATIVE_INFINITY
         && slope3 != Double.NEGATIVE_INFINITY
         && (slope1 == slope2 || slope1 == slope3 || slope2 == slope3));
  }

  private boolean fourMatch(double matchingSlope, double lastSlope)
  { return matchingSlope == lastSlope; }

  private void checkSize() {
    if (count == segments.length) {
      LineSegment[] tmp = new LineSegment[segments.length * 2];
      for (int i = 0; i < count; i++)
      { tmp[i] = segments[i]; }
      segments = tmp;
    }
  }

  // the number of line segments
  public int numberOfSegments()
  { return count; }

  // the line segments
  public LineSegment[] segments()
  { return segments; }
}
