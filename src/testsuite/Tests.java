package testsuite;

import static junit.framework.TestCase.assertTrue;

import dataStructures.avl.AVL;
import dataStructures.bst.BST;
import dataStructures.skiplist.SkipList;
import java.util.Arrays;
import java.util.Random;
import org.junit.Test;
import sortings.MergeSort;
import sortings.QuickSort;

public class Tests {

  Random random = new Random();

  //Arrays for testing
  private Integer[] a = {2, 1, 2, 1, 4, 2, 3};
  private Integer[] b = {1, 1, 2, 2, 2, 3, 4};

  //Arrays generators
  private Integer[] integerRandomGenerator(int no, int low, int high){

    return random.ints(no, low, high).boxed().toArray(Integer[]::new);
  }

  @Test
  public void qsortTest1() {
    long time = System.currentTimeMillis();
    QuickSort.qsort(a);
    System.out.println("qsortTest1 executed in: " + (System.currentTimeMillis() - time));

    assertTrue(Arrays.equals(a, b));
  }

  @Test
  public void qsortTest2() {

    Integer[] a = integerRandomGenerator(10000000, Integer.MIN_VALUE, Integer.MAX_VALUE);
    Integer[] b = a.clone();
    Arrays.sort(b);


    long time = System.currentTimeMillis();
    QuickSort.concurentQsort(a);
    System.out.println("qsortTest2 executed in: " + (System.currentTimeMillis() - time));

    assertTrue(Arrays.equals(a, b));
   }

  @Test
  public void mergeSortTest1() {

    Integer[] a = integerRandomGenerator(10000000, Integer.MIN_VALUE, Integer.MAX_VALUE);
    Integer[] b = a.clone();
    Arrays.sort(b);


    long time = System.currentTimeMillis();
    MergeSort.mergeSort(a);
    System.out.println("MergeSortTest1 executed in: " + (System.currentTimeMillis() - time));

    assertTrue(Arrays.equals(a, b));
  }

  @Test
  public void skipListTest1() {
    Integer[] a = integerRandomGenerator(10000000, Integer.MIN_VALUE, Integer.MAX_VALUE);
    Integer[] b = a.clone();
    Arrays.sort(b);


    long time = System.currentTimeMillis();
    SkipList<Integer> skipList = new SkipList<>(25);
    for(int i = 0 ; i < a.length ; ++i) {
      skipList.add(a[i], a[i]);
    }
    //Arrays.stream(a).forEach(i -> skipList.add(i, i));
    System.out.println("MergeSortTest1 executed in: " + (System.currentTimeMillis() - time));

    assertTrue(Arrays.equals(skipList.toList().toArray(), b));
  }

  @Test
  public void bsts1() {
    AVL<Integer> avl = new AVL<>();
    BST<Integer> bst = new BST<>();
    Integer[] a = new Integer[10000];

    for(int i = 0; i < 10000; ++i) {
      a[i] = i;
    }

    long time = System.currentTimeMillis();
    for(int i = 0; i < 10000; ++i) {
      avl.add(i);
    }

    System.out.println("AVL test executed in: " + (System.currentTimeMillis() - time));
    //assertTrue(Arrays.equals(avl.traverseInorder().toArray(), a));

    time = System.currentTimeMillis();
    for(int i = 0; i < 10000; ++i) {
      bst.add(i);
    }

    System.out.println("BST test executed in: " + (System.currentTimeMillis() - time));
    //assertTrue(Arrays.equals(avl.traverseInorder().toArray(), a));

  }

}
