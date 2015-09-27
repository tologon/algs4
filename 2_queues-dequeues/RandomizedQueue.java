import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] queue;
  private int numOfItems;

  // construct an empty randomized queue
  public RandomizedQueue() {
    queue = (Item[]) new Object[1];
    numOfItems = 0;
  }

  // is the queue empty?
  public boolean isEmpty()
  { return numOfItems == 0; }

  // return the number of items on the queue
  public int size()
  { return numOfItems; }

  // add the item
  public void enqueue(Item item) {
    if (item == null) throw new NullPointerException();
    upSize();
    queue[numOfItems++] = item;
  }

  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) throw new java.util.NoSuchElementException();
    downSize();

    int randomIndex = StdRandom.uniform(0, numOfItems);
    Item randomItem = queue[randomIndex];
    queue[randomIndex] = queue[numOfItems - 1];
    queue[numOfItems - 1] = null;
    numOfItems--;
    return randomItem;
  }

  // return (but do not remove) a random item
  public Item sample() {
    if (isEmpty()) throw new java.util.NoSuchElementException();

    int randomIndex = StdRandom.uniform(0, numOfItems);
    Item randomItem = queue[randomIndex];
    return randomItem;
  }

  private void upSize() {
    if (numOfItems == queue.length)
    { adjustSize(queue.length * 2); }
  }

  private void downSize() {
    if (numOfItems > 0 && numOfItems == queue.length / 4)
    { adjustSize(queue.length / 2); }
  }

  private void adjustSize(int newSize) {
    Item[] tmp = (Item[]) new Object[newSize];
    for (int i = 0; i < numOfItems; i++)
    { tmp[i] = queue[i]; }
    queue = tmp;
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator()
  { return new RQIterator(); }

  private class RQIterator implements Iterator<Item> {
    private Item[] iterator;
    private int index;
    private int size;

    public RQIterator() {
      size = numOfItems;
      iterator = (Item[]) new Object[size];
      for (int i = 0; i < size; i++)
      { iterator[i] = queue[i]; }
      StdRandom.shuffle(iterator);
      index = 0;
    }

    public Item next() {
      if (!hasNext()) throw new java.util.NoSuchElementException();

      Item item = iterator[index++];
      return item;
    }

    public boolean hasNext()
    { return index < size; }

    public void remove()
    { throw new UnsupportedOperationException(); }
  }

  // unit testing
  public static void main(String[] args) {
    RandomizedQueue<String> rq = new RandomizedQueue<>();

    StdOut.println("Adding/Removing random items to the queue...");
    for (int i = 0; i < 100; i++) {
      int r0 = StdRandom.uniform(0, 10);
      int r1 = StdRandom.uniform(0, 10);
      String item = "item-" + r0 + "-" + r1;
      if (r0 <= 4) {
        StdOut.println("Adding " + item);
        rq.enqueue(item);
      }
      else if (r0 > 5 && !rq.isEmpty()) {
        StdOut.println("Removing a random item");
        rq.dequeue();
      }
      else {
        StdOut.println("The RQ is empty.");
      }
      StdOut.println("========================================");
    }
    StdOut.println("Current size of the queue: " + rq.size());
    // int r0, r1;
    // StdOut.println("Testing adding 2 adds + 1 remove + 1 add");
    // // item #1
    // r0 = StdRandom.uniform(0, 10);
    // r1 = StdRandom.uniform(0, 10);
    // String item1 = "item-" + r0 + "-" + r1;
    // // item #2
    // r0 = StdRandom.uniform(0, 10);
    // r1 = StdRandom.uniform(0, 10);
    // String item2 = "item-" + r0 + "-" + r1;
    // // item #3
    // r0 = StdRandom.uniform(0, 10);
    // r1 = StdRandom.uniform(0, 10);
    // String item3 = "item-" + r0 + "-" + r1;
    // rq.enqueue(item1);
    // rq.enqueue(item2);
    // rq.dequeue();
    // rq.enqueue(item3);

    // StdOut.println("Sample item: " + rq.sample());
    // StdOut.println("Another sample item: " + rq.sample());
    // StdOut.println("=====================================");
    // while (!rq.isEmpty()) {
    //   StdOut.println("Removing " + rq.dequeue() + " from the queue.");
    // }
    // StdOut.println("Current size of the queue: " + rq.size());
  }
}
