package io.github.arrayv.sorts.misc;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.BogoSorting;

//literally sets random integers from 0 to max. if its sorted, good. Probably will
//NEVER result in the same data distribution as the previous.

public final class Jfknds extends BogoSorting {
     public JfkndsSort(ArrayVisualizer arrayVisualizer) {
         super(arrayVisualizer);
         
         this.setSortListName("Jfknds");
         this.setRunAllSortsName("Jfknds Sort");
         this.setRunSortName("Jfkndssort");
         this.setCategory("Miscellaneous Sorts");
         this.setComparisonBased(true);
         this.setBucketSort(false);
         this.setRadixSort(false);
         this.setUnreasonablySlow(true);
         this.setUnreasonableLimit(13);
         this.setBogoSort(true);
  } 
  
  @Override
  public void runSort(int[] array, int length, int bucketCount) {
    int min = Reads.analyzeMin(array, length, this.delay, true);
    int max = Reads.analyzeMax(array, length, this.delay, true);
    while (!this.isArraySorted(array, length)) {
      for (int i = 0; i < length; i++) {
        Writes.write(array, i, BogoSorting.ranInt(min, max), this.delay, true, false);
        Highlights.markArray(1, i);
      };
    };
  };
};
