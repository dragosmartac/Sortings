package dataStructures.avl;

import dataStructures.bst.BSTNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AVL <T extends Comparable<T>>{

    private AVLNode<T> root;


    public static void main(String[] args) {
        AVL<Integer> foo = new AVL<>();

        foo.add(1);
        foo.add(2);
        foo.add(11);
        foo.add(12);
        foo.add(6);
        foo.add(2);
        foo.add(5);
        foo.add(3);

        System.out.println(foo);
        System.out.println(foo.traverseInorder());

        foo.delete(5);
        foo.delete(2);
        foo.delete(0);

        System.out.println(foo);
        System.out.println(foo.traverseInorder());
    }

    public void add(T value) {
        if(root == null) {
            root = new AVLNode<>(null, null, value);
        } else {
            root = add(value, root);
        }
    }

    private AVLNode<T> add(T value, AVLNode<T> subtree) {
        if(subtree.getValue().compareTo(value) == 0) {
            //Element already in the BST
        } else if(subtree.getValue().compareTo(value) > 0) {
            if (subtree.getLeft() != null) {
                subtree.setLeft(add(value, (AVLNode<T>) subtree.getLeft()));
            } else {
                subtree.setLeft(new AVLNode<>(null, null, value));
            }
        } if(subtree.getValue().compareTo(value) < 0) {
            if (subtree.getRight() != null) {
                subtree.setRight(add(value, (AVLNode<T>) subtree.getRight()));
            } else {
                subtree.setRight(new AVLNode<>(null, null, value));
            }
        }
        subtree = rebalance(subtree);
        updateHeight(subtree);
        return subtree;
    }

    private int height(AVLNode<T> node) {
        return node == null ? -1 : node.getHeight();
    }

    private void updateHeight(AVLNode<T> node) {
        node.setHeight(Math.max(height((AVLNode<T>) node.getLeft()),
                height((AVLNode<T>) node.getRight()))
                + 1);
    }

    private AVLNode<T> rebalance(AVLNode<T> subtree) {
        final int deltaHeight = height((AVLNode<T>) subtree.getLeft()) - height((AVLNode<T>) subtree.getRight());

        if(deltaHeight >= 2) {
            if(height((AVLNode<T>) subtree.getLeft().getLeft()) >
                    height((AVLNode<T>) subtree.getLeft().getRight())) {
                return rotateRight(subtree);
            } else {
                return rotateLeftRight(subtree);
            }
        } else if(deltaHeight <= -2) {
            if(height((AVLNode<T>) subtree.getRight().getRight()) >
                    height((AVLNode<T>) subtree.getRight().getLeft())) {
                return rotateLeft(subtree);
            } else {
                return rotateRightLeft(subtree);
            }
        }
        return subtree;
    }

    private AVLNode<T> rotateRightLeft(AVLNode<T> subtree) {
        subtree.setRight(rotateRight((AVLNode<T>) subtree.getRight()));

        return rotateLeft(subtree);
    }

    private AVLNode<T> rotateLeftRight(AVLNode<T> subtree) {
        subtree.setLeft(rotateLeft((AVLNode<T>) subtree.getLeft()));

        return rotateRight(subtree);
    }

    private AVLNode<T> rotateLeft(AVLNode<T> subtree) {
        BSTNode<T> aux = subtree.getRight();
        subtree.setRight(aux.getLeft());
        aux.setLeft(subtree);

        updateHeight(subtree);

        return (AVLNode<T>) aux;
    }

    private AVLNode<T> rotateRight(AVLNode<T> subtree) {
        BSTNode<T> aux = subtree.getLeft();
        subtree.setLeft(aux.getRight());
        aux.setRight(subtree);

        updateHeight(subtree);

        return (AVLNode<T>) aux;
    }

    public void delete(T value) {
        if(root == null) {
            //Do nothing
        }else {
            root = delete(value, root);
        }
    }

    private AVLNode<T> delete(T value, AVLNode<T> subtree) {
        if(subtree.getValue() == value) {
            //Element found
            if(subtree.getLeft() == null && subtree.getRight() == null) {
                return null;
            }else if(subtree.getLeft() == null){
                return (AVLNode<T>) subtree.getRight();
            }else if(subtree.getRight() == null){
                return (AVLNode<T>) subtree.getLeft();
            }

            AVLNode<T> maxElem = findMax((AVLNode<T>) subtree.getLeft());
            delete(maxElem.getValue());
            maxElem.setLeft(subtree.getLeft());
            maxElem.setRight(subtree.getRight());
            return maxElem;
        }else if(subtree.getLeft() != null && subtree.getValue().compareTo(value) > 0){
            subtree.setLeft(delete(value, (AVLNode<T>) subtree.getLeft()));
        }else if(subtree.getRight() != null && subtree.getValue().compareTo(value) < 0){
            subtree.setRight(delete(value, (AVLNode<T>) subtree.getRight()));
        }

        rebalance(subtree);
        return subtree;
    }

    private AVLNode<T> findMax(AVLNode<T> subtree) {
        if(subtree == null) {
            return null;
        }else if(subtree.getRight() == null) {
            return subtree;
        }
        return findMax((AVLNode<T>) subtree.getRight());
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
