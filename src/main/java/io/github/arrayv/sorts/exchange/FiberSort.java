package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sortdata.SortMeta;
import io.github.arrayv.sorts.templates.Sort;

/*
 *
 gapped iterative circle sort
 *
 */
 
 @SortMeta(
	listName = "Fiber",
	showcaseName = "Fiber Sort",
	runName = "Fibersort",
	category = "Exchange Sorts",
    question = "Enter shrink factor (input/100):",
    defaultAnswer = 140
)
public final class FiberSort extends Sort {
    public FiberSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
    }

    public void runSort(int[] array, int length, int bucketCount) {
		boolean sorted = false;
		double shrink = bucketCount / 100d;
		int circleSize = length;
		int end = length;
		boolean swapped = false;
		boolean squared = false;
		int maxDepth = 1;
		int runC = 0;
		while((circleSize > 1) || !sorted) {
			sorted = true;
			Highlights.clearMark(2);
			if (circleSize > 0) {
				circleSize = (int) (circleSize / shrink);
			};
			if (circleSize > 0 && runC <= maxDepth) {
				runC++;
				for (int start = 0; start + circleSize < end; start += 2 * circleSize) {
					int high = start + 2 * circleSize - 1;
					int low = start;
					while (low < high) {
						if (high < end && Reads.compareIndices(array, low, high, 0.5 / 2, true) > 0) {
							Writes.swap(array, low, high, 0.5, true, false);
							sorted = false;
							swapped = true;
						};
						low++;
						high--;
					};
				};
			} else {
				if (runC > maxDepth) {
					runC = 0;
					maxDepth++;
					circleSize = length/2;
				};
				if (!squared) {
					circleSize = length/2;
				} else {
					circleSize = 1;
					for(; circleSize < length/2; circleSize*=2);
				};
				if (!swapped) {
					shrink = 2;
					circleSize = 1;
					for(; circleSize < length/2; circleSize*=2);
					squared = true;
				};
				swapped = false;
			};
		};
		
    }
}
