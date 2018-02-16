package testsuite;

import static junit.framework.TestCase.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
}
