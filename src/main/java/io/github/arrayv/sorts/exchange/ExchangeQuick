package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/*

 Probably an O(N^2) or O((N*logN) * (N^2)) quicksort.

 SORT IDEA BY RYLID
 CODED FOR ARRAYV BY RYLID
 --------------------
 -    SORTS PLUS    -
 --------------------

Sorts Plus July 2024

 */

public final class ExchangeQuickSort extends Sort {
    public ExchangeQuickSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Exchange Quick");
        this.setRunAllSortsName("Exchange Quick Sort");
        this.setRunSortName("Exchange Quicksort");
        this.setCategory("Exchange Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    };
    public int partition(int[] array, int start, int end) {
        boolean swapped = false;
        boolean done = false;
        int rounds = 0;
        int loc = start;
        int pivot = array[start];
        Highlights.markArray(1, start);
        Delays.sleep(5);
        Highlights.clearMark(1);
        for(int i = start + 1; i < end; i++) {
            Highlights.markArray(1, start);
            Highlights.markArray(2, i);
            Highlights.markArray(3, j);
            for (int j = i + 1; j < end + 1; j++) {
                done = true;
                Delays.sleep(0.02);
                if (Reads.compareValues(array[i], pivot) == 1) {
                    if(Reads.compareIndices(array, i, j, 0.02, true) == 1) {
                        Writes.swap(array, i, j, 0.04, true, false);
                        swapped = true;
                        done = false;
                    };
                } else break;
                if (i == end) {
                    rounds++;
                };
            };
            
            if (done) break;
            loc++;
        };
        
    };
    public void runSort(int[] array, int length, int bucketCount) {
    };
};
