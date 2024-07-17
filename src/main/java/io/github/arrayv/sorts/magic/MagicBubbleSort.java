package io.github.arrayv.sorts.magic;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/**
 *
A variant of Straightening sort. Makes sure the item at K is at its correct value.
 *
 */
public final class MagicBubbleSort extends Sort {
    public MagicBubbleSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Magic Bubble");
        this.setRunAllSortsName("Magic Bubble Sort");
        this.setRunSortName("Magic Bubblesort");
        this.setCategory("Magic Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    };
	public int[] countSort(int[] arr, int sortLength, int bucketCount) {
		int[] array = arr;
		int max = Reads.analyzeMax(array, sortLength, 0, false);
        int[] output = Writes.copyOfArray(array, sortLength);
        int[] counts = Writes.createExternalArray(max + 1);

        for (int i = 0; i < sortLength; i++) {
            Writes.write(counts, array[i], counts[array[i]] + 1, 1, false, true);
            Highlights.markArray(1, i);
        };

        for (int i = 1; i < counts.length; i++) {
            Writes.write(counts, i, counts[i] + counts[i - 1], 1, true, true);
        };

        for (int i = sortLength - 1; i >= 0; i--) {
            output[counts[array[i]] - 1] = array[i];
            counts[array[i]]--;
        };
        Writes.deleteExternalArray(counts);
		return output;
	};
    public void runSort(int[] array, int length, int bucketCount) {
		//make aux array
		int[] values = this.countSort(array, length, 0);
		double delay = Math.max(2d / length, 0.001);
		Highlights.clearAllMarks();
		boolean sorted = false;
		for(int k = length - 1; k >= 0; k--) {
			Highlights.markArray(3, k);
			sorted = true;
			for(int l = array[k]; l != values[k]; l = array[k]) {
				if(Reads.compareValues(array[k], values[k]) == 1) {
					Writes.write(array, k, array[k] - 1, delay, true, false);
					sorted = false;
				}
				else if(Reads.compareValues(array[k], values[k]) == -1) {
					Writes.write(array, k, array[k] + 1, delay, true, false);
					sorted = false;
				};
			};
			for(int m = 0; m < k; m++) {
				Highlights.markArray(2, m);
				if(Reads.compareValues(array[m], values[m]) == 1) {
					Writes.write(array, m, array[m] - 1, delay, true, false);
					sorted = false;
				}
				else if(Reads.compareValues(array[m], values[m]) == -1) {
					Writes.write(array, m, array[m] + 1, delay, true, false);
					sorted = false;
				};
			};
			if (sorted) break;
		};
    };
};
