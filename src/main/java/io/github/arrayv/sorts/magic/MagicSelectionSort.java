package io.github.arrayv.sorts.magic;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/**
 *
selection sort but *✨MAGICAL✨* ‼️🦅🦅😰🍳💥🍳🧪🧪🧪🧪
 *
 */
public final class MagicSelectionSort extends Sort {
    public MagicSelectionSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Magic Selection");
        this.setRunAllSortsName("Magic Selection Sort");
        this.setRunSortName("Magic Selection Sort");
        this.setCategory("Magic Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    };

    public void runSort(int[] array, int length, int bucketCount) {
		int[] values = Writes.copyOfArray(array, length);
		double delay = Math.max(1d / length, 0.001);
		for(int i = 0; i < length; i++) {
			int lowest = i;
			Highlights.markArray(4, i);
			for(int j = i; j < length; j++) {
				Highlights.markArray(3, j);
				Delays.sleep(0.01);
				if (Reads.compareValues(array[j], array[lowest]) == -1) {
					lowest = j;
					Delays.sleep(0.01);
				};
				Highlights.markArray(2, lowest);
			};
			Writes.multiSwap(values, lowest, i, 0.02, true, true);
			boolean done = false;
			Highlights.clearAllMarks();
			while(!done) {
				done = true;
				Highlights.markArray(3, i);
				if (i > 0) Highlights.markArray(4, i-1);
				for(int k = i; k < length; k++) {
					Highlights.markArray(2, k);
					if(Reads.compareValues(array[k], values[k]) == 1) {
						Writes.write(array, k, array[k] - 1, delay, true, false);
						done = false;
					}
					else if(Reads.compareValues(array[k], values[k]) == -1) {
						Writes.write(array, k, array[k] + 1, delay, true, false);
						done = false;
					};
				};
			};
		};
    };
};
