import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
  public static void main(String[] args) {
    int k = Integer.parseInt(args[0]);

    RandomizedQueue<String> queue = new RandomizedQueue<>();

    // adding items from the standard input
    while (!StdIn.isEmpty()) {
      String word = StdIn.readString();
      queue.enqueue(word);
    }

    while (k != 0) {
      String word = queue.dequeue();
      StdOut.println(word);
      k--;
    }
  }
}
