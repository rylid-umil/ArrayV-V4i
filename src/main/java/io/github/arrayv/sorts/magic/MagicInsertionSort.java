package io.github.arrayv.sorts.magic;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;
import io.github.arrayv.sorts.insert.InsertionSort;

/**
 *
A variant of Straightening sort. hard to explain but kinda slow
 *
 */
public final class MagicInsertionSort extends Sort {
	private InsertionSort inserter;
    public MagicInsertionSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Magic Insertion");
        this.setRunAllSortsName("Magic Insertion Sort");
        this.setRunSortName("Magic Insertionsort");
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
		int[] values = Writes.copyOfArray(array, length);
		double delay = Math.max(1d / length, 0.00005);
		Highlights.clearAllMarks();
		boolean sorted = false;
		inserter = new InsertionSort(this.arrayVisualizer);
		for(int k = 0; k < length; k++) {
			Highlights.markArray(4, k);
			sorted = false;
			inserter.customInsertSort(values, 0, k + 1, 0.01, true); 
			while(!sorted) {
				sorted = true;
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
				}
			};
		};
    }
}
