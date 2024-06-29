package io.github.arrayv.sorts.hybrid;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.FlammerSorting;

/*
 *
  An out of place variant of Lazy Stable Sort with a few additions. Another programmer reading this i want to know the time complexity-
 *
 */
public final class FlammerSort extends FlammerSorting {
    public FlammerSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Flammer");
        this.setRunAllSortsName("Rylid's Flammer Sort");
        this.setRunSortName("Flammersort");
        this.setCategory("Hybrid Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    public void runSort(int[] array, int length, int bucketCount) {
        this.flammerSort(array, length, 0.1);
    }
}
