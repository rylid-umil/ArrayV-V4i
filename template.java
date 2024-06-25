package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/*
 *
Optional sort explanation
 *
 */
public final class NewSort extends Sort {
    public NewSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("New");
        this.setRunAllSortsName("New Sort");
        this.setRunSortName("Newsort");
        this.setCategory("Uncategorized");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(true);
    }

    public void runSort(int[] array, int length, int bucketCount) {
    }
}
