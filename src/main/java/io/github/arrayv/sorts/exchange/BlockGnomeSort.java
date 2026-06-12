package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

public final class BlockGnomeSort extends Sort {

    public BlockGnomeSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Block Gnome");
        this.setRunAllSortsName("Block Gnome Sort");
        this.setRunSortName("Block Gnomesort");
        this.setCategory("Exchange Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    private int compareBlocks(int[] array, int left, int right, int gap, double sleep) {
        if(gap<=1) return Reads.compareValues(array[left], array[right]);
        double a = 0,
            b = 0;
        for(int j = left; j < right; j++) {
            Highlights.markArray(1, j);
            Highlights.markArray(2, j+gap);
            a += array[j];
            b += array[j+gap];
            Delays.sleep(sleep);
        }
        if(a>b) return 1;
        if(b>a) return -1;
        return 0;
    }
    private void swapBlocks(int[] array, int left, int right, int gap, double sleep) {
        if(gap<=1) {
            Writes.swap(array, left, right, sleep, true, false);
            return;
        }
        for(int j = left; j < right; j++) {
            Writes.swap(array, j, j+gap, sleep, true, false);
        }
    }
    @Override
    public void runSort(int[] array, int length, int bucketCount) {
        int gap = length;
        double sleep = 1;
        while(gap>1) {
            gap /= 2;
            sleep /= 1.46;
            for(int i = gap; i < length; i += gap) {
                int pos = i;
                while(pos > 0 && compareBlocks(array, pos - gap, pos, gap, sleep) == 1) {
                    swapBlocks(array, pos - gap, pos, gap, sleep);
                    pos -= gap;
                }
            }
        }
    }
}
