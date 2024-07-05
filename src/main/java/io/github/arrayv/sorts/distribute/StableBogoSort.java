package io.github.arrayv.sorts.distribute;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.BogoSorting;

/**
 * A variation of Bogo Sort that scans between the two to-be-swapped items, changing the swap points if it finds identicals until the two collide
 */
public final class StableBogoSort extends BogoSorting {
    public StableBogoSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Stable Bogo");
        this.setRunAllSortsName("Stable Bogo Sort");
        this.setRunSortName("Stable Bogosort");
        this.setCategory("Impractical Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(true);
        this.setUnreasonableLimit(8);
        this.setBogoSort(true);
    }

    @Override
    public void runSort(int[] array, int length, int bucketCount) {
        while(!this.isArraySorted(array, length))
            this.stableBogoSwap(array, 0, length, false);
    }
}
