package sortings;

public class InsertSort {

  public static void sort(Comparable[] v, int left, int right) {

    Comparable key;
    int j;

    for(int i = left; i <= right; ++i) {
      key = v[i];
      j = i - 1;

      while (j >= left && key.compareTo(v[j]) < 0) {
        v[j + 1] = v[j];
        j--;
      }

      v[j + 1] = key;
    }

  }

}
