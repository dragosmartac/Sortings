package sortings;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

public class MergeSort {

  private static Comparable[] aux;

  public static void mergeSort(Comparable[] v) {
    aux = new Comparable[v.length];
    mergeSortHelper(v, 0, v.length - 1);
  }

  private static void mergeSortHelper(Comparable[] v, int l, int r) {
    if(l >= r - 15) {
      InsertSort.sort(v, l, r);
      return;
    }

    int m = (l + r) / 2;

    mergeSortHelper(v, l, m);
    mergeSortHelper(v, m + 1,r);

    int i = l;
    int j = m + 1;
    int cnt = 0;

    while(i <= m && j <= r) {
      if(v[i].compareTo(v[j]) < 0) {
        aux[cnt++] = v[i++];
      }else {
        aux[cnt++] = v[j++];
      }
    }

    while (i <= m) aux[cnt++] = v[i++];
    while (j <= r) aux[cnt++] = v[j++];

    for(i = l, cnt = 0; i <= r; ++i, ++cnt) {
      v[i] = aux[cnt];
    }
  }


}
