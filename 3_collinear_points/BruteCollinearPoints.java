public class BruteCollinearPoints {
  private LineSegment[] segments;

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {
    /*
     * for sorting, use java.util.Arrays.sort
     * 1. for every combination of 4 points
     * 1.1 check every pair of points on whether they are collinear or not
     * 2. for every 4 collinear points, find end points
     * 3. make a line segment and add it to the array of segments
     */
  }

  // the number of line segments
  public int numberOfSegments()
  { return segments.length; }

  // the line segments
  public LineSegment[] segments()
  { return segments; }
}
