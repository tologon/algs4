public class Deque<Item> implements Iterable<Item> {
  private Node first;
  private Node last;
  private int numOfItems;

  private class Node {
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
  public Item removeFirst()

  // remove and return the item from the end
  public Item removeLast()

  // return an iterator over items in order from front to end
  public Iterator<Item> iterator()

  // unit testing
  public static void main(String[] args)
}
