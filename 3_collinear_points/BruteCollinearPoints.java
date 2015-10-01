public class BruteCollinearPoints {
  private LineSegment[] segments;
  private int count;

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {
    /*
     * for sorting, use java.util.Arrays.sort
     * 1. for every combination of 4 points
     * 1.1 check every pair of points on whether they are collinear or not
     * 2. for every 4 collinear points, find end points (i.e. sort them)
     * 3. make a line segment and add it to the array of segments
     */

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
           if (slope1to2 != Double.NEGATIVE_INFINITY
           && slope1to3 != Double.NEGATIVE_INFINITY
           && slope2to3 != Double.NEGATIVE_INFINITY
           && (slope1to2 == slope2to3
           || slope1to2 == slope1to3
           || slope1to3 == slope2to3)) {
             for (int m = k + 1; m < points.length; m++) {
               Point p4 = points[m];
               double slope4to1 = p4.slopeTo(p1);
               double slope4to2 = p4.slopeTo(p2);
               double slope4to3 = p4.slopeTo(p3);
               if ()
             }
           }
         }
       }
     }
  }

  private boolean isMatch(double slope1, double slope2, double slope3) {
    return (slope1 != Double.NEGATIVE_INFINITY
         && slope2 != Double.NEGATIVE_INFINITY
         && slope3 != Double.NEGATIVE_INFINITY
         && (slope1 == slope2 || slope1 == slope3 || slope2 == slope3));
  }

  private void resizeArray() {
  }

  // the number of line segments
  public int numberOfSegments()
  { return count; }

  // the line segments
  public LineSegment[] segments()
  { return segments; }
}
