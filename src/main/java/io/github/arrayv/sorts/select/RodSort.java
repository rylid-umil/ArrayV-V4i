package io.github.arrayv.sorts.select;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/*
 *
  Rod sort has two spots, i and j, which both start at 0. J iterates throguh the list, and if ever the item at j is greater than the one at i,
set i to the item at j, and set j to zero. If i reaches the end of the unsorted segment, swap the item at i and the item previous to the
sorted segment, and start a new pass. Repeat N times.
 *
 */
public final class RodSort extends Sort {
    public RodSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("New");
        this.setRunAllSortsName("New Sort");
        this.setRunSortName("Newsort");
        this.setCategory("Impractical Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(true);
        this.setUnreasonableLimit(2048);
        this.setBogoSort(true);
    };
    
    public void runSort(int[] array, int length, int bucketCount) {
        //that's a very good grade, a++! /j
        for(int a = 0; a < length - 1, a++) {
            for(int i = a; i < length, i + 1) {
                for(int j = a; j < length; j++) {
                    if(Reads.compareValues(array[i], array[j]) == 1) {
                        i = j;
                        Delays.sleep(0.01);
                        break;
                    };
                    Highlights.markArray(1, j);
                    Highlights.markArray(2, i);
                    Delays.sleep(0.01);
                };
                Writes.swap(array, i, a - 1, 0.03, true, false);
            };
        };
    };
};
