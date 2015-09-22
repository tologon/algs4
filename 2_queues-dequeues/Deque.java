import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> {
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
    if (item == null) throw new IllegalArgumentException();

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
    if (item == null) throw new IllegalArgumentException();

    Node oldLast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    if (isEmpty()) first = last;
    else {
      oldLast.next = last;
      last.previous = oldLast;
    numOfItems++;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (isEmpty()) throw new ChangeThisException();

    numOfItems--;
    Item item = first.item;
    first = first.next;
    if (isEmpty())  last = null;
    else            first.previous = null;
    return item;
  }

  // remove and return the item from the end
  public Item removeLast() {
    if (isEmpty()) throw new ChangeThisException();

    numOfItems--;
    Item item = last.item;
    last = last.previous;
    if (isEmpty()) first = null;
    else           last.next = null;
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
