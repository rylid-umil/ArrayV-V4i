package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sortdata.SortMeta;
import io.github.arrayv.sorts.templates.Sort;

/*
 *
A modified comb sort with decreasing gap mid-run.
 *
 */
 
 @SortMeta(
    name = "Shrivel2",
    category = "Exchange Sorts",
    question = "Enter shrink factor (input/100):",
    defaultAnswer = 150
)
public final class ShrivelSort extends Sort {
    public ShrivelSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
    }

    public void runSort(int[] array, int length, int bucketCount) {
		boolean sorted = false;
		int gap = length;
		int curGap = gap;
		double shrink = bucketCount / 100d;
		while((gap > 1) || !sorted) {
			Highlights.clearMark(2);
			if (gap > 1) {
				gap = (int) (gap / shrink);
			};
			curGap = gap;
			sorted = true;
			for(int i = 0; (i + curGap) < length; ++i) {
				if(Reads.compareIndices(array, i, i + curGap, 0.025, true) == 1) {
					Writes.swap(array, i, i + curGap, 0.075, true, false);
					sorted = false;
				}
				else {
					curGap--;
					if (curGap == 0) {
						i -= gap;
						curGap = gap + 1;
						if (i < 0) {
							curGap += i;
							i = 0;
						};
					};
				};
			}
		};
    }
}
