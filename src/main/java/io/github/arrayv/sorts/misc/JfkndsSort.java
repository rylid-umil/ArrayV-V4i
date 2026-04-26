package io.github.arrayv.sorts.distribute;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.BogoSorting;

//literally sets random integers from 0 to max. if its sorted, good. Probably will
//NEVER result in the same data distribution as the previous.
// Note 2: changed to only be sorted if the array = the sorted array (array figured out via counting sort)

public final class JfkndsSort extends BogoSorting {
     public JfkndsSort(ArrayVisualizer arrayVisualizer) {
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
  } 
  
  @Override
  public boolean sameArray(int[] array, int[] auxArray) {
       for (int i = 0; i < array.length; i++) {
            Highlights.markArray(1, i);
            Delays.sleep(this.delay);
            if (Reads.compareValues(array[i], auxArray[i]) != 0) {
                 return false;
            }
       }
       return true;
  }
  public void runSort(int[] array, int length, int bucketCount) {
  int max = Reads.analyzeMax(array, length, 0, false);

   int[] output = Writes.copyOfArray(array, length);
   int[] counts = Writes.createExternalArray(max + 1);

   for (int i = 0; i < length; i++) {
       Writes.write(counts, array[i], counts[array[i]] + 1, 1, false, true);
       Highlights.markArray(1, i);
   }

   for (int i = 1; i < counts.length; i++) {
       Writes.write(counts, i, counts[i] + counts[i - 1], 1, true, true);
   }

   for (int i = length - 1; i >= 0; i--) {
       output[counts[array[i]] - 1] = array[i];
       counts[array[i]]--;
   }
   Writes.deleteExternalArray(counts);
    int min = Reads.analyzeMin(array, length, 0.05, true);
    while (!this.sameArray(array, output)) {
      for (int i = 0; i < length; i++) {
        Writes.write(array, i, BogoSorting.randInt(min, max), this.delay, true, false);
        Highlights.markArray(1, i);
      };
    };
       Writes.deleteExternalArray(output);
  };
};
