import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
  Item[] queue;
  int numOfItems;

  // construct an empty randomized queue
  public RandomizedQueue() {
    queue = new Item[1];
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
    if (item == null || isEmpty()) throw new NullPointerException();
    checkSize();

    int randomIndex = StdRandom.uniform(0, numOfItems);
    Item randomItem = queue[randomIndex];
    queue[randomIndex] = queue[numOfItems - 1];
    numOfItems--;
    return randomItem;
  }

  // return (but do not remove) a random item
  public Item sample() {
    if (item == null || isEmpty()) throw new NullPointerException();

    int randomIndex = StdRandom.uniform(0, numOfItems);
    Item randomItem = queue[randomIndex];
    return randomItem;
  }

  private void checkSize() {
    if (numOfItems == queue.length)
    { adjustSize(numOfItems * 2); }
    else if (numOfItems == queue.length / 4)
    { adjustSize(numOfItems); }
  }

  private void adjustSize(int newSize) {
    Item[] tmp = new Item[newSize];
    for (int i = 0; i < newSize; i++)
    { tmp[i] = queue[i]; }
    rq = tmp;
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator()
  { return new RQIterator(); }

  private class RQIterator implements Iterator<Item> {
    Item[] iterator;

    public RQIterator() {
      for (int i = 0; i < numOfItems; i++)
      { iterator[i] = queue[i]; }
    }

    public Item next() {
      if (!hasNext()) throw new java.util.NoSuchElementException();

      return iterator.dequeue();
    }

    public boolean hasNext()
    { return iterator.isEmpty(); }

    public void remove()
    { throw new IllegalArgumentException(); }
  }

  // unit testing
  public static void main(String[] args)
}
