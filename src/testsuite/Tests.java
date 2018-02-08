package testsuite;

import static junit.framework.TestCase.assertTrue;

import java.util.Arrays;
import org.junit.Test;
import sortings.QuickSort;

public class Tests {

  @Test
  public void qsortTest() {
    Integer[] a = {2, 1, 2, 1, 4, 2, 3};
    Integer[] b = {1, 1, 2, 2, 2, 3, 4};
    QuickSort.qsort(a);

    assertTrue(Arrays.equals(a, b));
  }

}
