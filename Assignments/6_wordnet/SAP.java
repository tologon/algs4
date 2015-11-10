import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Stack;

public class SAP {
  private static final int INFINITY = Integer.MAX_VALUE;
  private Digraph digraph;

  // constructor takes a digraph (not necessarily a DAG)
  public SAP(Digraph G) {
    if (G == null) {
      throw new NullPointerException("digraph cannot be null.");
    }

    digraph = new Digraph(G.V());
    for (int v = 0; v < G.V(); v++) {
      for (int w : G.adj(v)) {
        digraph.addEdge(v, w);
      }
    }
  }

  // length of shortest ancestral path between v and w; -1 if no such path
  public int length(int v, int w) {
    BreadthFirstDirectedPaths vPaths =
      new BreadthFirstDirectedPaths(digraph, v);
    BreadthFirstDirectedPaths wPaths =
      new BreadthFirstDirectedPaths(digraph, w);

    int shortestPath = INFINITY;
    for (int i = 0; i < digraph.V(); i++) {
      // StdOut.println("current i value: " + i);
      // StdOut.println("vPaths.distTo(i): " + vPaths.distTo(i));
      // StdOut.println("wPaths.distTo(i): " + wPaths.distTo(i));

      if (vPaths.hasPathTo(i) && wPaths.hasPathTo(i)
       && vPaths.distTo(i) + wPaths.distTo(i) < shortestPath) {
         shortestPath = vPaths.distTo(i) + wPaths.distTo(i);
         StdOut.println("new short path: " + shortestPath);
       }
    }

    if (shortestPath != INFINITY)   return shortestPath;
    else                            return -1;
  }

  // a common ancestor of v and w that
  // participates in a shortest ancestral path; -1 if no such path
  public int ancestor(int v, int w) {
    return -1;
  }

  // length of shortest ancestral path
  // between any vertex in v and any vertex in w; -1 if no such path
  public int length(Iterable<Integer> v, Iterable<Integer> w) {
    if (v == null || w == null) {
      throw new NullPointerException("vertices v and w cannot be null.");
    }

    int shortestPath = INFINITY;
    for (int vertexV : v) {
      for (int vertexW : w) {
        int newPath = length(vertexV, vertexW);
        if (newPath != -1 && newPath < shortestPath) {
          shortestPath = newPath;
        }
      }
    }

    if (shortestPath != INFINITY)   return shortestPath;
    else                            return -1;
  }

  // a common ancestor that participates
  // in shortest ancestral path; -1 if no such path
  public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    if (v == null || w == null) {
      throw new NullPointerException("vertices v and w cannot be null.");
    }

    return -1;
  }

  // unit testing
  public static void main(String[] args) {
    // In in = new In(args[0]);
    // Digraph G = new Digraph(in);
    // SAP sap = new SAP(G);
    // while (!StdIn.isEmpty()) {
    //   int v = StdIn.readInt();
    //   int w = StdIn.readInt();
    //   int length   = sap.length(v, w);
    //   int ancestor = sap.ancestor(v, w);
    //   StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    // }

    Digraph G = new Digraph(13);
    G.addEdge(7, 3);
    G.addEdge(8, 3);
    G.addEdge(3, 1);
    G.addEdge(4, 1);
    G.addEdge(5, 1);
    G.addEdge(9, 5);
    G.addEdge(10, 5);
    G.addEdge(11, 10);
    G.addEdge(12, 10);
    G.addEdge(1, 0);
    G.addEdge(2, 0);
    StdOut.println("new initial graph:\n" + G);
    SAP sap = new SAP(G);
    Stack<Integer> vStack = new Stack<>();
    vStack.push(3);
    vStack.push(12);
    Stack<Integer> wStack = new Stack<>();
    wStack.push(11);
    wStack.push(2);
    // StdOut.println("length of sap: " + sap.length(3, 11));
    StdOut.println("length of sap: " + sap.length(vStack, wStack));
    // StdOut.println("copied graph in SAP:\n" + sap.digraph);
    // G.addEdge(1, 5);
    // StdOut.println("updated initial graph:\n" + G);
    // StdOut.println("unchanged copy graph in SAP:\n" + sap.digraph);
  }
}
