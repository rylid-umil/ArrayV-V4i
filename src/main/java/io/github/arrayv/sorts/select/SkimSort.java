package io.github.arrayv.sorts.select;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

//FUCK YOU JAVA GO FUCK YOURSELF IM NOT FIXING THIS BROKEN ASS SORT.
 // bubble sort but bad :D. basically an inverse peelsort but uses an auxillary array for skims. totally not because using a variable didnt work for some weason

public final class SkimSort extends Sort {
    public SkimSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Skim");
        this.setRunAllSortsName("Skim Sort");
        this.setRunSortName("Skimsort");
        this.setCategory("Selection Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    @Override
    public void runSort(int[] array, int length, int bucketCount) {
        int min = Reads.analyzeMin(array, length, 0.075, true);
        int max = Reads.analyzeMax(array, length, 0.075, true);
        
        int[] skims = Writes.createExternalArray(length);
        int h = max;
		for(int g = length - (length - max); g > 0; g--) {
			Writes.write(skims, h, g, 1, true, true);
			h--;
		}
	int k = length;
	boolean swapped = false;
	for(int i = length - 1; i > 0; i--) {
	    swapped = false;
            for(int j = 0; j < k; j++) {
                if(Reads.compareValues(array[j], skims[i] - 1) == 1 || Reads.compareValues(array[j], skims[i] - 1) == 0){
			Highlights.markArray(1, j);
	   		Highlights.markArray(2, j + 1);
	   		Highlights.markArray(3, i);
			Highlights.markArray(4, k);
			if(Reads.compareValues(array[j + 1], skims [i] - 1) == 1 || Reads.compareValues(array[j + 1], skims[i] - 1) == 0) {
				k++;
				if(Reads.compareValues(array[j], array[j + 1]) == 1) {
					Writes.swap(array, j, j + 1, 0.075, true, false);
					swapped = true;
				};
				Delays.sleep(0.025);
			}
			else {
				Writes.swap(array, j, j + 1, 0.075, true, false);
				swapped = true;
			}
			Delays.sleep(0.025);
		};
           };
	   if (swapped = true) {
	   	k--;
	   };
           Delays.sleep(0.025);
	}
	Writes.deleteExternalArray(skims);
    }
}
