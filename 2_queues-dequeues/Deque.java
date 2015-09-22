import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> {
  private Node first;
  private Node last;
  private int numOfItems;

  private class Node {
    // TODO implement double-linked list

    Item item;
    Node next;
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
    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;
    if (isEmpty()) last = first;
    numOfItems++;
  }

  // add the item to the end
  public void addLast(Item item) {
    Node oldLast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    if (isEmpty()) first = last;
    else           oldLast.next = last;
    numOfItems++;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    // TODO implement count + refine remove

    Item item = first.item;
    first = first.next;
    return item;
  }

  // remove and return the item from the end
  public Item removeLast() {
    // TODO implement count + refine remove

    Item item = last.item;
    last = last.previous;
    if (isEmpty()) last = null;
  }

  // return an iterator over items in order from front to end
  // public Iterator<Item> iterator()

  // unit testing
  public static void main(String[] args) {
    StdOut.print("Creating an empty Deque... ");
    Deque<String> d = new Deque<>();
    d.addFirst("phone");
    d.addLast("laptop");
    d.addFirst("love");
    d.addLast("tail");
    d.addFirst("head");

    StdOut.println("Items in the deque.");
    while (d.first != null) {
      StdOut.println(d.first.item);
      d.first = d.first.next;
    }
  }
}
