package io.github.arrayv.sorts.bogo;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.BogoSorting;

/*

 SORT IDEA BY RYLID
 CODED FOR ARRAYV BY RYLID
 --------------------
 -    SORTS PLUS    -
 --------------------

Sorts Plus July 2024

If you take the "Bo" in bogo sort as shuffle and the "go" in bogo sort as the check if sorted, Bobo sort shuffles the array, never checking if it is sorted.

Basically a whilebogo sort

*/
public final class BoboSort extends BogoSorting {
    public BoboSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Bobo");
        this.setRunAllSortsName("Bobo Sort");
        this.setRunSortName("Bobosort");
        this.setCategory("Joke Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(true);
        this.setUnreasonableLimit(1);
        this.setBogoSort(true);
    }

    @Override
    public void runSort(int[] array, int length, int bucketCount) {
        while(true)
            this.bogoSwap(array, 0, length, false);
    }
}
