 package io.github.arrayv.sorts.distribute;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.BogoSorting;

//bubble bogo sort but stupider, picks a random item from 0 to n-1 and swaps it with the subsequent item. Unconditionally. Checks if array = sorted. If no, do it again.
 
 public final class BosoSort extends BogoSorting {
   public BosoSort(ArrayVisualizer arrayVisualizer) {
     super(arrayVisualizer);
     this.setSortListName("Boso");
     this.setRunAllSortsName("Boso Sort (Stupid Bubble Bogo Sort)");
     this.setRunSortName("Boso Sort");
     this.setCategory("Bogo Sorts");
     this.setComparisonBased(true);
     this.setBucketSort(false);
     this.setRadixSort(false);
     this.setUnreasonablySlow(true);
     this.setUnreasonableLimit(32);
     this.setBogoSort(true);
   }
   public void runSort(int[] array, int length, int bucketCount) {
     while (!this.isArraySorted(array, length)) {
       int i = BogoSorting.randInt(0, length-1);
	   int highlight = BogoSorting.randInt(0, length-1);
       Writes.swap(array, i, i+1, this.delay, false, false);
	   Highlights.markArray(1, highlight);
	   Highlights.markArray(2, highlight + 1);
		 
   }
 }
 }
