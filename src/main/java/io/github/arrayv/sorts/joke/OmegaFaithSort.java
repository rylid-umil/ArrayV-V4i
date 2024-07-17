package io.github.arrayv.sorts.joke;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

// "The list is sorted in a way you cannot comprehend" - wise words

public final class OmegaSort extends Sort {
    public OmegaSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Omega Faith");
        this.setRunAllSortsName("Omega Faith Sort");
        this.setRunSortName("Omega Faithsort");
        this.setCategory("Joke Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(true);
        this.setUnreasonableLimit(1);
        this.setBogoSort(false);
    }

    public void runSort(int[] array, int length, int bucketCount) {
        this.runSort(array, length, bucketCount);
    }
}
