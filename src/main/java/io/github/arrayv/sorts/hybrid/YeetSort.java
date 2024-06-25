package io.github.arrayv.sorts.hybrid;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/*
 *
    Yeetsort is a hybrid of Shove Sort and Bubble Sort compares adjacent elements, shoving the left one to the end of the unsorted segment if they are out of order.
    If no elements are out of order or N - 1 passes have already been made, you're done.
 *
 */
public final class YeetSort extends Sort {
    public YeetSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Yeet");
        this.setRunAllSortsName("Yeet Sort");
        this.setRunSortName("Yeetsort");
        this.setCategory("Impractical Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(true);
        this.setUnreasonableLimit(1024);
        this.setBogoSort(false);
    };

    public void runSort(int[] array, int length, int bucketCount) {
        boolean sorted = true;
        for(int i = length - 1; i > 0; i--) {
            sorted = true;
            for(int j = 0; j < i - 1; j++) {
                Highlights.markArray(1, j);
                Highlights.markArray(2, j + 1);
                Delays.sleep(0.025);
                if (Reads.compareValues(array[j], array[j + 1]) == 1) {
                    Writes.multiSwap(array, j, i, 0.010, true, false);
                    sorted = false;
                };
            };
        if (sorted) break;
        };
    };
};
