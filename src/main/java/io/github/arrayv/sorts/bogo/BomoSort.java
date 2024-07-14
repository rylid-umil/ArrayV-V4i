package io.github.arrayv.sorts.bogo;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.BogoSorting;

// Bomosort moves a random item to a random index until the list is sorted.

// Bomo Sort is by Potassium, credit to him

public final class BomoSort extends BogoSorting {
    public BomoSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Bomo");
        this.setRunAllSortsName("Bomo SOrt)");
        this.setRunSortName("Bomosort");
        this.setCategory("Bogo Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(true);
        this.setUnreasonableLimit(9);
        this.setBogoSort(true);
    }

    @Override
    public void runSort(int[] array, int length, int bucketCount) {
        while(!this.isArraySorted(array, length))
            Writes.multiSwap(array, BogoSorting.randInt(0, length - 1), BogoSorting.randInt(0, length - 1), this.delay, true, false);
    }
}
