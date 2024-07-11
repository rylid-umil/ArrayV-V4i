package io.github.arrayv.sorts.hybrid;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.MergeSorting;
import io.github.arrayv.sorts.templates.IterativeCircleSorting
import io.github.arrayv.sorts.insertion.BinaryInsertionSort

// A sort optimized to adapt to every situation. O(1) space complexity and stability not included. WIP

public final class AdaptiveSort extends Sort {
    private MergeSorting merger;
    private IterativeCircleSorting iterativeCircleSorter;
    private BinaryInsertionSort binaryInserter;
    private int sublistCount;
    
    public AdaptiveSort(ArrayVisualizer arrayVisualizer) {
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
    }; //me if the void starts to go public so i have to try and catch the food for the private super final party that extends 
    //  tragedies that import and package hot dogs so i have to switch to a different case and try their visualizing unicorn syrup while i do so so i can assert
    //    my dominance over them else if they dont die i have to run else i must return home to code protected volatile sort:
    // Compares two items, swapping them if they are out of order. Accounts for situations in which left > right.
    public void compareSwap(int[] array, int left, int right, double compSleep, double swapSleep, boolean mark, boolean auxwrite) {
        if (left > right) {
            int tmp = left;
            left = right;
            right = tmp;
        };
        if (mark) {
            Highlights.markArray(1, left);
            Highlights.markArray(2, right);
        }
        Delays.sleep(compSleep);
        if (Reads.compareValues(array[left], array[right]) == 1) {
            Writes.swap(array, left, right, swapSleep, mark, auxwrite);
        };
    }
    //Find all sublists, marking their starts and ends in the provided Auxillary array if writeAux is true.
    public int findSublists(int[] array, boolean writeAux, int[] aux, int start, int end, int checklimit) {
        int count = 0;
        int subSiz = 0;
        if (writeAux) Writes.write(aux, start, 2, 0.1, true, true);
        for(int i = start; i < end; i++) {
            Highlights.markArray(1, i);
            if (i < (length - 1)) Highlights.markArray(2, i + 1);
            Delays.sleep(0.5)
            if(Reads.compareValues(array[i], array[i + 1]) != 1 && i < (length - 1)) {
                subsiz++;
            }
            else {
                count++;
                subSiz = 0;
                if (writeAux) {
                    Writes.write(aux, i, 3, 0.1, true, true);
                    if (i < (length - 1)) Writes.write(aux, i + 1, 2, 1, true, true);
                };
            };
            if (count > checkLimit) return -1;
        };
        return count;
    };
    // Do 1 round of merging sublists, whose starts and ends are marked in the provided Auxillary array.
    public int mergeRound(int[] array, int[] aux, int start, int end, boolean binary) {
        int left = 0;
        int right = 0;
        int mid = 0;
        int state = 0;
        Highlights.clearMark(2);
        for(int i = start; i < end; i++) {
            Highlights.markArray(1, i);
            Delays.sleep(0.5)
                // If you find a start point (2) and are in state 0, set left to I and go to state 1, if in state 2, do the same thing but instead set mid to I. 
                // If not in state 0 or 2, do nothing.
            if(Reads.compareValues(aux[i], 2) == 0) {
                switch(state) {
                    case 0:
                        left = i;
                        state = 1;
                        break;
                    case 2:
                        mid = i;
                        state = 3;
                        break;
                    default:
                };
            } else {
                Delays.sleep(0.5);
                if(Reads.compareValues(array[i], 3) == 0) {
                    switch(state) {
                        case 1:
                            state = 2;
                            break;
                        case 3:
                            right = i;
                            state = 4;
                            break;
                    }
                };
            };
            if(state == 4) {
                Highlights.markArray(1, mid - 1);
                Highlights.markArray(2, mid);
                sublistCount--; // Merging two sublists removes two and adds one, leading to a net loss of 1 sublist as you combine two.
                Delays.sleep(5);
                // If the two sublists consecutive are already sorted, there is no need to merge them
                if(Reads.compareValues(array[mid - 1], array[mid]) == 1) {
                    Writes.write(aux, mid, 1, 4, true, true);
                    Writes.write(aux, mid - 1, 1, 4, true, true);
                    merger.merge(array, aux, left, mid, right, binary); // TODO: use a partial merge instead of a full merge, see PartialMergeSort.java
                };
                state = 0;
                left = 0;
                mid = 0;
                right = 0;
            };
        };
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
    
    // Check if there are more than (N/1.5) + logbase 1.7(n) sublists. If so, the array is probably atleast mostly reversed, so do a circle pass, just in case it isn't mostly reversed.
    // If it's done exactly or more than N*Log(n) swaps in the circle pass, the list was probably reversed, or aslteast mostly reversed and is probably now at least mostly sorted,
    // so do an "optimistic insertion sort". If it does more than n/3 swaps, stop and continue sorting like normal.
    public boolean checkForManySublists(int[] array, int start, int end){
        int maxSubs = (int) Math.floor((n / 1.5) + MathExtra.logBase(n, 1.7));
        int[] empty = [];
        int sublists = this.findSublists(array, false, empty, start, end, maxSubs);
        boolean good = false;
        if (sublists == -1){
            int length = end - start;
            int swaps = iterativeCircleSorter.circleRoutine(array, length, 0.1);
            int nlogn = (int) Math.floor(MathExtra.nlogn(length));
            if (swaps >= nlogn) {
                int maxWrites = (int) Math.floor(length/3);
                good = this.partialInsertionSort(array, start, end, maxWrites);
                return good;
            };
        };
        return good;
    };
    // Do the sort in a range
    public void customAdaptiveSort(int[] array, int[] aux, int start, int end, double limitBase) {
        if (end == start) break;
        if (end - start == 1) {
            this.compareSwap(array, start, end, 0.5, 1, true, false);
        };
        if (end - start < 32) {
            binaryInserter.customBinaryInsert(array, start, end, 0.05);
        };
        int n = end - start;
        int maxSublists = (int) Math.floor((n / 1.5) + MathExtra.logBase(n, limitBase));
        sublistCount = this.findSublists(array, false, aux, start, end, maxSublists); //TODO:find segments that are reversed and reverse them so that they are full runs.
        if (sublistCount = -1) {
            boolean done = this.checkForManySublists(array, start, end);
            if (done) break;
            sublistCount = this.findSublists(array, true, aux, start, end, length * 2); // Length * 2 so that it never activates the limit.
        }
        else if (sublistCount > 1) {
            sublistCount = this.findSublists(array, true, aux, start, end, maxSublists);
            while (sublistCount > 1) {
                this.mergeRound(array, aux, start, end, false);
            }
        };
        if (done) break;
    };
    public void runSort(int[] array, int length, int bucketCount) {
        sublistCount = 0;
        int[] aux = Writes.createExternalArray(length);
        this.customAdaptiveSort(array, aux, 0, length - 1, 2);
    };
};
