public class BruteCollinearPoints {
  private LineSegment[] segments;
  private int count;

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {
    /*
     * for sorting, use java.util.Arrays.sort
     * 1. for every combination of 4 points
     * 1.1 check every pair of points on whether they are collinear or not
     * 2. for every 4 collinear points, find end points
     * 3. make a line segment and add it to the array of segments
     */

     segments = new LineSegment[1];
     count = 0;
     for (int i = 0; i < points.length; i++) {
       Point p1 = points[i];
       for (int j = i + 1; j < points.length; j++) {
         Point p2 = points[j];
         for (int k = j + 1; k < points.length; k++) {
           Point p3 = points[k];
           // if 3 points are not degenerate &&
           // their slopes match -> continue
           // else -> break
           for (int m = k + 1)
         }
       }
     }
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
