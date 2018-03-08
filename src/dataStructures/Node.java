package dataStructures;

public class Node<T> {
  private int key;
  private int level;
  private T data;
  private Node<T> next;
  private Node<T> down;
  private int order;


  public Node(int key, T data, Node<T> next, Node<T> down, int level) {
    this.key = key;
    this.data = data;
    this.next = next;
    this.down = down;
    this.level = level;
    order = 1;
  }

  public void setNext(Node<T> next) {
    this.next = next;
  }

  public void setDown(Node<T> down) {
    this.down = down;
  }

  public void setData(T data) {
    this.data = data;
  }

  public Node<T> getNext() {
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

  public Node<T> getDown() {
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
