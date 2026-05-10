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
        int limit = 0;
        boolean flip = false,
                sorted = false,
                alt = false;
        do {
            sorted = true;
            for(int j = alt ? 1 : 0; j <= limit; j += 2) {
                if(Reads.compareValues(array[j], array[j + 1]) == 1){
                    Writes.swap(array, j, j + 1, 0.075, true, false);
                    sorted = false;
                }
                Highlights.markArray(1, j);
                Highlights.markArray(2, j + 1);
                Delays.sleep(0.025);
            }
            if(limit < length - 2 && !flip) sorted = false;
            if (limit == length - 2 && !flip) flip = true;
            if(!flip) {
                limit++;
            } else if (limit > 0) {
                limit--;
            }
            alt = !alt;
        } while(!sorted);
    }
}
