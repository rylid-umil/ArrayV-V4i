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
         Writes.swap(array, i, i+1, this.delay, true, false);
   }
 }
 }
