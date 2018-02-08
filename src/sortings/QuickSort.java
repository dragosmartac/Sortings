package sortings;

public class QuickSort {

  public static void qsort(Comparable[] v) {
    qsortHelper(v, 0, v.length - 1);
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
    int i = l;
    int j = r - 1;
    Comparable pivot = v[r];

    while(i < j) {

      //Find the left most element greater than my pivot
      while(v[i].compareTo(pivot) < 0) {
        i++;
      }

      //Find the right most element lower than my pivot
      while(v[j].compareTo(pivot) >= 0 && j > i) {
        j--;
      }

      swap(v, i, j);
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
