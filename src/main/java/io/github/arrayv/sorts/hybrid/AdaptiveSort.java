package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;
import io.github.arrayv.sorts.insert.BinaryInsertionSort;
import io.github.arrayv.sorts.select.MaxHeapSort;

	//A sort meant to adapt to every situation. Stability and O(1) space complexity not included. WIP
public final class AdaptiveSortNew extends Sort {
	private BinaryInsertionSort binaryInserter;
	private MaxHeapSort maxHeapSorter;
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
	public int findAndFixReversedRuns(int[] array, int start, int end) {
        int i = start+1;
		int j = start;
		int reversed = 0;

        while(i < end) {
            if(Reads.compareIndices(array, i-1, i++, 1, true) != -1) {
                while(i < end && Reads.compareIndices(array, i-1, i, 1, true) != -1) i++;
                Writes.reversal(array, j, i-1, 1, true, false);
				reversed++;
            }
            else while(i < end && Reads.compareIndices(array, i-1, i, 1, true) != 1) i++;
            j = i++;
        }
		return reversed;
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
	//totally didnt steal from BubbleMergeSorting.java at Gaming32/ArrayV-Extra-Sorts
	public void bubbleSort(int[] a, int start, int end) {
        for (int i = end - 1; i > start; i--) {
            boolean sorted = true;
            for (int j = start; j < i; j++) {
                if (Reads.compareIndices(a, j, j + 1, 0.5, true) > 0) {
                    Writes.swap(a, j, j + 1, 1.0, true, false);
                    sorted = false;
                }
            }
            if (sorted)
                break;
        }
    }

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
			Highlights.markArray(3, i);
			Highlights.markArray(4, i+1);
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
					this.merge(array, tmp, left, mid, right + 1, false);
					return right + 1;
				};
			};
			if(found) {
				this.merge(array, tmp, left, mid, right + 1, false);
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
		maxHeapSorter = new MaxHeapSort(this.arrayVisualizer);
		while(!done) {
			high = low + runSize;
			if (high > end) {
				high = end + 1;
				done = true;
			}
			maxHeapSorter.customHeapSort(array, low, high, 0.7);
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
	// Is this number halved the same as it's floored halved? (because if its odd, it will have a decimal and floor will not, but if it's even,
	// dividing it by 2 leaves no decimal, so its equal to it's floor.)
	public boolean isEven(int n) {
		double div = n / 2d;
		if (div == Math.floor(div)) {
			return true;
		}
		else return false;
	};
	//totally didnt steal from IterativeCircleSorting.java :)
	public int circleSortRoutine(int[] array, int begin, int end, double sleep) {
        int swapCount = 0;
		int length = (end - begin) + 1;
        for (int gap = length / 2; gap > 0; gap /= 2) {
            for (int start = begin; start + gap < end; start += 2 * gap) {
                int high = start + 2 * gap - 1;
                int low = start;

                while (low < high) {
                    if (high < end && Reads.compareIndices(array, low, high, sleep / 2, true) > 0) {
                        Writes.swap(array, low, high, sleep, true, false);
                        swapCount++;
                    };

                    low++;
                    high--;
                };
            };
        };
        return swapCount;
    };
	// PDQ's insertion with a customizable limit.
    private boolean partialInsertSort(int[] array, int begin, int end, int maxWriteLimit) {
        if (begin == end) return true;

        double sleep = 1/5d;

        int limit = 0;
        for (int cur = begin + 1; cur != end; ++cur) {
            if (limit > maxWriteLimit) return false;

            int sift = cur;
            int siftMinusOne = cur - 1;

            // Compare first so we can avoid 2 moves for an element already positioned correctly.
            if (Reads.compareValues(array[sift], array[siftMinusOne]) < 0) {
                int tmp = array[sift];

                do {
                    Writes.write(array, sift--, array[siftMinusOne], sleep, true, false);
                } while (sift != begin && Reads.compareValues(tmp, array[--siftMinusOne]) < 0);

                Writes.write(array, sift, tmp, sleep, true, false);
                limit += cur - sift;
            };
        };
        return true;
    };
	//Actions to take when there are more than n/2 sublists. Do an iterative circle pass and count the swaps. If more than NlogN swaps,
	//do an "optimistic insertion sort" and stopping when it reaches n/3 swaps. If it never does, you're done.
	public boolean tooManySublists(int[] array, int start, int end) {
		int swaps = this.circleSortRoutine(array, start, end, 0.3);
		int length = end - start;
		if(swaps > MathExtra.nlogn(length)) {
			int maxLimit = (int) Math.floor(length / 3);
			boolean good = this.partialInsertSort(array, start, end, maxLimit);
			return good;
		};
		return false;
	};
	public boolean isSectionSorted(int[] array, int start, int end) {
		for(int i = start; i < end; i++) {
			if(Reads.compareIndices(array, i, i+1, 0.5, true) == 1){
				return false;
			};
		};
		return true;
	};
    public void runSort(int[] array, int length, int bucketCount) {
		int[] tmp = Writes.createExternalArray(length);
		int end = length - 1;
		int reversed = this.findAndFixReversedRuns(array, 0, length); //Reverses reversed runs.
		//If you reversed once, the array was probably fully reversed and it's probably now sorted, but just in case check.
		if (reversed == 1) {
			if(this.isSectionSorted(array, 0, end)) return;
		};
		this.runCount = this.countSublists(array, 0, end); // Count sublists
		if (runCount == 1) return;		// If there's only one run, there's no need to sort, it's already sorted. TODO: replace with function that stops early if it detects an unsorted element
		if (runCount > length / 1.7) {
			if (this.tooManySublists(array, 0, end)) return;
		};
		int potentialRunSize = (int) Math.min(Math.round(length/32), 64);
		if (runCount > length/4) buildRuns(array, 0, end, potentialRunSize); //If there are more than N/4 runs, in which random ordered data usually will have, build runs of size N/32, max size 64.
		this.runCount = this.countSublists(array, 0, end); // Count sublists again.
		while (runCount > 1) {
			this.mergeRound(array, tmp, 0, end); // Merge
		};
		Highlights.clearAllMarks();
    };
};
