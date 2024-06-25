package io.github.arrayv.sorts.distribute;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.BogoSorting;

//literally sets random integers from 0 to max. if its sorted, good. Probably will
//NEVER result in the same data distribution as the previous.

public final class JfkndsSort extends BogoSorting {
  public BosoSort(ArrayVisualizer arrayvisualizer) {
    super(arrayVisualizer);
    this.setSortListName("Jfknds");
    this.setRunAllSortsName("Jfknds Sort");
    this.setRunSortName("Jfkndssort");
    this.setCategory("Impractical Sorts");
    this.setComparisonBased(true);
    this.setBucketSort(false);
    this.setRadixSort(false);
    this.setUnreasonablySlow(true);
    this.setUnreasonableLimit(13);
    this.setBogoSort(true);
  };
  public void runSort(int[] array, int length, int bucketCount) {
    int min = Reads.analyzeMin(array, length, 0.05, true);
    int max = Reads.analyzeMax(array, length, 0.05, true);
    while (!this.isArraySorted(array, length)) {
      for (i = 0; i < length; i++) {
        Writes.write(array, i, BogoSorting.randomInt(min, max), this.delay, true, false);
        Highlights.markArray(1, i);
      };
    };
  };
};
