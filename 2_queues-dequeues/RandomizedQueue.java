import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
  Item[] queue;
  int numOfItems;

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
    if (isEmpty()) throw new NullPointerException();
    checkSize();

    int randomIndex = StdRandom.uniform(0, numOfItems);
    Item randomItem = queue[randomIndex];
    queue[randomIndex] = queue[numOfItems - 1];
    numOfItems--;
    return randomItem;
  }

  // return (but do not remove) a random item
  public Item sample() {
    if (isEmpty()) throw new NullPointerException();

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
    Item[] tmp = (Item[]) new Object[newSize];
    for (int i = 0; i < newSize; i++)
    { tmp[i] = queue[i]; }
    queue = tmp;
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator()
  { return new RQIterator(); }

  private class RQIterator implements Iterator<Item> {
    Item[] iterator;
    int index;
    int size;

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

      Item item = iterator[index];
      iterator[index] = null;
      return item;
    }

    public boolean hasNext()
    { return index + 1 < size; }

    public void remove()
    { throw new IllegalArgumentException(); }
  }

  // unit testing
  public static void main(String[] args) {

  }
}
