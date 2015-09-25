import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
  private Node first;
  private Node last;
  private int numOfItems;

  private class Node {
    Item item;
    Node next;
    Node previous;
  }

  // construct an empty deque
  public Deque() {
    first = null;
    last = null;
    numOfItems = 0;
  }

  // is the deque empty?
  public boolean isEmpty()
  { return numOfItems == 0; }

  // return the number of items on the deque
  public int size()
  { return numOfItems; }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) throw new NullPointerException();

    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;
    if (isEmpty()) last = first;
    else           oldFirst.previous = first;
    first.previous = null;
    numOfItems++;
  }

  // add the item to the end
  public void addLast(Item item) {
    if (item == null) throw new NullPointerException();

    Node oldLast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    if (isEmpty()) first = last;
    else {
      oldLast.next = last;
      last.previous = oldLast;
    }
    numOfItems++;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (isEmpty()) throw new java.util.NoSuchElementException();

    numOfItems--;
    Item item = first.item;
    first = first.next;
    if (isEmpty())  last = null;
    else            first.previous = null;
    return item;
  }

  // remove and return the item from the end
  public Item removeLast() {
    if (isEmpty()) throw new java.util.NoSuchElementException();

    numOfItems--;
    Item item = last.item;
    last = last.previous;
    if (isEmpty()) first = null;
    else           last.next = null;
    return item;
  }

  // return an iterator over items in order from front to end
  public Iterator<Item> iterator()
  { return new DequeIterator(); }

  private class DequeIterator implements Iterator<Item> {
    Node current;

    public DequeIterator()
    { current = first; }

    public Item next() {
      if (!hasNext()) throw new java.util.NoSuchElementException();

      Item item = current.item;
      current = current.next;
      return item;
    }

    public boolean hasNext()
    { return current != null; }

    public void remove()
    { throw new IllegalArgumentException(); }
  }

  // unit testing
  public static void main(String[] args) {
    Deque<String> d = new Deque<>();

    StdOut.println("Adding random items to the deque...");
    for (int i = 0; i < 10; i++) {
      int r0 = StdRandom.uniform(0, 10);
      int r1 = StdRandom.uniform(0, 10);
      String item = "item-" + r0 + "-" + r1;
      if (r0 <= 4) {
        StdOut.println("Adding " + item + " in the front.");
        d.addFirst(item);
      } else {
        StdOut.println("Adding " + item + " in the back.");
        d.addLast(item);
      }
    }

    StdOut.println("Items in the deque via iterator.");
    for (String item : d)
    { StdOut.println(item); }

    StdOut.println("Removing the items from deque in reverse order.");
    while (!d.isEmpty()) {
      String item = d.removeLast();
      StdOut.println("Removing " + item);
      Std // TODO
    }
  }
}
