package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

// "The list is sorted in a way you cannot comprehend" - wise words

public final class NewSort extends Sort {
    public NewSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Faith");
        this.setRunAllSortsName("Faith Sort");
        this.setRunSortName("Faithsort");
        this.setCategory("Joke Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(true);
        this.setUnreasonableLimit(1);
        this.setBogoSort(false);
    }

    public void runSort(int[] array, int length, int bucketCount) {
      
    }
}
