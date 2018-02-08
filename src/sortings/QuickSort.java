package sortings;

public class QuickSort {

  public static void qsort(Comparable[] v) {
    qsortHelper(v, 0, v.length - 1);
  }
  public static void concurentQsort(Comparable[] v) {
    Thread a = new Thread(() -> qsortHelper(v, 0, v.length / 2));
    a.start();

    Thread b = new Thread(() -> qsortHelper(v, v.length / 2 + 1, v.length - 1));
    b.start();

    try {
      a.join();
      b.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    //Combine the results
    Comparable[] u = new Comparable[v.length];
    int i = 0;
    int j = v.length / 2 + 1;
    int ui = 0;
    while(i <= v.length / 2 && j <= v.length - 1) {
      if(v[i].compareTo(v[j]) < 0) {
        u[ui++] = v[i++];
      }else {
        u[ui++] = v[j++];
      }
    }

    while(i <= v.length / 2 ) {
      u[ui++] = v[i++];
    }

    while (j <= v.length - 1) {
      u[ui++] = v[j++];
    }

    for(i = 0; i < v.length; ++i) {
      v[i] = u[i];
    }
  }

  private static void qsortHelper(Comparable[] v, int l, int r) {
    if(l >= r) {
      return ;
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
