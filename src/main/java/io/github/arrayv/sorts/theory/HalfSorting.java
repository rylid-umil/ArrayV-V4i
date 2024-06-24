package io.github.arrayv.sorts.theory;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.insert.BinaryInsertionSort;
import io.github.arrayv.sorts.templates.Sort;

/*
 *  This algorithm is a quick way to sort an array with the following restrictions: you can't interact with the second
 *  half of the array, except read and compare elements with them, except for the last element, which you can shift any
 *  item from the first half to. Example for the shift is [1 2 3 4] shift the 1 to the where the 4 is like radix lsd [2 3 4 1].
 *  
 *  It can NOT shift from the last to the first, just the first to the last.
 */

public final class HalfSorting extends Sort {
    public BadSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Half Sorting");
        this.setRunAllSortsName("Half-access Sorting");
        this.setRunSortName("Halfsorting");
        this.setCategory("Theory Sorting");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    @Override
    public void runSort(int[] array, int length, int bucketCount) {
      int highest = 0;
      int l = 0
      int[] excluded = Writes.createExternalArray(length);
      for (int i = 0; i < ((Math.ceil(length / 2)) - 1); i++) {
        highest = 0;
        for (int j = 0; i < length; j++) {
          if (Reads.compareValues(array[j], array[highest]) == -1) {
            highest = j;
            Delays.sleep(0.001);
          };
          Highlights.markArray(1, highest);
          Highlights.markArray(2, j);
          Delays.sleep(0.01);
        };
        l = 0;
        for (int k = highest; k > ((Math.ceil(length / 2)) - 1); (; k) {
          if (excluded[l] == 0) {
            Writes.multiSwap(array, l, length - l, 0.075, true, false);
            Writes.multiSwap(excluded, l, length - l, 0.075, true, true);
            k--;
          }
          else {
            l++
          };
          Highlghts.markArray(1, l);
          Highlights.markArray(2, k);
        }
        Writes.write(excluded, k, 1, 0.075, true, true);
      }
      Writes.deleteExternalArray(excludes);
      BinaryInsertionSort.customBinaryInsert(array, 0, (Math.ceil(length / 2)) - 1, 0.1);
      for (int i = 0; i < ((Math.ceil(length / 2)) - 1); i++) {
        Writes.multiSwap(array, 1, 1 - length, 0.075, true, false)
      };
      BinaryInsertionSort.customBinaryInsert(array, 0, (Math.ceil(length / 2)) - 1, 0.1);
    }
}
