package io.github.arrayv.sorts.select;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/*
 *
  bad sort but even worse
 *
 */
public final class RodSort extends Sort {
    public RodSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Rod");
        this.setRunAllSortsName("Rod Sort");
        this.setRunSortName("Rodsort");
        this.setCategory("Impractical Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(true);
        this.setUnreasonableLimit(2048);
        this.setBogoSort(true);
    };
    
    public void runSort(int[] array, int length, int bucketCount) {
        //that's a very good grade, a++! /j
        for(int a = 0; a < length - 1; a++) {
            for(int i = a; i < length; i++) {
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
                Writes.swap(array, i, a, 0.03, true, false);
            };
        };
    };
};
