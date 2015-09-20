public class RandomizedQueue<Item> implements Iterable<Item> {
  // construct an empty randomized queue
  public RandomizedQueue()

  // is the queue empty?
  public boolean isEmpty()

  // return the number of items on the queue
  public int size()

  // add the item
  public void enqueue(Item item)

  // remove and return a random item
  public Item dequeue()

  // return (but do not remove) a random item
  public Item sample()

  // return an independent iterator over items in random order
  public Iterator<Item> iterator()

  // unit testing
  public static void main(String[] args)
}
