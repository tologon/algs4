public class WordNet {
  // constructor takes the name of the two input files
  public WordNet(String synsets, String hypernyms) {
    if (synsets == null || hypernyms == null) {
      throw new NullPointerException("synsets and hypernyms cannot be null.");
    }
  }

  // returns all WordNet nouns
  public Iterable<String> nouns() {
  }

  // is the word a WordNet noun?
  public boolean isNoun(String word) {
    if (word == null) {
      throw new NullPointerException("word cannot be null.");
    }
  }

  // distance between nounA and nounB (defined below)
  public int distance(String nounA, String nounB) {
    if (nounA == null || nounB == null) {
      throw new NullPointerException("noun A and noun B cannot be null.");
    }
  }

  // a synset (second field of synsets.txt)
  // that is the common ancestor of nounA and nounB
  // in a shortest ancestral path (defined below)
  public String sap(String nounA, String nounB) {
    if (nounA == null || nounB == null) {
      throw new NullPointerException("noun A and noun B cannot be null.");
    }
  }

  // unit testing
  public static void main(String[] args) {
  }
}
