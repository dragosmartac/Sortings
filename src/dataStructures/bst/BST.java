package dataStructures.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BST<T extends Comparable<T>> {

  private BSTNode<T> root;

  public static void main(String[] args) {
    BST<Integer> bst = new BST<>();

    bst.add(5);
    bst.add(11);
    bst.add(1);
    bst.add(2);
    bst.add(3);
    bst.add(10);
    bst.add(8);
    bst.add(7);
    bst.add(6);
    bst.add(9);
    System.out.println(bst);
    System.out.println(bst.traverseInorder());
    bst.delete(5);
    System.out.println(bst);
    System.out.println(bst.traverseInorder());
    bst.delete(2);
    bst.delete(3);
    bst.delete(11);
    bst.delete(10);
    System.out.println(bst);
    System.out.println(bst.traverseInorder());
    System.out.println(bst.contains(6));
    System.out.println(bst.contains(1));
    System.out.println(bst.contains(9));
    System.out.println(bst.contains(10));
  }

  public void add(T newValue) {
    if(root == null) {
      root = new BSTNode<>(null, null, newValue);
    } else {
      add(newValue, root);
    }
  }

  private void add(T newValue, BSTNode<T> subtree) {
    if(subtree.getValue().compareTo(newValue) == 0) {
      //Element is already in the BST
    }else if(subtree.getValue().compareTo(newValue) > 0){
      if(subtree.getLeft() == null) {
        subtree.setLeft(new BSTNode<>(null, null, newValue));
      } else {
        add(newValue, subtree.getLeft());
      }
    } else {
      if(subtree.getRight() == null) {
        subtree.setRight(new BSTNode<>(null, null, newValue));
      } else {
        add(newValue, subtree.getRight());
      }
    }
  }

  public void delete(T value) {
    root = delete(value, root);
  }

  private BSTNode<T> delete(T value, BSTNode<T> subtree) {
    if(subtree == null) {
      return null;
    } else if(subtree.getValue().compareTo(value) == 0) {
      //Element found
      if(subtree.getLeft() == null) {
        return subtree.getRight();
      }

      BSTNode<T> maxLeft = findMax(subtree.getLeft());
      delete(maxLeft.getValue());
      maxLeft.setLeft(subtree.getLeft());
      maxLeft.setRight(subtree.getRight());

      return maxLeft;

    }else if(subtree.getValue().compareTo(value) > 0){
      if(subtree.getLeft() == null) {
        //Do nothing - element not in BST
      } else {
        subtree.setLeft(delete(value, subtree.getLeft()));
      }
    } else {
      if(subtree.getRight() == null) {
        //Do nothing - element not in BST
      } else {
        subtree.setRight(delete(value, subtree.getRight()));
      }
    }

    return subtree;
  }

  private BSTNode<T> findMax(BSTNode<T> subtree) {
    if(subtree.getRight() == null) {
      return subtree;
    } else {
      return findMax(subtree.getRight());
    }
  }

  public boolean contains(T value) {
    return contains(root, value);
  }

  private boolean contains(BSTNode<T> subtree, T value) {
    if (subtree == null) {
      return false;
    } else if (subtree.getValue().compareTo(value) == 0) {
      return true;
    } else if (subtree.getValue().compareTo(value) > 0) {
      return contains(subtree.getLeft(), value);
    } else {
      return contains(subtree.getRight(), value);
    }
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    toString(root, 0, stringBuilder);
    return stringBuilder.toString() + '\n';
  }

  private void toString(BSTNode<T> node, int indentation, StringBuilder stringBuilder) {
    if (node == null) {
      return;
    }
    IntStream.range(0, indentation).forEach(i -> stringBuilder.append("  "));
    stringBuilder.append(node.getValue() + "\n");
    toString(node.getLeft(), indentation + 1, stringBuilder);
    toString(node.getRight(), indentation + 1, stringBuilder);
  }

  public List<T> traverseInorder() {
    List<T> traversal = new ArrayList<>();
    traverseInorder(root, traversal);
    return traversal;
  }

  private void traverseInorder(BSTNode<T> subtree, List<T> traversal) {
    if (subtree == null) {
      return;
    }
    traverseInorder(subtree.getLeft(), traversal);
    traversal.add(subtree.getValue());
    traverseInorder(subtree.getRight(), traversal);
  }


}
