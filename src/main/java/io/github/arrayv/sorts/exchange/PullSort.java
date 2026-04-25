package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sortdata.SortMeta;
import io.github.arrayv.sorts.templates.Sort;

/*
 *
  A variation of Push Sort. With gap size starts at 1, compare i and i + gap. If they are in order, gap++. If they aren't, rotate I to I + gap (multiswap) and advance I by 1.
  If no swaps are made in a pass, increase the starting position by 1. If starting position is at the end of the array you're done.
 *
 */
 
 @SortMeta(
	listName = "Pull",
	showcaseName = "Pull Sort",
	runName = "Pull Sort",
	category = "Exchange Sorts",
	slowSort = true,
	unreasonableLimit = 512
)
public final class PullSort extends Sort {
    public PullSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
    };
	private void rotate(int[] array, int left, int right, boolean mark, boolean auxwrite) {
		Writes.multiSwap(array, left, right, 0.002, mark, auxwrite);
	};
    public void runSort(int[] array, int length, int bucketCount) {
		boolean swapped = false;
		int start = 0;
		int gap = 1;
		while(start < length) {
			Highlights.markArray(3, start);
			swapped = false;
			gap = 1;
			for(int i = start; i + gap < length; i++) {
				Highlights.markArray(1, i);
				Highlights.markArray(2, i + gap);
				if(Reads.compareIndices(array, i, i + gap, 0.025, true) == 1) {
					this.rotate(array, i, i + gap, true, false);
					swapped = true;
				}
				else {
					gap++;
					i--;
				};
			};
			if (!swapped) start++;
		};
    };
};
