package dataStructures.skiplist;

public class SkipListNode<T> {
  private int key;
  private int level;
  private T data;
  private SkipListNode<T> next;
  private SkipListNode<T> down;
  private int order;


  public SkipListNode(int key, T data, SkipListNode<T> next, SkipListNode<T> down, int level) {
    this.key = key;
    this.data = data;
    this.next = next;
    this.down = down;
    this.level = level;
    order = 1;
  }

  public void setNext(SkipListNode<T> next) {
    this.next = next;
  }

  public void setDown(SkipListNode<T> down) {
    this.down = down;
  }

  public void setData(T data) {
    this.data = data;
  }

  public SkipListNode<T> getNext() {
    return next;
  }

  public T getData() {
    return data;
  }

  public int getKey() {
    return key;
  }

  public int getLevel() {
    return level;
  }

  public SkipListNode<T> getDown() {
    return down;
  }

  public void increaseOrder() {
    ++order;
  }

  public void decreaseOrder() {
    --order;
  }

  public void resetOrder() {
    order = 1;
  }

  public int getOrder() {
    return order;
  }
}
