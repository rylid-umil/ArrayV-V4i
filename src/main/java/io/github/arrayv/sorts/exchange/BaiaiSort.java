package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/*
 *
 Original idea by Kuvina Saydaki, credit to them.
*
 */

public final class BaiaiSort extends Sort {
    public BaiaiSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Baiai");
        this.setRunAllSortsName("Baiai Sort");
        this.setRunSortName("Baiaisort");
        this.setCategory("Exchange Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    @Override
    public void runSort(int[] array, int length, int bucketCount) {
        int limit = 1;
        boolean sorted = false;
        while(!sorted)
            for(int j = 0; j < limit; j++) {
                if(Reads.compareValues(array[j], array[j + 1]) == 1){
                    Writes.swap(array, j, j + 1, 0.075, true, false);
                    sorted = false;
                }

                Highlights.markArray(1, j);
                Highlights.markArray(2, j + 1);
                Delays.sleep(0.025);
            }
            if(sorted) break;
            if (limit < length - 2) limit++;
        }
}
