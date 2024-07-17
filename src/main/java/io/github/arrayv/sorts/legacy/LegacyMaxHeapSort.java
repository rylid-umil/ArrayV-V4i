package io.github.arrayv.sorts.legacy;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/*
 *
Copyright (c) rosettacode.org.
Permission is granted to copy, distribute and/or modify this document
under the terms of the GNU Free Documentation License, Version 1.2
or any later version published by the Free Software Foundation;
with no Invariant Sections, no Front-Cover Texts, and no Back-Cover
Texts.  A copy of the license is included in the section entitled "GNU
Free Documentation License".

Max heap but modify to match highlights shown in Worthy's videos (mainly 16 sorts - color circle)
 *
 */

public final class LegacyMaxHeapSort extends Sort {
    public LegacyMaxHeapSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Legacy Max Heap");
        this.setRunAllSortsName("Max Heap Sort");
        this.setRunSortName("Legacy Heap Sort");
        this.setCategory("Legacy");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }
	public void siftDown(int[] array, int root, int dist, int start, double sleep, boolean isMax) {
        int compareVal = 0;

        if(isMax) compareVal = -1;
        else compareVal = 1;

        while (root <= dist / 2) {
            int leaf = 2 * root;
            if (leaf < dist && Reads.compareValues(array[start + leaf - 1], array[start + leaf]) == compareVal) {
                leaf++;
            }
            Highlights.markArray(1, start + root - 1);
            Highlights.markArray(2, start + leaf - 1);
            Delays.sleep(sleep);
            if (Reads.compareValues(array[start + root - 1], array[start + leaf - 1]) == compareVal) {
                Writes.swap(array, start + root - 1, start + leaf - 1, 0, true, false);
                root = leaf;
            }
            else break;
        }
    }

    public void heapify(int[] arr, int low, int high, double sleep, boolean isMax) {
        int length = high - low;
        for (int i = length / 2; i >= 1; i--) {
            siftDown(arr, i, length, low, sleep, isMax);
        }
    }

    // This version of heap sort works for max and min variants, alongside sorting
    // partial ranges of an array.
    public void heapSort(int[] arr, int start, int length, double sleep, boolean isMax) {
        heapify(arr, start, length, sleep, isMax);

        for (int i = length - start; i > 1; i--) {
			Highlights.markArray(3, i);
            Writes.swap(arr, start, start + i - 1, sleep, true, false);
            siftDown(arr, 1, i - 1, start, sleep, isMax);
        }

        if(!isMax) {
            Writes.reversal(arr, start, start + length - 1, 1, true, false);
        }
    }

    public void makeHeap(int[] array, int start, int length, double sleep) {
        this.heapify(array, start, length, sleep, true);
    }

    public void customHeapSort(int[] array, int start, int length, double sleep) {
        this.heapSort(array, start, length, sleep, true);
    }

    @Override
    public void runSort(int[] array, int length, int bucketCount) {
        this.heapSort(array, 0, length, 1, true);
    }
}
