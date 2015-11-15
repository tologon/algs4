import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class WordNet {
  private String[] synsets;
  private int numOfSynsets;
  private Digraph digraph;
  SAP sap;

  // constructor takes the name of the two input files
  public WordNet(String synsets, String hypernyms) {
    if (synsets == null || hypernyms == null) {
      throw new NullPointerException("synsets and hypernyms cannot be null.");
    }

    buildSynsets(synsets);
    buildDigraph(hypernyms);
    sap = new SAP(digraph);
  }

  private void buildSynsets(String synsets) {
    this.synsets = new String[1];
    numOfSynsets = 0;
    In in = new In(synsets);

    StdOut.println("Parsing " + synsets);
    while (!in.isEmpty()) {
      String line = in.readLine();
      String[] synsetData = line.split(",");
      upSize();

      int synsetID = Integer.parseInt(synsetData[0]);
      this.synsets[synsetID] = synsetData[1];
      numOfSynsets++;
    }
  }

  private void upSize() {
    if (numOfSynsets == synsets.length)
    { adjustSize(synsets.length * 2); }
  }

  private void adjustSize(int newSize) {
    String[] tmp = new String[newSize];
    for (int i = 0; i < numOfSynsets; i++)
    { tmp[i] = synsets[i]; }
    synsets = tmp;
  }

  private void buildDigraph(String hypernyms) {
    digraph = new Digraph(numOfSynsets);
    In in = new In(hypernyms);

    StdOut.println("Parsing " + hypernyms);
    while (!in.isEmpty()) {
      String line = in.readLine();
      String[] hypernymData = line.split(",");
      int synsetID = Integer.parseInt(hypernymData[0]);
      for (int i = 1; i < hypernymData.length; i++) {
        int hypernymID = Integer.parseInt(hypernymData[i]);
        digraph.addEdge(synsetID, hypernymID);
      }
    }

    // StdOut.println(digraph);
  }

  // returns all WordNet nouns
  public Iterable<String> nouns() {
    SET<String> nouns = new SET<>();
    int count = 0;
    for (int i = 0; i < numOfSynsets; i++) {
      String[] set = synsets[i].split(" ");
      for (int j = 0; j < set.length; j++) {
        if (!nouns.contains(set[j])) {
          nouns.add(set[j]);
          count++;
        }
      }
    }
    StdOut.println("# of nouns: " + count);
    return nouns;
  }

  // is the word a WordNet noun?
  public boolean isNoun(String word) {
    if (word == null) {
      throw new NullPointerException("word cannot be null.");
    }

    return getNounID(word) != -1;
  }

  // distance between nounA and nounB (defined below)
  public int distance(String nounA, String nounB) {
    if (nounA == null || nounB == null) {
      throw new NullPointerException("noun A and noun B cannot be null.");
    }

    StdOut.println("========================================");
    int v = getNounID(nounA);
    int w = getNounID(nounB);

    if (v == -1 || w == -1)   throw new IllegalArgumentException();
    return sap.length(v, w);
  }

  // a synset (second field of synsets.txt)
  // that is the common ancestor of nounA and nounB
  // in a shortest ancestral path (defined below)
  public String sap(String nounA, String nounB) {
    if (nounA == null || nounB == null) {
      throw new NullPointerException("noun A and noun B cannot be null.");
    }

    StdOut.println("========================================");
    int v = getNounID(nounA);
    int w = getNounID(nounB);

    if (v == -1 || w == -1)   throw new IllegalArgumentException();
    int ancestorID = sap.ancestor(v, w);
    // StdOut.println("ancestorID: " + ancestorID);
    return synsets[ancestorID];
  }

  private int getNounID(String noun) {
    StdOut.println("search for index of " + noun);
    int min = 0;
    int max = numOfSynsets - 1;
    while (min <= max) {
      int mid = min + (max - min) / 2;
      if (synsets[mid].contains(noun)) {
        return mid;
      }
      else if (synsets[mid].compareTo(noun) > 0) {
        max = mid - 1;
      }
      else {
        min = mid + 1;
      }
    }
    return -1;
  }

  // unit testing
  public static void main(String[] args) {
    WordNet wn = new WordNet(args[0], args[1]);

    // for (int i = 0; i < 100; i++) {
    //   int randomIndex = StdRandom.uniform(0, wn.numOfSynsets);
    //   StdOut.println("random synset: " + wn.synsets[randomIndex]);
    // }
    StdOut.println("# of synsets: " + wn.numOfSynsets);

    StdOut.println("# of vertices in digraph: " + wn.digraph.V());
    StdOut.println("# of edges in digraph: " + wn.digraph.E());

    for (int i = 0; i < 10; i++) {
      String nounA = wn.synsets[StdRandom.uniform(0, wn.numOfSynsets)];
      String nounB = wn.synsets[StdRandom.uniform(0, wn.numOfSynsets)];
      StdOut.println("SAP FOR "
                   + nounA + " AND " + nounB
                   + " IS " + wn.sap(nounA, nounB));
    }

    wn.nouns();

    // In in = new In(args[0]);
    // StdOut.println("Parsing " + args[0]);
    // while (!in.isEmpty()) {
    //   String line = in.readLine();
    //   String[] synsetData = line.split(",");
    //   StdOut.println("synset id: " + synsetData[0]
    //                + "; synset: " + synsetData[1]
    //                + "; gloss: " + synsetData[2]);
    // }
    // StdOut.println("==============================");
    // In in = new In(args[1]);
    // StdOut.println("Parsing " + args[1]);
    // while (!in.isEmpty()) {
    //   String line = in.readLine();
    //   String[] synsetData = line.split(",");
    //   StdOut.print("synset id: " + synsetData[0]
    //              + "; hypernyms id(s): ");
    //   for (int i = 1; i < synsetData.length - 1; i++)
    //   { StdOut.print(synsetData[i] + ", "); }
    //   StdOut.println(synsetData[synsetData.length - 1]);
    // }
  }
}
