package io.github.arrayv.sorts.joke;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

// "The list is sorted in a way you cannot comprehend" - wise words

public final class NlognFaithSort extends Sort {
    public NlognFaithSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Nlogn Faith");
        this.setRunAllSortsName("Nlogn Faith Sort");
        this.setRunSortName("Nlogn Faithsort");
        this.setCategory("Joke Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(true);
        this.setUnreasonableLimit(1);
        this.setBogoSort(false);
    }
    public void faith(int[] array, int start, int end) {
        if (start == end) break;
        this.faith(array, start, ((start + end) / 2) - 1);
        this.faith(array, (start + end / 2), end);
    };
    public void runSort(int[] array, int length, int bucketCount) {
        this.faith(array, 0, length - 1);
    }
}
