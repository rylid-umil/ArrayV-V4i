package io.github.arrayv.sorts.magic;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

public final class DrillStraighteningSort extends Sort {
    public DrillStraighteningSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Drill Straightening");
        this.setRunAllSortsName("Drill Straightening Sort");
        this.setRunSortName("Drill Straighteningsort");
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
		for(int k = 0; k < length; k++) {
			sorted = false;
			while(!sorted) {
				sorted = true;
				Highlights.markArray(2, k);
				if(Reads.compareValues(array[k], values[k]) == 1) {
					Writes.write(array, k, array[k] - 1, delay, true, false);
					sorted = false;
				} 
				else if(Reads.compareValues(array[k], values[k]) == -1) {
					Writes.write(array, k, array[k] + 1, delay, true, false);
					sorted = false;
				};
			}
		};
    }
}
