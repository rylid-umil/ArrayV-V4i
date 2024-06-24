package io.github.arrayv.sorts.theory;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.insert.BinaryInsertionSort;
import io.github.arrayv.sorts.templates.Sort;

/*
 *  This algorithm is a quick way to sort an array with the following restrictions: you can't interact with the second
 *  half of the array, except read and compare elements with them, except for the last element, which you can shift any
 *  item from the first half to. Example for the shift is [1 2 3 4] shift the 1 to the where the 4 is like radix lsd [2 3 4 1].
 *  
 *  It can NOT shift from the last to the first, just the first to the last.
 */
public final class HalfSorting extends Sort {
    public HalfSorting(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Half");
        this.setRunAllSortsName("Half-access Sorting");
        this.setRunSortName("Halfsorting");
        this.setCategory("Misc Sorting/Restricted Access");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    @Override
    public void runSort(int[] array, int length, int bucketCount) {
      int highest = 0;
      int l = 0;
	  int hp = 0;
	  double half = ((Math.ceil(length / 2)) - 1);
      int[] excluded = Writes.createExternalArray(length);
	  for (int v = 0; v < length; v++) {
		  Writes.write(excluded, v, -1, 0.1, true, true);
	  };
      for (int p = 0; p < half + 1; p++) {
        highest = 0;
		hp = 0;
		boolean good = false;
		  for (int hh = highest; hh > -1; hh++) {
			Reads.dummyCompare();
			Delays.sleep(0.01);
		    if (excluded[hh] == 0) {
			highest = highest + 1;
			good = false;
			}
			else {
			good = true;
			};
			if (good == true || hh >= (length - 1)) break;
			Delays.sleep(0.01);
		  };
		  for (int q = 0; q < length; q++) {
			Reads.dummyCompare();
			if (excluded[q] != 0) {
			  if(Reads.compareValues(array[highest], array[q]) == -1) {
				highest = q;
				Delays.sleep(0.001);
			  };
			};
			hp = highest;
          Highlights.markArray(1, highest);
          Highlights.markArray(2, q);
          Delays.sleep(0.001);
		  };
        l = 0;
		int kp = 0;
		boolean swapped = false;
		if (hp > half) {
          for (int k = hp; k > half; k = k) {
		    Highlights.clearMark(2);
            Highlights.markArray(1, k);
		    Delays.sleep(0.025);
            if (excluded[l] == -1) {
			  swapped = true;
              for (int ss = l; ss < length - 1, ss++){
				Writes.swap(array, ss, ss + 1, 0.001, true, false);
				Writes.swap(excluded, ss, ss + 1, 0.001, true, true);
				Highlights.markArray(1, ss)
				Highlights.markArray(2, ss + 1)
			  }
			  k--;
			  kp = k;
		    }
            else {
              l++;
			  Highlights.markArray(1, l);
            };
          };
		  if (swapped == false) {
		  kp = hp;
		  };
		}
		else {
		  kp = hp;
		};
        Writes.write(excluded, kp, 0, 0.075, true, true);
      }
      Writes.deleteExternalArray(excluded);
      int z = (int) Math.ceil(length / 2);
	  BinaryInsertionSort binaryInserter = new BinaryInsertionSort(this.arrayVisualizer);
      binaryInserter.customBinaryInsert(array, 0, z, 0.1);
      for (int r = 0; r < (half + 1); r++) {
        for (int ss = 0; ss < length - 1, ss++){
		  Writes.swap(array, ss, ss + 1, 0.001, false, false);
		  Highlights.markArray(1, ss)
		  Highlights.markArray(2, ss + 1)
		}
      };
      binaryInserter.customBinaryInsert(array, 0, z, 0.1);
    }
}
