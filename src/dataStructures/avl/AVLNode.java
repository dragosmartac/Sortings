package dataStructures.avl;

import dataStructures.bst.BSTNode;

public class AVLNode<T extends Comparable<T>> extends BSTNode<T> {

    private int height;

    public AVLNode(BSTNode left, BSTNode right, T value) {
        super(left, right, value);
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
