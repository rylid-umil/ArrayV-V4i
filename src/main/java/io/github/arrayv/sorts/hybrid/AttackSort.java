package io.github.arrayv.sorts.hybrid;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;
import io.github.arrayv.sorts.insert.InsertionSort;

public final class AttackSort extends Sort {
	private InsertionSort insertionSorter;
    public AttackSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Attack");
        this.setRunAllSortsName("Attack Sort");
        this.setRunSortName("Attack Sort");
        this.setCategory("Hybrid Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    };
	public void attack(int[] array, int start, int end) {
		insertionSorter = new InsertionSort(this.arrayVisualizer);
		if (end - start < 16) {
			if (end - start > 1) insertionSorter.customInsertSort(array, start, end + 1, 1, false);
			return;
		};
		int endp = end + 1;
		int subLength = start + Math.min(end - start + 1, Math.min(8, Math.max(end - start / 32, 2)));
		insertionSorter.customInsertSort(array, start, subLength + 1, 1, false);
		int a = 0,
			b = 0;
		for (int i = subLength; i >= start; i--) {
			a = subLength + 1;
			Highlights.markArray(3, i);
			for (b = a; b < end + 1; b++) {
				Highlights.markArray(1, a);
				Highlights.markArray(2, b);
				Delays.sleep(0.167);
				if (Reads.compareValues(array[i], array[b]) == 1) {
					Writes.swap(array, a, b, 0.33, true, false);
					a++;
				};
			}
			subLength--;
			end = a - 1;
			Highlights.clearAllMarks();
			Writes.swap(array, i, a - 1, 1, true, false);
			attack(array, a - 1, b);
		}
		attack(array, start, a - 2);
    };
    public void runSort(int[] array, int length, int bucketCount) {
		attack(array, 0, length - 1);
    };
};
