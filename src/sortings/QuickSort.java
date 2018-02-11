package sortings;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

public class QuickSort {

  public static void qsort(Comparable[] v) {
    qsortHelper(v, 0, v.length - 1);
  }

  public static void concurentQsort(Comparable[] v) {

    Thread[] sortingThreads = new Thread[4];

    sortingThreads[0] = new Thread(() -> qsortHelper(v, 0, v.length / 4));
    sortingThreads[1] = new Thread(() -> qsortHelper(v, v.length / 4 + 1, v.length / 2));
    sortingThreads[2] = new Thread(() -> qsortHelper(v, v.length / 2 + 1, v.length / 4 * 3));
    sortingThreads[3] = new Thread(() -> qsortHelper(v, v.length / 4 * 3 + 1, v.length - 1) );

    Arrays.stream(sortingThreads).forEach(Thread::start);
    Arrays.stream(sortingThreads).forEach(t -> {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });


    //Combine the results
    Comparable[] u = new Comparable[v.length];
    int[] limits = new int[5];
    int[] ind = new int[4];

    limits[0] = -1;
    limits[1] = v.length / 4;
    limits[2] = v.length / 2;
    limits[3] = v.length / 4 * 3;
    limits[4] = v.length - 1;
    IntStream.range(0, 4).parallel().forEach(i -> ind[i] = limits[i] + 1);

    Optional<Comparable> opMin;
    int minInd;

    for(int i = 0; i < v.length; ++i) {
      opMin = Optional.empty();
      minInd = 0;

      for(int j = 0; j < 4; ++j) {
        if(ind[j] <= limits[j + 1]) {
          if(!opMin.isPresent()){
            opMin = Optional.of(v[ind[j]]);
            minInd = j;
          }else if(v[ind[j]].compareTo(opMin.get()) < 0) {
            opMin = Optional.of(v[ind[j]]);
            minInd = j;
          }
        }
      }

      u[i] = opMin.get();
      ind[minInd]++;
    }

    IntStream.range(0, v.length).parallel().forEach(i -> v[i] = u[i]);
  }

  private static void qsortHelper(Comparable[] v, int l, int r) {
    if(l >= r - 15) {
      InsertSort.sort(v, l, r);
      return;
    }

    int partIndex = partition(v, l, r);

    qsortHelper(v, l, partIndex - 1);
    qsortHelper(v, partIndex + 1, r);
  }

  private static int partition(Comparable[] v, int l, int r) {
    int i = l - 1;
    int j = r;
    Comparable pivot = v[r];

    while(i < j) {


      //Find the left most element greater than my pivot
      while(v[++i].compareTo(pivot) < 0);

      //Find the right most element lower than my pivot
      while(v[--j].compareTo(pivot) > 0 && j > i);

      if(i < j) {
        swap(v, i, j);
      }
    }

    swap(v, i, r);

    return i;
  }

  private static void swap(Comparable[] v, int i, int j) {
    Comparable aux = v[i];
    v[i] = v[j];
    v[j] = aux;
  }

}
