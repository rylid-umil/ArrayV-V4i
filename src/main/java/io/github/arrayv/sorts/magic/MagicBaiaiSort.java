package io.github.arrayv.sorts.magic;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/**
 *
	Baiai sort by Kuvina Saydaki (?). Hybrid of Baiai (structure) and Magic Bubble (sorting)
 *
 */
public final class MagicBaiaiSort extends Sort {
    public MagicBaiaiSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Magic Baiai");
        this.setRunAllSortsName("Magic Baiai Sort");
        this.setRunSortName("Magic Baiaiort");
        this.setCategory("Magic Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }
	public int[] countSort(int[] arr, int sortLength, int bucketCount) {
		int[] array = arr;
		int max = Reads.analyzeMax(array, sortLength, 0, false);
        int[] output = Writes.copyOfArray(array, sortLength);
        int[] counts = Writes.createExternalArray(sortLength);

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
		double delay = Math.max(2d / length, 0.0001);
		Highlights.clearAllMarks();
		boolean sorted = false;
		for(int k = 0; k < length; k++) {
			Highlights.markArray(4, k);
			sorted = false;
			Highlights.markArray(3, k);
			for(int m = 0; m <= k; m++) {
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
		};
		for(int n = length - 1; n >= 0; n--) {
			Highlights.markArray(3, n);
			sorted = true;
			for(int l = array[n]; l != values[n]; l = array[n]) {
				if(Reads.compareValues(array[n], values[n]) == 1) {
					Writes.write(array, n, array[n] - 1, delay, true, false);
					sorted = false;
				}
				else if(Reads.compareValues(array[n], values[n]) == -1) {
					Writes.write(array, n, array[n] + 1, delay, true, false);
					sorted = false;
				};
			};
			for(int m = 0; m < n; m++) {
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
    }
}
