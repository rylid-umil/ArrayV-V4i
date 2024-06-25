package io.github.arrayv.sorts.select;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/*
 *
  bad sort but even worse
 *
 */
public final class RodSort extends Sort {
    public RodSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Rod");
        this.setRunAllSortsName("Rod Sort");
        this.setRunSortName("Rodsort");
        this.setCategory("Impractical Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(true);
        this.setUnreasonableLimit(1024);
        this.setBogoSort(true);
    };
    
    public void runSort(int[] array, int length, int bucketCount) {
		int h = 0; //highest element's index
		int preserve; //preserve, i am going insane
        for(int i = 0; i < length - 1; i++) {
			h = i;
			preserve = 0;
            for(int j = i + 1; j < length; j++) {
                Highlights.markArray(1, j);
                Highlights.markArray(2, h);
				Delays.sleep(0.05);
				Reads.dummyCompare();
                if(array[h] > array[j]) {
                    h = j;
					j = i + 1;
                    Delays.sleep(0.05);
                };
				
            };
			Writes.swap(array, i, h, 0.05, true, false);
        };
    };
};
