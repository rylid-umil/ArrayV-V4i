package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

public final class ConvergenceSort extends Sort {
    public ConvergenceSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Convergence");
        this.setRunAllSortsName("Convergence Sort");
        this.setRunSortName("Convergence Sort");
        this.setCategory("Impractical Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(true);
        this.setUnreasonableLimit(10);
        this.setBogoSort(false);
    };
	public boolean compswap(int[] array, int left, int right, double delay, boolean mark, boolean auxwrite) {
		boolean result = (Reads.compareIndices(array, left, right, delay, mark) == 1);
		if (result == true) {
			Writes.swap(array, left, right, delay*3, mark, auxwrite);
		};
		return result;
		
	};
	public void conv(int[] array, int s, int e, boolean auxwrite) {
		if (s == e) return;
		conv(array, s, e-1, auxwrite);
		Writes.swap(array, s, e, 0.00003, true, auxwrite);
		if (e-s<2) return;
		boolean sorted = false, c = false;
		while (!sorted) {
			sorted = true;
			for(int i = s; i < e; i++) {
				if (Reads.compareIndices(array, i, i+1, 0.00001, true) == 1) {
					sorted = false;
					break;
				};
			};
			if (!sorted) {
				conv(array, s, e-1, auxwrite);
				c = false;
				for(int j = s; j < e; j++) {
					if(compswap(array, j, e, 0.00001, true, auxwrite)) {
						c = true;
						break;
					};
				};
				if (c = false) break;
			};
		};
	};
    public void runSort(int[] array, int length, int bucketCount) {
		conv(array, 0, length-1, false);
	};
};
