package io.github.arrayv.sorts.hybrid;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sortdata.SortMeta;
import io.github.arrayv.sorts.templates.Sort;
import io.github.arrayv.sorts.insert.InsertionSort;

/*
 *
 Comb sort but after each run does a iterative circles one layer of depth decreasing each pass. only does them if the gap size is greater than 1.
 *
 */
 
 @SortMeta(
    name = "Flex Comb",
    category = "Hybrid Sorts",
    question = "Enter shrink factor (input/100):",
    defaultAnswer = 140
)
public final class FlexCombSort extends Sort {
	private int minCircleSize;
	private InsertionSort insertSorter;
    public FlexCombSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
    }

    public void runSort(int[] array, int length, int bucketCount) {
		insertSorter = new InsertionSort(this.arrayVisualizer);
		boolean sorted = false;
		int gap = length;
		double shrink = bucketCount / 100d;
		int circleSize = 1;
		this.minCircleSize = 2;
		for(; circleSize < length; circleSize*=2);
		int end = circleSize;
		while((gap > 1) || !sorted) {
			Highlights.clearMark(2);
			if (gap > 1) {
				gap = (int) (gap / shrink);
			};
			if(gap <= Math.min(8, length * 0.03125)) {
                    gap = 0;

                    insertSorter.customInsertSort(array, 0, length, 0.5, false);
                    break;
            };
			sorted = true;
			for(int i = 0; (i + gap) < length; ++i) {
				if(Reads.compareIndices(array, i, i + gap, 0.25, true) == 1) {
					Writes.swap(array, i, i + gap, 0.75, true, false);
					sorted = false;
				};
			};
			if (gap > 1 && circleSize > (this.minCircleSize - 1)) {
				for (int start = 0; start + circleSize < end; start += 2 * circleSize) {
					int high = start + 2 * circleSize - 1;
					int low = start;
					while (low < high) {
						if (high < end && Reads.compareIndices(array, low, high, 0.25 / 2, true) > 0) {
							Writes.swap(array, low, high, 0.75, true, false);
						};
						low++;
						high--;
					};
				};
				circleSize = (int) Math.round(gap / 6);
				if (circleSize < 1) circleSize = 1;
			};
		};
		
    }
}
