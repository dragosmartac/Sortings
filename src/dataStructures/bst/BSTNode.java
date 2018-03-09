package dataStructures.bst;

public class BSTNode<T extends Comparable<T>> {

  private BSTNode left;
  private BSTNode right;
  private T value;


  public BSTNode(BSTNode left, BSTNode right, T value) {
    this.left = left;
    this.right = right;
    this.value = value;
  }

  public BSTNode getLeft() {
    return left;
  }

  public BSTNode getRight() {
    return right;
  }

  public T getValue() {
    return value;
  }

  public void setLeft(BSTNode left) {
    this.left = left;
  }

  public void setRight(BSTNode right) {
    this.right = right;
  }
}
