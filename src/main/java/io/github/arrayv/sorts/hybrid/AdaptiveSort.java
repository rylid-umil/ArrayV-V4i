package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;
import io.github.arrayv.sorts.insert.BinaryInsertionSort;

	//A sort meant to adapt to every situation. Stability and O(1) space complexity not included. WIP
public final class AdaptiveSortNew extends Sort {
	private BinaryInsertionSort binaryInserter;
	private int runCount;
    public AdaptiveSortNew(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Adaptive");
        this.setRunAllSortsName("Adaptive Sort");
        this.setRunSortName("Adaptivesort");
        this.setCategory("Project Adaptive Sort");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }
	//totally didn't steal from AdaptiveGrailSort.java
	public void fixReversedRuns(int[] array, int start, int end) {
        int i = start+1;
		int j = start;

        while(i < end) {
            if(Reads.compareIndices(array, i-1, i++, 1, true) != -1) {
                while(i < end && Reads.compareIndices(array, i-1, i, 1, true) != -1) i++;
                Writes.reversal(array, j, i-1, 1, true, false);
            }
            else while(i < end && Reads.compareIndices(array, i-1, i, 1, true) != 1) i++;
            j = i++;
        }
    }

	//we do a little steali- i mean borrowing
	public void merge(int[] array, int[] tmp, int start, int mid, int end, boolean binary) {
        if(start == mid) return;

        if(end - start < 64 && binary) {
			binaryInserter = new BinaryInsertionSort(this.arrayVisualizer);
            binaryInserter.customBinaryInsert(array, start, end, 0.333);
        }
        else {
            int low = start;
            int high = mid;
		this.runCount = this.runCount - 1;

            for(int nxt = 0; nxt < end - start; nxt++){
                if(low >= mid && high >= end) break;

                Highlights.markArray(1, low);
                Highlights.markArray(2, high);

                if(low < mid && high >= end){
                    Highlights.clearMark(2);
                    Writes.write(tmp, nxt, array[low++], 1, false, true);
                }
                else if(low >= mid && high < end){
                    Highlights.clearMark(1);
                    Writes.write(tmp, nxt, array[high++], 1, false, true);
                }
                else if(Reads.compareValues(array[low], array[high]) <= 0){
                    Writes.write(tmp, nxt, array[low++], 1, false, true);
                }
                else{
                    Writes.write(tmp, nxt, array[high++], 1, false, true);
                };
            };
            Highlights.clearMark(2);

            for(int i = 0; i < end - start; i++){
                Writes.write(array, start + i, tmp[i], 1, true, false);
            };
        };
    };
	//Count sublists.
	public int countSublists(int[] array, int start, int end) {
		int sublists = 1;
		for(int i = start; i < end; i++) {
			if(Reads.compareIndices(array, i, i + 1, 1, true) == 1) {
				sublists++;
			};
		};
		return sublists;
	};
	//Find two consecutive sorted runs to merge. Unlike this sort's previous version, it relies on out of order elements instead of start and end markers from an aux array.
	public int findTwoRunsToMerge(int[] array, int[] tmp, int start, int end) {
		int state = 0;
		int left = start;
		int mid = 0;
		int right = end;
		boolean found = false;
		boolean comp = true;
		for(int i = start; i < end + 1 && !found; i++) {
			Highlights.markArray(1, start);
			Highlights.markArray(2, mid);
			Highlights.markArray(3, end);
			Highlights.markArray(4, i);
			Highlights.markArray(5, i+1);
			Delays.sleep(1);
			if(i >= end || Reads.compareValues(array[i], array[i+1]) == 1) {
				if(state == 0) {
					state = 1;
					mid = i+1;
				}
				else if(state == 1) {
					state = 2;
					found = true;
					right = i;
					this.merge(array, tmp, left, mid, right, false);
					return right + 1;
				};
			};
			if(found) {
				this.merge(array, tmp, left, mid, right, false);
				return right + 1;
			}
		};
		return -1;
	};
	public void mergeRound(int[] array, int[] tmp, int start, int end) {
		for(int i = start; i < end; i++) {
			i = this.findTwoRunsToMerge(array, tmp, i, end);
			i--;
			if (i == -2) return;
		};
	};
	public void buildRuns(int[] array, int start, int end, int runSize) {
		binaryInserter = new BinaryInsertionSort(this.arrayVisualizer);
		int low = 0;
		int high = 0;
		boolean done = false;
		while(!done) {
			high = low + runSize;
			if (high > end-1) {
				high = end;
				done = true;
			}
			binaryInserter.customBinaryInsert(array, low, high, 0.333);
			low = high;
		};
	};
	public void logRC(int rc, int[] tmp){
		int bad = rc;
		while (bad > 0) {
			Writes.write(tmp, bad + 5, bad + 5, 5, true, true);
			bad--;
		};
	};
	public boolean isEven(int n) {
		double div = n / 2d;
		if (div == Math.floor(div)) {
			return true;
		}
		else return false;
	};
    public void runSort(int[] array, int length, int bucketCount) {
		int[] tmp = Writes.createExternalArray(length);
		int end = length - 1;
		this.fixReversedRuns(array, 0, length); //Reverses reversed runs.
		this.runCount = this.countSublists(array, 0, end); // Count sublists
		if (runCount == 1) return; // If there's only one run, there's no need to sort, it's already sorted. TODO: replace with function that stops early if it detects an unsorted element
		if (runCount > length/4) buildRuns(array, 0, end, length/32); //If there are more than N/4 runs, in which random ordered data usually will have, build runs of size N/32.
		this.runCount = this.countSublists(array, 0, end); // Count sublists again.
		if (this.isEven(runCount) != true && runCount != 1) {
			this.findTwoRunsToMerge(array, tmp, 0, end);
		};
		while (runCount > 1) {
			this.mergeRound(array, tmp, 0, end); // Merge
		};
    };
};
