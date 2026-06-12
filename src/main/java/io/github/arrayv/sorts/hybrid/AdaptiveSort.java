package io.github.arrayv.sorts.adaptive;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;
import io.github.arrayv.sorts.templates.TimSorting;
import io.github.arrayv.sorts.insert.BinaryInsertionSort;

public final class AdaptiveSort extends Sort {
    private BinaryInsertionSort binaryInserter;
    private TimSorting timSortInstance;
    private int runCount;
    public AdaptiveSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Adaptive");
        this.setRunAllSortsName("Adaptive Sort");
        this.setRunSortName("Adaptivesort");
        this.setCategory("Hybrid Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    public int findAndFixReversedRuns(int[] array, int start, int end) {
        int i = start+1;
		int j = start;
		int reversed = 0;

        while(i < end) {
            if(Reads.compareIndices(array, i-1, i++, 1, true) != -1) {
                while(i < end && Reads.compareIndices(array, i-1, i, 1, true) != -1) i++;
				if(i-1 - j > 1) {
					Writes.reversal(array, j, i-1, 1, true, false);
					reversed++;
				}
            }
            else while(i < end && Reads.compareIndices(array, i-1, i, 1, true) != 1) i++;
            j = i++;
        }
		return reversed;
    }

    public void multiSwap(int[] array, int a, int b, int len) {
        for(int i = 0; i < len; i++)
            Writes.swap(array, a+i, b+i, 1, true, false);
    }

	public void insertTo(int[] array, int a, int b) {
        Highlights.clearMark(2);
        int temp = array[a];
        while(a > b) Writes.write(array, a, array[(a--)-1], 0.5, true, false);
        Writes.write(array, b, temp, 0.5, true, false);
    }

    public void insertToBW(int[] array, int a, int b) {
        Highlights.clearMark(2);
        int temp = array[a];
        while(a < b) Writes.write(array, a, array[(a++)+1], 0.5, true, false);
        Writes.write(array, a, temp, 0.5, true, false);
    }

    public void rotate(int[] array, int a, int m, int b) {
        int l = m-a, r = b-m;

        while(l > 1 && r > 1) {
            if(r < l) {
                this.multiSwap(array, m-r, m, r);
                b -= r;
                m -= r;
                l -= r;
            }
            else {
                this.multiSwap(array, a, m, l);
                a += l;
                m += l;
                r -= l;
            }
        }

        Highlights.clearMark(2);
        if(r == 1)      this.insertTo(array, m, a);
        else if(l == 1) this.insertToBW(array, a, b-1);
    }

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

    public boolean partialInsertSort(int[] array, int begin, int end, int maxWriteLimit) {
        if (begin == end) return true;

        double sleep = 0.2;

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

    public boolean clearingCocktailShaker(int[] array, int start, int end, int maxRounds) {
		boolean sorted = false;
		int rounds = 0;
		while(!sorted) {
			sorted = true;
			for(int i = start; i < end; i++) {
				if(Reads.compareIndices(array, i, i + 1, 0.075, true) == 1) {
					Writes.swap(array, i, i + 1, 0.15, true, false);
					sorted = false;
				};
			};
			for(int j = end; j > start; j--) {
				if(Reads.compareIndices(array, j - 1, j, 0.075, true) == 1) {
					Writes.swap(array, j, j - 1, 0.15, true, false);
					sorted = false;
				};
			};
			rounds++;
			if (rounds > maxRounds) return false;
		};
		return true;
	};

	public boolean isSectionSorted(int[] array, int start, int end) {
		for(int i = start; i < end; i++) {
			if(Reads.compareIndices(array, i, i+1, 0.5, true) == 1){
				return false;
			};
		};
		return true;
	};

    public void buildRuns(int[] array, int start, int end, int runSize) {
		binaryInserter = new BinaryInsertionSort(this.arrayVisualizer);
		int low = 0;
		int high = 0;
		boolean done = false;
		while(!done) {
			high = low + runSize;
			if (high > end) {
				high = end + 1;
				done = true;
			}
			binaryInserter.customBinaryInsert(array, low, high, 0.5);
			low = high;
		};
	};

    public int countSublists(int[] array, int start, int end) {
		int sublists = 1;
		for(int i = start; i < end; i++) {
			if(Reads.compareIndices(array, i, i + 1, 0.75, true) == 1) {
				sublists++;
			};
		};
		return sublists;
	};

    public boolean tooManySublists(int[] array, int start, int end) {
		int swaps = this.circleSortRoutine(array, start, end+1, 0.3);
		int length = (end - start) + 1;
		if(swaps > length * 0.45 && swaps < length * 1.65) {
			int insertMaxLimit = (int) Math.floor(length * 1.5);
			int shakerMaxLimit = (int) Math.floor(Math.min(Math.max(length / 64, 2), 8));
			boolean good = this.clearingCocktailShaker(array, start, end, shakerMaxLimit);
			if (!good) {
                Highlights.clearAllMarks();
				good = this.partialInsertSort(array, start, end, insertMaxLimit);
			};
			return good;
		};
		return false;
	};

    @Override
    public void runSort(int[] array, int length, int bucketCount) {
        this.timSortInstance = new TimSorting(array, length, this.arrayVisualizer);
        int[] tmp = Writes.createExternalArray(length);
        int end = length - 1;
        this.runCount = this.countSublists(array, 0, end);
        if (this.runCount == 1)  return;
        if (runCount > length / 2.5) {
            Highlights.clearAllMarks();
			if (this.tooManySublists(array, 0, end)) return;
		};
        int reversed = this.findAndFixReversedRuns(array, 0, length);
        Highlights.clearAllMarks();
		//If you reversed once, the array was probably fully reversed and it's probably now sorted, but just in case check.
        this.runCount = this.countSublists(array, 0, end);
		if (this.runCount == 1)  return;
        int potentialRunSize = (int) Math.min(Math.round(length/32), 16);
		if (runCount > length/4) buildRuns(array, 0, end, potentialRunSize);
        TimSorting.sort(this.timSortInstance, array, length);
    }
}
