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
	listName = "Flex (Iterative)",
	showcaseName = "Iterative Flex Sort",
	runName = "Iterative Flex Sort",
	category = "Exchange Sorts",
    question = "Enter shrink factor (input/100):",
    defaultAnswer = 120
)
public final class FlexSortIterative extends Sort {
    public FlexSortIterative(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
    }

    public void runSort(int[] array, int length, int bucketCount) {
		boolean sorted = false;
		double shrink = bucketCount / 100d;
		int circleSize = length/2;
		int end = length;
		boolean swapped = false;
		boolean squared = false;
		int biggestSwappedGapSize = 2; //the largest gap size the sort detects a swap at
		while((circleSize > 1) || !sorted) {
			sorted = true;
			Highlights.clearMark(2);
			if (circleSize > 0) {
				circleSize = (int) (circleSize / shrink);
			};
			if (circleSize > 0) {
				for (int start = 0; start + circleSize < end; start += 2 * circleSize) {
					int high = start + 2 * circleSize - 1;
					int low = start;
					while (low < high) {
						if (high < end && Reads.compareIndices(array, low, high, 0.25 / 2, true) > 0) {
							Writes.swap(array, low, high, 0.75, true, false);
							if (!swapped) biggestSwappedGapSize = circleSize;
							sorted = false;
							swapped = true;
						};
						low++;
						high--;
					};
				};
			} else {
				if (!squared) {
					circleSize = biggestSwappedGapSize;
				} else {
					circleSize = 1;
					for(; circleSize < biggestSwappedGapSize; circleSize*=2);
				};
				if (!swapped) {
					shrink = 2;
					circleSize = 1;
					biggestSwappedGapSize = length;
					for(; circleSize < biggestSwappedGapSize; circleSize*=2);
					squared = true;
				};
			};
			swapped = false;
		};
		
    }
}
