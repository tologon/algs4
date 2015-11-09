import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
  private Digraph graph;

  // constructor takes a digraph (not necessarily a DAG)
  public SAP(Digraph G) {
    if (G == null) {
      throw new NullPointerException("digraph cannot be null.");
    }

    graph = new Digraph(G.V());
    for (int v = 0; v < G.V(); v++) {
      for (int w : G.adj(v)) {
        graph.addEdge(v, w);
      }
    }
  }

  // length of shortest ancestral path between v and w; -1 if no such path
  public int length(int v, int w) {
    return -1;
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

    return -1;
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
    Digraph G = new Digraph(6);
    G.addEdge(1, 0);
    G.addEdge(1, 2);
    G.addEdge(2, 3);
    G.addEdge(3, 4);
    G.addEdge(4, 5);
    G.addEdge(5, 0);
    StdOut.println("new initial graph:\n" + G);
    SAP sap = new SAP(G);
    StdOut.println("copied graph in SAP:\n" + sap.graph);
    G.addEdge(1, 5);
    StdOut.println("updated initial graph:\n" + G);
    StdOut.println("unchanged copy graph in SAP:\n" + sap.graph);
  }
}
