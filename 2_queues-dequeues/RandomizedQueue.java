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
    checkSize();
    queue[numOfItems++] = item;
  }

  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) throw new java.util.NoSuchElementException();
    checkSize();

    int randomIndex = StdRandom.uniform(0, numOfItems);
    Item randomItem = queue[randomIndex];
    queue[randomIndex] = queue[numOfItems - 1];
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

  private void checkSize() {
    if (numOfItems == queue.length)
    { adjustSize(numOfItems * 2); }
    else if (numOfItems == 0)
    { /* this accounts for empty queue*/ }
    else if (numOfItems == queue.length / 4)
    { adjustSize(numOfItems); }
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

    String[] items = {"diaper", "violin", "car", "apple", "piano"};
    for (String item : items) {
      StdOut.println("Adding " + item + " to the queue.");
      rq.enqueue(item);
    }

    StdOut.println("Current size of the queue: " + rq.size());
    // StdOut.println("Sample item: " + rq.sample());
    // StdOut.println("Another sample item: " + rq.sample());
    // StdOut.println("=====================================");
    // while (!rq.isEmpty()) {
    //   StdOut.println("Removing " + rq.dequeue() + " from the queue.");
    // }
    // StdOut.println("Current size of the queue: " + rq.size());

    StdOut.println("========================================");
    // StdOut.println("Items in the queue via iterator.");
    // for (String item : rq)
    // { StdOut.println(item); }
    Iterator<String> it1 = rq.iterator();
    Iterator<String> it2 = rq.iterator();
    int count = 0;
    StdOut.println("Item #\t|\t1st Iterator\t|\t2nd Iterator");
    while (it1.hasNext() && it2.hasNext()) {
      StdOut.println("   " + count + "\t|\t" + it1.next() + "\t\t|\t" + it2.next());
      count++;
    }
  }
}
