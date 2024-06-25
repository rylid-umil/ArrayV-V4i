package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/*
 *
Each iteration, does a bubble sort pass. But it's no regular one. The two elements being compared must be at least (d/2) - 1 apart in value, d being the difference between the 
highest and lowest value. If no swaps happen during an iteration, do a reverse bubble sort iteration. If in both iterations no swaps happen, you can stop, the list is sorted.
 *
 */
public final class StubbornSort extends Sort {
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
        double seperator = ((min + max) / 2);
        while (!sorted); {
            sorted = true;
            for(int i = 0; i < length - 1; i++) {
                Highlights.markArray(1, i);
                Highlights.markArray(2, i + 1);
                Reads.dummyCompare();
                Delays.sleep(0.05);
                if (Reads.compareValues(array[i], array[i + 1]) == 1 && (array[i] - array[i + 1]) > (seperator - 1)) {
                    Writes.swap(array, i, i + 1, 0.075, true, false);
                    sorted = false;
                };
            };
            if (sorted) { // just because it didnt swap in the first iteration means it's sorted... the sort is stubborn, remember?
                for(int j = length - 1; j > 1; j--) {
                    Highlights.markArray(1, j);
                    Highlights.markArray(2, j - 1);
                    Delays.sleep(0.025);
                    if (Reads.compareValues(array[j], array[j - 1]) == -1) {
                        Writes.swap(array, j, j - 1, 0.075, true, false);
                        sorted = false;
                    };
                };
            };
        };
    };
};
