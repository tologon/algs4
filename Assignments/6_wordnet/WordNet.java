import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class WordNet {
  // // constructor takes the name of the two input files
  // public WordNet(String synsets, String hypernyms) {
  //   if (synsets == null || hypernyms == null) {
  //     throw new NullPointerException("synsets and hypernyms cannot be null.");
  //   }
  // }
  //
  // // returns all WordNet nouns
  // public Iterable<String> nouns() {
  // }
  //
  // // is the word a WordNet noun?
  // public boolean isNoun(String word) {
  //   if (word == null) {
  //     throw new NullPointerException("word cannot be null.");
  //   }
  // }
  //
  // // distance between nounA and nounB (defined below)
  // public int distance(String nounA, String nounB) {
  //   if (nounA == null || nounB == null) {
  //     throw new NullPointerException("noun A and noun B cannot be null.");
  //   }
  // }
  //
  // // a synset (second field of synsets.txt)
  // // that is the common ancestor of nounA and nounB
  // // in a shortest ancestral path (defined below)
  // public String sap(String nounA, String nounB) {
  //   if (nounA == null || nounB == null) {
  //     throw new NullPointerException("noun A and noun B cannot be null.");
  //   }
  // }

  // unit testing
  public static void main(String[] args) {
    In in = new In(args[0]);
    StdOut.println("Parsing " + args[0]);
    while (!in.isEmpty()) {
      String line = in.readLine();
      String[] synsetData = line.split(",");
      StdOut.println("synset id: " + synsetData[0]
                   + "; synset: " + synsetData[1]
                   + "; gloss: " + synsetData[2]);
    }
    StdOut.println("==============================");
    in = new In(args[1]);
    StdOut.println("Parsing " + args[1]);
    while (!in.isEmpty()) {
      String line = in.readLine();
      String[] synsetData = line.split(",");
      StdOut.print("synset id: " + synsetData[0]
                 + "; hypernyms id(s): ");
      for (int i = 1; i < synsetData.length - 1; i++)
      { StdOut.print(synsetData[i] + ", "); }
      StdOut.println(synsetData[synsetData.length - 1]);
    }
  }
}
