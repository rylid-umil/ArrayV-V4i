package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.BogoSorting;

/*
 *
Each iteration, does a bubble sort pass. But it's no regular one. The two elements being compared must be at least (d/2) - 1 apart in value, d being the difference between the 
highest and lowest value. If no swaps happen during an iteration, compare-and-swap 2 random elements n/2 times.
 *
 */
public final class StubbornSort extends BogoSorting {
    public StubbornSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Stubborn");
        this.setRunAllSortsName("Stubborn Sort");
        this.setRunSortName("Stubbornsort");
        this.setCategory("Impractical Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(true);
        this.setUnreasonableLimit(256);
        this.setBogoSort(false); // "erm akshully"
    };

    public void runSort(int[] array, int length, int bucketCount) {
        boolean sorted = false;
        int min = Reads.analyzeMin(array, length, 0.025, true);
        int max = Reads.analyzeMax(array, length, 0.025, true);
        double seperator = Math.ceil((min + max) / 2);
		int nd2 = (int) Math.ceil(length / 2);
		int lft = 0;
		int rgt = 0;
		int temp = 0;
        while (!sorted) {
            sorted = true;
            for(int k = 0; k < length - 1; k++) {
                Highlights.markArray(1, k);
                Highlights.markArray(2, k + 1);
                Reads.dummyCompare();
                Delays.sleep(0.005);
                if (Reads.compareValues(array[k], array[k + 1]) == 1 && (array[k] - array[k + 1]) > (seperator - 1)) {
                    Writes.swap(array, k, k + 1, 0.075, true, false);
                    sorted = false;
                };
            };
            if (sorted) { // just because it didnt swap in the first iteration means it's sorted... the sort is stubborn, remember?
                for(int j = 0; j < (nd2 + 1); j++) {
					
					lft = BogoSorting.randInt(0, length); // just becuase it isn't
					rgt = BogoSorting.randInt(0, length); // deterministic doesn't mean it's a bogo sort. if you go by that logic, deterministic bogo sort is not a bogo sort.
					if (lft > rgt) {
						temp = rgt;
						rgt = lft;
						lft = temp;
					}
					Delays.sleep(0.001);
					if (Reads.compareValues(array[lft], array[rgt]) == 1) {
						Writes.swap(array, lft, rgt, 0.2, true, false);
						sorted = false;
					};
                };
            };
			if (sorted) {
				for(int l = 0; l < length - 1; l++) {
					Highlights.markArray(1, l);
					Highlights.markArray(2, l + 1);
					Delays.sleep(0.005);
					if (Reads.compareValues(array[l], array[l + 1]) == 1){
						sorted = false;
						break;
					};
				};
			};
        };
    };
};
